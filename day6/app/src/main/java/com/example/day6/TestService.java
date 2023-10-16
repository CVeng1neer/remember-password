package com.example.day6;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.security.Provider;

public class TestService extends Service {

    //service和activity通信
    private final IBinder binder = new LocalBinder();
    private final String TAG = "TestService";

    public class LocalBinder extends Binder {
        TestService getService() {
            return TestService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind方法被调用");
        return binder;
    }

    public int add(int x, int y) {
        Log.i(TAG, "add方法被调用，参数x=" + x + "，y=" + y);
        int result = x + y;
        Log.i(TAG, "add方法返回结果：" + result);
        return result;
    }
    }



    //service简单使用
//    private final String TAG = "TestService";
//    public IBinder onBind(Intent intent){
//        Log.i(TAG,"onBind方法被调用");
//        return null;
//    }
//
//    //Service被调用时创建
//    public void onCreate(){
//        Log.i(TAG,"onCreate方法被调用");
//        super.onCreate();
//    }
//
//    //Service被启动时调用
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.i(TAG, "onStartCommand方法被调用!");
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    //Service被关闭之前回调
//    @Override
//    public void onDestroy() {
//        Log.i(TAG, "onDestory方法被调用!");
//        super.onDestroy();
//    }

