package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkAccountSalesReportEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GkAccountSalesReportEntityMapper {
    /**
     * 根据主键删除数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    int insert(GkAccountSalesReportEntity record);

    int insertOrUpdate(GkAccountSalesReportEntity record);

    int insertOrUpdateSelective(GkAccountSalesReportEntity record);

    int insertSelective(GkAccountSalesReportEntity record);

    GkAccountSalesReportEntity selectByPrimaryKey(String id);
    Map<String, Object> selectByIdAndTime(@Param("shopId") String shopId,@Param("date") String date);

    int updateByPrimaryKeySelective(GkAccountSalesReportEntity record);

    int updateByPrimaryKey(GkAccountSalesReportEntity record);

    int updateBatch(List<GkAccountSalesReportEntity> list);

    int batchInsert(@Param("list") List<GkAccountSalesReportEntity> list);

    List<Map<String, Object>> selectSaleReportListsByPosId (@Param("date") String date, @Param("status") String status,@Param("keyword")  String keyword,
                                                            @Param("positionId") String positionId, @Param("empId") String empId, @Param("parentId") String parentId);

    List<Map<String, Object>> selectSaleReportListsByOrgId (@Param("date") String date, @Param("status") String status,@Param("keyword")  String keyword,
                                                            @Param("orgId") String orgId, @Param("empId") String empId, @Param("parentId") String parentId);

    List<Map<String, Object>> getAccountSalesReportByShopId (@Param("shopId") String shopId,  @Param("empId") String empId, @Param("positionId") String positionId,@Param("salesReportId") String salesReportId );

    List<Map<String, Object>> getCrmSalesReport (@Param("intId") String intId, @Param("date") String date);
    List<Map<String, Object>> getBrandSalesReport ();
    List<Map<String, Object>> getSalesReportCount (@Param("shopIntId") String shopIntId,@Param("startDate") String startDate,@Param("endDate") String endDate);

    List<Map<String, Object>> getSalesReportCountDetail (@Param("shopIntId") String shopIntId,@Param("date") String date,@Param("lastDate") String lastDate);

    Map<String,Object> getWxType(@Param("positionId") String positionId, @Param("intId") String intId);
    Map<String,Object> selectVindaBrandShow(@Param("positionId") String positionId);
    List<Map<String, Object>> getShopName(@Param("shopIntId") String shopIntId);

    int insertSaleReport(@Param("date") String date);


}