package com.ecar.epark.eotherpushlib;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 *
 */

public class EPushMsgActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_push_msg);
        TextView txMsg = findViewById(R.id.tx_msg);
        txMsg.setText("EPushMsgActivity");
//        Intent intent = getIntent();
//        Uri data = intent.getData();
        if (getIntent() != null && getIntent().getData() != null) {
            String content = getIntent().getData().toString();
            Log.e("EPushMsgActivity", "onCreate:" + content);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("EPushMsgActivity", "onNewIntent");
        if (getIntent() != null && getIntent().getData() != null) {
            String content = getIntent().getData().toString();
            Log.e("EPushMsgActivity", "onNewIntent:" +content);
        }
    }
}
