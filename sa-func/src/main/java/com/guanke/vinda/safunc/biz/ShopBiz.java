package com.guanke.vinda.safunc.biz;

import com.github.pagehelper.Page;
import com.guanke.vinda.samodels.model.pojo.shop.ShopChangePojo;
import com.guanke.vinda.samodels.model.pojo.shop.ShopManageListPojo;
import com.guanke.vinda.samodels.model.pojo.shop.create.NewShopSubmitPojo;
import com.guanke.vinda.samodels.model.pojo.shop.edit.ShopChangeSubmitPojo;

import java.util.List;
import java.util.Map;

/**
 * 门店相关业务逻辑接口
 *
 * @author Nicemorning
 */
public interface ShopBiz {
    /**
     * 通过当前用户的职位ID，组织ID，微信角色和筛选条件筛选所有的门店列表
     *
     * @param page       页码
     * @param pageSize   页容量
     * @param id         职位ID或组织ID
     * @param isPosition 如果是通过职位，则为true，反之则为false以组织查找
     * @param params     筛选条件
     * @return 返回符合条件的但无拜访任务的门店列表
     * @see com.guanke.vinda.safunc.biz.ShopBiz
     */
    Page<ShopManageListPojo> selectAccountByCondition(
            int page, int pageSize, String id, boolean isPosition, Map<String, Object> params);

    /**
     * 通过门店的ID获取门店的详情
     *
     * @param shopId 门店ID
     * @return 返回该门店的详情信息
     */
    Map<String, Object> getShopDetailByShopId(String shopId);

    /**
     * 通过门店ID获取门店基础信息
     *
     * @param accountId 门店ID
     * @return 返回该门店基础信息
     */
    Map<String, Object> getShopByShopId(String accountId);

    /**
     * 通过门店ID获取门店的变更历史
     *
     * @param page     页码
     * @param pageSize 页容量
     * @param id       门店ID
     * @return 返回该门店的变更历史
     */
    Page<ShopChangePojo> getShopChangeHistoryById(int page, int pageSize, String id);

    /**
     * 通过门店ID获取门店变更历史时间，仅用于门店详情页展示
     *
     * @param id 门店ID
     * @return 返回该门店的变更历史时间
     */
    Map<String, String> getShopChangeTimeById(String id);

    /**
     * 根据职位ID获取于其相同职位的人，用于客户团队选择
     *
     * @param page     页码
     * @param pageSize 页容量
     * @param id       要查找的职位ID
     * @param name     要查找的人员姓名
     * @return 返回与该职位ID相同的其他人员
     */
    Page<Map<String, String>> getEmpListByPositionId(int page, int pageSize, String id, String name);

    /**
     * 通过用户的职位ID，获取该用户名下所有门店的行政区域列表
     *
     * @param positionId 职位ID
     * @return 返回该职位ID下所有的门店行政区域列表
     */
    List<String> getAllShopDistrictByPositionId(String positionId);

    /**
     * 通过职位ID获取该职位下所属门店可选的经销商列表
     *
     * @param positionId 职位ID
     * @param keyWord    搜索关键字
     * @param page       页码
     * @param pageSize   页容量
     * @return 该职位下所有门店的可选经销商列表
     */
    Page<Map<String, String>> getAllDealerListByPositionId(String positionId, String keyWord, int page, int pageSize);

    /**
     * 新建门店
     *
     * @param phone      用户手机号
     * @param postnId    职位ROW-ID
     * @param orgId      组织ID
     * @param submitPojo 门店参数
     * @return 如果新建成功则返回"success"，反之返回相应错误信息
     */
    String newShop(String phone, String postnId, String orgId, NewShopSubmitPojo submitPojo);

    /**
     * 修改门店信息
     *
     * @param submitPojo 门店参数
     * @return 如果修改成功则返回"success"，反之返回相应错误信息
     */
    String editShop(ShopChangeSubmitPojo submitPojo);
}
