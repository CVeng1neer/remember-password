package com.example.rememberpassword;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity{

    private String Tag = "404";
    private ForceOfflineReceiver mReceiver=new ForceOfflineReceiver();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.example.rememberpassword.FORCE_OFFLINE");
        registerReceiver(mReceiver,  filter);


        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button forceOffline = (Button) findViewById(R.id.force_offline);
        forceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.rememberpassword.FORCE_OFFLINE");
                sendBroadcast(intent);

                Log.i(Tag,"onclick");

                //finish();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
