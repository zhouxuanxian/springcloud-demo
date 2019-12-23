package com.guanke.vinda.safunc.controller;

import com.github.pagehelper.Page;
import com.guanke.vinda.safunc.biz.CostCancelBiz;
import com.guanke.vinda.samodels.model.response.ObjectGeneralResponseEntity;
import com.guanke.vinda.samodels.model.response.PageableTableGeneralResponseEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * GT費用核销相关接口
 *
 * @author ldm
 */
@Api("aaa")
@RestController
@RequestMapping("costCancel")
public class CostCancelController {

    private final CostCancelBiz costCancelBiz;

    public CostCancelController(CostCancelBiz costCancelBiz) {
        this.costCancelBiz = costCancelBiz;
    }

    /**
     * 获取GT费用列表
     *
     * @param params 页面上传参数
     * @return 费用列表
     */
    @PostMapping("getCostList")
    public PageableTableGeneralResponseEntity getCostLists(@RequestBody Map<String, Object> params ) {
        Page<Map<String, Object>> result = null;
        //页签类别
        String partFlag = (String) (params.get("partFlag"));
        if ("未完成".equals(partFlag)) {
            result = costCancelBiz.getNoDoneCostLists(
                    String.valueOf(params.get("Keyword")),
                    String.valueOf(params.get("positionId")),
                    String.valueOf(params.get("empId")),
                    Integer.parseInt(String.valueOf(params.get("pageNum"))),
                    Integer.parseInt(String.valueOf(params.get("pageSize"))));
        } else {
            result = costCancelBiz.getMoreCostLists(
                    String.valueOf(params.get("Approval_Status")),
                    String.valueOf(params.get("Reim_Status")),
                    String.valueOf(params.get("Keyword")),
                    String.valueOf(params.get("positionId")),
                    String.valueOf(params.get("empId")),
                    Integer.parseInt(String.valueOf(params.get("pageNum"))),
                    Integer.parseInt(String.valueOf(params.get("pageSize"))));
        }
        return new PageableTableGeneralResponseEntity.Builder().data(result.getResult()).
                total(result.getTotal()).build();
    }

    /**
     * 获取GT费用核销详情
     *
     * @param params 参数
     * @return 对应核销id的详情
     */
    @PostMapping("/getCostDetails")
    public ObjectGeneralResponseEntity getCostDetails(@RequestBody Map<String, Object> params) {
        String positionId = (String) params.get("positionId");
        String empId = (String) params.get("empId");
        String reimId = (String) params.get("reimId");
        return new ObjectGeneralResponseEntity.Builder().data(
                costCancelBiz.getCostDetailsBiz(positionId, empId, reimId)
        ).build();
    }

    /**
     * 获取 临时数据用于回显
     *
     * @param params 参数
     * @return 返回map
     */
    @PostMapping("/getCostTempDatas")
    public ObjectGeneralResponseEntity getCostTempDatas(@RequestBody Map<String, Object> params) {
        String positionId = (String) params.get("positionId");
        String empId = (String) params.get("empId");
        String reimId = (String) params.get("reimId");
        return new ObjectGeneralResponseEntity.Builder().data(
                costCancelBiz.getCostTempDatas(positionId, empId, reimId)
        ).build();
    }

    /**
     * 上传GT照片
     *
     * @param serverId  微信本地url
     * @param storeName 店铺名称
     * @return 返回key
     */
    @GetMapping("costUpPhoto")
    public ObjectGeneralResponseEntity costUpPhoto(String serverId, String storeName) {
        Map<String, String> result = costCancelBiz.costUpPhoto(serverId, storeName);
        if (result == null) {
            return new ObjectGeneralResponseEntity.Builder().code(500).build();
        }
        return new ObjectGeneralResponseEntity.Builder().data(result).build();
    }


}
