package com.guanke.vinda.sasync.biz;

import java.util.Map;

/**
 * 门店信息修改或新增业务类
 *
 * @author Nicemorning
 */
public interface ShopUpdateOrSaveBiz {

    /**
     * 门店更新
     *
     * @param params 门店信息
     * @return 如果更新成功返回true，反之返回false
     */
    boolean updateShopInfo(Map<String, Object> params);


    /**
     * 新增门店
     *
     * @param params 门店信息
     * @return 如果新增门店成功返回true，反之返回false
     */
    boolean saveShopInfo(Map<String, Object> params);

    /**
     * 通用WEB SERVICE请求接口
     *
     * @param params 请求报文
     * @return 返回响应报文的MSG
     */
    String generaWebServiceRequest(Map<String, Object> params);
}
