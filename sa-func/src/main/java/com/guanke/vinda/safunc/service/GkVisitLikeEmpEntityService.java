package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkVisitLikeEmpEntity;
public interface GkVisitLikeEmpEntityService{


    int deleteByPrimaryKey(String id);

    int insert(GkVisitLikeEmpEntity record);

    int insertSelective(GkVisitLikeEmpEntity record);

    GkVisitLikeEmpEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkVisitLikeEmpEntity record);

    int updateByPrimaryKey(GkVisitLikeEmpEntity record);

}
