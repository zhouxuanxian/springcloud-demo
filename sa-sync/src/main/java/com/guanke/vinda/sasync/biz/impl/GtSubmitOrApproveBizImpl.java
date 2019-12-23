package com.guanke.vinda.sasync.biz.impl;

import com.guanke.vinda.samodels.model.entity.CxGtReimEntity;
import com.guanke.vinda.samodels.model.entity.GkCostGtPhotoEntity;
import com.guanke.vinda.samodels.model.entity.GkCostHistoryTemp;
import com.guanke.vinda.samodels.model.entity.GkGtReimTempEntity;
import com.guanke.vinda.sasync.biz.GtSubmitOrApproveBiz;
import com.guanke.vinda.sasync.config.ParamsConfig;
import com.guanke.vinda.sasync.mapper.CxGtReimEntityMapper;
import com.guanke.vinda.sasync.mapper.GkCostGtPhotoEntityMapper;
import com.guanke.vinda.sasync.mapper.GkCostHistoryTempMapper;
import com.guanke.vinda.sasync.mapper.GkGtReimTempEntityMapper;
import com.guanke.vinda.sasync.siebel.core.WebServiceRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *GT费用新增及审批业务类
 *
 * @author ldm
 */
@Service
public class GtSubmitOrApproveBizImpl implements GtSubmitOrApproveBiz {
    private static final String NULLSTRING = "null";
    private static final Logger LOGGER = LoggerFactory.getLogger(GtSubmitOrApproveBizImpl.class);

    private final WebServiceRequest webServiceRequest;
    private final ParamsConfig paramsConfig;
    private final CxGtReimEntityMapper cxGtReimEntityMapper;
    private final GkGtReimTempEntityMapper gkGtReimTempEntityMapper;
    private final GkCostGtPhotoEntityMapper gkCostGtPhotoEntityMapper;
    private final GkCostHistoryTempMapper gkCostHistoryTempMapper;

    public GtSubmitOrApproveBizImpl(WebServiceRequest webServiceRequest, CxGtReimEntityMapper cxGtReimEntityMapper, GkGtReimTempEntityMapper gkGtReimTempEntityMapper,
                                    GkCostGtPhotoEntityMapper gkCostGtPhotoEntityMapper, GkCostHistoryTempMapper gkCostHistoryTempMapper,ParamsConfig paramsConfig) {
        this.webServiceRequest = webServiceRequest;
        this.cxGtReimEntityMapper = cxGtReimEntityMapper;
        this.gkGtReimTempEntityMapper = gkGtReimTempEntityMapper;
        this.gkCostGtPhotoEntityMapper = gkCostGtPhotoEntityMapper;
        this.gkCostHistoryTempMapper = gkCostHistoryTempMapper;
        this.paramsConfig = paramsConfig;
    }

