package com.guanke.vinda.safunc.biz.impl;

import com.guanke.vinda.safunc.biz.CommonLovLIstBiz;
import com.guanke.vinda.safunc.mapper.GkListOfValueEntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 通用选择列表类
 *
 * @author Nicemorning
 */
@Service
public class CommonLovListBizImpl implements CommonLovLIstBiz {
    private final GkListOfValueEntityMapper gkListOfValueEntityMapper;

    public CommonLovListBizImpl(GkListOfValueEntityMapper gkListOfValueEntityMapper) {
        this.gkListOfValueEntityMapper = gkListOfValueEntityMapper;
    }

    /**
     * 通过指定的Type获取相关选择列表
     *
     * @param type 类型
     * @return 返回该类型下的选择列表
     */
    @Override
    public List<Map<String, Object>> getLovList(String type) {
        return gkListOfValueEntityMapper.selectLovListByType(type);
    }
}
