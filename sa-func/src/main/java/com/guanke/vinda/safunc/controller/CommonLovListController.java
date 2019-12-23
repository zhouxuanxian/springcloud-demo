package com.guanke.vinda.safunc.controller;

import com.guanke.vinda.safunc.biz.CommonLovLIstBiz;
import com.guanke.vinda.samodels.model.response.ObjectGeneralResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通用选择列表接口类
 *
 * @author Nicemorning
 */
@RestController
@RequestMapping("lovList")
public class CommonLovListController {
    private final CommonLovLIstBiz commonLovLIstBiz;

    public CommonLovListController(CommonLovLIstBiz commonLovLIstBiz) {
        this.commonLovLIstBiz = commonLovLIstBiz;
    }

    @ApiOperation("获取选择列表")
    @GetMapping("getLovList")
    public ObjectGeneralResponseEntity getLovList(@RequestParam("type") @ApiParam("type仅可在以下值中选择一项：\n" +
            "        GK_VISIT_PURPOSE 拜访意向;\n" +
            "        GK_VISIT_CANCEL_REASON 取消拜访原因;\n" +
            "        PROMOTION_TYPE 促销方式;\n" +
            "        VD_STORE_SECOND_TYPE 门店二级分类;\n" +
            "        VD_STORE_THIRD_TYPE  门店三级分类;\n" +
            "        VD_STORE_LEVEL 门店等级;\n" +
            "        DAILY_CORE_WORK 核心工作;\n" +
            "        GK_YES_OR_NO 业务是否覆盖门店;\n" +
            "        ACCOUNT_STATUS 客户状态") String type) {
        return new ObjectGeneralResponseEntity.Builder().data(
                commonLovLIstBiz.getLovList(type)
        ).build();
    }
}
