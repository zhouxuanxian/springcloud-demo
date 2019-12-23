package com.guanke.vinda.safunc.biz;

import java.util.Map;

/**
 * 用户控制相关业务处理
 *
 * @author Nicemorning
 */
public interface UserControlBiz {
    /**
     * 通过用户的临时登录code获取该用户的个人信息，并判断该用户是否允许登录
     *
     * @param code 用户的微信临时CODE
     * @return 如果存在则返回该用户的信息，否则返回null
     */
    Map<String, Object> getUserInfoByCode(String code);

    /**
     * 根据用户的员工ID获取该用户在个人设置界面中的基本信息
     *
     * @param empId 用户的员工ID
     * @return 返回该员工ID对应的设置页所需的信息
     */
    Map<String, Object> getUserSettingsInfoByEmpId(String empId);

    /**
     * 通过用户ID和职位ID获取该用户指定日期下的首页数据
     *
     * @param positionId 职位ID
     * @param empId      用户ID
     * @param date       要查询的日期
     * @return 指定日期下该用户该职位的首页数据信息
     */
    Map<String, Object> getHomeDataByPositionIdAndEmpIdAndDate(String positionId, String empId, String date);

    /**
     * 通过用户的手机号更新该用户的头像
     *
     * @param serverId 头像的微信临时资源路径
     * @param phone    用户的手机号
     * @return 返回用户新头像的七牛云外链地址
     */
    Map<String, String> updateAvatar(String serverId, String phone);


}
