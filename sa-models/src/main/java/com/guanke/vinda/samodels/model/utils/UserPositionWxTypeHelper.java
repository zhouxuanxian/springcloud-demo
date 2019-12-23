package com.guanke.vinda.samodels.model.utils;

public class UserPositionWxTypeHelper {
    private UserPositionWxTypeHelper() {
    }

    public static String transformDB2WxType(String dbType) {
        String wxType = "BOTH_SALESMAN_CHN_";
        switch (dbType) {
            case "KA Store Clerk":
                wxType = "KA_SALESMAN_CHN_";
                break;
            case "GT Store Clerk":
                wxType = "GT_SALESMAN_CHN_";
                break;
            case "HD KA Store Clerk":
                wxType = "KA_SALESMAN_HD_";
                break;
            case "HD GT Store Clerk":
                wxType = "GT_SALESMAN_HD_";
                break;
            case "KA Supervisor":
                wxType = "KA_SALESSUPERVISOR_CHN_";
                break;
            case "HD KA Supervisor":
                wxType = "KA_SALESSUPERVISOR_HD_";
                break;
            case "KA Director":
                wxType = "KA_SALESMANAGER_CHN_";
                break;
            case "GT Director":
                wxType = "GT_SALESMANAGER_CHN_";
                break;
            case "HD KA Director":
                wxType = "KA_SALESMANAGER_HD_";
                break;
            case "HD GT Director":
                wxType = "GT_SALESMANAGER_HD_";
                break;
            case "KA System Manager":
                wxType = "KA_SYSTEMSALESMANAGER_CHN_";
                break;
            case "HD KA System Manager":
                wxType = "KA_SYSTEMSALESMANAGER_HD_";
                break;
            case "KA Region Manager":
                wxType = "KA_SALESBOSS_CHN_";
                break;
            case "KA Province Manager":
                wxType = "KA_SALESBOSS_CHN_";
                break;
            case "KA Agency Manager":
                wxType = "KA_SALESBOSS_CHN_";
                break;
            case "GT Region Manager":
                wxType = "GT_SALESBOSS_CHN_";
                break;
            case "GT Province Manager":
                wxType = "GT_SALESBOSS_CHN_";
                break;
            case "GT Agency Manager":
                wxType = "GT_SALESBOSS_CHN_";
                break;
            case "Region Manager":
                wxType = "BOTH_SALESBOSS_CHN_";
                break;
            case "HD KA Region Manager":
                wxType = "KA_SALESBOSS_HD_";
                break;
            case "HD KA Province Manager":
                wxType = "KA_SALESBOSS_HD_";
                break;
            case "HD KA Agency Manager":
                wxType = "KA_SALESBOSS_HD_";
                break;
            case "HD GT Region Manager":
                wxType = "GT_SALESBOSS_HD_";
                break;
            case "HD GT Province Manager":
                wxType = "GT_SALESBOSS_HD_";
                break;
            case "HD GT Agency Manager":
                wxType = "GT_SALESBOSS_HD_";
                break;
            case "HD Region Manager":
                wxType = "BOTH_SALESBOSS_HD_";
                break;
            case "Region Marketing Dept":
                wxType = "BOTH_SALESBOSS_HD_";
                break;
            case "Province Marketing Dept":
                wxType = "BOTH_SALESBOSS_HD_";
                break;
            case "HD Marketing Dept":
                wxType = "BOTH_SALESBOSS_HD_";
                break;
            case "HD Channel Dept":
                wxType = "BOTH_SALESBOSS_HD_";
                break;
            case "Commercial Store Clerk":
                wxType = "C_Commercial_";
                break;
            case "Commercial Director":
                wxType = "C_Commercial_";
                break;
            case "Commercial Agency Manager":
                wxType = "C_Commercial_";
                break;
            case "Commercial Province Manager":
                wxType = "C_Commercial_";
                break;
            case "Headquarters":
                wxType = "BOTH_BOSSHEADS_CHN_";
                break;
            case "Group Senior Manager":
                wxType = "Group_Senior_CHN_";
                break;
            default:
                break;
        }
        return wxType;
    }
}
