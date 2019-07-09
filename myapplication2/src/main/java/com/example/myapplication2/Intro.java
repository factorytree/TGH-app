package com.example.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Intro extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        imageView=(ImageView)findViewById(R.id.image);
        imageView.setImageResource(R.drawable.intro_back);
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(3000);
        imageView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                        Intent intent=new Intent(Intro.this,MainActivity.class);
                        startActivity(intent);
                        Intro.this.finish();



            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}
