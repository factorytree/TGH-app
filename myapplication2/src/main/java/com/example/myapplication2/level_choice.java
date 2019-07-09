package com.example.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class level_choice extends AppCompatActivity {


    ArrayList<Button> levelbtn=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_choice);


        Button button_4 = (Button) findViewById(R.id.button4);
        Button button_5 =(Button) findViewById(R.id.button5);
        Button button_6 =(Button) findViewById(R.id.button6);
        Button re_level_scene=(Button)findViewById(R.id.returnbtn);
        levelbtn.add(button_4);
        levelbtn.add(button_5);
        levelbtn.add(button_6);


        re_level_scene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(level_choice.this,scene.class));
            }
        });

        for (int i=0;i<3;i++){
            Button tmp=levelbtn.get(i);
            final int x=i;
            tmp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int level=SelectControl.select_on_levelpage(x);
                    startActivity(new Intent(level_choice.this,game.class));
                }
            });
        }

    }
}