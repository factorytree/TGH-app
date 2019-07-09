package com.example.myapplication2;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class search extends AppCompatActivity {



    EditText searchcontent;
    Button bt_s;
    TextView searchresult;

    SQL_database dbHelper=MainActivity.dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);




        searchcontent = (EditText) findViewById(R.id.search_text);
        bt_s=(Button)findViewById(R.id.search);
        searchresult=(TextView)findViewById(R.id.search_result);




        bt_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            String con=searchcontent.getText().toString();
            if(SearchControl.init_trash_data()){
                InputStream input=getResources().openRawResource(R.raw.trash);
                InputStreamReader inputStreamReader = null;
                try {
                    inputStreamReader = new InputStreamReader(input, "gbk");
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                BufferedReader reader = new BufferedReader(inputStreamReader);

                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        int len=line.length(),index1=line.indexOf('*'),index2=line.indexOf('%');
                        String trashname=line.substring(0,index1);
                        String trashkind=line.substring(index1+1,index2);
                        String trashintro=line.substring(index2+1,len);
                        ContentValues value=new ContentValues();
                        value.put("trashname",trashname);
                        value.put("trashkind",trashkind);
                        value.put("trashintro",trashintro);
                        dbHelper.inserttrash(value);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String res=SearchControl.get_trash_kind(con);
            searchresult.setText(res);
            }
        });
    }


}
