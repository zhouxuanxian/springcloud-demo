package com.guanke.vinda.safunc.biz.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guanke.vinda.safunc.biz.SaleReportBiz;
import com.guanke.vinda.safunc.mapper.GkAccountSalesReportEntityMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Calendar.MONTH;
import static java.util.Calendar.getInstance;

/**
 * 销量提报 查询
 *
 * @author ldm
 */
@Service
public class SaleReportBizImpl implements SaleReportBiz {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleReportBizImpl.class);

    private final GkAccountSalesReportEntityMapper gkAccountSalesReportEntityMapper;

    public SaleReportBizImpl(GkAccountSalesReportEntityMapper gkAccountSalesReportEntityMapper) {
        this.gkAccountSalesReportEntityMapper = gkAccountSalesReportEntityMapper;
    }


    /**
     * @param date       日期
     * @param status     提报状态
     * @param keyword
     * @param positionId 职位id
     * @param empId      人员id
     * @param pageNum    页码
     * @param pageSize   页大小
     * @return
     */
    @Override
    public Page<Map<String, Object>> getSaleReportLists(String date, String status, String keyword, String positionId,
                                                        String empId, int pageNum, int pageSize) {
        Page<Map<String, Object>> result = null;
        //保留字段
        String parentId = null;
        Map<String, Object> wxMap = gkAccountSalesReportEntityMapper.getWxType(positionId, null);
        String wxType = (String) wxMap.get("WxType");
        String orgId = (String) wxMap.get("orgId");

        if (1 == pageNum && !this.isNone(keyword) && !this.isNone(status) && !this.isNone(parentId)) {
            this.insertCrmData(date);
        }
        if (!this.isNone(date)) {
            return null;
        }

        //业务员 和 业务主管
        if (wxType.contains("Store Clerk") || wxType.contains("Director")) {
            result = PageHelper.startPage(pageNum, pageSize).doSelectPage(() ->
                    gkAccountSalesReportEntityMapper.selectSaleReportListsByPosId(date, status, keyword, positionId, empId, parentId));
        } else {
            result = PageHelper.startPage(pageNum, pageSize).doSelectPage(() ->
                    gkAccountSalesReportEntityMapper.selectSaleReportListsByOrgId(date, status, keyword, orgId, empId, parentId));
        }
        return result;

    }


    /**
     * @param shopIntId 店铺intid
     * @return
     */
    @Override
    public List<Map<String, Object>> getAccountSalesReportCounts(String shopIntId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String startDate = sdf.format(new Date());
        String endDate = sdf.format(this.stepMonth(new Date(), -6));
        List list = gkAccountSalesReportEntityMapper.getSalesReportCount(shopIntId, startDate, endDate);
        return list;
    }
    /**
     * @param shopIntId 店铺intid
     * @return
     */
    @Override
    public Map<String, Object> getShopName(String shopIntId) {
        List<Map<String, Object>> list = gkAccountSalesReportEntityMapper.getShopName(shopIntId);
        Map<String, Object> map = new HashMap();
        if(list!=null&&list.size()>0){
            map = list.get(0);
        }
        return map;
    }

    /**
     *
     * @param shopIntId 店铺intid
     * @param date 年月
     * @return
     */
    @Override
    public Map<String, Object> getAccountSalesReportCountDetail(String shopIntId,String date) {
        //获取上个月的年月
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        try {
            c.setTime(format.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String lastDate = format.format(m);

        List<Map<String, Object>> list = gkAccountSalesReportEntityMapper.getSalesReportCountDetail(shopIntId, date,lastDate);
        Double all = 0.0;
        for(Map<String,Object> map : list){
            Double quality = Double.valueOf(map.get("quantity").toString());
            all = all + quality;
        }

        for (Map<String, Object> map : list) {
            Double quality = Double.valueOf(map.get("quantity").toString());
            if(all==0.0) {
                map.put("AccFor",0);
            }else {
                map.put("AccFor", Math.round(quality / all * 1000) / 10);
            }
        }
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("all", all);
        reMap.put("list", list);
        return reMap;
    }

    public Date stepMonth(Date sourceDate, int month) {
        Calendar c = getInstance();
        c.setTime(sourceDate);
        c.add(MONTH, month);
        return c.getTime();
    }

    /**
     * @param positionId    职位id
     * @param empId         人员id
     * @param date          日期
     * @param shopId        店铺id
     * @param salesReportId 销量提报id
     * @return 销量提报品牌列表
     */
    @Override
    public Map<String, Object> getSaleReportBrands(String positionId, String empId, String date, String shopId, String salesReportId) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<Map<String, Object>> list = gkAccountSalesReportEntityMapper.getAccountSalesReportByShopId(shopId, empId, positionId, salesReportId);
        returnMap.put("accountSalesReport", list.get(0));
        returnMap.put("salesReportId", salesReportId);
        returnMap.put("date", date);

        String intId = (String) list.get(0).get("intId");

        // 获取crm数据
        List<Map<String, Object>> crmList = gkAccountSalesReportEntityMapper.getCrmSalesReport(intId, date);
        // 获取品牌
        List<Map<String, Object>> brandList = gkAccountSalesReportEntityMapper.getBrandSalesReport();
        //获取销量提报信息
        Map<String, Object> as = gkAccountSalesReportEntityMapper.selectByIdAndTime(shopId, date);

        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < brandList.size(); i++) {
            for (int j = 0; j < crmList.size(); j++) {
                if (String.valueOf(brandList.get(i).get("name"))
                        .equals(String.valueOf(crmList.get(j).get("BRAND")))) {
                    map = brandList.get(i);
                    map.put("quantity", String.valueOf(crmList.get(j).get("quantity")));
                    map.put("ROW_ID", intId);

                    //处理维达 子品牌问题
                    if (!"".equals(crmList.get(j).get("COMMENTS")) && !"undefined".equals(crmList.get(j).get("COMMENTS")) && crmList.get(j).get("COMMENTS") != null) {
                        String comment = (String) crmList.get(j).get("COMMENTS");
                        String[] vinda = comment.split(";");
                        List subList = new ArrayList<>();
                        for (int k = 0; k < vinda.length; k++) {
                            if (vinda[k] != null && !"".equals(vinda[k])) {
                                Map<String, Object> subMap = new HashMap<>();
                                String[] dry = vinda[k].split(",");
                                Double num = 0.0;
                                try {
                                    num = Double.valueOf(dry[1]);
                                } catch (Exception e) {
                                }
                                subMap.put("name",dry[0]);
                                subMap.put("quantity",num);
                                //因为没有维达子品牌的品牌映射，则先写死；
                                if("vindaDry".equals(dry[0])) {
                                    subMap.put("val", "维达干巾");
                                }
                                if("vindaWet".equals(dry[0])) {
                                    subMap.put("val", "维达湿巾");
                                }
                                subList.add(subMap);
                            }
                        }
                        map.put("subList", subList );
                    }
                } else {
                    map = brandList.get(i);
                    map.put("ROW_ID", intId);
                }
                continue;
            }
        }

        if (as != null) {
            returnMap.put("reportStatus", "已提报");
        } else {
            returnMap.put("reportStatus", "未提报");
        }

        returnMap.put("brandList", brandList);

        //仅仅华中GT才显示 维达子品牌
        Map<String, Object> pMap = gkAccountSalesReportEntityMapper.selectVindaBrandShow(positionId);

        String wxType = (String) pMap.get("wxType");
        //只限于华中区域，GT人员
        if (wxType.contains("GT") && pMap.containsValue("华中区域")) {
            //可见 维达子品牌
            returnMap.put("VindaFlag", "1");
        } else {
            returnMap.put("VindaFlag", "0");
        }
        return returnMap;
    }

    private boolean isNone(String str) {
        if (StringUtils.isNotBlank(str) && !"none".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * crm销量提报可以导入数据导致销售助手的销量提报没有提交也存在数据
     * 此方法用来查询crm有导入数据自动向销量提报表插入数据
     *
     * @param date
     */
    private void insertCrmData(String date) {
        int count = gkAccountSalesReportEntityMapper.insertSaleReport(date);
        System.out.println("自动向销量提报表插入数据" + count + "条 date : " + new Date());
    }

}
