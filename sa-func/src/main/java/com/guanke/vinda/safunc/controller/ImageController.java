package com.guanke.vinda.safunc.controller;

import com.guanke.vinda.safunc.biz.ImageCommonBiz;
import com.guanke.vinda.samodels.model.pojo.PhotoUploadPojo;
import com.guanke.vinda.samodels.model.response.ObjectGeneralResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 统一图片上传类
 *
 * @author Nicemorning
 */
@RestController
@RequestMapping("image")
public class ImageController {
    private final ImageCommonBiz imageCommonBiz;

    public ImageController(ImageCommonBiz imageCommonBiz) {
        this.imageCommonBiz = imageCommonBiz;
    }

    @ApiOperation("上传图片")
    @PostMapping("upLoad")
    public ObjectGeneralResponseEntity upLoadImage(@RequestBody @ApiParam("图片参数") PhotoUploadPojo params) {
        return new ObjectGeneralResponseEntity.Builder().data(
                imageCommonBiz.upLoadImage(params)
        ).build();
    }

}
