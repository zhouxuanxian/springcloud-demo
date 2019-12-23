package com.guanke.vinda.safunc.controller;

import com.guanke.vinda.safunc.biz.ShopBiz;
import com.guanke.vinda.safunc.biz.UserControlBiz;
import com.guanke.vinda.samodels.model.response.ObjectGeneralResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户控制相关接口
 *
 * @author Nicemorning
 */
@RestController
@RequestMapping("/user")
public class UserCtrlController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserCtrlController.class);

    private final UserControlBiz userControlBiz;

    private final ShopBiz shopBiz;

    public UserCtrlController(UserControlBiz userControlBiz, ShopBiz shopBiz) {
        this.userControlBiz = userControlBiz;
        this.shopBiz = shopBiz;
    }

    /**
     * 用户登陆接口
     *
     * @param code 用户的临时CODE
     * @return 如果登陆成功，则data为用户个人信息，否则返回data为null
     */
    @ApiOperation("用户登录接口")
    @PostMapping("/signIn")
    public ObjectGeneralResponseEntity userSignIn(@RequestParam @ApiParam("微信返回的用户临时CODE") String code) {
        Map<String, Object> result = userControlBiz.getUserInfoByCode(code);
        return new ObjectGeneralResponseEntity.Builder().data(result).build();
    }

    //=====================业务接口======================

    @ApiOperation("获取指定日期下指定人员和职位的首页数据")
    @GetMapping("/homeData")
    public ObjectGeneralResponseEntity homeData(@RequestParam @ApiParam("职位ID") String positionId,
                                                @RequestParam @ApiParam("人员ID") String empId,
                                                @RequestParam @ApiParam("要查询的日期") String date) {
        return new ObjectGeneralResponseEntity.Builder().data(
                userControlBiz.getHomeDataByPositionIdAndEmpIdAndDate(positionId, empId, date)
        ).build();
    }

    @ApiOperation("获取该用户名下所有门店的行政区域列表")
    @GetMapping("businessOffice")
    public ObjectGeneralResponseEntity businessOffice(@RequestParam @ApiParam("职位ID") String id) {
        return new ObjectGeneralResponseEntity.Builder().data(
                shopBiz.getAllShopDistrictByPositionId(id)
        ).build();
    }

    @ApiOperation("根据用户的员工ID获取该用户在个人设置界面中的基本信息")
    @GetMapping("settingInfo")
    public ObjectGeneralResponseEntity getSettingsInfo(@RequestParam @ApiParam("人员ID") String empId) {
        return new ObjectGeneralResponseEntity.Builder().data(
                userControlBiz.getUserSettingsInfoByEmpId(empId)
        ).build();
    }

    @ApiOperation("通过用户的手机号更新该用户的头像")
    @GetMapping("updateAvatar")
    public ObjectGeneralResponseEntity updateAvatar(@RequestParam @ApiParam("微信返回的临时资源服务器ID") String serverId,
                                                    @RequestParam @ApiParam("当前登录用户的手机号") String phone) {
        Map<String, String> result = userControlBiz.updateAvatar(serverId, phone);
        if (result == null) {
            return new ObjectGeneralResponseEntity.Builder().code(500).build();
        }
        return new ObjectGeneralResponseEntity.Builder().data(result).build();
    }

}
