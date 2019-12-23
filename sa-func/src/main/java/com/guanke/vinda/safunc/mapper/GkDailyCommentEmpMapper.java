package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkDailyCommentEmpEntity;
import org.apache.ibatis.annotations.Param;

public interface GkDailyCommentEmpMapper {
    int deleteByPrimaryKey(@Param("commentAccountId") String commentAccountId, @Param("dailyReportId") String dailyReportId);

    int insert(GkDailyCommentEmpEntity record);

    int insertSelective(GkDailyCommentEmpEntity record);

    GkDailyCommentEmpEntity selectByPrimaryKey(@Param("commentAccountId") String commentAccountId, @Param("dailyReportId") String dailyReportId);

    int updateByPrimaryKeySelective(GkDailyCommentEmpEntity record);

    int updateByPrimaryKey(GkDailyCommentEmpEntity record);
}