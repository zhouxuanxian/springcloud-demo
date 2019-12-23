package com.guanke.vinda.safunc.controller;

import com.github.pagehelper.Page;
import com.guanke.vinda.safunc.biz.VisitMissionBiz;
import com.guanke.vinda.samodels.model.pojo.visit.AccountVisitDetailPojo;
import com.guanke.vinda.samodels.model.pojo.visit.NonAccountVisitDetailPojo;
import com.guanke.vinda.samodels.model.pojo.visit.SubmitVisitPojo;
import com.guanke.vinda.samodels.model.response.ObjectGeneralResponseEntity;
import com.guanke.vinda.samodels.model.response.PageableTableGeneralResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 拜访任务相关接口
 *
 * @author Nicemorning
 */
@RequestMapping("visitMission")
@RestController
public class VisitMissionController {
    private final VisitMissionBiz visitMissionBiz;

    public VisitMissionController(VisitMissionBiz visitMissionBiz) {
        this.visitMissionBiz = visitMissionBiz;
    }

    @ApiOperation("查询该员工在指定月份下的任务统计数据")
    @GetMapping("getMonthMission")
    public ObjectGeneralResponseEntity getMonthMissionList(@RequestParam("date") @ApiParam("要查询的日期") String date,
                                                           @RequestParam("empId") @ApiParam("人员ID") String empId,
                                                           @RequestParam("positionId") @ApiParam("职位ID") String positionId) {
        List<Map<String, Object>> result =
                visitMissionBiz.getMonthVisitMissionByEmpIdAndPositionIdAndMonth(empId, positionId, date);
        if (result == null) {
            return new ObjectGeneralResponseEntity.Builder().msg("当月无任务").build();
        } else {
            return new ObjectGeneralResponseEntity.Builder().data(result).build();
        }
    }

    @ApiOperation("查询该员工指定日期下的所有任务列表")
    @PostMapping("getMissionListByDate")
    public PageableTableGeneralResponseEntity getMissionListByDate(@RequestParam("empId") @ApiParam("人员ID") String empId,
                                                                   @RequestParam("positionId") @ApiParam("职位ID") String positionId,
                                                                   @RequestBody Map<String, Object> params) {
        int page = Integer.parseInt(String.valueOf(params.get("page")));
        int pageSize = Integer.parseInt(String.valueOf(params.get("pageSize")));
        String date = String.valueOf(params.get("date"));
        Page<Map<String, Object>> result =
                visitMissionBiz.getMissionListByDateAndPositionIdAndEmpId(positionId,
                        empId, date, page, pageSize);
        return new PageableTableGeneralResponseEntity.Builder()
                .data(result.getResult()).total(result.getTotal()).build();
    }

    @ApiOperation("通过职位ID和筛选条件获取该职位下的GT门店列表")
    @PostMapping("listGTShop")
    public PageableTableGeneralResponseEntity listGtShop(@RequestParam("positionId") @ApiParam("职位ID") String postionId,
                                                         @RequestBody Map<String, Object> params) {
        int page = (int) params.get("page");
        int pageSize = (int) params.get("pageSize");
        Page<Map<String, Object>> result = visitMissionBiz.getGtShopList(postionId, params, page, pageSize);
        return new PageableTableGeneralResponseEntity.Builder().data(result.getResult()).total(result.getTotal()).build();
    }

