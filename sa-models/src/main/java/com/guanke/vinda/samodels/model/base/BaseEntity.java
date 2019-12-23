package com.guanke.vinda.samodels.model.base;

import com.guanke.vinda.samodels.model.utils.UUIDUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类，所有表的实体类必须继承该类
 *
 * @author Nicemorning
 */
@Data
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间，默认为null
     */
    private Date updateTime;
    /**
     * 行版本号，默认为：0
     */
    private Integer rowVersion;
    /**
     * 逻辑删除标记，默认为0，删除为1
     */
    private Integer deleteFlag;

    public BaseEntity() {
        this.id = UUIDUtils.generateUuid();
        Date date = new Date();
        this.createTime = date;
        this.updateTime = date;
        this.rowVersion = 0;
        this.deleteFlag = 0;
    }
}
