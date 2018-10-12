package com.ecar.epark.eotherpush;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ecar.epark.eotherpushlib.EPushMsgActivity;
import com.ecar.epark.eotherpushlib.EotherPushManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity","onCreate");

        if(getIntent()!=null && getIntent().getData()!=null){
            Log.e("MainActivity","onCreate:"+getIntent().getData().toString());
        }
        test();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("MainActivity","onNewIntent");
        if(getIntent()!=null && getIntent().getData()!=null){
            Log.e("MainActivity","onNewIntent:"+getIntent().getData().toString());
        }
    }

    private void test() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String s = MainActivity.class.getCanonicalName();
//        EpushUtil.loge("qob 1","    mainActivity :"+s);
        intent.setComponent(new ComponentName(getPackageName(), s));
//        intent.setComponent(new ComponentName("com.ecar.pushTest", "com.ecar.epark.eotherpushlib.EPushMsgActivity"));
        intent.setData(Uri.parse("unitehnreader://nativepage/book/detail?bid=357735?closeback=1&startinner=0"));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        String intentUri = intent.toUri(Intent.URI_INTENT_SCHEME);
        Log.e("qob 1","    mainActivity :"+intentUri);
//        intent://nativepage/book/detail?bid=357735?closeback=1&startinner=0#Intent;scheme=unitehnreader;launchFlags=0x4000000;end
//        intent:#Intent;component=com.ecar.pushTest/com.ecar.epark.eotherpushlib.EPushMsgActivity;end
//        intent://nativepage/book/detail?bid=357735?closeback=1&startinner=0#Intent;scheme=unitehnreader;launchFlags=0x4000000;component=com.ecar.pushTest/com.ecar.epark.eotherpushlib.EPushMsgActivity;end

    }
}
