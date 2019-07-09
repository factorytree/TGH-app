package com.example.myapplication2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by dell on 2019/5/17.
 */

public class SelectControl {

    ArrayList<AppCompatActivity> activities = new ArrayList();
    static public int select_on_mainpage(int i){
        Data.current_game=i+1;
        return i;
    }
    static public int select_on_scenepage(int i){
        Data.current_scene=i+1;
        return i;
    }
    static public int select_on_levelpage(int i){
        Data.current_level=(Data.current_scene-1)*3+i+1;
        return i;
    }
    static public void next_level(){
        Data.current_level++;
        if(Data.current_level>Data.level) Data.level=Data.current_level;
        SQL_database dbHelper=MainActivity.dbHelper;
        dbHelper.modify_level(Data.username,Data.level);
        //TODO
    }

}
