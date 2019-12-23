package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 日报实体类
 * <br/>
 * 日报对象比较特殊，一个职位每天只能创建一个日报，但提交并没有限制，第二天也可以提交昨天的订单，<br/>
 * 所以在报表汇总的时候需要根据创建时间createdTime来汇总和判断，<br/>
 * 或者dailyDate改为日报创建日期，也可以用于报表汇总便于sql查询 <br/>
 *
 * @author Nicemorning
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GkDailyEntity extends BasePojo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 人员ID
     */
    private String empId;
    /**
     * 职位ID
     */
    private String positionId;
    /**
     * 日报提交时间(时分秒，无汇总逻辑使用此字段，单纯的展示和记录)
     */
    private Date reportDate;
    /**
     * 日报创建日期（报表逻辑汇总字段）
     */
    private Date dailyDate;
    /**
     * 核心工作
     */
    private String coreWork;
    /**
     * 日报内容
     */
    private String content;
    /**
     * 点赞计数
     */
    private Integer likeQty;
    /**
     * 状态 已保存/已提交
     */
    private String status;
    /**
     * 保存时间（这个字段暂时没有实际意义，仅记录）
     */
    private Date draftDate;
}