package com.example.day11;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AnimatorTest extends Activity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //alphaimageview
        //setContentView(R.layout.attr);


        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        LinearLayout container = (LinearLayout) findViewById(R.id.container);
              container.addView(new MyAnimationView(this));


        TextView tv = (TextView) findViewById(R.id.tv);
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv,"alpha",1f,0f,1f);
        animator.setDuration(3000);
        animator.start();
    }
    public class MyAnimationView extends View {
        public MyAnimationView(Context context){
            super(context);
            ObjectAnimator colorAnim = (ObjectAnimator) AnimatorInflater.loadAnimator(AnimatorTest.this,R.animator.color_anim);
            colorAnim.setTarget(this);
            colorAnim.start();
        }
    }
}
