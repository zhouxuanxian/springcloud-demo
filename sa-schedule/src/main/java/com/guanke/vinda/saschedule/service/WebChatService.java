package com.guanke.vinda.saschedule.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guanke.vinda.samodels.model.entity.WxCpMessageSendResultEntity;
import com.guanke.vinda.saschedule.config.ParamsConfig;
import com.guanke.vinda.saschedule.pojo.BaseMessage;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;



@Service
public class WebChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebChatService.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ParamsConfig paramsConfig;

    /**
     * 发送微信消息
     * @param message
     * @return
     * @throws Exception
     */
    public WxCpMessageSendResultEntity sendMessage(BaseMessage message) throws Exception{
        String responseBody="";
        WxCpMessageSendResultEntity wxCpMessageSendResult=null;
        //获取accessToken
        try {
            String accessToken = String.valueOf(redisTemplate.opsForValue().get("accessToken"));
            if (accessToken == null || "null".equals(accessToken)) {
                LOGGER.info("Access token is expired, refresh token now.");
                accessToken = this.storeAccessToken();
            }
        String json=JSON.toJSONString(message);

        //申明给服务端传递一个json串
        //创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        //创建一个请求对象
        String params ="?access_token="+accessToken;
         String url= "https://qyapi.weixin.qq.com/cgi-bin/message/send";

        Request request = (new Request.Builder())
                .url(url+params)
                .post(requestBody)
                .build();

        //发送请求获取响应

            Response response=okHttpClient.newCall(request).execute();
            //判断请求是否成功
            if(response.isSuccessful()){
                //打印服务端返回结果
               responseBody = Objects.requireNonNull(response.body()).string();
                wxCpMessageSendResult=JSON.parseObject(responseBody,WxCpMessageSendResultEntity.class);
                if(wxCpMessageSendResult.getErrCode()!=0){
                    throw new Exception(wxCpMessageSendResult.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;

        }

        return wxCpMessageSendResult;
    }

    /**
     * 重新获取AccessToken
     * @return
     */

    public String storeAccessToken() {
        String accessToken = null;
        OkHttpClient client = new OkHttpClient();
        String params = "?corpid=" +
                paramsConfig.getCorpId() +
                "&corpsecret=" +
                paramsConfig.getSecret();
        String url="https://qyapi.weixin.qq.com/cgi-bin/gettoken" + params;
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                LOGGER.info("Request weChat for access token is failed: \n" + response.body());
            }
            String responseBody = Objects.requireNonNull(response.body()).string();
            JSONObject jsonObject = JSONObject.parseObject(responseBody);
            accessToken = String.valueOf(jsonObject.get("access_token"));
            if (accessToken != null && !("null".equals(accessToken))) {
                redisTemplate.opsForValue().set("accessToken", accessToken, Long.parseLong(String.valueOf(jsonObject.get("expires_in"))), TimeUnit.SECONDS);

            }
        } catch (IOException e) {
            LOGGER.info("Request weChat for access token throw an exception: " + e.getMessage());
        }
        return accessToken;
    }


}
