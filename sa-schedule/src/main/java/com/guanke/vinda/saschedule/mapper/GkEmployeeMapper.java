package com.guanke.vinda.saschedule.mapper;
import com.guanke.vinda.samodels.model.entity.GkEmployeeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface GkEmployeeMapper {

    /**
     *
     * @param login
     * @return
     */
    int countDutyByLogin (@Param("login")String login);

    /**
     * 查询没有提交日报的员工列表
     * @param today
     * @return
     */

    List<Map<String, Object>> getEmpNoListOfNoSubmitDaily(Date today);

    /**
     *查询需要提醒的KA费用列表
     */
    List<Map<String, Object>> queryKaCostVerify();

    /**
     *
     * @param today
     * @return
     */


    List<Map<String, Object>> getEmpNoListOfNoVisit(Date today);



}