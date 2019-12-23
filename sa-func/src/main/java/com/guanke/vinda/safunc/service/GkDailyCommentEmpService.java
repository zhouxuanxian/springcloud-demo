package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkDailyCommentEmpEntity;
public interface GkDailyCommentEmpService{

    int deleteByPrimaryKey(String commentAccountId,String dailyReportId);

    int insert(GkDailyCommentEmpEntity record);

    int insertSelective(GkDailyCommentEmpEntity record);

    GkDailyCommentEmpEntity selectByPrimaryKey(String commentAccountId, String dailyReportId);

    int updateByPrimaryKeySelective(GkDailyCommentEmpEntity record);

    int updateByPrimaryKey(GkDailyCommentEmpEntity record);

}
