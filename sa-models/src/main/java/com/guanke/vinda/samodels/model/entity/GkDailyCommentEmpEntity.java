package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 日报评论表实体类，通过其中dailyReportId与日报表取得对应关系
 *
 * @author Nicemorning
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GkDailyCommentEmpEntity extends BasePojo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 评论发布者的用户ID
    */
    private String commentAccountId;

    /**
    * 评论对应日报或拜访ID
    */
    private String dailyReportId;

    /**
    * 评论正文内容
    */
    private String commentContent;

    /**
    * 评价等级，，由0-4分别对应低到高评价
    */
    private Integer commentLevel;

    /**
    * 评论发布时间
    */
    private Date commentTime;

    /**
    * 评论发布者的对应的职位ID
    */
    private String positionId;
}