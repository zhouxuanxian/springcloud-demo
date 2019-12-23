package com.guanke.vinda.safunc.controller;

import com.github.pagehelper.Page;
import com.guanke.vinda.safunc.biz.DailyBiz;
import com.guanke.vinda.samodels.model.entity.GkDailyEntity;
import com.guanke.vinda.samodels.model.entity.GkDailyPhotoEntity;
import com.guanke.vinda.samodels.model.pojo.daily.DailySubmitPojo;
import com.guanke.vinda.samodels.model.response.ObjectGeneralResponseEntity;
import com.guanke.vinda.samodels.model.response.PageableTableGeneralResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

/**
 * 日报相关接口
 *
 * @author Nicemorning
 */
@RestController
@RequestMapping("daily")
public class DailyController {

    private final DailyBiz dailyBiz;

    public DailyController(DailyBiz dailyBiz) {
        this.dailyBiz = dailyBiz;
    }

    @SuppressWarnings("unchecked")
    @ApiOperation("分页获取我的日报列表")
    @PostMapping("getMyDaily")
    public PageableTableGeneralResponseEntity getMyDaily(@RequestBody @ApiParam("positionId:职位ID；" +
            "empId:员工ID；pageNum:页码；pageSize:页容量") Map<String, Object> params) {
        Page<Map<String, Object>> result = dailyBiz.getMyDailys(
                String.valueOf(params.get("positionId")),
                String.valueOf(params.get("empId")),
                Integer.parseInt(String.valueOf(params.get("pageNum"))),
                Integer.parseInt(String.valueOf(params.get("pageSize"))));
        return new PageableTableGeneralResponseEntity.Builder().data(result.getResult()).
                total(result.getTotal()).build();
    }

    @ApiOperation("获取我的日报消息提醒")
    @PostMapping("dailyMessage")
    public ObjectGeneralResponseEntity dailyMessage(@RequestBody
                                                    @ApiParam("positionId:职位ID；empId:员工ID") Map<String, Object> params) {
        Map<String, Integer> result = dailyBiz.dailyMessage(
                String.valueOf(params.get("positionId")),
                String.valueOf(params.get("empId"))
        );
        return new ObjectGeneralResponseEntity.Builder().data(result).build();
    }

    @ApiOperation("更新消息查看时间")
    @GetMapping("updateMessageCheck")
    public ObjectGeneralResponseEntity updateMessageCheck(@RequestParam @ApiParam("当前登录用户手机号") String phone) {
        return new ObjectGeneralResponseEntity.Builder().data(
                dailyBiz.updateMessageCheck(phone)
        ).build();
    }

    @ApiOperation("提交日报")
    @PostMapping("submit")
    public ObjectGeneralResponseEntity submitDaily(@PathParam("positionId") @ApiParam("职位ID") String positionId,
                                                   @PathParam("empId") @ApiParam("员工ID") String empId,
                                                   @PathParam("option") @ApiParam("操作类型，如果为0则保存为草稿，如果为1则为提交日报，如果为2则为提交草稿") Integer option,
                                                   @RequestBody @ApiParam("日报参数") DailySubmitPojo daily) {
        System.out.println(daily);
        GkDailyEntity gkDailyEntity = dailyBiz.saveDaily(option, empId, positionId, daily);
        if (gkDailyEntity != null) {
            return new ObjectGeneralResponseEntity.Builder().data(gkDailyEntity).build();
        } else {
            return new ObjectGeneralResponseEntity.Builder().code(500).msg("日报填写失败").build();
        }
    }

    @ApiOperation("获取日报详情")
    @GetMapping("getDailyDetail")
    public ObjectGeneralResponseEntity getDailyDetail(@RequestParam @ApiParam("日报ID") String id) {
        return new ObjectGeneralResponseEntity.Builder().data(dailyBiz.getDailyDetail(id)).build();
    }