    @ApiOperation("新建拜访事项，如果是非拜访事项，则accountIds传入null即可")
    @PostMapping("newMission")
    public ObjectGeneralResponseEntity newMission(@RequestParam("empId") @ApiParam("人员ID") String empId,
                                                  @RequestParam("positionId") @ApiParam("职位ID") String postionId,
                                                  @RequestBody Map<String, Object> params) {
        List<String> accountIds = (List<String>) params.get("accountIds");
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
        Date visitDate = new Date();
        try {
            visitDate = dateFormat.parse(String.valueOf(params.get("visitDate")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (visitMissionBiz.insertAccountVisitEntity(empId, postionId, visitDate, accountIds)) {
            return new ObjectGeneralResponseEntity.Builder().build();
        } else {
            return new ObjectGeneralResponseEntity.Builder().code(500).msg("新建失败").build();
        }
    }

    @ApiOperation("获取非拜访事项详情")
    @GetMapping("getNonAccountVisitDetail")
    public ObjectGeneralResponseEntity getNonAccountVisitDetail(@RequestParam("visitId") @ApiParam("拜访ID") String visitId) {
        NonAccountVisitDetailPojo nonAccountVisitDetailPojo = visitMissionBiz.getNonAccountVisitDetail(visitId);
        if (nonAccountVisitDetailPojo == null) {
            return new ObjectGeneralResponseEntity.Builder().code(500).msg("拜访ID错误").build();
        } else {
            return new ObjectGeneralResponseEntity.Builder().data(nonAccountVisitDetailPojo).build();
        }
    }

    @ApiOperation("获取门店拜访事项的详情")
    @GetMapping("getRealAccountVisitDetail")
    public ObjectGeneralResponseEntity getRealAccountVisitDetail(@RequestParam("visitId") @ApiParam("拜访ID") String visitId) {
        AccountVisitDetailPojo accountVisitDetailPojo = visitMissionBiz.getRealAccountVisitDetail(visitId);
        if (accountVisitDetailPojo == null) {
            return new ObjectGeneralResponseEntity.Builder().code(500).msg("拜访ID错误").build();
        } else {
            return new ObjectGeneralResponseEntity.Builder().data(accountVisitDetailPojo).build();
        }
    }

    @ApiOperation("提交拜访记录")
    @PostMapping("submitVisit")
    public ObjectGeneralResponseEntity submitVisit(@RequestParam("positionId") @ApiParam("职位ID") String positionId,
                                                   @RequestParam("empId") @ApiParam("员工ID") String empId,
                                                   @RequestBody @ApiParam("拜访详情") SubmitVisitPojo params) {
        String message = visitMissionBiz.submitVisit(params, positionId, empId);
        if ("success".equals(message)) {
            return new ObjectGeneralResponseEntity.Builder().build();
        } else {
            return new ObjectGeneralResponseEntity.Builder().code(500).msg(message).build();
        }
    }

    @ApiOperation("取消拜访")
    @PostMapping("cancelVisit")
    public ObjectGeneralResponseEntity cancelVisit(@RequestBody Map<String, String> params) {
        String message = visitMissionBiz.cancelVisit(params);
        if ("success".equals(message)) {
            return new ObjectGeneralResponseEntity.Builder().msg(message).build();
        } else {
            return new ObjectGeneralResponseEntity.Builder().code(500).msg(message).build();
        }
    }

    @ApiOperation("通过拜访ID删除拜访任务")
    @PostMapping("deleteVisit")
    public ObjectGeneralResponseEntity deleteVisit(@RequestParam @ApiParam("拜访ID") String id) {
        return new ObjectGeneralResponseEntity.Builder().data(
                visitMissionBiz.deleteVisit(id)
        ).build();
    }

    @ApiOperation("查询该拜访下的V码列表")
    @PostMapping("loadCommonVCodeList")
    public ObjectGeneralResponseEntity loadCommonVCodeList(@RequestBody @ApiParam("查询参数") Map<String, Object> params) {
        return new ObjectGeneralResponseEntity.Builder().data(visitMissionBiz.loadCommonVCodeList(params)).build();
    }

    @ApiOperation("通过关键字和默认V码分页查询V码列表")
    @PostMapping("getVCodeList")
    public PageableTableGeneralResponseEntity getVCodeList(@RequestParam("page") @ApiParam("页码") int page,
                                                           @RequestParam("pageSize") @ApiParam("页容量") int pageSize,
                                                           @RequestBody Map<String, Object> params) {
        Page<Map<String, Object>> resut = visitMissionBiz.getVCodeList(page, pageSize, params);
        return new PageableTableGeneralResponseEntity.Builder().data(resut.getResult()).total(resut.getTotal()).build();
    }

    @ApiOperation("获取拜访图片列表")
    @GetMapping("getPhotoList")
    public ObjectGeneralResponseEntity getPhotoList(@RequestParam @ApiParam("拜访ID") String visitId) {
        return new ObjectGeneralResponseEntity.Builder().data(
                visitMissionBiz.getVisitPhotos(visitId)
        ).build();
    }

    @ApiOperation("根据条件分页查询拜访记录")
    @PostMapping("getVisitMissionByCondition")
    public PageableTableGeneralResponseEntity getVisitMissionByCondition(@RequestParam(value = "accountId", required = false) @ApiParam("门店Id") String accountId,
                                                                         @RequestParam(value = "empId", required = false) @ApiParam("员工Id") String empId,
                                                                         @RequestParam(value = "positionId", required = false) @ApiParam("职位Id") String positionId,
                                                                         @RequestParam(value = "visitDate", required = false) @ApiParam("拜访日期") String visitDate,
                                                                         @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam("页码") Integer pageNum,
                                                                         @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam("每页数量") Integer pageSize) {
        Page<Map<String, Object>> result = visitMissionBiz.getVisitMissionByCondition(accountId, empId, positionId, visitDate, pageNum, pageSize);
        return new PageableTableGeneralResponseEntity.Builder().data(result.getResult()).total(result.getTotal()).build();
    }

    @ApiOperation("获取拜访取消原因")
    @GetMapping("getCancelReason")
    public ObjectGeneralResponseEntity getSubmitCancelReason(@RequestParam("visitId") @ApiParam("拜访ID") String visitId) {
        Map<String, Object> reason = visitMissionBiz.getCancelReason(visitId);
        if (reason == null) {
            return new ObjectGeneralResponseEntity.Builder().code(500).msg("获取拜访原因失败").build();
        } else {
            return new ObjectGeneralResponseEntity.Builder().data(reason).build();
        }
    }

    @ApiOperation("根据门店Id查询今天是否存在拜访任务")
    @PostMapping("checkVisitMissionByAccountId")
    public ObjectGeneralResponseEntity checkVisitMissionByAccountId(@RequestParam("accountId") @ApiParam("门店Id") String accountId) {
        boolean exist = visitMissionBiz.checkVisitMissionByAccountId(accountId);
        return new ObjectGeneralResponseEntity.Builder().data(exist).build();
    }

}