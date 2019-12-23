package com.guanke.vinda.safunc.biz;

import com.github.pagehelper.Page;

import java.util.Map;

/**
 * 门店V码相关业务接口
 */
public interface GkAccountVcodeBiz {
    /**
     * 根据条件分页查询门店V码
     *
     * @param accountId 门店Id
     * @param pageNum   页码
     * @param pageSize  每页数量
     * @return 返回指定条件下的竞品
     */
    Page<Map<String, Object>> getGkAccountVcodeByCondition(String accountId,
                                                           int pageNum,
                                                           int pageSize);
}
