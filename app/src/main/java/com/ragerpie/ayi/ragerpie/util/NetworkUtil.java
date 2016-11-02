package com.ragerpie.ayi.ragerpie.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by WangBo on 2016/9/28.
 */

public class NetworkUtil {
    /**
     * Unknown network class.
     */
    public static final int NETWORK_CLASS_UNKNOWN = 0;
    /**
     * Class of broadly defined "2G" networks.
     */
    public static final int NETWORK_CLASS_2_G = 1;
    /**
     * Class of broadly defined "3G" networks.
     */
    public static final int NETWORK_CLASS_3_G = 2;
    /**
     * Class of broadly defined "4G" networks.
     */
    public static final int NETWORK_CLASS_4_G = 3;
    /**
     * Class of broadly defined "WIFI" networks.
     */
    public static final int NETWORK_CLASS_WIFI = 4;

    /**
     * Network type is unknown
     */
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    /**
     * Current network is GPRS
     */
    public static final int NETWORK_TYPE_GPRS = 1;
    /**
     * Current network is EDGE
     */
    public static final int NETWORK_TYPE_EDGE = 2;
    /**
     * Current network is UMTS
     */
    public static final int NETWORK_TYPE_UMTS = 3;
    /**
     * Current network is CDMA: Either IS95A or IS95B
     */
    public static final int NETWORK_TYPE_CDMA = 4;
    /**
     * Current network is EVDO revision 0
     */
    public static final int NETWORK_TYPE_EVDO_0 = 5;
    /**
     * Current network is EVDO revision A
     */
    public static final int NETWORK_TYPE_EVDO_A = 6;
    /**
     * Current network is 1xRTT
     */
    public static final int NETWORK_TYPE_1xRTT = 7;
    /**
     * Current network is HSDPA
     */
    public static final int NETWORK_TYPE_HSDPA = 8;
    /**
     * Current network is HSUPA
     */
    public static final int NETWORK_TYPE_HSUPA = 9;
    /**
     * Current network is HSPA
     */
    public static final int NETWORK_TYPE_HSPA = 10;
    /**
     * Current network is iDen
     */
    public static final int NETWORK_TYPE_IDEN = 11;
    /**
     * Current network is EVDO revision B
     */
    public static final int NETWORK_TYPE_EVDO_B = 12;
    /**
     * Current network is LTE
     */
    public static final int NETWORK_TYPE_LTE = 13;
    /**
     * Current network is eHRPD
     */
    public static final int NETWORK_TYPE_EHRPD = 14;
    /**
     * Current network is HSPA+
     */
    public static final int NETWORK_TYPE_HSPAP = 15;

    public static boolean isWifiConnected(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetInfo != null)
                && (activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI);
    }

    /**
     * 检测网络是否可用
     */
    public static boolean isNetworkConnected(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetInfo != null) && (activeNetInfo.isConnected());
    }

    public static int getNetworkClass(int networkType) {
        switch (networkType) {
            case NETWORK_TYPE_GPRS:
            case NETWORK_TYPE_EDGE:
            case NETWORK_TYPE_CDMA:
            case NETWORK_TYPE_1xRTT:
            case NETWORK_TYPE_IDEN:
                return NETWORK_CLASS_2_G;
            case NETWORK_TYPE_UMTS:
            case NETWORK_TYPE_EVDO_0:
            case NETWORK_TYPE_EVDO_A:
            case NETWORK_TYPE_HSDPA:
            case NETWORK_TYPE_HSUPA:
            case NETWORK_TYPE_HSPA:
            case NETWORK_TYPE_EVDO_B:
            case NETWORK_TYPE_EHRPD:
            case NETWORK_TYPE_HSPAP:
                return NETWORK_CLASS_3_G;
            case NETWORK_TYPE_LTE:
                return NETWORK_CLASS_4_G;
            default:
                return NETWORK_CLASS_UNKNOWN;
        }
    }

    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络
     */
    public static int getNetworkType(Context context) {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return NETWORK_CLASS_UNKNOWN;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            netType = getNetworkClass(networkInfo.getSubtype());
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETWORK_CLASS_WIFI;
        }
        return netType;
    }

    /**
     * 获取当前网络类型
     */
    public static String getNetworkTypeString(Context context) {
        int networkStype = NetworkUtil.getNetworkType(context);
        switch (networkStype) {
            case 1:
                return "2G";
            case 2:
                return "3G";
            case 3:
                return "4G";
            case 4:
                return "WIFI";
        }
        return "NONETWORK";
    }

    /**
     * 获取当前处于联通还是移动之类的
     */
    public static String getCarrier(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String carrierName = manager.getNetworkOperatorName();
        switch (carrierName) {
            case "中国联通":
                carrierName = "ChinaUnicom";
                break;
            case "中国移动":
                carrierName = "ChinaMobile";
                break;
            case "中国电信":
                carrierName = "ChinaTel";
                break;
            default:
                carrierName = "unknown";
                break;
        }
        return carrierName;
    }


    /**
     * 流量上网时是否网速比较快(3G或3G以上的网络类型)
     *
     * @return
     */
    public static boolean isFastMoblieNetwork(Context context) {
        TelephonyManager manager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        switch (manager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false;    //50-100kbps
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false;    //14-64kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false;    //50-100kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true;    //400-1000kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true;    //600-1400kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return false;    //100kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true;    //2-14Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true;    //700-1700kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true;    //1-23Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return true;    //400-700kbps
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return true;    //1-2Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return true;    //5Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return true;    //10-20Mbps
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return false;    //25kbps
            case TelephonyManager.NETWORK_TYPE_LTE:
                return true;    //10Mbps+
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return false;    //25kbps
            default:
                return false;
        }
    }
}
