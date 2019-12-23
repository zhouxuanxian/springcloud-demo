package com.guanke.vinda.safunc.biz.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guanke.vinda.safunc.biz.GkStockOrderBiz;
import com.guanke.vinda.safunc.mapper.GkStockOrderEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 门店拜访相关业务实现类
 *
 * @author Nicemorning
 */
@Service
public class GkStockOrderBizImpl implements GkStockOrderBiz {
    private static final Logger LOGGER = LoggerFactory.getLogger(GkStockOrderBizImpl.class);

    private final GkStockOrderEntityMapper gkStockOrderEntityMapper;

    public GkStockOrderBizImpl(GkStockOrderEntityMapper gkStockOrderEntityMapper) {
        this.gkStockOrderEntityMapper = gkStockOrderEntityMapper;
    }


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
    @Override
    public Page<Map<String, Object>> getGkStockByCondition(String accountId,
                                                           String empId,
                                                           String positionId,
                                                           String visitId,
                                                           int pageNum,
                                                           int pageSize) {
        Page<Map<String, Object>> result = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> {
            gkStockOrderEntityMapper.getGkStockOrderByCondition(accountId, empId, positionId, visitId);
        });
        return result;
    }
}