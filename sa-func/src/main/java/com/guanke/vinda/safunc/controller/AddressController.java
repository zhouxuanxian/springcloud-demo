package com.guanke.vinda.safunc.controller;

import com.guanke.vinda.safunc.service.AdminRegionService;
import com.guanke.vinda.samodels.model.response.ObjectGeneralResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nicemorning
 */
@RestController
@RequestMapping("address")
public class AddressController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);

    private final AdminRegionService adminRegionService;

    public AddressController(AdminRegionService adminRegionService) {
        this.adminRegionService = adminRegionService;
    }

    @ApiOperation("根据条件获取行政区域列表")
    @GetMapping("getRegionList")
    public ObjectGeneralResponseEntity getRegionList(@RequestParam
                                                     @ApiParam("要查询的区划等级：1为省、直辖市、特别行政区；\n2为地级市、自治州；\n3为区、县、自治旗")
                                                             Integer type,
                                                     @RequestParam @ApiParam("查询条件") String query) {
        return new ObjectGeneralResponseEntity.Builder().data(
                adminRegionService.getAdministrativeRegionList(type, query)).build();
    }
}
