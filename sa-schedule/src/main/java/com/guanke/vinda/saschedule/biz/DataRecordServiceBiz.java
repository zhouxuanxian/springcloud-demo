package com.guanke.vinda.saschedule.biz;

import com.guanke.vinda.samodels.model.entity.SaWxMessageEntity;
import com.guanke.vinda.samodels.model.utils.StringUtil;
import com.guanke.vinda.saschedule.service.SyncLogService;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 单独创建一个保存service的目的是保证事务隔离
 */
public interface DataRecordServiceBiz {
    /**
     * 微信消息发送和回写
     * @param saWxMessageEntity
     * @param model
     * @param method
     * @return
     */
    Map<String, Object> wxMsgSendAndUpd(SaWxMessageEntity saWxMessageEntity, String model, String method);

    /**
     * 创建日报的提醒消息
     * @param map
     * @param model
     * @param method
     * @return
     */
    Map<String, Object> createWxMessageByDailyPostn(Map<String, Object> map,String model,String method);

    /**
     * 创建未添加拜访计划的提醒消息
     * @param map
     * @param model
     * @param method
     * @return
     */
    Map<String, Object> createWxMessageByNoVisitPostn(Map<String, Object> map,String model,String method);

    }
