package com.example.day5;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainActivity extends Activity {

    //alertdialog用法
    private TextView view1;


    //progressDialog用法
    final static int Max_progress = 100;
    //该程序模拟填充长度为100的数组
    private int[] data = new int[50];
    //记录进度对话框完成的百分比
    int progressStatus = 0;
    int hasData = 0;
    ProgressDialog pd1, pd2;
    //定义一个负责更新进度的handler
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0x123) {
                pd2.setProgress(progressStatus);
            }
        }
    };


    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressdialog);


//        //alertdialog用法
//        findViewById(R.id.button1).setOnClickListener(this::onClick);
//        view1 = (TextView) findViewById(R.id.view1);
    }

    //progressDialog用法
    //用法一
    public void showSpinner(View source) {
        //调用静态方法显示进度条
        ProgressDialog.show(this, "任务执行中", "任务执行中，请等待", false, true);
    }

    //用法二
    public void showIndeterminate(View source) {
        pd1 = new ProgressDialog(MainActivity.this);
        pd1.setTitle("任务执行中");
        pd1.setMessage("任务执行中，敬请等待。。。。。。");
        pd1.setCancelable(true);
        pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd1.setIndeterminate(true);
        pd1.show();
    }

    //用法三
    public void showProgress(View source) {
        //进度条进度设为0
        progressStatus = 0;
        //重新填充数组
        hasData = 0;
        pd2 = new ProgressDialog(MainActivity.this);
        pd2.setMax(Max_progress);
        pd2.setTitle("任务完成百分比");
        pd2.setMessage("耗时任务的完成百分比");
        pd2.setCancelable(false);
        pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd2.setIndeterminate(false);
        pd2.show();

        new Thread() {
            public void run() {
                while (progressStatus < Max_progress) {
                    //获取耗时操作的完成百分比
                    progressStatus = Max_progress * doWork() / data.length;
                    //发送空消息到handler
                    handler.sendEmptyMessage(0x123);
                }
                //如果任务已完成
                if (progressStatus >= Max_progress) {
                    //关闭对话框
                    pd2.dismiss();
                    runOnUiThread(() -> Toast.makeText(MainActivity.this,"完成",Toast.LENGTH_SHORT).show());
                }
            }
        }.start();
    }
    public int doWork(){
        //为数组元素赋值
        data[hasData++] = (int) (Math.random()*100);
        try{
            Thread.sleep(100);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return hasData;
    }

//    //alertdialog用法
//    public void onClick(View V){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("尊敬的用户")
//                .setMessage("确认卸载？")
//                .setPositiveButton("确认卸载",(dialogInterface, i) -> {
//                    view1.setText("虽然依依不舍，但是只能卸载了");
//                })
//                .setNegativeButton("我再想想",(dialogInterface, i) -> {
//                    view1.setText("让我继续陪你");
//                });
//        builder.create().show();
//    }
}