    /**
     * Gt费用 临时保存在数据库
     * @param empId 人员id
     * @param positionId 职位id
     * @param GtCost 报错的数据对象
     * @param saveFlag 是否保存
     * @return 保存成功的记录对象
     */
    @Override
    public CxGtReimEntity saveGtCost(String empId, String positionId, Map<String, Object> GtCost,String saveFlag) {

        String Row_Id = (String)GtCost.get("Row_Id");

        if (Row_Id == null || Row_Id.isEmpty()){
            LOGGER.info("Parameter Error,there is no Row_Id");
            return null;
        }
        //兼容销售助手的表，把分隔符替换用于回显；
        replaceSymbol(GtCost);
        CxGtReimEntity gt = cxGtReimEntityMapper.selectByRowId(Row_Id);
        String AppStat = (String) GtCost.get("Approval_Status");
        String ReimStat = (String) GtCost.get("Reim_Status");
        if(!gt.getApproStatus().equals(AppStat) || !gt.getReimStatus().equals(ReimStat) || (!"新建".equals(AppStat)&&!AppStat.contains("驳回")) ) { //状态判断
//            return result.fail("状态异常，该核销任务已被处理!");
            LOGGER.info("Status error,this task may be done.");
            return null;
        }

        GkGtReimTempEntity vs = gkGtReimTempEntityMapper.selectByReimId(Row_Id);
        String saveNew = "0";
        if(vs==null) {
            saveNew = "1";
            vs = new GkGtReimTempEntity();
        }
        try {
            vs.setReimid((String) GtCost.get("Row_Id"));
            vs.setStoreType((String) GtCost.get("Store_Type"));

            vs.setPositionInfo((String) GtCost.get("locationAddr")); //定位
            vs.setDryShelfNumberstr((String) GtCost.get("Dry_Shelf_NumberStr"));
            vs.setDryVindaShelfNumberstr((String) GtCost.get("Dry_Vinda_Shelf_NumberStr"));
            vs.setWetShelfNumberstr((String) GtCost.get("Wet_Shelf_NumberStr"));
            vs.setWetVindaShelfNumberstr((String) GtCost.get("Wet_Vinda_Shelf_NumberStr"));
            vs.setTamponShelfNumberstr((String) GtCost.get("Tampon_Shelf_NumberStr"));
            vs.setTamponVindaShelfNumberstr((String) GtCost.get("Tampon_Vinda_Shelf_NumberStr"));
            vs.setAdultShelfNumberstr((String) GtCost.get("Adult_Shelf_NumberStr"));
            vs.setAdultVindaShelfNumberstr((String) GtCost.get("Adult_Vinda_Shelf_NumberStr"));
            vs.setTgCheckNumberstr((String) GtCost.get("TG_Check_NumberStr"));
            vs.setDjCheckNumberstr((String) GtCost.get("DJ_Check_NumberStr"));

            vs.setDryVindaShelfPosition((String) GtCost.get("Dry_Vinda_Shelf_Position"));
            vs.setWetVindaShelfPosition((String) GtCost.get("Wet_Vinda_Shelf_Position"));
            vs.setTamponVindaShelfPosition((String) GtCost.get("Tampon_Vinda_Shelf_Position"));
            vs.setAdultVindaShelfPosition((String) GtCost.get("Adult_Vinda_Shelf_Position"));
            vs.setTgPosition((String) GtCost.get("TG_Position"));
            vs.setDjListPosition((String) GtCost.get("DJ_List_Position"));
            vs.setCheckProblem((String) GtCost.get("Check_Problem"));
            vs.setGroundingTypestr((String) GtCost.get("Grounding_TypeStr"));
            vs.setShelfCheckNumberstr((String) GtCost.get("Shelf_Check_NumberStr"));
            vs.setShelfPosition((String)GtCost.get("Shelf_Position"));
            vs.setStoreeq((String)GtCost.get("StoreEq"));
            vs.setSalesPredict((String) GtCost.get("Sales_Predict"));
            vs.setApplyPosId((String)cxGtReimEntityMapper.getWxType(positionId,null).get("intId"));
            // 臨時保存
            if("1".equals(saveNew)) { //新建插入
                gkGtReimTempEntityMapper.insertSelective(vs);
            }else{
                gkGtReimTempEntityMapper.updateByPrimaryKeySelective(vs);
            }
            //保存图片

            List<Map<String, String>> otherShow_PicList= (List<Map<String, String>>) GtCost.get("otherShow_PicList");
            toSavePhoto(otherShow_PicList,Row_Id,"otherShow_Pic");

            //更改主表状态,确定检核人：
            if("save".equals(saveFlag)) {
                if (gt==null) {
                    LOGGER.info("Parameter Error,there is no Row_Id");
                    return null;
                }
                Date date = new Date();
                if(gt.getApproStatus().indexOf("新建")==-1) {
                    date = gt.getReimTime();
                } else {
                    gt.setReimStatus("草稿");
                }
//				gt.setApply_name(getUserById(empId).getName());
                gt.setApplyNum((String)cxGtReimEntityMapper.selectEmployeeById(empId).get("login"));
                gt.setApplyPosId((String)(cxGtReimEntityMapper.getWxType(positionId,null).get("intId")));
                gt.setReimTime(date);
                cxGtReimEntityMapper.updateByRowId(gt,Row_Id);
            }


        }catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(gt.getRowId()+"GT task save error, try later.");
            return null;
        }
        return gt;

    }

    /**
     * GT费用提交到crm并保存
     * @param empId 人员id
     * @param positionId 职位id
     * @param GtCost 提交的数据map
     * @return 提交成功的对象
     */
    @Override
    public CxGtReimEntity submitCostCancel(String empId, String positionId, Map<String, Object> GtCost) {

        String row_Id = (String)GtCost.get("Row_Id");
        //存临时表
        saveGtCost(empId,positionId,GtCost,"submit");

        CxGtReimEntity result = cxGtReimEntityMapper.selectByRowId(row_Id);
        String AppStat = (String) GtCost.get("Approval_Status");
        boolean showError = !result.getApproStatus().equals(AppStat) || (!"新建".equals(AppStat)&&!AppStat.contains("驳回"));
        if(showError) {
            LOGGER.info("status error, this GT task be done. ");
            return null;
        }

        if (row_Id == null || row_Id.isEmpty() ){
            LOGGER.info("Parameter Error,there is no row_Id");
            return null;
        }
        String intId ="";

        //判断检查 核销状态
        cupReim_Status(GtCost);
        //判断检查 审批状态,若无内部业务员则 市办审批中
        String approStatus = cheackStatus(positionId,row_Id);

        if (approStatus == null){
            LOGGER.info("there is no superior man, please connect The administrator.");
            return null;
        }

        GtCost.put("Approval_Status", approStatus);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        String GTCode ="";
        if(result.getApproStatus().indexOf("新建")==-1) {
            GTCode = result.getGtExpVerfCode();
        }else {
            //获取GT费用核销编码
            GTCode = getGTCode();
        }
        Map <String,Object> empMap = cxGtReimEntityMapper.selectEmployeeById(empId);
        String empName = (String) empMap.get("name");
        String Login = (String) empMap.get("login");

        result.setApplyNum(Login);
        result.setReimTime(date);

        GtCost.put("Reim_Time", sdf.format(date));

        String sa03 = sdf.format(date)+"|"+(String) GtCost.get("Reim_Status")+"|"+(String) GtCost.get("Diffrent_Contant")+"|"+(String) GtCost.get("Approval_Status")+"|"+(String) GtCost.get("Grounding_Type")+"|"+(String) GtCost.get("Dry_Shelf_Number")+"|"+(String) GtCost.get("Dry_Vinda_Shelf_Number")+"|"+(String) GtCost.get("Dry_Vinda_Shelf_Position")+"|"+(String) GtCost.get("Wet_Shelf_Number")+"|"+(String) GtCost.get("Wet_Vinda_Shelf_Number")+"|"+(String) GtCost.get("Wet_Vinda_Shelf_Position")+"|"+(String) GtCost.get("Tampon_Shelf_Number")+"|"+(String) GtCost.get("Tampon_Vinda_Shelf_Number")+"|"+(String) GtCost.get("Tampon_Vinda_Shelf_Position")+"|"+(String) GtCost.get("Adult_Shelf_Number")+"|"+(String) GtCost.get("Adult_Vinda_Shelf_Number")+"|"+(String) GtCost.get("Adult_Vinda_Shelf_Position")+"|"+(String) GtCost.get("TG_Check_Number")+"|"+(String) GtCost.get("TG_Position")+"|"+(String) GtCost.get("DJ_Check_Number")+"|"+(String) GtCost.get("DJ_List_Position")+"|"+(String) GtCost.get("Check_Problem")+"|"+result.getApplyNum()+"|"+(String) GtCost.get("Store_Type")
                +"|"+(String)GtCost.get("locationAddr")+"|"+GTCode+"|"+(String)GtCost.get("Diffrent_Type")+"|"+(String)GtCost.get("Shelf_Check_Number")+"|"+(String)GtCost.get("Shelf_Position")+"|"+(String)GtCost.get("Sales_Predict")+"|"+(String)GtCost.get("StoreEq")+"|"+(String)GtCost.get("isStoreEq") ;

        String approveDate = sdf.format(date);
        String emIntId = (String) empMap.get("intId");
        String posIntId = (String)cxGtReimEntityMapper.getWxType(positionId,null).get("intId");

        Map<String, Object> auditInput = new HashMap<String, Object>();
        auditInput.put("objectname","UpdateGTExpense");
        auditInput.put("attr01", row_Id);

        auditInput.put("attr02", (String)GtCost.get("Approval_Status"));
        auditInput.put("attr03", sa03.trim());
        if("新建".equals(AppStat)) {
            //审批人
            auditInput.put("attr04", emIntId+"|"+empName+"|"+approveDate+"|"+"发起申请"+"|"+posIntId);
        } else if (AppStat.contains("驳回")) {
            //审批人
            auditInput.put("attr04", emIntId+"|"+empName+"|"+approveDate+"|"+"重新申请"+"|"+posIntId);
        }

        //图片遍历拼接：
        List<Map<String, String>> otherShow_PicList= (List<Map<String, String>>) GtCost.get("otherShow_PicList");
//        String rootUrl=constantsnts.QINIU_DOMAIN_NAME;
        auditInput.put("attr08", linkedPhoto(otherShow_PicList,paramsConfig.getPhotoPrefix()));

        try{
            if(!sendToCrm(auditInput)){
                LOGGER.info("GT Cost cancel after verification ,send to crm is error. try again later.");
                return null;
            }else{
                intId = row_Id;
            }
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.info("GT submit error."+e.getMessage());
            return null;
        }

        //存数据库
        System.out.println("插入数据库：核销Id "+row_Id);
        result.setGroundingType((String)GtCost.get("Grounding_Type"));
        result.setDryShelfNum(toBigD(GtCost.get("Dry_Shelf_Number")));
        result.setDryVdShelfNum(toBigD(GtCost.get("Dry_Vinda_Shelf_Number")));
        result.setDryVdShelfPos((String)GtCost.get("Dry_Vinda_Shelf_Position"));

        result.setWetShelfNum(toBigD(GtCost.get("Wet_Shelf_Number")));
        result.setWetVdShelfNum(toBigD(GtCost.get("Wet_Vinda_Shelf_Number")));
        result.setWetVdShelfPos((String)GtCost.get("Wet_Vinda_Shelf_Position"));

        result.setTamponShelfNum(toBigD(GtCost.get("Tampon_Shelf_Number")));
        result.setTamponVdShelfNum(toBigD(GtCost.get("Tampon_Vinda_Shelf_Number")));
        result.setTamponVdShelfPos((String)GtCost.get("Tampon_Vinda_Shelf_Position"));

        result.setAduShelfNum(toBigD(GtCost.get("Adult_Shelf_Number")));
        result.setAduVdShelfNum(toBigD(GtCost.get("Adult_Vinda_Shelf_Number")));
        result.setAduVdShelfPos((String)GtCost.get("Adult_Vinda_Shelf_Position"));

        result.setApproStatus((String)GtCost.get("Approval_Status"));

        result.setTgCheckNum(toBigD(GtCost.get("TG_Check_Number")));
        result.setTgPos((String)GtCost.get("TG_Position"));
        result.setDjCheckNum(toBigD(GtCost.get("DJ_Check_Number")));
        result.setDjListPos((String)GtCost.get("DJ_List_Position"));
        result.setHjCheckNum(toBigD(GtCost.get("Shelf_Check_Number") ));
        result.setHjListPos((String)GtCost.get("Shelf_Position"));

        result.setReimStatus((String) GtCost.get("Reim_Status"));

        result.setCheckProblem((String)GtCost.get("Check_Problem"));
        result.setDiffContant((String)GtCost.get("Diffrent_Contant"));
        result.setDiffType((String)GtCost.get("Diffrent_Type"));
        result.setPositionInfo((String)GtCost.get("locationAddr") );
        result.setLastUpd(new Date());
        result.setGtExpVerfCode(GTCode);
        result.setApplyPosId(posIntId);
        result.setCheckStoreType((String)GtCost.get("StoreEq"));
        result.setStoreTypeFlg((String)GtCost.get("isStoreEq"));
        result.setLastMonSales((String)GtCost.get("Sales_Predict"));

        try {
            int i = cxGtReimEntityMapper.updateByRowId(result,row_Id);
        }catch(Exception e){
            System.err.println("cx_gt_reim表已同步完成！");
        }

        //备份数据实时更新列表
        GkCostHistoryTemp aproDt = new GkCostHistoryTemp();
        aproDt.setReimId(row_Id);
        aproDt.setApproDatetime(date);
        aproDt.setApproEmpId(emIntId);
        aproDt.setApproEmpPosId(posIntId);
        aproDt.setApproRemark("新建".equals(AppStat)?"发起申请":"重新申请");

        try {
            int temNum = gkCostHistoryTempMapper.insert(aproDt);
        }catch(Exception e){
            System.err.println("GK_COST_HISTORY_TEMP表已同步完成！");
        }
        //查找上级经理 发送微信消息 要去销售助手发

        return result;
    }

    private String cheackStatus(String positionId, String row_Id) {
        //门店的客户团队中找
        String status = "内部业务员审批中";
        List<Map<String, Object>> list = cxGtReimEntityMapper.checkSuperiorManByPosId("GT Store Clerk", row_Id);
        if(list==null||list.size()==0){
        list = cxGtReimEntityMapper.checkSuperiorManByPosId("GT Director", row_Id);
            status = "内部业务员审批中";
        }
        //检核人职位所在组织及父组织
        if(list==null||list.size()==0){
        list = cxGtReimEntityMapper.checkCityAndProvinceOffice(positionId, "GT Agency Manager");
            status = "市办审批中";
        }
        //门店的父客户（区域拆分客户）所在的管理团队
        if(list==null||list.size()==0){
        list = cxGtReimEntityMapper.checkPromoteByPosId(row_Id);
            status = "推广主管审批中";
        }
        //检核人职位所在组织及父组织
        if(list==null||list.size()==0){
        list = cxGtReimEntityMapper.checkCityAndProvinceOffice(positionId, "GT Province Manager");
            status = "省办审批中";
        }
        if(list==null||list.size()==0){
            status = null;
        }
        return status;
    }

    /**
     * 检查核销状态 及核销差异
     * @param visit 数据map
     */
    private void cupReim_Status(Map<String, Object> visit) {
        String rowId = (String) visit.get("Row_Id");
        StringBuffer different = new StringBuffer();

        CxGtReimEntity gtCost = cxGtReimEntityMapper.selectByRowId(rowId);

        Double TG = transToNum(gtCost.getTgSendNum()) - transToNum(visit.get("TG_Check_Number"));
        if(TG!=0) {
            String diff = "堆头（花车）检核数比提报数" + (TG>0 ? ("少"+String.format("%.1f",TG) +"节" ): ("多"+String.format("%.1f",Math.abs(TG))+"节") );
            different.append(diff+",");
        }
        Double DJ = transToNum(gtCost.getDjSendNum()) - transToNum(visit.get("DJ_Check_Number"));
        if (DJ != 0) {
            String diff = "端架检核数比提报数" + (DJ > 0 ? ("少" + String.format("%.1f", DJ) + "节")
                    : ("多" + String.format("%.1f", Math.abs(DJ)) + "节"));
            different.append(diff+",");
        }
        Double Shelf = transToNum(gtCost.getHjSendNum()) - transToNum(visit.get("Shelf_Check_Number"));
        if (Shelf != 0) {
            String diff = "货架检核数比提报数" + (Shelf > 0 ? ("少" + String.format("%.1f", Shelf) + "节")
                    : ("多" + String.format("%.1f", Math.abs(Shelf)) + "节"));
            different.append(diff+",");
        }
        Double all = TG + DJ + Shelf;
        StringBuffer difType = new StringBuffer();
        String stq = (String) visit.get("Store_Type");
        if(!stq.equals(visit.get("StoreEq"))) {
//			difType.append("门店类型不匹配，");
            visit.put("isStoreEq", "N");
        }else {
            visit.put("isStoreEq", "Y");
        }
        //提报 > 检核
        boolean state = true;
        if(all>0) {
            difType.append("多提报少投入");
        }else {
            if(TG>0 || DJ>0 || Shelf>0 ) {
                difType.append("陈列类型不符");
            } else {
                difType.append("无差异");
                state = false;
            }
        }
        visit.put("Diffrent_Type", difType.toString());

        visit.put("Diffrent_Contant", different.toString());
        if(!state) {
            visit.put("Reim_Status", "正常");
        } else {
            visit.put("Reim_Status", "异常");
        }
    }

    /**
     * 转 Double
     * @param obj 传入参数
     * @return double类型的值
     */
    private Double transToNum(Object obj) {
        Double num = 0.0;
        try{
            if (obj instanceof String) {
                String str = obj.toString();
                num = Double.valueOf(str);
            }else if (obj instanceof BigDecimal) {
                num = ((BigDecimal) obj).doubleValue();
            }
        }catch(Exception e ) {
            return num;
        }
        return num;
    }

    /**
     * 字符串转BigDecimal
     * @param obj 传入参数
     * @return DigDecimal的值
     */
    private BigDecimal toBigD(Object obj) {
        BigDecimal num = BigDecimal.ZERO;
        try{
            if (obj instanceof String) {
                Double str = Double.valueOf((obj.toString()));
                num = BigDecimal.valueOf(str);
            }else if (obj instanceof BigDecimal) {
                num = (BigDecimal) obj;
            }
        }catch(Exception e ) {
            return num;
        }
        return num;
    }

    /**
     * 获取Gt 核销编号
     * @return 核销编号
     */
    private String getGTCode() {
        String gtCode = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String dateStr = sdf.format(date)+"GT";
        String codStr = cxGtReimEntityMapper.getGtCode(dateStr);
        if(codStr==null || "".equals(codStr)) {
            gtCode = dateStr + "0001";
        }else {
            String codeStr = codStr;
            String nextStr = codeStr.split("GT")[1];
            Integer s = Integer.valueOf(nextStr)+1;
            String str = "0000"+s.toString();
            int start = str.length()-4;
            if(s>99999) {
                start = str.length()-6;
            } else if(s>9999) {
                start = str.length()-5;
            }
            gtCode = dateStr + str.substring(start,str.length());
        }
        return gtCode;
    }

    /**
     * 保存图片
     * @param picList 图片list
     * @param row_Id 核销id
     * @param type 图片类型
     */
    private void toSavePhoto(List<Map<String, String>> picList,String row_Id,String type) {

        //删除照片
        List<GkCostGtPhotoEntity> list = gkCostGtPhotoEntityMapper.selectByRowId(row_Id,type);
        for (int i = 0; i < list.size(); i++) {
            GkCostGtPhotoEntity oldPhoto = list.get(i);
            oldPhoto.setType("delete");
            int a = gkCostGtPhotoEntityMapper.updateByPrimaryKeySelective(oldPhoto);
        }
        if (picList!=null && picList.size() > 0) {
            //存储
            for (Map<String, String> dp : picList) {
                GkCostGtPhotoEntity newPhoto = new GkCostGtPhotoEntity();
                newPhoto.setRowId(row_Id);
                if(StringUtils.isBlank(dp.get("photoKey"))) {
                    continue;
                }
                newPhoto.setPhotoKey(dp.get("photoKey"));
                newPhoto.setType(type);
                gkCostGtPhotoEntityMapper.insert(newPhoto);
            }
        }
    }

    /**
     * 图片拼接
     * @param picList 图片list
     * @param rootUrl 根目录url
     * @return 拼接后的字符串
     */
    private String linkedPhoto(List<Map<String, String>> picList, String rootUrl) {
        String uri = "";
        if(picList!=null && picList.size()>0) {
            StringBuffer rUrl = new StringBuffer();
            for (Map<String, String> dp : picList) {
                String url = rootUrl+dp.get("photoKey")+","+dp.get("photoKey");
                rUrl.append(url+"|");
            }
            uri =rUrl.toString();
            if(!"".equals(rUrl.toString())) {
                uri = uri.substring(0,uri.length()-1);
            }
        }
        return uri;
    }

    /**
     * 兼容销售助手 用于 页面回显
     * @param map 替换了可以的map
     */
    private void replaceSymbol(Map<String,Object> map){
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.putAll(map);
        map1.forEach((key, value) -> {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            if(key.contains(",")||key.contains("Number")) {
                String ikey = key + "Str";
                String val = ((String) value).replace(",", "||") + "||";
                if (!"Grounding_Type".equals(key) && !"".equals(value)){
                    map.put(ikey, val);
                    String str = ((String) value).replace(",", "+");
                    Object result = "";
                    try {
                        result = engine.eval(str);
                    }catch (Exception e){
                        LOGGER.info("GT submit,string Add failure ,this string is :"+str);
                    }
                    map.put(key, result.toString());
                }
            }
            if("Grounding_Type".equals(key)){
                String val = ((String) value).replace(",", "||") + "||";
                map.put(key+"Str", val);
            }
            System.out.println(key + ":" + value);
        });
    }

    /**
     * 发送crm接口 传输GT
     * @param params GT费用
     * @return 如果成功返回true，反之返回false
     */
    public boolean sendToCrm(Map<String, Object> params) {
        boolean flag = false;
        String action = "\"document/http://siebel.com/CustomUI:VDWeChatIntegrationWSInvokeMethod\"";
        String inName = "VDWeChatIntegrationWSInvokeMethod_Input";
        String url = "http://10.100.240.16:88/eai_chs/start.swe?SWEExtSource=WebService&SWEExtCmd=Execute&WSSOAP=1";
        params.put("objectname", "UpdateGTExpense");

        String result = webServiceRequest.sendRequestToCrm(action, inName, url, params);
        if (!NULLSTRING.equals(result)) {
            flag = true;
        }
        return flag;
    }


}
