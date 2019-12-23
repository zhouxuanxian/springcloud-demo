package com.guanke.vinda.safunc.biz.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guanke.vinda.safunc.biz.GkCompetitorBiz;
import com.guanke.vinda.safunc.config.ParamsConfig;
import com.guanke.vinda.safunc.mapper.GkCompetitorMapper;
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
public class GkCompetitorBizImpl implements GkCompetitorBiz {
    private static final Logger LOGGER = LoggerFactory.getLogger(GkCompetitorBizImpl.class);

    private final GkCompetitorMapper gkCompetitorMapper;
    private final ParamsConfig paramsConfig;

    public GkCompetitorBizImpl(GkCompetitorMapper gkCompetitorMapper,
                               ParamsConfig paramsConfig) {
        this.gkCompetitorMapper = gkCompetitorMapper;
        this.paramsConfig = paramsConfig;
    }


    /**
     * 根据条件分页查询竞品
     *
     * @param accountId  门店Id
     * @param empId      员工Id
     * @param positionId 职位Id
     * @param pageNum    页码
     * @param pageSize   每页数量
     * @return 返回指定条件下的订单/库存
     */
    @Override
    public Page<Map<String, Object>> getGkCompetitorByCondition(String accountId,
                                                                String empId,
                                                                String positionId,
                                                                int pageNum,
                                                                int pageSize) {
        String photoPrefix = paramsConfig.getPhotoPrefix();
        Page<Map<String, Object>> result = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> {
            gkCompetitorMapper.selectCompetitorByCondition(accountId, empId, positionId, photoPrefix);
        });
        return result;
    }
}