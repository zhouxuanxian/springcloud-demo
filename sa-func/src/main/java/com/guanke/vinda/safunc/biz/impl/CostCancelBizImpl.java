package com.guanke.vinda.safunc.biz.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guanke.vinda.safunc.biz.CostCancelBiz;
import com.guanke.vinda.safunc.config.ParamsConfig;
import com.guanke.vinda.safunc.feign.GeneralFeignService;
import com.guanke.vinda.safunc.mapper.CxGtReimEntityMapper;
import com.guanke.vinda.safunc.mapper.GkCostGtPhotoEntityMapper;
import com.guanke.vinda.safunc.mapper.GkGtReimTempEntityMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GT费用 查询
 *
 * @author ldm
 */
@Service
public class CostCancelBizImpl implements CostCancelBiz {

    private static final Logger LOGGER = LoggerFactory.getLogger(CostCancelBizImpl.class);

    private final GeneralFeignService generalFeignService;

    private final CxGtReimEntityMapper cxGtReimEntityMapper;
    private final GkCostGtPhotoEntityMapper gkCostGtPhotoEntityMapper;
    private final GkGtReimTempEntityMapper gkGtReimTempEntityMapper;
    private final ParamsConfig paramsConfig;

    public CostCancelBizImpl(CxGtReimEntityMapper cxGtReimEntityMapper,
                             GkCostGtPhotoEntityMapper gkCostGtPhotoEntityMapper,
                             GkGtReimTempEntityMapper gkGtReimTempEntityMapper,
                             GeneralFeignService generalFeignService,
                             ParamsConfig paramsConfig) {
        this.cxGtReimEntityMapper = cxGtReimEntityMapper;
        this.gkCostGtPhotoEntityMapper = gkCostGtPhotoEntityMapper;
        this.gkGtReimTempEntityMapper = gkGtReimTempEntityMapper;
        this.generalFeignService = generalFeignService;
        this.paramsConfig = paramsConfig;
    }

    /**
     * 通过职位ID，员工ID和筛选条件获取该员工名下的所有GT費用核销，返回分页结果
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @param pageNum    页码
     * @param pageSize   页容量
     * @return 符合条件的所有GT費用核销的分页结果
     */
    @Override
    public Page<Map<String, Object>> getNoDoneCostLists(String keyWord,
                                                        String positionId,
                                                        String empId,
                                                        int pageNum,
                                                        int pageSize) {
        Map<String, Object> wxMap = cxGtReimEntityMapper.getWxType(positionId, null);
        String wxType = (String) wxMap.get("WxType");
        return PageHelper.startPage(pageNum, pageSize).doSelectPage(
                () -> cxGtReimEntityMapper.selectNoDoneCostListByPositionAndEmpIdSortByUpdTime(null, null,
                        positionId, empId, wxType, keyWord));
    }

    /**
     * 通过职位查询 更多 GT数据，按条件筛选
     *
     * @param approvalStatus 审批状态
     * @param reimStatus     检核状态
     * @param keyword        关键字
     * @param positionId     职位id
     * @param empId          人员id
     * @param pageNum        页码
     * @param pageSize       页数
     * @return 对象
     */
    @Override
    public Page<Map<String, Object>> getMoreCostLists(String approvalStatus,
                                                      String reimStatus,
                                                      String keyword,
                                                      String positionId,
                                                      String empId,
                                                      int pageNum,
                                                      int pageSize) {
        Map<String, Object> wxMap = cxGtReimEntityMapper.getWxType(positionId, null);
        String wxType = (String) wxMap.get("WxType");
        return PageHelper.startPage(pageNum, pageSize).doSelectPage(
                () -> cxGtReimEntityMapper.selectMoreCostListByPositionAndEmpIdSortByUpdTime(
                        positionId, empId, wxType, keyword, approvalStatus, reimStatus));
    }

    /**
     * 按reimId查询详情信息
     *
     * @param positionId 职位id
     * @param empId      人员id
     * @param reimId     GT费用核销id
     * @return 返回该核销的详情
     */
    @Override
    public Map<String, Object> getCostDetailsBiz(String positionId, String empId, String reimId) {

        //店铺信息
        Map<String, Object> map = cxGtReimEntityMapper.getCostCancelGTById(reimId);
        map.put("ShopLevel", setTrans(map.get("ShopLevel")));
        //核销任务信息 主表信息
        Map<String, Object> costCancel = cxGtReimEntityMapper.selectAllByRowIdReturnMap(reimId);
        //审批历史
        List<Map<String, Object>> audit = cxGtReimEntityMapper.selectAuditListByRowId(reimId);
        //检核图片
        List<Map<String, Object>> pict = gkCostGtPhotoEntityMapper.selectByRowId(reimId);
        //去掉后面的零
//        cutTofix(visit);
        map.putAll(costCancel);
        admapToList(audit, map);
        map.put("auditList", audit);
        map.put("pictList", pict);
        map.put("prefix", paramsConfig.getPhotoPrefix());

        String[] adr = ((String) map.get("locationAddr")).split(",");
        map.put("locationAddr1", adr[0]);
        if (adr.length > 1) {
            map.put("locationAddr2", adr[1]);
        }

        return map;
    }

