package com.guanke.vinda.safunc.biz.impl;

import com.guanke.vinda.safunc.biz.ImageCommonBiz;
import com.guanke.vinda.safunc.config.ParamsConfig;
import com.guanke.vinda.safunc.feign.GeneralFeignService;
import com.guanke.vinda.samodels.model.pojo.PhotoUploadPojo;
import com.guanke.vinda.samodels.model.utils.UUIDUtils;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 统一图片上传接口
 *
 * @author Nicemorning
 */
@Service
public class ImageCommonBizImpl implements ImageCommonBiz {
    private final ParamsConfig paramsConfig;

    private final GeneralFeignService generalFeignService;

    public ImageCommonBizImpl(GeneralFeignService generalFeignService,
                              ParamsConfig paramsConfig) {
        this.generalFeignService = generalFeignService;
        this.paramsConfig = paramsConfig;
    }

    /**
     * 图片上传接口
     *
     * @param photoUploadPojo 图片参数
     * @return 返回图片结果
     */
    @Override
    public Map<String, Object> upLoadImage(PhotoUploadPojo photoUploadPojo) {
        Map<String, Object> result = new HashMap<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String name = photoUploadPojo.getName() == null ? "" : photoUploadPojo.getName();
        List<Map<String, String>> keys = this.getImageKeys(photoUploadPojo.getServerIds(),
                name + "_" + photoUploadPojo.getType() + "_" + dateFormat.format(new Date())
                        + "_" + UUIDUtils.generateShortUuid());
        result.put("photos", keys);
        result.put("prefix", paramsConfig.getPhotoPrefix());
        return result;
    }

    /**
     * 将图片连续上传至七牛云，并返回所有的图片外链KEY
     *
     * @param serverIds 微信临时资源ID
     * @param fileName  指定文件名
     * @return 七牛云外链KEY
     */
    private List<Map<String, String>> getImageKeys(List<String> serverIds, String fileName) {
        List<Map<String, String>> keys = new ArrayList<>();
        serverIds.forEach(id -> {
            Map<String, String> map = new HashMap<>();
            map.put("key", String.valueOf(generalFeignService.upLoadToQiNiuYun(id, fileName).getData()));
            map.put("id", id);
            keys.add(map);
        });
        return keys;
    }
}
