package com.guanke.vinda.samodels.model.base;

import com.guanke.vinda.samodels.model.utils.UUIDUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类，所有与销售助手共用的表均需继承该类
 *
 * @author Nicemorning
 */
@Data
public abstract class BasePojo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    private String id;
    /**
     * 创建人Id
     */
    private String createdBy;
    /**
     * 更新人Id
     */
    private String updatedBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updatedTime;
    /**
     * 版本号
     */
    private Integer version;

    protected BasePojo() {
        this.id = UUIDUtils.generateUuid();
        this.createdBy = "0-1";
        this.updatedBy = "0-1";
        Date date = new Date();
        this.createdTime = date;
        this.updatedTime = date;
        this.version = 0;
    }
}
