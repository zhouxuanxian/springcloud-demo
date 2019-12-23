package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class SaPhotoConncetEntity extends BaseEntity implements Serializable {
    /**
     * 关联外表ID
     */
    private String foreignId;

    /**
     * 关联的图片表ID
     */
    private String photoId;

    /**
     * 图片类型
     */
    private String photoType;

    /**
     * 创建者的员工ID
     */
    private String createEmpId;

    /**
     * 创建人的职位ID
     */
    private String createPositionId;
}