package com.guanke.vinda.safunc.biz.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guanke.vinda.safunc.biz.GkAccountVcodeBiz;
import com.guanke.vinda.safunc.mapper.GkAccountVcodeEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 门店V码相关业务实现类
 *
 * @author Nicemorning
 */
@Service
public class GkAccountVcodeBizImpl implements GkAccountVcodeBiz {
    private static final Logger LOGGER = LoggerFactory.getLogger(GkAccountVcodeBizImpl.class);

    private final GkAccountVcodeEntityMapper gkAccountVcodeEntityMapper;

    public GkAccountVcodeBizImpl(GkAccountVcodeEntityMapper gkAccountVcodeEntityMapper) {
        this.gkAccountVcodeEntityMapper = gkAccountVcodeEntityMapper;
    }


    /**
     * 根据条件分页查询门店V码
     *
     * @param accountId 门店Id
     * @param pageNum   页码
     * @param pageSize  每页数量
     * @return 返回指定条件下的门店V码
     */
    @Override
    public Page<Map<String, Object>> getGkAccountVcodeByCondition(String accountId,
                                                                  int pageNum,
                                                                  int pageSize) {
        Page<Map<String, Object>> result = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> {
            gkAccountVcodeEntityMapper.selectGkAccountVcodeByCondition(accountId);
        });
        return result;
    }
}