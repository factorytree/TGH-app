package com.example.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class mainpage extends AppCompatActivity {


    ArrayList<Button> mainpage_select=new ArrayList();
    ArrayList<AppCompatActivity> mainpageac=new ArrayList();
    Button trash_select,user_select,search_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        trash_select = (Button) findViewById(R.id.trash);
        user_select =(Button) findViewById(R.id.water);
        search_select = (Button) findViewById(R.id.search);
        mainpage_select.add(trash_select);
        mainpage_select.add(user_select);
        mainpage_select.add(search_select);


        for (int i=0;i<3;i++){
            Button se=mainpage_select.get(i);
            final int x=i;
            se.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int level=SelectControl.select_on_mainpage(x);
                    if(level==0) startActivity(new Intent(mainpage.this,scene.class));
                    else if(level==1) startActivity(new Intent(mainpage.this,userpage.class));
                    else if (level==2) startActivity(new Intent(mainpage.this,search.class));
                    else Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}