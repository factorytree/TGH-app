package com.example.myapplication2;

import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Created by dell on 2019/5/17.
 */

public class SearchControl {

    static public boolean init_trash_data(){
        SQL_database db=MainActivity.dbHelper;
        return !db.query_trash_exists("报纸");
    }

    static public String get_trash_kind(String trash_name){

        SQL_database db=MainActivity.dbHelper;
        ArrayList<String> x=new ArrayList<String>();
        String tmp="";
        String get="";
        if (!db.get_trashkind(trash_name).equals(""))x=db.get_trashkind(trash_name);
        //Log.i("trashkind",tmp);
        for(int i=0;i<x.size();i++){
            tmp=x.get(i);
            Log.i("trash",tmp);
            switch (tmp.charAt(0)){
                case '1':get+="\n\n"+"可回收垃圾"+tmp.substring(1,tmp.length());break;
                case '2':get+="\n\n"+"其他垃圾"+tmp.substring(1,tmp.length());break;
                case '3':get+="\n\n"+"厨余垃圾"+tmp.substring(1,tmp.length());break;
                case '4':get+="\n\n"+"有害垃圾"+tmp.substring(1,tmp.length());break;

            }
        }
        if(get.equals("")) get="没有找到呢，试试其他的吧";
        return get;
    }


}
