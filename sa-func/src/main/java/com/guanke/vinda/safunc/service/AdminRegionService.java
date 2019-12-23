package com.guanke.vinda.safunc.service;

import java.util.List;

/**
 * @author Nicemorning
 */
public interface AdminRegionService {
    /**
     * 根据条件获取行政区域列表
     *
     * @param type  要查询的区划等级
     * @param query 查询条件
     * @return 返回该区划等级的所有名称列表
     */
    List<String> getAdministrativeRegionList(Integer type, String query);
}
