package com.example.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class register extends AppCompatActivity {

    EditText newname,newid,newrname;
    Button btn_newre;
    String name_con,id_con,rname_con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        newname = (EditText) findViewById(R.id.re_name_input);
        newid=(EditText)findViewById(R.id.re_id_input);
        newrname=(EditText)findViewById(R.id.re_rn_input);
        btn_newre=(Button)findViewById(R.id.re_login);


        btn_newre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                name_con=newname.getText().toString();
                id_con=newid.getText().toString();
                rname_con=newrname.getText().toString();
                String tmp=UserControl.register_fun(name_con,id_con,rname_con);
                char flag=tmp.charAt(0);
                String text=tmp.substring(1,tmp.length());
                Log.i("in register",tmp);
                if(flag!='5') Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                else {
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(register.this,MainActivity.class));
                }
            }
        });

    }
}
