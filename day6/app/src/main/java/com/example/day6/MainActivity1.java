package com.example.day6;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity1 extends Activity {

    //service与activity通信
    TestService myService;
    boolean isBound = false;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            TestService.LocalBinder binder = (TestService.LocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serviceactivity);

        Intent intent = new Intent(this, TestService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void onButtonClick(View view) {
        if (isBound) {
            int num1 = 3;
            int num2 = 4;
            int result = myService.add(num1, num2);
            Toast.makeText(this, "result: " + result, Toast.LENGTH_LONG).show();
        }
    }





    //service简单使用
//    private Button start;
//    private Button stop;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.servicetest);
//
//        start = (Button) findViewById(R.id.btnstart);
//        stop = (Button) findViewById(R.id.btnstop);
//        //创建启动Service的Intent,以及Intent属性
//        final Intent intent = new Intent(MainActivity1.this,TestService.class);
//        intent.setAction("com.jay.example.day6");
//        //为两个按钮设置点击事件,分别是启动与停止service
//        start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startService(intent);
//            }
//        });
//
//        stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                stopService(intent);
//
//            }
//        });
//    }
}
