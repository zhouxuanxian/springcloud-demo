package com.guanke.vinda.sasync.mapper;

import com.guanke.vinda.samodels.model.entity.GkAccountEntity;
import com.guanke.vinda.samodels.model.pojo.shop.ShopChangePojo;
import com.guanke.vinda.samodels.model.pojo.shop.ShopDetailPojo;
import com.guanke.vinda.samodels.model.pojo.shop.ShopManageListPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GkAccountEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkAccountEntity record);

    int insertSelective(GkAccountEntity record);

    GkAccountEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkAccountEntity record);

    int updateByPrimaryKey(GkAccountEntity record);

    int selectCountByAccountNameAndAccountId(@Param("name") String name,
                                             @Param("id") String id);
}