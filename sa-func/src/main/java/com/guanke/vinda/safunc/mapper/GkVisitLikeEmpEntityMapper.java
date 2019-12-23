package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkVisitLikeEmpEntity;

public interface GkVisitLikeEmpEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkVisitLikeEmpEntity record);

    int insertSelective(GkVisitLikeEmpEntity record);

    GkVisitLikeEmpEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkVisitLikeEmpEntity record);

    int updateByPrimaryKey(GkVisitLikeEmpEntity record);
}