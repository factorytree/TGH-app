package com.example.myapplication2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by dell on 2019/5/23.
 */

public class SQL_database extends SQLiteOpenHelper {

    public static final String CREATE_User = "create table User(" +
            //primary key 将id列设为主键    autoincrement表示id列是自增长的
            "id integer primary key autoincrement," +
            "username text," +
            "pwd text," +
            "realname text," +
            "level text)";
    public static final String CREATE_Trash = "create table Trash(" +
            "id integer primary key autoincrement," +
            "trashname text," +
            "trashkind text," +
            "trashintro text)";



    public Context mContext;


    //构造方法：第一个参数Context，第二个参数数据库名，第三个参数cursor允许我们在查询数据的时候返回一个自定义的光标位置，一般传入的都是null，第四个参数表示目前库的版本号（用于对库进行升级）
    public  SQL_database(Context context,String name,SQLiteDatabase.CursorFactory factory , int version){
        super(context,name ,factory,version);

        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //调用SQLiteDatabase中的execSQL（）执行建表语句。
        db.execSQL(CREATE_User);
        db.execSQL(CREATE_Trash);
        //创建成功
        Log.i( "sql_db","Create succeeded");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //如果表已存在则删除表
        db.execSQL("drop table if exists User");
        db.execSQL("drop table if exists Trash");
        onCreate(db);
        Log.i("sql_db","Upgrading");
    }

    public void insertUser(ContentValues values){
        SQLiteDatabase db;
        db = getWritableDatabase();
        db.insert("User",null,values);
        Log.i("sql_db","add user");
    }

    public boolean query_user_exists(String name){
        boolean flag=false;
        SQLiteDatabase db;
        db = getReadableDatabase();
        String sql="select * from User where username=?";
        Cursor cursor=db.rawQuery(sql,new String[]{name});
        if(cursor.moveToFirst()) flag=true;//user exists
        cursor.close();
        return flag;
    }

    public String query_user_realname(String name){
        String realn="";
        SQLiteDatabase db;
        db = getReadableDatabase();
        String sql="select * from User where username=?";
        Cursor cursor=db.rawQuery(sql,new String[]{name});
        if(cursor.moveToFirst()) realn = cursor.getString(3);
        return realn;
    }

    public boolean query_pwd(String name,String pwd){
        boolean flag=false;
        SQLiteDatabase db;
        db=getReadableDatabase();
        String sql="select * from user where username=? and pwd=?";
        Cursor cursor=db.rawQuery(sql, new String[]{name,pwd});
        if(cursor.moveToFirst()) flag=true;
        cursor.close();
        return flag;
    }

    public void delete_user(String name){
        SQLiteDatabase db=getWritableDatabase();
        db.delete("User","username=?",new String[]{name});
    }

    public boolean modify_pwd(String name,String oldpwd,String newpwd){
        if(!query_pwd(name,oldpwd)) return false;
        SQLiteDatabase db=getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put("pwd",newpwd);
        db.update("User",value,"username=?",new String[]{name});
        return true;
    }

    public boolean modify_realn(String name,String newname){
        //if(!query_pwd(name,oldpwd)) return false;
        SQLiteDatabase db=getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put("realname",newname);
        db.update("User",value,"username=?",new String[]{name});
        return true;
    }

    public int query_level(String name){
        int level=0;
        String ass="0";
        SQLiteDatabase db;
        db=getReadableDatabase();
        String sql="select * from user where username=?";
        Cursor cursor=db.rawQuery(sql, new String[]{name});
        if(cursor.moveToFirst()) ass = cursor.getString(4);
        level=Integer.valueOf(ass).intValue();
        cursor.close();
        return level;
    }

    public boolean modify_level(String name,int level){
        //if(!query_pwd(name,oldpwd)) return false;
        SQLiteDatabase db=getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put("level",Integer.toString(level));
        db.update("User",value,"username=?",new String[]{name});
        return true;
    }










    public boolean query_trash_exists(String name){
        boolean flag=false;
        SQLiteDatabase db;
        db = getReadableDatabase();
        String sql="select * from Trash where trashname=?";
        Cursor cursor=db.rawQuery(sql,new String[]{name});
        if(cursor.moveToFirst()) flag=true;//trash exists
        cursor.close();
        return flag;
    }

    public void inserttrash(ContentValues values){
        SQLiteDatabase db;
        db = getWritableDatabase();
        db.insert("Trash",null,values);
        Log.i("sql_db","add trash");
    }

    public ArrayList<String> get_trashkind(String trashname){
        SQLiteDatabase db;
        db = getWritableDatabase();
        ArrayList<String> re=new ArrayList<String>();
        String kind="";
        String info="";
        String sql = "SELECT * FROM "+"Trash"+" where "+"trashname"+" like '%"+trashname+"%'";
        Cursor cursor=db.rawQuery(sql,null);
        while(cursor.moveToNext()) {
            kind = cursor.getString(2);
            info=kind+'\n'+cursor.getString(1)+'\n'+cursor.getString(3);
            re.add(info);
        }
        return re;
    }



/*
        while (c_test.moveToNext()) {
            String name = c_test.getString(c_test.getColumnIndex(tab_field02));
            //name.contains(str[0]);
            // 让集合中的数据不重复;
            if (!result_list.contains(name)) {
                result_list.add(name);
                Log.e("tag", name);
            }
        }
*/
}
