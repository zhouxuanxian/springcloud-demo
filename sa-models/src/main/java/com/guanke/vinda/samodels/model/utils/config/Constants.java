package com.guanke.vinda.samodels.model.utils.config;

public class Constants {


    //默认值
    public static final String DEFAULT_USER_ID = "0-1";
    public static final String DEFAULT_BRAND_ID = "DefaultBrand";

    //Token认证常量
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";
    public static final String CURRENT_ORG_ID = "CURRENT_ORG_ID";
    public static final String CURRENT_BRAND_ID = "CURRENT_BRAND_ID";
    public static final int TOKEN_EXPIRES_HOUR = 1;
    public static final String AUTHORIZATION = "X-Auth-Token";

    //七牛云存储-账号
    public static final String QINIU_ACCESS_KEY = "U2cE0UOM3YbUpsLTT7qfWfMp7PnNxPj6YmOsHrCN";
    public static final String QINIU_SECRET_KEY = "-HuyEESUeJEKELDs59eMT8jTh5TWQt6xPUR3Upkc";

    //百度OCR
    public static final String OCR_APP_ID = "16097827";
    public static final String OCR_API_KEY = "esbhIYujQ7ygu7c4jIC4V7ql";
    public static final String OCR_SECRET_KEY = "LGS3Ak4iSQ6aTQtV9ksA7AYvcR54RgpS";


    //微信相关
    public static final String WX_APP_ID = "";
    public static final String WX_APP_SECRET = "";
    public static final String WX_NOTIFU_URL = "";
    public static final String WX_MCH_ID = "";
    public static final String WX_PAY_KEY = "";
    public static final String COUPON_SHARE_URL = "";

    //********** 测试 start **********
    //七牛云存储-空间
    public static final String QINIU_BUCKET_NAME = "salesassistnew";//测试
    public static final String QINIU_DOMAIN_NAME = "http://qiniutest.vinda.com/";//测试
    //易源数据（扫码）---修改为与正式环境共用一个账户应用20180905
    //public static final String SHOWAPI_APPKEY = "52906";//appkey测试
    //public static final String SHOWAPI_APPSECRET = "c4e234655936452597833942a1699f2f";//appSecret测试
    public static final String SHOWAPI_APPKEY = "53362";//appkey测试
    public static final String SHOWAPI_APPSECRET = "a4cd92ef50e94373a66688143315e2c9";//appSecret测试
    //企业号相关
    public static final String WX_CORP_ID = "wx919fbd2c998bb536";//测试
    //dev
    public static final String WX_CORP_SECRET = "lmK5dYRJLs0LVwh9xMftJodTDgEvxwQFK3lS3mfPYcw";//测试
    public static final Integer WX_AGENT_ID = 1000014;//测试
    //业务测试环境UAT
//  	public static final String WX_CORP_SECRET = "F9DkrEXJxU4X-XBPrXd9NO9gxObhr9QUc3rwLhiEvsM";//测试
//  	public static final Integer WX_AGENT_ID = 1000042;//测试
    //本地存储路径
    public static final String FILE_BASE_PATH = "//10.100.240.16/Siebel_share/siebel";	//dev测试
//  	public static final String FILE_BASE_PATH = "//10.100.240.15/Siebel_share/siebel";	//UAT测试

    //本地图片存储路径
    public static final String FILE_CRM_PATH ="//192.168.101.247/crm$/CRM-TEST/zhushou";
    //客诉平台本地图片存储路径
    public static final String FILE_KSPT_PATH ="//192.168.101.134/upload";
    public static final String FILE_KSPT_PATH_TEMP ="//192.168.101.134/upload/temp";
    //本机 照片打包zip临时目录
    public static final String LOCAL_FILE_PATH_TEMP ="/yingyu/tempImg/";
    //siebel webservice用户名密码
    public static final String SIEBEL_WS_USERNAME = "SADMIN";
    public static final String SIEBEL_WS_PASSWORD = "SADMINUAT";	//测试环境dev
//  	public static final String SIEBEL_WS_PASSWORD = "SADMIN";	//测试环境UAT240

    //OA考勤打卡URL
    public static final String OA_SELFHELP_URL = "http://rstweixin.vinda.com:8218/RstHrKqWx/zzkq/clockHomeTX?pageSign=1";//测试环境
    //********** 测试 end **********



    //********** 正式 start **********
	/*//七牛云存储-空间
  	public static final String QINIU_BUCKET_NAME = "salesassistprod";//正式
  	public static final String QINIU_DOMAIN_NAME = "http://qnsa.vinda.com/";//正式
  	//易源数据（扫码）
  	public static final String SHOWAPI_APPKEY = "53362";//appkey正式
  	public static final String SHOWAPI_APPSECRET = "a4cd92ef50e94373a66688143315e2c9";//appSecret正式
  	//企业号相关
  	public static final String WX_CORP_ID = "wxfdb3e0cd2c1429c9";//正式
  	public static final String WX_CORP_SECRET = "HdrF_nwXAFyrwj9oZcIrHSukCuvWYFAzV9A1SDkfRCg";//正式
  	public static final Integer WX_AGENT_ID = 1000010;//正式
  	//本地存储路径
  	public static final String FILE_BASE_PATH = "//192.168.101.185/Siebel_share/siebel";	//正式
  	//本地图片存储路径
  	public static final String FILE_CRM_PATH ="//192.168.101.247/crm$/CRM/zhushou";
  	//本机 照片打包zip临时目录
	public static final String LOCAL_FILE_PATH_TEMP ="/yingyu/tempImg/";
  	//siebel webservice用户名密码
  	public static final String SIEBEL_WS_USERNAME = "SADMIN";
  	public static final String SIEBEL_WS_PASSWORD = "SADMIN1597";	//正式环境
  	//客诉平台本地图片存储路径
  	public static final String FILE_KSPT_PATH = "//192.168.101.234/vinda3/complain_upload";
  	public static final String FILE_KSPT_PATH_TEMP = "//192.168.101.234/vinda3/complain_upload/temp";
  	//OA考勤打卡URL
  	public static final String OA_SELFHELP_URL = "http://wechat.vinda.com:9000/RstHrKqWx/zzkq/clockHomeTX?pageSign=1";//正式
    *///********** 正式 end **********
}
