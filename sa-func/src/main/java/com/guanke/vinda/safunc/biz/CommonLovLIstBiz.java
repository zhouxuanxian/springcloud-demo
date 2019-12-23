package com.guanke.vinda.safunc.biz;

import java.util.List;
import java.util.Map;

/**
 * 通用选择列表接口类
 *
 * @author Nicemorning
 */
public interface CommonLovLIstBiz {
    /**
     * 通过指定的Type获取相关选择列表
     *
     * @param type 类型
     * @return 返回该类型下的选择列表
     */
    List<Map<String, Object>> getLovList(String type);
}
