package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.guanke.vinda.safunc.mapper.GkMissionShopEntityMapper;
import com.guanke.vinda.samodels.model.entity.GkMissionShopEntity;
import com.guanke.vinda.safunc.service.GkMissionShopEntityService;
@Service
public class GkMissionShopEntityServiceImpl implements GkMissionShopEntityService{

    @Resource
    private GkMissionShopEntityMapper gkMissionShopEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkMissionShopEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkMissionShopEntity record) {
        return gkMissionShopEntityMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(GkMissionShopEntity record) {
        return gkMissionShopEntityMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(GkMissionShopEntity record) {
        return gkMissionShopEntityMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(GkMissionShopEntity record) {
        return gkMissionShopEntityMapper.insertSelective(record);
    }

    @Override
    public GkMissionShopEntity selectByPrimaryKey(String id) {
        return gkMissionShopEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkMissionShopEntity record) {
        return gkMissionShopEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkMissionShopEntity record) {
        return gkMissionShopEntityMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<GkMissionShopEntity> list) {
        return gkMissionShopEntityMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<GkMissionShopEntity> list) {
        return gkMissionShopEntityMapper.batchInsert(list);
    }

}
