package com.guanke.vinda.samodels.model.enums;

import lombok.Data;

/**
 * 图片类型
 *
 * @author Nicemorning
 */
@Data
public class PhotoTypeContent {
    public static final String VISIT_NONACCOUNT = "门店拜访_非拜访事项";
    public static final String VISIT_SHELF = "门店拜访_货架";
    public static final String VISIT_STACK = "门店拜访_地堆";
    public static final String VISIT_ENDFRAME = "门店拜访_端架";
    public static final String VISIT_PROMOTE = "门店拜访_促销";
    public static final String VISIT_EXPAND = "门店拜访_推广";
    public static final String COMPETING = "竞品";
    public static final String DAILY = "日报";
    public static final String COST_CHECK = "费用核销_检核";

    public PhotoTypeContent() {
    }
}
