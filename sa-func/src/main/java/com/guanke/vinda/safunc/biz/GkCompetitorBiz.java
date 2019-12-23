package com.guanke.vinda.safunc.biz;

import com.github.pagehelper.Page;

import java.util.Map;

/**
 * 竞品相关业务接口
 */
public interface GkCompetitorBiz {
    /**
     * 根据条件分页查询竞品
     *
     * @param accountId  门店Id
     * @param empId      员工Id
     * @param positionId 职位Id
     * @param pageNum    页码
     * @param pageSize   每页数量
     * @return 返回指定条件下的竞品
     */
    Page<Map<String, Object>> getGkCompetitorByCondition(String accountId,
                                                         String empId,
                                                         String positionId,
                                                         int pageNum,
                                                         int pageSize);
}
