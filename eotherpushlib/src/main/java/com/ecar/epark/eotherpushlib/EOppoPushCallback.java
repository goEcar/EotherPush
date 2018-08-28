package com.ecar.epark.eotherpushlib;

import android.util.Log;

import com.coloros.mcssdk.callback.PushCallback;
import com.coloros.mcssdk.mode.ErrorCode;
import com.coloros.mcssdk.mode.SubscribeResult;

import java.util.List;

/**
 * Created by lh on 2018/8/27.
 */

public class EOppoPushCallback implements PushCallback {
    @Override
    public void onRegister(int i, String s) {
        Log.e("EOppoPushCallback", "onRegisterStatus " + s);
        String pushId = ErrorCode.SUCCESS == i?s:"";
        EDeviceUtils.setPushId(EotherPushManager.mApp,pushId);
    }

    @Override
    public void onUnRegister(int i) {

    }

    @Override
    public void onGetAliases(int i, List<SubscribeResult> list) {

    }

    @Override
    public void onSetAliases(int i, List<SubscribeResult> list) {

    }

    @Override
    public void onUnsetAliases(int i, List<SubscribeResult> list) {

    }

    @Override
    public void onSetUserAccounts(int i, List<SubscribeResult> list) {

    }

    @Override
    public void onUnsetUserAccounts(int i, List<SubscribeResult> list) {

    }

    @Override
    public void onGetUserAccounts(int i, List<SubscribeResult> list) {

    }

    @Override
    public void onSetTags(int i, List<SubscribeResult> list) {

    }

    @Override
    public void onUnsetTags(int i, List<SubscribeResult> list) {

    }

    @Override
    public void onGetTags(int i, List<SubscribeResult> list) {

    }

    @Override
    public void onGetPushStatus(int i, int i1) {

    }

    @Override
    public void onSetPushTime(int i, String s) {

    }

    @Override
    public void onGetNotificationStatus(int i, int i1) {

    }
}
