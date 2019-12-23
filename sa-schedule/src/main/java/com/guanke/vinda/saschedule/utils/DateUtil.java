package com.guanke.vinda.saschedule.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Date Utility Class used to convert Strings to Dates and Timestamps
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by
 *         <a href="mailto:dan@getrolling.com">Dan Kibler </a> to correct time
 *         pattern. Minutes should be mm not MM (MM is month).
 */
public final class DateUtil {
    private static Log log = LogFactory.getLog(DateUtil.class);
    private static final String TIME_PATTERN = "HH:mm";
    private static final String defaultDatePattern = "yyyy-MM-dd";
    public static final String DATE_TIME_NO_SLASH = "yyyyMMddHHmmss";

    private DateUtil() {
    }
    
    public static String getWeekDay(Date date) {
    	final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五","星期六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);  
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
		if(dayOfWeek <0)dayOfWeek=0;
		return dayNames[dayOfWeek];
    }
    
    public static Long getCurrentTime(){
		return new Date().getTime();
	}

    public static String getDatePattern() {
        return defaultDatePattern;

    }

    public static String getDateTimePattern() {
        return DateUtil.getDatePattern() + " HH:mm:ss.S";
    }

    public static String getDate(Date aDate, String datePattern) {
        SimpleDateFormat df;

        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(datePattern);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }
    
    /**
	 * 获取当前日期
	 * 日期格式：yyyyMMdd
	 * @return
	 */
	public static String getYYYYMMDD() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}

    public static Date getDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date getDate(String dateStr, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return new Date();
        }
        return date;
    }

    public static Date getDate(Date day) {
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Integer getMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }


    public static Date convertStringToDate(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '"
                    + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            // log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }
    
    public static Date convertStringToDate(String aMask, String strDate, Locale language)
            throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask, language);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '"
                    + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            // log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    public static String getTimeNow(Date theTime) {
        return getDateTime(TIME_PATTERN, theTime);
    }

    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.warn("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    public static String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }


    public static String convertDateToString(Date aDate, String format) {
        return getDateTime(format, aDate);
    }


    public static Date convertStringToDate(final String strDate)
            throws ParseException {
        return convertStringToDate(getDatePattern(), strDate);
    }

    /**
     * 不含时分秒
     *
     * @param datetime
     * @return
     */
    public static Date convertLongToSimpleDate(long datetime) {

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(datetime);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 包含时分秒
     *
     * @param datetime
     * @return
     */
    public static Date convertLongToDate(long datetime) {

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(datetime);
        return c.getTime();
    }

    /**
     * 得到 某个日期 相隔多少天的日期
     *
     * @param baseDate 基准日期
     * @param days     天数
     * @return
     */
    public static Date addDays(Date baseDate, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(baseDate);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    /**
     * 得到 某个日期 相隔多少月的日期
     *
     * @param baseDate 基准日期
     * @return
     */
    public static Date addMonths(Date baseDate, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(baseDate);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }

    /**
     * 去掉时分秒
     *
     * @return
     * @throws ParseException
     */
    public static Date subDate(Date input) {
        Calendar c = Calendar.getInstance();
        c.setTime(input);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    /**
     * 获取当月第一天
     *
     * @return
     * @throws ParseException
     */
    public static Date getFirstDay() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH,1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取某个月第一天
     *
     * @return
     * @throws ParseException
     */
    public static Date getFirstDay(Integer year, Integer month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取当月结束时间
     *
     * @return
     * @throws ParseException
     */
    public static Date getEndDay() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
        return c.getTime();
    }


    public static Calendar convertDateToCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }


    public static String format(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat(getDatePattern());
        return sf.format(date);
    }

    public static Long convertTime(Date date, int start) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        if (start % 2 == 0) {
            c.set(Calendar.HOUR_OF_DAY, start / 2);
        } else {
            c.set(Calendar.HOUR_OF_DAY, start / 2);
            c.set(Calendar.MINUTE, 30);
        }
        return c.getTimeInMillis();
    }

    /**
     * 获取时间索引
     *
     * @return
     */
    public static int getTimeIndex(Long time) {
        Date date = DateUtil.convertLongToSimpleDate(time);
        int startIndex = (int) ((time - date.getTime()) * 2 / 3600000);
        if (startIndex < 0 || startIndex > 48)
            new RuntimeException("开始时间不合法");
        return startIndex;
    }

    /**
     * 昨日凌晨不含时分秒
     *
     * @return
     */
    public static Date getLastDay() {
        Date today = getDate();
        return addDays(today, -1);
    }


    /***
     * 比较2个时间之间间隔的天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer getDateSpace(Date startDate, Date endDate) {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();

        calst.setTime(startDate);
        caled.setTime(endDate);

        //设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        //得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;
        return days;
    }

    /*
     * md-datepicker：
     */
    public static Date utcStrToDate(String utcDate) throws ParseException {
    	utcDate = utcDate.replace("Z", " UTC");
		//System.out.println(utcDate);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		Date d = format.parse(utcDate);
		return d;
    }
    
    public static String getDateStrAfterAddDays(String baseDateStr, int days) throws ParseException {
    	Date baseDate = utcStrToDate(baseDateStr);
    	Date resultDate = addDays(baseDate, days);
    	
    	return convertDateToString(resultDate);
    }
    
    public static void main(String[] args) {
		
    	
    	System.out.println(getDate(new Date(), DATE_TIME_NO_SLASH));
	}
}
