package com.guanke.vinda.sasync.biz.impl;

import com.guanke.vinda.samodels.model.entity.GkAccountSalesReportEntity;
import com.guanke.vinda.samodels.model.utils.StringUtil;
import com.guanke.vinda.sasync.biz.SaleReportSaveBiz;
import com.guanke.vinda.sasync.mapper.GkAccountSalesReportEntityMapper;
import com.guanke.vinda.sasync.siebel.core.WebServiceRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 销量提报提交 业务类
 *
 * @author ldm
 */
@Service
public class SaleReportSaveBizImpl implements SaleReportSaveBiz {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleReportSaveBizImpl.class);

    private static final String NULLSTRING = "null";

    private final WebServiceRequest webServiceRequest;

    private final GkAccountSalesReportEntityMapper gkAccountSalesReportEntityMapper;

    public SaleReportSaveBizImpl(WebServiceRequest webServiceRequest,
                                 GkAccountSalesReportEntityMapper gkAccountSalesReportEntityMapper) {
        this.webServiceRequest = webServiceRequest;
        this.gkAccountSalesReportEntityMapper = gkAccountSalesReportEntityMapper;
    }


    /**
     * 销量提报 提交
     *
     * @param empId      用户id
     * @param positionId 职位id
     * @param params     提报信息
     * @return
     */
    @Override
    public boolean saveSaleReport(String empId, String positionId, String shopId, String intId, String date, Map<String, Object> params) {

        boolean ret = false;
        String reportMonth = date;

        GkAccountSalesReportEntity as = null;

        try {
            as = gkAccountSalesReportEntityMapper.selectByIdAndTime(shopId, date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            if (as == null) {
                as = new GkAccountSalesReportEntity();
                as.setStatus("已提报");
                as.setAccountId(shopId);
                as.setEmpId(empId);
                as.setPositionId(positionId);
                as.setSubmitTime(sdf.parse(date));
                int a = gkAccountSalesReportEntityMapper.insertSelective(as);
            }
            List<Map<String, Object>> barndList = (List<Map<String, Object>>) params.get("brandList");
            Map<String, Object> barndNameMap = new HashMap<String, Object>();

            StringBuffer barndNameSplice = new StringBuffer("");
            StringBuffer quantitySplice = new StringBuffer("");
            StringBuffer vindaSubBrand = new StringBuffer(";");

            for (int i = 0; i < barndList.size(); i++) {
                Map map = barndList.get(i);
                Object ob = map.get("quantity");
                boolean goFlag = null == ob || "".equals(ob)
                        || (ob != null && !"".equals(ob) && Double.valueOf(0).compareTo(Double.valueOf(String.valueOf(ob))) == 0);
                if (goFlag) {
                    continue;
                } else {
                    String brandName = (String)map.get("name");
                    try {
                        List<Map<String, Object>> subBrandList = (List) map.get("subList");
                        Double vinda = 0.0;
                        //维达子品牌另外处理,无子品牌老数据将按原来走；
                        boolean vindaFlag = "VINDA".equals(brandName) && null != subBrandList && subBrandList.size() > 0;
                        if (vindaFlag) {
                            for (int j = 0; j < subBrandList.size(); j++) {
                                Map<String, Object> subMap = subBrandList.get(j);
                                Object q = subMap.get("quantity");
                                if (q != null && !"".equals(q)) {
                                    vinda = vinda + Double.valueOf(String.valueOf(q));
                                    vindaSubBrand.append(subMap.get("name") + "," + String.valueOf(q) + ";");
                                }
                            }
                            barndNameSplice.append("VINDA");
                            quantitySplice.append(String.valueOf(vinda));
                            //有子品牌 就跳过维达这个品牌的处理
                            continue;
                        }
                    }catch (Exception e){
                        LOGGER.info("转换报错，没有维达子品牌，map.get(\"subBrand\")："+map.get("subBrand"));
                    }
                    if (StringUtils.isBlank(barndNameSplice.toString())) {
                        barndNameSplice.append(brandName);
                        quantitySplice.append(String.valueOf(ob));
                    } else {
                        barndNameSplice.append("|").append(brandName);
                        quantitySplice.append("|").append(String.valueOf(ob));
                    }
                }
            }

            if (StringUtils.isBlank(barndNameSplice.toString())) {
                throw new RuntimeException("门店销量提报品牌销量不能为空！");
            }
            String sDate = this.dateSplice(date);
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM-yyyy");
            String dateStr = sdf1.format(sdf.parse(reportMonth));
            String ok = this.saveSiebel(intId, barndNameSplice.toString(), sDate, quantitySplice.toString(), dateStr, vindaSubBrand.toString());
            if (!"ok".equals(ok)) {
                throw new RuntimeException("门店销量提报调用Siebe失败:" + ok);
            }
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            LOGGER.debug("-----门店销量提报保存失败-------");
            throw new RuntimeException(e.getMessage());
        }
        return ret;
    }

    private String dateSplice(String date) {
        date = date + "-15";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
        try {
            date = sdf1.format(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 上传crm attr01 对应 客户ID attr02 对应 品牌 attr03 对应 日期 attr04 对应 实际销售额
     */
    private String saveSiebel(String accountId, String brand, String date, String quantity, String reportMonth, String subBrand) {

        Map<String, Object> auditInput = new HashMap<>();
        auditInput.put("objectname", "CreateStock");
        auditInput.put("attr01", accountId);
        auditInput.put("attr02", brand);
        auditInput.put("attr03", date);
        auditInput.put("attr04", quantity);
        auditInput.put("attr05", reportMonth);
        auditInput.put("attr06", subBrand);
        String intId = "";

        boolean flag = false;
        String action = "\"document/http://siebel.com/CustomUI:VDWeChatDealerIntegrationWSInvokeMethod\"";
        String inName = "VDWeChatDealerIntegrationWSInvokeMethod_Input";
        String url = "http://crm-test.vinda.com:88/eai_chs/start.swe?SWEExtSource=WebService&SWEExtCmd=Execute&WSSOAP=1";

        String result = webServiceRequest.sendRequestToCrm(action, inName, url, auditInput);
        if (NULLSTRING.equals(result)) {
            LOGGER.info("Request to crm failed");
            return StringUtil.cleanSiebelError(result);
        }
        return "ok";

    }

    /**
     * 获取上个月
     *
     * @return 2019-12
     */
    public String getLastMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);
        return mon;
    }

}