    @ApiOperation("查询当前用户在制定日期下是否允许提交日报")
    @PostMapping("canICommit")
    public ObjectGeneralResponseEntity canICommit(@RequestBody @ApiParam("positionId:职位ID；" +
            "empId:员工ID；date:要查询的日期") Map<String, String> params) {
        return new ObjectGeneralResponseEntity.Builder().data(
                dailyBiz.isCommittedByPositionIdAndEmpIdAndDailyDate(
                        params.get("positionId"),
                        params.get("empId"),
                        params.get("date"))).build();
    }

    @ApiOperation("获取当前用户在指定日期下的日报草稿主键")
    @PostMapping("getDraftId")
    public ObjectGeneralResponseEntity getDraftId(@RequestBody @ApiParam("positionId:职位ID；" +
            "empId:员工ID；date:要查询的日期") Map<String, String> params) {
        return new ObjectGeneralResponseEntity.Builder().data(
                dailyBiz.getDraftIdByPositionIdAndEmpIdAndDailyDate(
                        params.get("positionId"),
                        params.get("empId"),
                        params.get("date"))).build();
    }

    @ApiOperation("通过员工ID和职位ID获取该员工的所有日报消息提醒列表")
    @GetMapping("getDailyMessage")
    public ObjectGeneralResponseEntity getDailyMessage(@RequestParam @ApiParam("员工ID") String empId,
                                                       @RequestParam @ApiParam("职位ID") String positionId) {
        return new ObjectGeneralResponseEntity.Builder().data(
                dailyBiz.getDailyMessageByPositionIdAndEmpId(positionId, empId)
        ).build();
    }

    @ApiOperation("获取日报图片列表")
    @GetMapping("getDailyPhoto")
    public ObjectGeneralResponseEntity getDailyPhoto(@RequestParam @ApiParam("日报ID") String id) {
        return new ObjectGeneralResponseEntity.Builder().data(
                dailyBiz.getDailyImages(id)
        ).build();
    }

    @ApiOperation("获取日报提交的顶部初始数据")
    @GetMapping("getDailySubmitTopData")
    public ObjectGeneralResponseEntity getVisitMissionForDaily(@RequestParam("empId") @ApiParam("员工ID") String empId,
                                                               @RequestParam("positionId") @ApiParam("职位ID") String positionId,
                                                               @RequestParam("date") @ApiParam("要查询的日期") String date) {
        return new ObjectGeneralResponseEntity.Builder().data(
                dailyBiz.getVisitMissionForDaily(empId, positionId, date)
        ).build();
    }

    @ApiOperation("获取指定日期提交的竞品信息")
    @GetMapping("getCompetitorList")
    public ObjectGeneralResponseEntity getCompetitorList(@RequestParam("empId") @ApiParam("员工ID") String empId,
                                                         @RequestParam("positionId") @ApiParam("职位ID") String positionId,
                                                         @RequestParam(value = "date", required = false) @ApiParam("查询日期") String date) {
        return new ObjectGeneralResponseEntity.Builder().data(
                dailyBiz.getCompetitorListByPositionIdAndEmpIdAndDate(positionId, empId, date)
        ).build();
    }

    @ApiOperation("获取指定日期的拍照信息")
    @GetMapping("getUploadPhotoList")
    public ObjectGeneralResponseEntity getUploadPhotoList(@RequestParam("empId") @ApiParam("员工ID") String empId,
                                                          @RequestParam("positionId") @ApiParam("职位ID") String positionId,
                                                          @RequestParam(value = "date", required = false) @ApiParam("查询日期") String date) {
        return new ObjectGeneralResponseEntity.Builder().data(
                dailyBiz.getUploadPhotosListByEmpIdAndPositionIdAndDate(empId, positionId, date)
        ).build();
    }

    @ApiOperation("获取指定日期的门店维护记录")
    @GetMapping("getMaintainAccountList")
    public ObjectGeneralResponseEntity getMaintainAccountList(@RequestParam("empId") @ApiParam("员工ID") String empId,
                                                              @RequestParam(value = "date", required = false) @ApiParam("查询日期") String date) {
        return new ObjectGeneralResponseEntity.Builder().data(
                dailyBiz.getMaintainAccountListByEmpIdAndDate(empId, date)
        ).build();
    }

}
