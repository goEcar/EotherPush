package com.ecar.epark.eotherpushlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.meizu.cloud.pushsdk.util.MzSystemUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class EDeviceUtils {

    /**
     * 小米手机（MIUI系统）
     */
    public static final String SYS_MIUI = "xiaomi";
    /**
     * 华为手机（EMUI系统）
     */
    public static final String SYS_EMUI = "huawei";
    /**
     * 魅族手机，FLYME系统
     */
    public static final String SYS_FLYME = "meizu";
    /**
     * 其他系统
     */
    public static final String SYS_OTHER = "os_other";

    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

    private static final String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";
    private static final String KEY_EMUI_VERSION = "ro.build.version.emui";
    private static final String KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion";

    /**
     * 8.0之后有些系统信息获取不到，没有在各种版本手机上逐一测试
     */
    public static String getSystemType() {
        String sysType= "";
        try {
            sysType = Build.MANUFACTURER.toLowerCase();
            //官方提供的判断是否为小米手机（而非MIUI系统）的方法)
            if (sysType.equals("xiaomi")) {
                return SYS_MIUI;
            } else if (isEMUI()) {
                //华为
                return SYS_EMUI;
            } else if (isMeizu()//魅族推送SDK中提供的判断是否是魅族的方法
                    || EDeviceHelper.isMeizu()) {//QMUI提供的判断是否是魅族的方法
                return SYS_FLYME;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            if (//官方提供的判断是否为小米手机（而非MIUI系统）的方法
                    prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null//QMUI提供的判断是否是MIUI的方法
                            || prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null//下面两个是网上补充的方法，感觉没必要的
                            || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null) {
                return SYS_MIUI;
            } else if (//华为
                    prop.getProperty(KEY_EMUI_API_LEVEL, null) != null
                            || prop.getProperty(KEY_EMUI_VERSION, null) != null
                            || prop.getProperty(KEY_EMUI_CONFIG_HW_SYS_VERSION, null) != null) {
                return SYS_EMUI;
            } else if (//魅族推送SDK中提供的判断是否是魅族的方法
                    EDeviceHelper.isMeizu()) {//QMUI提供的判断是否是魅族的方法
                return SYS_FLYME;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return !TextUtils.isEmpty(sysType)?sysType:SYS_OTHER;
    }

    @SuppressLint("PrivateApi")
    private static boolean isEMUI() {
        Class<?>[] clsArray = new Class<?>[]{String.class};
        Object[] objArray = new Object[]{"ro.build.version.emui"};
        try {
            Class<?> SystemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method get = SystemPropertiesClass.getDeclaredMethod("get", clsArray);
            String version = (String) get.invoke(SystemPropertiesClass, objArray);
            Log.i("bqt", "EMUI version is:" + version);
            return !TextUtils.isEmpty(version);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断是否为魅族设备
     */
    private static boolean isMeizu() {
        return MzSystemUtils.isBrandMeizu();
    }

    /**
     * 友盟获取设备参数
     *
     * @param context
     * @return
     */
    public static String getDeviceInfo(Context context) {
        String device_id = "";
        try {
//            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            device_id = tm.getDeviceId();
//            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//            String mac = wifi.getConnectionInfo().getMacAddress();
//            json.put("mac", mac);

//            if (TextUtils.isEmpty(device_id)) {
//                device_id = mac;
//            }
//            json.put("device_id", device_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(device_id)) {
            device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        }
        return TextUtils.isEmpty(device_id) ? "" : device_id;
    }

    public static String getOsBuildModel() {
        String model = "notknow";
        try {
            model = android.os.Build.MODEL;
        } catch (Exception e) {
        }
        return model;
    }
}