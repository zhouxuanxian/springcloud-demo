package com.guanke.vinda.safunc.service;

import java.util.List;
import com.guanke.vinda.samodels.model.entity.SaAccountVcodeTemplateEntity;
public interface SaAccountVcodeTemplateEntityService{


    int deleteByPrimaryKey(String id);

    int insert(SaAccountVcodeTemplateEntity record);

    int insertOrUpdate(SaAccountVcodeTemplateEntity record);

    int insertOrUpdateSelective(SaAccountVcodeTemplateEntity record);

    int insertSelective(SaAccountVcodeTemplateEntity record);

    SaAccountVcodeTemplateEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaAccountVcodeTemplateEntity record);

    int updateByPrimaryKey(SaAccountVcodeTemplateEntity record);

    int updateBatch(List<SaAccountVcodeTemplateEntity> list);

    int batchInsert(List<SaAccountVcodeTemplateEntity> list);

}
