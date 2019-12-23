package com.guanke.vinda.samodels.model.utils;

import java.math.BigDecimal;
import java.util.Random;

public final class StringUtil {

    /**
     * 校验字符串是否为空
     * @param str
     * @return 空true
     */
    public static boolean isNull(String str){
        if(null == str || str.length() <= 0){
            return true;
        }
        return false;
    }

    /**
     * 空转空串
     * @param str
     * @return
     */
    public static String null2EmptyString(String str){
        try {
            if(null == str || str.length() <= 0 || "null".equals(str)) {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
        return str;
    }

    /**
     * 空转空串-为九州通接口提供
     * @param str
     * @return
     */
    public static String null2EmptyStringSync(String str,String resultString){
        try {
            if(null == str || str.length() <= 0 || "null".equals(str.toLowerCase()) || "无".equals(str)) {
                return resultString;
            }
        } catch (Exception e) {
            return resultString;
        }
        return str;
    }

    /**
     * boolean 转 String
     * @return
     */
    public static String boo2Str(Boolean boo){
        String booStr = "N";
        if(boo != null && boo){
            booStr = "Y";
        }
        return booStr;
    }

    /**
     * String 转 boolean
     * @param str
     * @return
     */
    public static Boolean str2Boo(String str){
        Boolean boo = false;
        if(str != null && str.length() > 0 && (("1").equals(str) || ("Y").equals(str.toUpperCase()) || "true".equals(str.toLowerCase()))){
            boo = true;
        }
        return boo;
    }

    /**
     * int 转 String
     * @return
     */
    public static String int2String(int i) throws Exception{
        return String.valueOf(i);
    }

    /**
     * String 转 int
     * @param str
     * @return
     */
    public static int str2Int(String str) throws Exception{
        if(StringUtil.isNull(str))return 0;
        return Integer.parseInt(str);
    }

    /**
     * Object 转 int
     * @param obj
     * @return
     */
    public static int obj2Int(Object obj) {
        if(obj == null || obj.toString().trim().length() == 0) {
            return 0;
        }
        return Integer.parseInt(obj.toString());
    }

    /**
     * Object 转 int
     * @param obj
     * @return
     */
    public static Integer obj2IntIfNullReturnNull(Object obj) {
        if(obj == null || obj.toString().trim().length() == 0) {
            return null;
        }
        return Integer.parseInt(obj.toString());
    }

    /**
     * Object 转 BigDecimal
     * @param obj
     * @return
     */
    public static BigDecimal obj2BigDecimalWithNullVal(Object obj) {
        if(obj == null || obj.toString().trim().length() == 0) {
            return null;
        }
        return new BigDecimal(obj.toString());
    }

    /**
     * Object 转 BigDecimal
     * @return
     */
    public static BigDecimal Integer2BigDecimal(Integer integer) {
        if(integer == null) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(String.valueOf(integer.intValue()));
    }

    /**
     * Object 转 double
     * @param obj
     * @return
     */
    public static double obj2double(Object obj) {
        if(obj == null || obj.toString().trim().length() == 0) {
            return 0;
        }
        return Double.parseDouble(obj.toString());
    }

    /**
     * double 转 String
     * @return
     */
    public static String double2String(double d) throws Exception{
        return String.valueOf(d);
    }

    /**
     * String 转 double
     * @param str
     * @return
     */
    public static double str2Double(String str) throws Exception{
        return Double.valueOf(str);
    }

    public static String cleanSiebelError(String returnmsg) {
        if(returnmsg == null || "".equals(returnmsg)) {
            return returnmsg;
        }
        returnmsg = returnmsg.replace("Unhandled Exception:","");
        int i = returnmsg.indexOf("SBL-DAT");
        if(i >= 0){
            returnmsg = returnmsg.substring(0, i-1);
        }
        return returnmsg;
    }

    /**
     * 获取唯一NO.
     * Rover.chen
     */
    public static String generateUniqueNo() {
        Integer random = new Random(new Object().hashCode()).nextInt(8999) + 1000;  // 4位数随机数
        String numStr = new Long(System.currentTimeMillis()).toString() + random.toString();
        Long uniqueInt = new Long(numStr);
        return Long.toHexString(uniqueInt.intValue());
    }

    /**
     * 字符串转16进制字符串（无需unicode编码，支持中文）
     */
    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString().trim();
    }

    /**
     * 16进制字符串转字符串（无需unicode编码，支持中文）
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    public static String numStrToHexStr(String numStr) {
        if (numStr == null || numStr.equals("")) {
            return null;
        }
        StringBuilder sbs = new StringBuilder();
        if(numStr.length() % 4 > 0) {
            for(int i = 0; i < numStr.length() % 4; i++) {
                numStr = "0" + numStr;
            }
        }
        for (int i = 0; i < numStr.length() / 4; i++) {
            String subStr = numStr.substring(i * 4, i * 4 + 4);
            String hexStr = Integer.toHexString(Integer.parseInt(subStr));
            sbs.append(hexStr);
        }

        return sbs.toString();
    }

}