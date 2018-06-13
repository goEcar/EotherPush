package com.ecar.epark.eotherpush;

import android.app.Application;
import android.util.Log;

/**
 * Created by Administrator on 2018/6/1 0001.
 */

public class EApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Eapp","onCreate");
    }
}
