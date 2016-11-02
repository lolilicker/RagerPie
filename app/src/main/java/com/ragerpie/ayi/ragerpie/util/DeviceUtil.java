package com.ragerpie.ayi.ragerpie.util;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.PowerManager;
import android.support.graphics.drawable.BuildConfig;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

/**
 * 工具类，有关于设备的基本信息(IMEI,手机型号，网络信息，网络状态，发送邮件，发送短信，拨号等等基本功能)
 *
 * @author wang
 */
public class DeviceUtil {

    /**
     * 判断是否是平板
     **/
    private static Boolean isTablet = null;

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneType() {
        return Build.MODEL;
    }

    /**
     * 获取IMEI
     *
     * @return
     */
    public static String getBuildSerial(Context context) {
//        TelephonyManager tel = (TelephonyManager) context
//                .getSystemService(Context.TELEPHONY_SERVICE);
//        return tel.getDeviceId();

        /**
         * android 6.0 获取IMEI需要权限，直接获取SERIAL值也是唯一的
         * http://wangshifuola.blogspot.jp/2011/12/androidandroididimeiwifi-mac.html
         */
        return Build.SERIAL;
    }


    /**
     * 获取当前的进程
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }


    /**
     * 将内容复制到剪切板
     *
     * @param string
     */
    @SuppressWarnings("deprecation")
    public static void copyTextToBoard(String string, Context context) {
        if (android.text.TextUtils.isEmpty(string))
            return;
        ClipboardManager clip = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        clip.setText(string);
    }

    /**
     * 获取状态栏的高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            return context.getResources()
                    .getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 是否联网
     *
     * @return
     */
    public static boolean hasInternet(Context context) {
        boolean flag;
        if (((ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    /**
     * 强制关闭输入法
     */
    public static void hideSoftKeyboardByForce(View view, Context context) {
        ((InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public static void hideSoftKeyboard(View view, Context context) {
        if (view == null) {
            return;
        }
        ((InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 显示软甲盘
     *
     * @param view
     */
    public static void showSoftKeyboard(View view, Context context) {
        ((InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE)).showSoftInput(view,
                InputMethodManager.SHOW_FORCED);
    }

    /**
     * 是否是平板
     *
     * @return
     */
    public static boolean isTablet(Context context) {
        if (isTablet == null) {
            boolean flag;
            if ((0xf & context.getResources()
                    .getConfiguration().screenLayout) >= 3) {
                flag = true;
            } else {
                flag = false;
            }
            isTablet = Boolean.valueOf(flag);
        }
        return isTablet.booleanValue();
    }

    /**
     * 获取手机的像素信息
     *
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics;
    }

    /**
     * 获取手机像素宽度
     *
     * @return
     */
    public static float getScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    /**
     * 获取手机像素高度
     *
     * @return
     */
    public static float getScreenHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    public static String getDeviceInfoDetail(Context context) {
        String app_version = BuildConfig.VERSION_NAME;
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        String os = "Android";
        String os_version = Build.VERSION.RELEASE;
        int screen_height = (int) DeviceUtil.getScreenHeight(context);
        int screen_width = (int) DeviceUtil.getScreenWidth(context);
        int wifi = NetworkUtil.getNetworkType(context) == NetworkUtil.NETWORK_CLASS_WIFI ? 1 : 0;
        String carrier = NetworkUtil.getCarrier(context);
        String network_type = NetworkUtil.getNetworkTypeString(context);
        String distinct_id = DeviceUtil.getBuildSerial(context);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("app_version=" + app_version)
                .append("&manufacturer=" + manufacturer)
                .append("&model=" + model)
                .append("&os=" + os)
                .append("&os_version=" + os_version)
                .append("&screen_height=" + screen_height)
                .append("&screen_width=" + screen_width)
                .append("&wifi=" + wifi)
                .append("&carrier=" + carrier)
                .append("&network_type=" + network_type)
                .append("&distinct_id=" + distinct_id);
        return stringBuilder.toString();
    }

    /**
     * 唤醒设备
     *
     * @param context
     */
    public static void wakeDevice(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            isScreenOn = pm.isInteractive();
        } else {
            isScreenOn = pm.isScreenOn();
        }
        LogUtils.d("screen on................................." + isScreenOn);
        if (!isScreenOn) {
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "MyLock");
            wl.acquire(10000);
            PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyCpuLock");
            wl_cpu.acquire(10000);
        }
    }

    /**
     * 当前系统是否为MIUI
     */
    public static boolean isMIUI() {
        String property = getSystemProperty("ro.miui.ui.version.name");
        LogUtils.d("property = " + property);
        return property != null && property.length() != 0;
    }

    public static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            LogUtils.e("Unable to read sysprop " + propName, ex);
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LogUtils.e("Exception while closing InputStream", e);
                }
            }
        }
        return line;
    }

    /**
     * 关闭notification
     */
    public static void cancelNotifications(Context context) {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(ns);
        notificationManager.cancelAll();
    }
}
