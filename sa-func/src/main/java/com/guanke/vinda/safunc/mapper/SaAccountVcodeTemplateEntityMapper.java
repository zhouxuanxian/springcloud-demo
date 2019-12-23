package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.SaAccountVcodeTemplateEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaAccountVcodeTemplateEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SaAccountVcodeTemplateEntity record);

    int insertOrUpdate(SaAccountVcodeTemplateEntity record);

    int insertOrUpdateSelective(SaAccountVcodeTemplateEntity record);

    int insertSelective(SaAccountVcodeTemplateEntity record);

    SaAccountVcodeTemplateEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaAccountVcodeTemplateEntity record);

    int updateByPrimaryKey(SaAccountVcodeTemplateEntity record);

    int updateBatch(List<SaAccountVcodeTemplateEntity> list);

    int batchInsert(@Param("list") List<SaAccountVcodeTemplateEntity> list);

    int deleteByAccountId(@Param("accountId")String accountId);

	int updateDeleteFlagByAccountId(@Param("updatedDeleteFlag")Integer updatedDeleteFlag,@Param("accountId")String accountId);


}