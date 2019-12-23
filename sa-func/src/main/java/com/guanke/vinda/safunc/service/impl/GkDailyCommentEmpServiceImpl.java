package com.guanke.vinda.safunc.service.impl;

import com.guanke.vinda.safunc.mapper.GkDailyCommentEmpMapper;
import com.guanke.vinda.safunc.service.GkDailyCommentEmpService;
import com.guanke.vinda.samodels.model.entity.GkDailyCommentEmpEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class GkDailyCommentEmpServiceImpl implements GkDailyCommentEmpService {

    @Resource
    private GkDailyCommentEmpMapper gkDailyCommentEmpMapper;

    @Override
    public int deleteByPrimaryKey(String commentAccountId,String dailyReportId) {
        return gkDailyCommentEmpMapper.deleteByPrimaryKey(commentAccountId,dailyReportId);
    }

    @Override
    public int insert(GkDailyCommentEmpEntity record) {
        return gkDailyCommentEmpMapper.insert(record);
    }

    @Override
    public int insertSelective(GkDailyCommentEmpEntity record) {
        return gkDailyCommentEmpMapper.insertSelective(record);
    }

    @Override
    public GkDailyCommentEmpEntity selectByPrimaryKey(String commentAccountId, String dailyReportId) {
        return gkDailyCommentEmpMapper.selectByPrimaryKey(commentAccountId,dailyReportId);
    }

    @Override
    public int updateByPrimaryKeySelective(GkDailyCommentEmpEntity record) {
        return gkDailyCommentEmpMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkDailyCommentEmpEntity record) {
        return gkDailyCommentEmpMapper.updateByPrimaryKey(record);
    }

}
