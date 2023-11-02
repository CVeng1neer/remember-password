package com.example.rememberpassword;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class BaseActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }


    private ForeOffLineReceiver receiver;
    //在OnResume 和 OnPause中注册和取消注册，
    // 目的是 让一个栈顶活动 注册广播接收器  当该活动失去栈顶位置时 取消注册
    //因为 非栈顶的活动 也不需要注册这个强制下线广播
    //注册广播接收器
    protected  void onResume(){
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.broadcastpractice.FORCE_OFFLINE");
        receiver=new ForeOffLineReceiver();
        registerReceiver(receiver,intentFilter);
    }
    //取消广播接收器注册
    @Override
    protected void onPause() {
        super.onPause();
        if (receiver!=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
    }

    //广播接收器，在BaseActivity类中动态注册，则 在所有活动界面都能接收到广播
    class ForeOffLineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder=new  AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline. Please try to login again");
            //无法关闭的对话框
            builder.setCancelable(false);
            //只能点击其中的按钮“ok”，销毁所有活动，并重启LoginActivity活动
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCollector.finishAll();
                    Intent intent =new Intent(context ,LoginActivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }
}
