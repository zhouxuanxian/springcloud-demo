package com.guanke.vinda.sasync.biz.impl;

import com.guanke.vinda.sasync.biz.ShopUpdateOrSaveBiz;
import com.guanke.vinda.sasync.mapper.GkAccountEntityMapper;
import com.guanke.vinda.sasync.siebel.core.WebServiceRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 门店信息修改或新增业务类
 *
 * @author Nicemorning
 */
@Service
public class ShopUpdateOrSaveBizImpl implements ShopUpdateOrSaveBiz {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopUpdateOrSaveBizImpl.class);

    private final WebServiceRequest webServiceRequest;

    private final GkAccountEntityMapper accountEntityMapper;

    public ShopUpdateOrSaveBizImpl(WebServiceRequest webServiceRequest,
                                   GkAccountEntityMapper accountEntityMapper) {
        this.webServiceRequest = webServiceRequest;
        this.accountEntityMapper = accountEntityMapper;
    }

    /**
     * 门店更新
     *
     * @param params 门店信息
     * @return 如果更新成功返回true，反之返回false
     */
    @Override
    public boolean updateShopInfo(Map<String, Object> params) {
        boolean flag = false;
        String action = "\"document/http://siebel.com/CustomUI:VDWeChatDealerIntegrationWSInvokeMethod\"";
        String inName = "VDWeChatDealerIntegrationWSInvokeMethod_Input";
        String url = "http://crm-test.vinda.com:88/eai_chs/start.swe?SWEExtSource=WebService&SWEExtCmd=Execute&WSSOAP=1";
        params.put("objectname", "UpdateBaseShop");
        String result = webServiceRequest.sendRequestToCrm(action, inName, url, params);
        if (this.isNotNullOrEmptyString(result)) {
            LOGGER.info("Request to crm failed");
            flag = true;
        }
        return flag;
    }

    /**
     * 新增门店
     *
     * @param params 门店信息
     * @return 如果新增门店成功返回true，反之返回false
     */
    @Override
    public boolean saveShopInfo(Map<String, Object> params) {
        boolean flag = false;
        if (isNameExist(String.valueOf(params.get("attr02")),
                String.valueOf(params.get("attr01")))) {
            LOGGER.info("This shop name is already exist.");
            return flag;
        }
        String action = "\"document/http://siebel.com/CustomUI:VDWeChatDealerIntegrationWSInvokeMethod\"";
        String inName = "VDWeChatDealerIntegrationWSInvokeMethod_Input";
        String url = "http://crm-test.vinda.com:88/eai_chs/start.swe?SWEExtSource=WebService&SWEExtCmd=Execute&WSSOAP=1";
        params.put("objectname", "ShopAccount");
        String result = webServiceRequest.sendRequestToCrm(action, inName, url, params);
        if (this.isNotNullOrEmptyString(result)) {
            LOGGER.info("Request to crm failed");
            flag = true;
        }
        return flag;
    }

    private boolean isNameExist(String name, String id) {
        return accountEntityMapper.selectCountByAccountNameAndAccountId(name, id) > 0;
    }

    /**
     * 通用WEB SERVICE请求接口
     *
     * @param params 请求报文
     * @return 返回响应报文的MSG
     */
    @Override
    public String generaWebServiceRequest(Map<String, Object> params) {
        String action = String.valueOf(params.get("action"));
        String inName = String.valueOf(params.get("inName"));
        String url = "http://crm-test.vinda.com:88/eai_chs/start.swe?SWEExtSource=WebService&SWEExtCmd=Execute&WSSOAP=1";
        String result = webServiceRequest.sendRequestToCrm(action, inName, url, params);
        if (this.isNotNullOrEmptyString(result)) {
            LOGGER.info("Request to crm failed");
        }
        return result;
    }

    /**
     * 判断一个字符串是否为空或者null
     *
     * @param string 要判断的字符串
     * @return 如果是null或空字符串或"null"或者仅包含空格，均返回false，反之返回true
     */
    private boolean isNotNullOrEmptyString(String string) {
        String nullString = "null";
        return !StringUtils.isEmpty(string) && !nullString.equals(string);
    }
}
