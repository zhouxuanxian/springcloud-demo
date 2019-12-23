package com.guanke.vinda.safunc.mapper;

import java.util.List;

/**
 * 行政区域相关
 *
 * @author Nicemorning
 */
public interface SaRealAddressMapper {
    /**
     * 获取省
     *
     * @return 返回国内所有省
     */
    List<String> getProvincesList();

    /**
     * 获取指定省下的市
     *
     * @param province 要查询的省
     * @return 返回该省的所有市
     */
    List<String> getCitiesList(String province);


    /**
     * 获取指定城市下的区/县
     *
     * @param city 要查询的市
     * @return 返回该市的所有区/县
     */
    List<String> getCountiesList(String city);
}
