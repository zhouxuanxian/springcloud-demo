package com.guanke.vinda.sasync.biz;

import com.guanke.vinda.samodels.model.entity.CxGtReimEntity;

import java.util.Map;

/**
 * 门店信息修改或新增业务类
 *
 * @author Nicemorning
 */
public interface GtSubmitOrApproveBiz {
    /**
     * Gt费用 临时保存在数据库
     *
     * @param empId      人员id
     * @param positionId 职位id
     * @param GtCost     报错的数据对象
     * @param saveFlag   是否保存
     * @return 保存成功的记录对象
     */
    CxGtReimEntity saveGtCost(String empId, String positionId, Map<String, Object> GtCost, String saveFlag);

    /**
     * GT费用提交到crm并保存
     *
     * @param empId      人员id
     * @param positionId 职位id
     * @param GtCost     提交的数据map
     * @return 提交成功的对象
     */
    CxGtReimEntity submitCostCancel(String empId, String positionId, Map<String, Object> GtCost);

}
