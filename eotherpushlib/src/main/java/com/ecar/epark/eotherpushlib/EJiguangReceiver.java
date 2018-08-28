package com.ecar.epark.eotherpushlib;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by lh on 2018/8/28.
 */
public class EJiguangReceiver extends BroadcastReceiver {
    private static final String TAG = "EJiguangReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null && JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String registerId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.e("EJiguangReceiver", "onReceive " + registerId);
            if (!TextUtils.isEmpty(registerId)) {
                EDeviceUtils.setPushId(EotherPushManager.mApp, registerId);
            }
        }
        else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
//            Logger.d(TAG, "用户点击打开了通知");当用户点击时触发
            openNotification(context, bundle);
        }
    }

    private void openNotification(Context context, Bundle bundle) {
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        try {
            JSONObject extrasJson = new JSONObject(extras);
            String contentStr = extrasJson.getString("content");
            Gson gson = new Gson();
            OtherMessage obj = gson.fromJson(contentStr, OtherMessage.class);
            startEpushActivity(context,obj);
        } catch (Exception e) {
            Log.w(TAG, "Unexpected: extras is not a valid json", e);
        }
//        if (TYPE_THIS.equals(myValue)) {
//            Intent mIntent = new Intent(context, ThisActivity.class);
//            mIntent.putExtras(bundle);
//            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(mIntent);
//        }
    }

    private void startEpushActivity(Context context,OtherMessage obj) {
        if(obj == null || context == null){
            return;
        }
        //设置action
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //设置路径，与上 二者用其一即可，这里都设置了
        intent.setComponent(new ComponentName(context.getPackageName(), obj.getActivityPath()));
//        intent.setComponent(new ComponentName("com.ecar.pushTest", "com.ecar.epark.eotherpushlib.EPushMsgActivity"));

        //设置data： 命名空间 、主机名、路径、参数
//        intent.setData(Uri.parse("unitehnreader://nativepage/book/detail?bid=357735?content="));
        String data = TextUtils.isEmpty(obj.getData())?"":"?content="+obj.getData();
        intent.setData(Uri.parse(obj.getFilterScheme()+"://"+obj.getFilterHost()+obj.getFilterPath()+data));

        //启动模式
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(!TextUtils.isEmpty(obj.getFilterLaunchMode())){
            try {
                intent.addFlags(Integer.parseInt(obj.getFilterLaunchMode().replaceAll("0x",""), 16));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        String intentUri = intent.toUri(Intent.URI_INTENT_SCHEME);
        context.startActivity(intent);

//        EpushUtil.loge("qob 1","    mainActivity :"+intentUri);
//        intent://nativepage/book/detail?bid=357735?closeback=1&startinner=0#Intent;scheme=unitehnreader;launchFlags=0x4000000;end
//        intent:#Intent;component=com.ecar.pushTest/com.ecar.epark.eotherpushlib.EPushMsgActivity;end
//        intent://nativepage/book/detail?bid=357735?closeback=1&startinner=0#Intent;scheme=unitehnreader;launchFlags=0x4000000;component=com.ecar.pushTest/class%20com.ecar.epark.eotherpushlib.EPushMsgActivity;end

    }
}
