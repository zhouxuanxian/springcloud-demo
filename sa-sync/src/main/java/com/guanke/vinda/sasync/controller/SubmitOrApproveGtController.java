package com.guanke.vinda.sasync.controller;

import com.guanke.vinda.samodels.model.entity.CxGtReimEntity;
import com.guanke.vinda.samodels.model.response.ObjectGeneralResponseEntity;
import com.guanke.vinda.sasync.biz.GtSubmitOrApproveBiz;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

/**
 * GT费用提交
 * @author niko
 */
@RestController
@RequestMapping("costCancel")
public class SubmitOrApproveGtController {

    private final GtSubmitOrApproveBiz gtSubmitOrApproveBiz;

    public SubmitOrApproveGtController(GtSubmitOrApproveBiz gtSubmitOrApproveBiz) {
        this.gtSubmitOrApproveBiz = gtSubmitOrApproveBiz;
    }

    @PostMapping("save")
    public ObjectGeneralResponseEntity saveCostCancel(@PathParam("positionId") String positionId,
                                                      @PathParam("empId") String empId,
                                                      @RequestBody Map<String, Object> GtCost) {

        CxGtReimEntity GkCostCancelEntity = gtSubmitOrApproveBiz.saveGtCost(empId, positionId, GtCost, "save");

        if (GkCostCancelEntity != null) {
            return new ObjectGeneralResponseEntity.Builder().data(GkCostCancelEntity).build();
        } else {
            return new ObjectGeneralResponseEntity.Builder().code(500).build();
        }
    }

    @PostMapping("submit")
    public ObjectGeneralResponseEntity submitCostCancel(@PathParam("positionId") String positionId,
                                                        @PathParam("empId") String empId,
                                                        @RequestBody Map<String, Object> GtCost) {

        CxGtReimEntity GkCostCancelEntity = gtSubmitOrApproveBiz.submitCostCancel(empId, positionId, GtCost);

        if (GkCostCancelEntity != null) {
            return new ObjectGeneralResponseEntity.Builder().data(GkCostCancelEntity).build();
        } else {
            return new ObjectGeneralResponseEntity.Builder().code(500).msg("网络异常").build();
        }
    }


}
