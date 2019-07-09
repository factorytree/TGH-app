package com.example.myapplication2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class userpage extends AppCompatActivity {

    TextView username, real, level;
    Button dell, log_out, changename;
    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpage);


        username = (TextView) findViewById(R.id.username) ;
        real = (TextView) findViewById(R.id.realname) ;
        level = (TextView) findViewById(R.id.level) ;

        dell = (Button) findViewById(R.id.delett) ;
        log_out = (Button) findViewById(R.id.logout) ;
        changename = (Button) findViewById(R.id.change_name);




        String lelll = Integer.toString(Data.level);

        username.setText(Data.username);
        real.setText(Data.realname);
        level.setText(lelll);

        dell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQL_database db=MainActivity.dbHelper;
                db.delete_user(Data.username);
                startActivity(new Intent(userpage.this,MainActivity.class));
            }
        });

        changename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert_edit();

            }
        });

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(userpage.this,MainActivity.class));
            }
        });

    }


    public void alert_edit(){
        final EditText et = new EditText(this);
        new AlertDialog.Builder(this).setTitle("你有更好听的昵称吗？")
                .setIcon(android.R.drawable.sym_def_app_icon)
                .setView(et)
                .setPositiveButton("这个更好", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //按下确定键后的事件
                        real.setText(et.getText().toString());
                        SQL_database db=MainActivity.dbHelper;
                        db.modify_realn(Data.username,et.getText().toString());
                        Data.realname = et.getText().toString();

                          }
                }).setNegativeButton("算了算了",null).show();
    }
}
