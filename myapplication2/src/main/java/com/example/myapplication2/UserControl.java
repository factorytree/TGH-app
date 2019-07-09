package com.example.myapplication2;

import android.content.ContentValues;
import android.util.Log;

/**
 * Created by dell on 2019/5/17.
 */

//修改姓名，修改密码，真实姓名


public class UserControl {

    //login to usercontrol
    //selectcontrol to gametimecontrol to usercontrol



    static public String login(String name,String pwd){


        SQL_database dbHelper=MainActivity.dbHelper;

        if(name.equals("")) return "1Username can't be empty!";
        else if(pwd.equals("")) return "2Password can't be empty!";
        else if(!dbHelper.query_user_exists(name)) return "3User doesn't exist!";
        else{
            if(dbHelper.query_pwd(name,pwd)) {
                Data.username = name;
                Data.pwd = pwd;
                Data.realname=dbHelper.query_user_realname(name);
                Data.level=dbHelper.query_level(name);
                Log.i("user",Data.realname);
                return "4Login successfully!";
            }
            else return "5Wrong password!";
        }
    }

    static public void levelup(){
        if(Data.current_level== Data.level) Data.level++;
        Log.i("level",Data.level+"");
        SQL_database dbHelper=MainActivity.dbHelper;
        dbHelper.modify_level(Data.username,Data.level);
        //TODO
    }

    static public String register_fun(String name,String pwd,String Rname){

        SQL_database dbHelper=MainActivity.dbHelper;
        ContentValues value=new ContentValues();


        if(name.equals("")) return "1Username can't be empty!";
        else if(pwd.equals("")) return "2Password can't be empty!";
        else if(Rname.equals("")) return "3Realname can't be empty!";
        else if (dbHelper.query_user_exists(name)) return "4User already exists!";//TODO: existing user
        else{
            value.put("username",name);
            value.put("pwd",pwd);
            value.put("realname",Rname);
            value.put("level","0");
            dbHelper.insertUser(value);
            Log.i("Add User", name+pwd+Rname);
            return "5Register succesfully!";
        }//TODO: insert user
    }
}
