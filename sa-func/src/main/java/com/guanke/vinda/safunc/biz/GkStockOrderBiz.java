package com.guanke.vinda.safunc.biz;

import com.github.pagehelper.Page;

import java.util.Map;

/**
 * 订单、库存相关业务接口
 */
public interface GkStockOrderBiz {
    /**
     * 根据条件分页查询订单/库存
     *
     * @param accountId  门店Id
     * @param empId      员工Id
     * @param positionId 职位Id
     * @param visitId    拜访Id
     * @param pageNum    页码
     * @param pageSize   每页数量
     * @return 返回指定条件下的订单/库存
     */
    Page<Map<String, Object>> getGkStockByCondition(String accountId,
                                                    String empId,
                                                    String positionId,
                                                    String visitId,
                                                    int pageNum,
                                                    int pageSize);
}
