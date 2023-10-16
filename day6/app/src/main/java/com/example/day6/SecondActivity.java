package com.example.day6;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SecondActivity extends Activity {

    private TextView txtshow;
    private String name;
    private String sex;
    private Button back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity2);

        txtshow = (TextView)findViewById(R.id.txtshow);
        back = (Button)findViewById(R.id.back);
        //获得Intent对象,并且用Bundle出去里面的数据
        Intent it = getIntent();
        Bundle bd = it.getExtras();

        //按键值的方式取出Bundle中的数据
        name = bd.getCharSequence("user").toString();
        sex = bd.getCharSequence("sex").toString();
        txtshow.setText("尊敬的"+name + " " + sex + "士"+"恭喜你,注册成功~");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                //界面切换动画
                overridePendingTransition(R.anim.fade, R.anim.fade);
            }
        });

    }


}
