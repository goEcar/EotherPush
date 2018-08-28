package com.ecar.epark.eotherpushlib;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by lh on 2018/8/28.
 */

public class EOtherPushUtil {

    public static String getPushData(Intent intent){
        if (intent != null ) {
            String content = "";
            if(intent.getData() != null){
                content = intent.getData().getQueryParameter("content");
            }else if(intent.getExtras()!=null){
                Bundle bundle =  intent.getExtras();
                if(bundle!=null){
                    content = bundle.getString("content");
                }
            }
            if(!TextUtils.isEmpty(content)){
                return content;
            }
        }
        return null;
    }
}
