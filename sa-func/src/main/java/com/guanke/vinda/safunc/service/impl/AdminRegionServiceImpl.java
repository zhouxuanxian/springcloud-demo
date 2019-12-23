package com.guanke.vinda.safunc.service.impl;

import com.guanke.vinda.safunc.mapper.SaRealAddressMapper;
import com.guanke.vinda.safunc.service.AdminRegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nicemorning
 */
@Service
public class AdminRegionServiceImpl implements AdminRegionService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AdminRegionServiceImpl.class);

    private final SaRealAddressMapper saRealAddressMapper;

    public AdminRegionServiceImpl(SaRealAddressMapper saRealAddressMapper) {
        this.saRealAddressMapper = saRealAddressMapper;
    }

    /**
     * 根据条件获取行政区域列表
     *
     * @param type  要查询的区划等级
     * @param query 查询条件
     * @return 返回该区划等级的所有名称列表
     */
    @Override
    public List<String> getAdministrativeRegionList(Integer type, String query) {
        if (type == 1) {
            return saRealAddressMapper.getProvincesList();
        }
        if (type == 2) {
            return saRealAddressMapper.getCitiesList(query);
        }
        if (type == 3) {
            return saRealAddressMapper.getCountiesList(query);
        }
        LOGGER.info("Missing condition");
        return null;
    }
}
