package com.guanke.vinda.safunc.biz;

import com.guanke.vinda.samodels.model.pojo.PhotoUploadPojo;

import java.util.Map;

/**
 * 统一图片上传接口
 *
 * @author Nicemorning
 */
public interface ImageCommonBiz {

    /**
     * 图片上传接口
     *
     * @param photoUploadPojo 图片参数
     * @return 返回图片结果
     */
    Map<String, Object> upLoadImage(PhotoUploadPojo photoUploadPojo);

}
