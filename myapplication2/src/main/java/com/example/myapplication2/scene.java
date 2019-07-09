package com.example.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class scene extends AppCompatActivity {


    ArrayList<Button> scenepage_select=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);

        Button button_1 = (Button) findViewById(R.id.button4);
        Button button_2 =(Button) findViewById(R.id.button2);
        Button button_3 = (Button) findViewById(R.id.button3);
        Button re_scene_main=(Button)findViewById(R.id.returnbtn);
        scenepage_select.add(button_1);
        scenepage_select.add(button_2);
        scenepage_select.add(button_3);


        re_scene_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(scene.this,mainpage.class));
            }
        });

        for(int i=0;i<3;i++){
            Button btn=scenepage_select.get(i);
            final int x=i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int scene=SelectControl.select_on_scenepage(x);
                    startActivity(new Intent(scene.this,level_choice.class));
                }
            });
        }

    }
}