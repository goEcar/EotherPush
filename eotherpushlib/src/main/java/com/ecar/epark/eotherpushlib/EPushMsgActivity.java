package com.ecar.epark.eotherpushlib;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
        Intent intent = getIntent();
        Uri data = intent.getData();
    }
}
