package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkAccountEntity;
import com.guanke.vinda.samodels.model.pojo.shop.ShopChangePojo;
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

    List<ShopChangePojo> selectShopChangeHistoryByShopId(@Param("id") String id);

    String selectShopChangeTimeByShopId(@Param("id") String id);

    List<Map<String, String>> selectEmpListByPositionId(@Param("positionId") String positionId,
                                                        @Param("name") String name);

    List<String> selectAllShopDistrictByPositionId(@Param("positionId") String positionId);

    List<Map<String, String>> selectAllDealerListByPositionId(@Param("positionId") String positionId,
                                                              @Param("keyword") String keyword);

    List<Map<String, Object>> selectAllVisitShopListByPositionIdAndQuery(@Param("positionId") String positionId,
                                                                         @Param("keyword")String keyword,
                                                                         @Param("district") String district);

    Map<String, Object> selectShopDetailByShopId(@Param("shopId") String shopId);

    Map<String, Object> selectLastVisitByShopId(@Param("shopId") String shopId);

    Map<String, Object> selectLastStockByShopId(@Param("shopId") String shopId);

    Map<String, Object> selectLastCompetitorByShopId(@Param("shopId") String shopId);

    Map<String, Object> selectCountVcodeByShopId(@Param("shopId") String shopId);

    Map<String, Object> selectLastStockDateByShopId(@Param("shopId") String shopId);

    Map<String, Object> selectLastAccountChangeByShopId(@Param("shopId") String shopId);

    GkAccountEntity selectFirstByIntid(@Param("intid")String intid);

    /**
     * 通过以下参数筛选符合条件的门店列表，该方法仅供微信角色为SALESMAN或SALESMANAGER使用
     *
     * @param positionId 职位ID
     * @param keyWord    搜索关键字
     * @param area       区域
     * @param level      等级
     * @param feature    特性
     * @param city       用户所属城市组织
     * @param today      当前日期
     * @return
     */
    List<ShopManageListPojo> selectAccountByCondition(
            @Param("positionId") String positionId,
            @Param("keyWord") String keyWord,
            @Param("area") String area,
            @Param("level") String level,
            @Param("feature") String feature,
            @Param("city") String city,
            @Param("today") String today
    );
}