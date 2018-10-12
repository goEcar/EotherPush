package com.ecar.epark.eotherpushlib;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;

import com.coloros.mcssdk.callback.PushCallback;
import com.ecar.epark.eproviderlib.provider.SPHelper;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.push.HuaweiPush;
import com.huawei.hms.support.api.push.TokenResult;
import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

import cn.jpush.android.api.JPushInterface;


public class EotherPushManager implements HuaweiApiClient.ConnectionCallbacks, HuaweiApiClient.OnConnectionFailedListener {

    private HuaweiApiClient client;
    public static Application mApp;

    private static EotherPushManager eotherPushManager;

    public static EotherPushManager getInstance(Application mApp) {
        synchronized (EotherPushManager.class) {
            if (eotherPushManager == null) {
                synchronized (EotherPushManager.class) {
                    eotherPushManager = new EotherPushManager(mApp);
                }
            }
        }
        return eotherPushManager;
    }

    public Application getmApp() {
        return mApp;
    }

    private EotherPushManager(Application mApp) {
        this.mApp = mApp;
        init();
    }

    private void init() {
        if (mApp == null) {
            return;
        }
        if (!shouldInit()) {
            return;
        }
        SPHelper.getInstance().init(mApp);
        String systemType = EDeviceUtils.getSystemType();
        if (TextUtils.isEmpty(systemType)) {
            return;
        }
        if (systemType.equals(EDeviceUtils.SYS_EMUI)) {
            huawei();
        } else if (systemType.equals(EDeviceUtils.SYS_MIUI)) {
            xiaomi();
        } else if (systemType.equals(EDeviceUtils.SYS_FLYME)) {
            meizu();
        } else if (com.coloros.mcssdk.PushManager.isSupportPush(mApp)) {
            systemType = "Oppo";
            oppo();
        } else {
            systemType = "Jiguang";
            jiguang();
        }
        //设备厂商，小米、华为等
        SPHelper.getInstance().save("E_PUSH_BRAND", systemType);
        //具体型号
        SPHelper.getInstance().save("E_PUSH_MODEL", EDeviceUtils.getOsBuildModel());
    }

    private void jiguang() {
        try {
            JPushInterface.setDebugMode(true);
            JPushInterface.init(mApp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void oppo() {
        try {
            Log.i(TAG, "oppo init push");
            String appKeyValue = getMetaValue(mApp, OPPO_APP_KEY);
            String appSecretValue = getMetaValue(mApp, OPPO_APP_SECRET);
            com.coloros.mcssdk.PushManager.getInstance().register(mApp, appKeyValue, appSecretValue, new EOppoPushCallback());//setPushCallback接口也可设置callback
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void meizu() {
        if (MzSystemUtils.isBrandMeizu()) {
            String appIdValue = getMetaValue(mApp, MeiZu_APP_ID);
            String appKeyValue = getMetaValue(mApp, Meizu_APP_KEY);
            if (TextUtils.isEmpty(appIdValue) || TextUtils.isEmpty(appKeyValue)) {
                Log.i(TAG, "MeiZu_APP_ID or Meizu_APP_KEY  is null");
                return;
            }
            PushManager.register(mApp, appIdValue, appKeyValue);
        }
    }

    public final String XIAOMI_APP_ID = "e_xiaomi_app_id";
    public final String XIAOMI_APP_KEY = "e_xiaomi_app_key";

    public final String MeiZu_APP_ID = "e_meizu_app_id";
    public final String Meizu_APP_KEY = "e_meizu_app_key";

    public final String OPPO_APP_KEY = "e_oppo_app_key";
    public final String OPPO_APP_SECRET = "e_oppo_app_secret";

    public void xiaomi() {
        //初始化push推送服务
        if (shouldInit()) {
            String appIdValue = getMetaValue(mApp, XIAOMI_APP_ID);
            String appKeyValue = getMetaValue(mApp, XIAOMI_APP_KEY);
            if (TextUtils.isEmpty(appIdValue) || TextUtils.isEmpty(appKeyValue)) {
                Log.i(TAG, "XIAOMI_APP_ID or XIAOMI_APP_KEY  is null");
                return;
            }
            MiPushClient.registerPush(mApp, appIdValue, appKeyValue);
//打开Log
            LoggerInterface newLogger = new LoggerInterface() {

                @Override
                public void setTag(String tag) {
                    // ignore
                }

                @Override
                public void log(String content, Throwable t) {
                    Log.d(TAG, content, t);
                }

                @Override
                public void log(String content) {
                    Log.d(TAG, content);
                }
            };
            Logger.setLogger(mApp, newLogger);
        }
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) mApp.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = mApp.getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }


    private void huawei() {
//		showLog("get token: begin");
//        PendingResult<TokenResult> tokenResult = HuaweiPush.HuaweiPushApi.getToken(ApiClient.class.);
//        TokenResult result = tokenResult.await();
//		HMSAgent.Push.getToken(new GetTokenHandler() {
//
//		});
//		new GetTokenApi().getToken(handler);
//		HuaweiPush.HuaweiPushApi.getToken()
        //创建华为移动服务client实例用以使用华为push服务
//需要指定api为HuaweiId.PUSH_API
//连接回调以及连接失败监听
        client = new HuaweiApiClient.Builder(mApp)
                .addApi(HuaweiPush.PUSH_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
//建议在oncreate的时候连接华为移动服务
//业务可以根据自己业务的形态来确定client的连接和断开的时机，但是确保connect和disconnect必须成对出现
        client.connect();
    }

    public static final String TAG = "Huawei";

    @Override
    public void onConnected() {
        //华为移动服务client连接成功，在这边处理业务自己的事件
        Log.i(TAG, "HuaweiApiClient 连接成功");
        getTokenAsyn();
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        //HuaweiApiClient断开连接的时候，业务可以处理自己的事件
        Log.i(TAG, "HuaweiApiClient 连接断开");
        client.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult arg0) {
        Log.i(TAG, "HuaweiApiClient连接失败，错误码：" + arg0.getErrorCode());
    }

//    protected void onDestroy() {
//        //建议在onDestroy的时候停止连接华为移动服务
//        //业务可以根据自己业务的形态来确定client的连接和断开的时机，但是确保connect和disconnect必须成对出现
//        client.disconnect();
//    }

    /**
     * 使用异步接口来获取pushtoken
     * 结果通过广播的方式发送给应用，不通过标准接口的pendingResul返回
     * CP可以自行处理获取到token
     * 同步获取token和异步获取token的方法CP只要根据自身需要选取一种方式即可
     */
    private void getTokenAsyn() {
        if (!client.isConnected()) {
            Log.i(TAG, "获取token失败，原因：HuaweiApiClient未连接");
            return;
        }
        PendingResult<TokenResult> tokenResult = HuaweiPush.HuaweiPushApi.getToken(client);
        tokenResult.setResultCallback(new ResultCallback<TokenResult>() {
            @Override
            public void onResult(TokenResult result) {
                Log.i(TAG, "HuaweiApiClientgetTokenAsyn：" + result.getTokenRes().getToken());
                //这边的结果只表明接口调用成功，是否能收到响应结果只在广播中接收，广播这块后面会有讲到
            }
        });
    }

    // 获取ApiKey
    public String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                Object obj = metaData.get(metaKey);
                apiKey = String.valueOf(obj).trim();
            }
        } catch (Exception e) {
            Log.e(TAG, "error " + e.getMessage());
        }
        return apiKey;
    }

}
