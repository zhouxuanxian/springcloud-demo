package com.guanke.vinda.safunc.biz;

import com.github.pagehelper.Page;
import com.guanke.vinda.samodels.model.entity.CxGtReimEntity;
import com.guanke.vinda.samodels.model.entity.GkDailyEntity;
import com.guanke.vinda.samodels.model.pojo.daily.DailySubmitPojo;

import java.util.List;
import java.util.Map;

/**
 * GT费用相关业务逻辑
 *
 * @author ldm
 */
public interface CostCancelBiz {
    /**
     * 通过职位ID，员工ID和筛选条件获取该员工名下的所有GT費用核销，返回分页结果
     * @param keyWord 关键字
     * @param positionId 职位ID
     * @param empId      员工ID
     * @param pageNum    页码
     * @param pageSize   页容量
     * @return 符合条件的所有GT費用核销的分页结果
     */
    Page<Map<String, Object>> getNoDoneCostLists(
            String keyWord, String positionId, String empId, int pageNum, int pageSize);

    /**
     * 通过职位查询 更多 GT数据，按条件筛选
     *
     * @param approvalStatus 审批状态
     * @param reimStatus 检核状态
     * @param keyWord 关键字
     * @param positionId 职位id
     * @param empId 人员id
     * @param pageNum 页码
     * @param pageSize 页数
     * @return 对象
     */
    Page<Map<String, Object>> getMoreCostLists(String approvalStatus, String reimStatus, String keyWord,
                                               String positionId, String empId, int pageNum, int pageSize);

    /**
     * 获取GT费用详情
     *
     * @param positionId 职位id
     * @param empId      人员id
     * @param reimId     GT费用核销id
     * @return 返回该核销的详情
     */
    Map<String, Object> getCostDetailsBiz(String positionId, String empId, String reimId);

    /**
     * 获取GT临时表数据 用于回显
     *
     * @param positionId 职位id
     * @param empId      人员id
     * @param reimId     核销id
     * @return 返回核销详情信息
     */
    Map<String, Object> getCostTempDatas(String positionId, String empId, String reimId);

    /**
     * GT核销费用照片上传 到七牛云
     *
     * @param serverId  微信本地路径
     * @param storeName 店铺名称
     * @return 返回图片key
     */
    public Map<String, String> costUpPhoto(String serverId, String storeName);
}