    /**
     * 查询 历史审批人列表
     *
     * @param auditList 审批历史列表
     * @param map1      返回的map
     * @return 审批列表
     */
    private void admapToList(List<Map<String, Object>> auditList, Map<String, Object> map1) {
        for (Map<String, Object> map : auditList) {
            String result = (String) map.get("Appro_Remark");
            map.put("status", showReject(result, map1).get("status"));
            map.put("rejectReson", showReject(result, map1).get("rejectReson"));
        }
    }

    /**
     * 切割驳回状态 和 原因
     *
     * @param result 审批结果
     * @param map    审批列表元素
     * @return 返回状态和原因
     */
    private Map<String, String> showReject(String result, Map<String, Object> map) {
        //已驳回+驳回原因
        Map<String, String> mapr = new HashMap<String, String>();
        if (result.indexOf("审批同意") == 0) {
            mapr.put("status", result.substring(0, 4));
            mapr.put("rejectReson", result.substring(5));
        } else if (result.length() > 4) {
//			map.put("rejectReson", result.substring(4,result.length())); //这是数组；
            mapr.put("status", result.substring(0, 3));
            mapr.put("rejectReson", result.substring(4));
        } else if (result.indexOf("发起申请") == 0 || result.indexOf("重新申请") == 0) {
            mapr.put("status", result);
            mapr.put("rejectReson", "无");
        } else {
            mapr.put("status", result.substring(0, 3));
            mapr.put("rejectReson", "无");
        }
        return mapr;
    }

    /**
     * 翻译店铺类型
     *
     * @param obj 翻译key
     * @return 翻译的value
     */
    private String setTrans(Object obj) {
        String o = "";
        switch (obj.toString()) {
            case "Non Top 50":
                o = "非Top50门店";
                break;
            case "Top 50":
                o = "Top50门店";
                break;
            case "Core Store":
                o = "核心店";
                break;
            case "Flagship Store":
                o = "旗舰店";
                break;
            case "General Store":
                o = "普通店";
                break;
            case "Standard Store":
                o = "标准店";
                break;
            default:
                break;
        }
        return o;
    }

    /**
     * 获取GT临时表数据 用于回显
     *
     * @param positionId 职位id
     * @param empId      人员id
     * @param reimId     核销id
     * @return 返回核销详情信息
     */
    @Override
    public Map<String, Object> getCostTempDatas(String positionId, String empId, String reimId) {
        //店铺信息
        Map<String, Object> map = cxGtReimEntityMapper.getCostCancelGTById(reimId);
        //GT临时核销数据
        Map<String, Object> map1 = gkGtReimTempEntityMapper.selectTempListByRowId(reimId);
        //兼容销售助手的表，把分隔符替换用于回显；
        if (map1 != null) {
            replaceSymbol(map1);
            //核销图片
            List<Map<String, Object>> pict = gkCostGtPhotoEntityMapper.selectPhotoByRowId(reimId);
            map.putAll(map1);
            map.put("photoList", pict);
            map.put("prefix", paramsConfig.getPhotoPrefix());
        }
        return map;
    }

    private void replaceSymbol(Map<String, Object> map) {
        Map<String, Object> map1 = new HashMap<>(map);
        map1.forEach((key, value) -> {
            if (key.contains("Str")) {
                String keys = key.substring(0, key.length() - 3);

                if (value!=null && !"".equals(value) && !"||".equals(value)) {
                    String val = ((String) value).replace("||", ",");
                    map.remove(key);
                    map.put(keys, val.substring(0, val.length() - 1));
                } else if (value==null||"".equals(value) || "||".equals(value)) {
                    map.remove(key);
                }
            }
//            System.out.println(key + ":" + value);
        });
    }

    /**
     * GT核销费用照片上传 到七牛云
     *
     * @param serverId  微信本地路径
     * @param storeName 店铺名称
     * @return 返回图片key
     */
    @Override
    public Map<String, String> costUpPhoto(String serverId, String storeName) {
        //重命名照片
        String type = "检核照片";
        SimpleDateFormat sft = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String rename = storeName + "_" + type + "_" + sft.format(new Date());

        Map<String, String> result = new HashMap<>();
        String qnKey = String.valueOf(generalFeignService.upLoadToQiNiuYun(serverId, rename).getData());
        if (StringUtils.isEmpty(qnKey) || "null".equals(qnKey)) {
            return null;
        }
        result.put("photoKey", qnKey);
        return result;
    }
}
