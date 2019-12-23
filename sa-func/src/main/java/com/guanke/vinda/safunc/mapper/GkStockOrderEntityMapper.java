package com.guanke.vinda.safunc.mapper;
import java.util.Date;

import com.guanke.vinda.samodels.model.entity.GkStockOrderEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GkStockOrderEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkStockOrderEntity record);

    int insertSelective(GkStockOrderEntity record);

    GkStockOrderEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkStockOrderEntity record);

    int updateByPrimaryKey(GkStockOrderEntity record);

    int selectCountByVisitIdAndAccountIdAndToday(@Param("visitId") String visitId,
                                                 @Param("accountId") String accountId,
                                                 @Param("todayStr") String todayStr);

    List<Map<String, Object>> selectVCodeInfoByAccountIdAndVisitIdAndToday(@Param("accountId") String accountId,
                                                                           @Param("visitId") String visitId,
                                                                           @Param("todayStr") String todayStr);

    List<Map<String, Object>> selectExistVCodeInfoByAccountIdAndVisitIdAndToday(@Param("accountId") String accountId,
                                                                                @Param("visitId") String visitId,
                                                                                @Param("todayStr") String todayStr);

    List<Map<String, Object>> getGkStockOrderByCondition(@Param("accountId") String accountId,
                                                         @Param("empId") String empId,
                                                         @Param("positionId") String positionId,
                                                         @Param("visitId") String visitId);

    GkStockOrderEntity selectOneByVisitIdAndAccountIdAndWriteTime(@Param("visitId")String visitId,@Param("accountId")String accountId,@Param("writeTime")Date writeTime);

    int deleteByVisitIdAndAccountIdAndEmpId(@Param("visitId")String visitId,@Param("accountId")String accountId,@Param("empId")String empId);


}