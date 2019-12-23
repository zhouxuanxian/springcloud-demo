package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.safunc.mapper.GkVisitLikeEmpEntityMapper;
import com.guanke.vinda.samodels.model.entity.GkVisitLikeEmpEntity;
import com.guanke.vinda.safunc.service.GkVisitLikeEmpEntityService;
@Service
public class GkVisitLikeEmpEntityServiceImpl implements GkVisitLikeEmpEntityService{

    @Resource
    private GkVisitLikeEmpEntityMapper gkVisitLikeEmpEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkVisitLikeEmpEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkVisitLikeEmpEntity record) {
        return gkVisitLikeEmpEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(GkVisitLikeEmpEntity record) {
        return gkVisitLikeEmpEntityMapper.insertSelective(record);
    }

    @Override
    public GkVisitLikeEmpEntity selectByPrimaryKey(String id) {
        return gkVisitLikeEmpEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkVisitLikeEmpEntity record) {
        return gkVisitLikeEmpEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkVisitLikeEmpEntity record) {
        return gkVisitLikeEmpEntityMapper.updateByPrimaryKey(record);
    }

}
