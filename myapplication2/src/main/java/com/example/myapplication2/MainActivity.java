package com.example.myapplication2;

import android.animation.Animator;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.String;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.text.Editable;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Ev1_pwd, Ev2_name;
    String nameStr,pwdStr;



    private NBButton button,button2;
    private RelativeLayout rlContent;
    private Handler handler;
    private Animator animator;

    static public SQL_database dbHelper;




    private void gotoNew(int i) {
        NBButton btn;
        if(i==1) btn=button;
        else btn=button2;
        btn.gotoNew();
        final Intent intent;

        if(i==1) intent=new Intent(this,mainpage.class);
        else intent=new Intent(this,register.class);

        int xc=(btn.getLeft()+btn.getRight())/2;
        int yc=(btn.getTop()+btn.getBottom())/2;
        animator= ViewAnimationUtils.createCircularReveal(rlContent,xc,yc,0,1111);
        animator.setDuration(130);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);

                    }
                },200);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
        rlContent.getBackground().setAlpha(255);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置内容UI，把页面加载出来
        setContentView(R.layout.activity_main);
        //找到界面上的按钮

        dbHelper = new SQL_database(this,"TGH.db",null,8);

        Ev1_pwd = (EditText) findViewById(R.id.pswdtext);
        Ev2_name = (EditText) findViewById(R.id.nametext);
        nameStr = Ev2_name.getText().toString();
        pwdStr = Ev1_pwd.getText().toString();

        button = findViewById(R.id.button_test);

        rlContent = findViewById(R.id.rl_content);

        rlContent.getBackground().setAlpha(0);

        button2 = findViewById(R.id.button_reg);



        handler=new Handler();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp=UserControl.login(Ev2_name.getText().toString(),Ev1_pwd.getText().toString());
                char flag=tmp.charAt(0);
                String text=tmp.substring(1,tmp.length());
                if(flag=='4') {
                    button.startAnim();
                    button2.setVisibility(View.INVISIBLE);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gotoNew(1);
                        }
                    },1000);
                }
                else Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();



            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button2.startAnim();
                button.setVisibility(View.INVISIBLE);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gotoNew(2);
                    }
                },1000);

            }
        });


    }
}

