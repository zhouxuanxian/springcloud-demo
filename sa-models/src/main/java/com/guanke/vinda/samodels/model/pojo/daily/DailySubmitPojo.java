package com.guanke.vinda.samodels.model.pojo.daily;

import com.guanke.vinda.samodels.model.entity.GkDailyPhotoEntity;
import lombok.Data;

import java.util.List;

/**
 * 日报提交实体类
 *
 * @author Nicemorning
 */
@Data
public class DailySubmitPojo {
    private String content;
    private String coreWork;
    private String id;
    private List<GkDailyPhotoEntity> photos;
}
