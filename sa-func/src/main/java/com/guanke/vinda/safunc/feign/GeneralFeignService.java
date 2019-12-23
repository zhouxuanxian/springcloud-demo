package com.guanke.vinda.safunc.feign;

import com.guanke.vinda.samodels.model.response.ObjectGeneralResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 微信模块FEIGN
 *
 * @author Nicemorning
 */
@FeignClient(value = "vinda-gate")
@RequestMapping("api")
public interface GeneralFeignService {
    /**
     * 通过CODE获取用户的基本个人信息
     *
     * @param code 用户的微信临时CODE
     * @return 返回响应实体类
     */
    @GetMapping("sawx/user/getUserInfoByCode")
    ObjectGeneralResponseEntity getUserInfoByCode(@RequestParam("code") String code);

    /**
     * 通过用户的企业微信ID获取用户的基本个人信息
     *
     * @param userId 用户的企业微信ID
     * @return 返回响应实体类
     */
    @GetMapping("sawx/user/getUserInfoByUserId")
    ObjectGeneralResponseEntity getUserInfoByUserId(@RequestParam("userId") String userId);

    /**
     * 通过媒体ID获取微信临时服务器中的文件并上传至七牛云
     *
     * @param serverId 媒体ID
     * @return 返回文件在七牛云中的KEY列表
     */
    @GetMapping("sawx/image/upLoadToQiNiuYun")
    ObjectGeneralResponseEntity upLoadToQiNiuYun(@RequestParam("serverIds") String serverId);

    /**
     * 通过媒体ID获取微信临时服务器中的文件并重命名后上传至七牛云
     *
     * @param serverId 媒体ID
     * @param fileName 指定文件名
     * @return 返回文件在七牛云中的KEY列表
     */
    @GetMapping("sawx/image/upLoadToQiNiuYunRename")
    ObjectGeneralResponseEntity upLoadToQiNiuYun(@RequestParam("serverIds") String serverId,
                                                 @RequestParam("fileName") String fileName);

    /**
     * 通用CRM请求
     *
     * @param params 请求参数
     * @return 返回响应结果
     */
    @PostMapping("sasync/wsController/request")
    ObjectGeneralResponseEntity generalRequest(@RequestBody Map<String, Object> params);

    /**
     * 新建门店
     *
     * @param params 门店信息参数
     * @return 返回响应结果
     */
    @PostMapping("sasync/shopInfoManage/saveShopInfo")
    ObjectGeneralResponseEntity saveShopInfo(@RequestBody Map<String, Object> params);

    /**
     * 更新门店信息
     *
     * @param params 门店信息参数
     * @return 返回响应结果
     */
    @PostMapping("sasync/shopInfoManage/updateShopInfo")
    ObjectGeneralResponseEntity updateShopInfo(@RequestBody Map<String, Object> params);

}