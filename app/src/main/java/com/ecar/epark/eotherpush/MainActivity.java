package com.ecar.epark.eotherpush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ecar.epark.eotherpushlib.EotherPushManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EotherPushManager.getInstance(this.getApplication());
    }
}
