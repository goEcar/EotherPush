package com.ecar.epark.eotherpushlib;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.ecar.epark.eproviderlib.provider.SPHelper;
import com.huawei.hms.support.api.push.PushReceiver;

import java.io.FileWriter;
import java.io.IOException;

public class EHwReceiver extends PushReceiver {



	@Override
	public void onEvent(Context context, Event arg1, Bundle arg2) {
		super.onEvent(context, arg1, arg2);
		
		showToast("onEvent" + arg1 + " Bundle " + arg2 ,  context);
	}

	@Override
	public boolean onPushMsg(Context context, byte[] arg1, Bundle arg2) {
		
		showToast("onPushMsg" + new String(arg1) + " Bundle " + arg2 ,  context);
		return super.onPushMsg(context, arg1, arg2);
	}

	@Override
	public void onPushMsg(Context context, byte[] arg1, String arg2) {
		
		showToast("onPushMsg" + new String(arg1) + " arg2 " + arg2 ,  context);
		super.onPushMsg(context, arg1, arg2);
	}

	@Override
	public void onPushState(Context context, boolean arg1) {
		
		showToast("onPushState" + arg1,  context);
		super.onPushState(context, arg1);
	}

	@Override
	public void onToken(Context context, String arg1, Bundle arg2) {
		super.onToken(context, arg1, arg2);
		arg1 = TextUtils.isEmpty(arg1)?"":arg1;
		EDeviceUtils.setPushId(context,arg1);
		showToast(" onToken" + arg1 + "bundke " + arg2,  context);
	}

	@Override
	public void onToken(Context context, String arg1) {
		super.onToken(context, arg1);
		 showToast(" onToken" + arg1 ,  context);
	}

	public  void showToast(final String toast, final Context context)
    {

		Log.e("huawei showToast",""+toast);
//    	new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				Looper.prepare();
//				Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
//				Looper.loop();
//			}
//		}).start();
    }
	
	private void  writeToFile(String conrent) {

		String SDPATH = Environment.getExternalStorageDirectory() + "/huawei.txt";
		try {
			FileWriter fileWriter = new FileWriter(SDPATH, true);
			
			fileWriter.write(conrent+"\r\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}