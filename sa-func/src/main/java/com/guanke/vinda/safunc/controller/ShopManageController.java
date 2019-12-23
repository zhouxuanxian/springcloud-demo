package com.guanke.vinda.safunc.controller;

import com.github.pagehelper.Page;
import com.guanke.vinda.safunc.biz.ShopBiz;
import com.guanke.vinda.samodels.model.pojo.shop.ShopChangePojo;
import com.guanke.vinda.samodels.model.pojo.shop.ShopManageListPojo;
import com.guanke.vinda.samodels.model.pojo.shop.create.NewShopSubmitPojo;
import com.guanke.vinda.samodels.model.pojo.shop.edit.ShopChangeSubmitPojo;
import com.guanke.vinda.samodels.model.response.ObjectGeneralResponseEntity;
import com.guanke.vinda.samodels.model.response.PageableTableGeneralResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 门店管理模块相关接口
 *
 * @author Nicemorning
 */
@RestController
@RequestMapping("shopManage")
public class ShopManageController {
    private final ShopBiz shopBiz;

    public ShopManageController(ShopBiz shopBiz) {
        this.shopBiz = shopBiz;
    }

    @ApiOperation("分页查询该用户下的门店列表")
    @PostMapping("selectAccountByCondition")
    public PageableTableGeneralResponseEntity listShop(@RequestParam("page") Integer page,
                                                       @RequestParam("pageSize") Integer pageSize,
                                                       @RequestBody Map<String, Object> params) {
        String wxType = String.valueOf(params.get("wxType"));
        Page<ShopManageListPojo> result = shopBiz.selectAccountByCondition(
                page, pageSize,
                String.valueOf(params.get("id")),
                (wxType.contains("SALESMANAGER_") || wxType.contains("SALESMAN_")),
                (Map<String, Object>) params.get("params"));
        return new PageableTableGeneralResponseEntity.Builder()
                .data(result.getResult()).total(result.getTotal()).build();
    }

    @ApiOperation("通过门店ID获取门店详情")
    @GetMapping("shopDetail")
    public ObjectGeneralResponseEntity shopDetail(String id) {
        return new ObjectGeneralResponseEntity.Builder().data(
                shopBiz.getShopDetailByShopId(id)
        ).build();
    }

    @ApiOperation("获取门店基础信息")
    @GetMapping("getShop")
    public ObjectGeneralResponseEntity getVisitMissionByCondition(@RequestParam(value = "accountId") @ApiParam("门店Id") String accountId) {
        return new ObjectGeneralResponseEntity.Builder().data(shopBiz.getShopByShopId(accountId)).build();
    }

    @ApiOperation("通过门店ID分页获取门店的变更历史")
    @GetMapping("shopChangeHistory")
    public PageableTableGeneralResponseEntity shopChangeHistory(@RequestParam @ApiParam("页码") int page,
                                                                @RequestParam @ApiParam("页容量") int pageSize,
                                                                @RequestParam @ApiParam("门店ID") String id) {
        Page<ShopChangePojo> result = shopBiz.getShopChangeHistoryById(page, pageSize, id);
        return new PageableTableGeneralResponseEntity.Builder()
                .data(result.getResult()).total(result.getTotal()).build();
    }

    @ApiOperation("通过门店ID获取门店最后一次更新信息时间")
    @GetMapping("shopChangeTime")
    public ObjectGeneralResponseEntity shopChangeTime(@RequestParam @ApiParam("门店ID") String id) {
        return new ObjectGeneralResponseEntity.Builder().data(shopBiz.getShopChangeTimeById(id)).build();
    }

    @ApiOperation("根据职位ID获取于其相同职位的人，用于客户团队选择")
    @GetMapping("empList")
    public PageableTableGeneralResponseEntity empList(@RequestParam @ApiParam("页码") int page,
                                                      @RequestParam @ApiParam("页容量") int pageSize,
                                                      @RequestParam @ApiParam("职位ID") String positionId,
                                                      @RequestParam @ApiParam("搜索条件：要查找的人员姓名") String name) {
        Page<Map<String, String>> result = shopBiz.getEmpListByPositionId(page, pageSize, positionId, name);
        return new PageableTableGeneralResponseEntity.Builder()
                .data(result.getResult()).total(result.getTotal()).build();
    }

    @ApiOperation("通过职位ID获取该职位下所属门店可选的经销商列表")
    @GetMapping("dealerList")
    public PageableTableGeneralResponseEntity dealerList(@RequestParam @ApiParam("职位ID") String positionId,
                                                         @RequestParam @ApiParam("搜索关键字") String keyword,
                                                         @RequestParam @ApiParam("页码") int page,
                                                         @RequestParam @ApiParam("页容量") int pageSize) {
        Page<Map<String, String>> result =
                shopBiz.getAllDealerListByPositionId(positionId, keyword, page, pageSize);
        return new PageableTableGeneralResponseEntity.Builder()
                .data(result.getResult()).total(result.getTotal()).build();
    }

    @ApiOperation("新建门店")
    @PostMapping("newShop")
    public ObjectGeneralResponseEntity newShop(@RequestParam("phone") @ApiParam("用户手机号") String phone,
                                               @RequestParam("postnId") @ApiParam("用户职位ROW-ID") String postnId,
                                               @RequestParam("orgId") @ApiParam("用户组织ID") String orgId,
                                               @RequestBody @ApiParam("门店信息") NewShopSubmitPojo submitPojo) {
        String message = shopBiz.newShop(phone, postnId, orgId, submitPojo);
        if ("success".equals(message)) {
            return new ObjectGeneralResponseEntity.Builder().build();
        } else {
            return new ObjectGeneralResponseEntity.Builder().code(500).msg(message).build();
        }
    }

    @ApiOperation("修改门店信息")
    @PostMapping("editShop")
    public ObjectGeneralResponseEntity editShop(@RequestBody @ApiParam("门店信息") ShopChangeSubmitPojo submitPojo) {
        String message = shopBiz.editShop(submitPojo);
        if ("success".equals(message)) {
            return new ObjectGeneralResponseEntity.Builder().build();
        } else {
            return new ObjectGeneralResponseEntity.Builder().code(500).msg(message).build();
        }
    }

}
