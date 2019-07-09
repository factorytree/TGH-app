package com.example.myapplication2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.lang.String;
import java.util.ArrayList;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.text.Editable;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

//Map<String level_item,String image_or_id>
//<"0101","trash_1_1"><"0102","trash_1_2"><"0103","trash_1_3"><"0104","timg.png">



//level theme
//1     Kitchen

public class game extends AppCompatActivity {

    GifImageView gifImageView;
    View layout;

    boolean[] trash_state={true,true,true},trash_wrong_state={false,false,false};
    ArrayList<Button> bin_all=new ArrayList(),trash_all=new ArrayList();
    int wrong_times=0;
    boolean trash_flag=true;
    boolean bin_flag =false;
    int now_trash_id=1;
    long com;
    int[] background={R.drawable.kkitchen,R.drawable.room,R.drawable.livingroom,R.drawable.waytoschool,R.drawable.classroom,R.drawable.garden,R.drawable.sea,R.drawable.amusementpark,R.drawable.mall};
    Button trash1, trash2, trash3, bin1, bin2, bin3, bin4,re_game_level, help;

//    String tl1="trash_"+Integer.toString(Data.current_level)+"_1";
//    String tl2="trash_"+Integer.toString(Data.current_level)+"_2";
//    String tl3="trash_"+Integer.toString(Data.current_level)+"_3";



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        LayoutInflater inflater = getLayoutInflater();
        layout = inflater.inflate(R.layout.activity_game, (ViewGroup) findViewById(R.id.custom_toast_container));
        trash1 = (Button) findViewById(R.id.trash_1);
        trash2 = (Button) findViewById(R.id.trash_2);
        trash3 = (Button) findViewById(R.id.trash_3);
        help = (Button) findViewById(R.id.help);
        bin1 = (Button) findViewById(R.id.bin_1);
        bin2 = (Button) findViewById(R.id.bin_2);
        bin3 = (Button) findViewById(R.id.bin_3);
        bin4 = (Button) findViewById(R.id.bin_4);
        re_game_level=(Button) findViewById(R.id.returnbtn);

        bin_all.add(bin1);
        bin_all.add(bin2);
        bin_all.add(bin3);
        bin_all.add(bin4);
        trash_all.add(trash1);
        trash_all.add(trash2);
        trash_all.add(trash3);
        getWindow().setBackgroundDrawableResource(background[Data.current_level-1]);
        if(Data.current_level==2)
        {
            trash1.setBackgroundResource(R.drawable.trash_4);
            trash2.setBackgroundResource(R.drawable.trash_5);
            trash3.setBackgroundResource(R.drawable.trash_6);
        }
//        trash1.setText(tl1);
//        trash2.setText(tl2);
//        trash3.setText(tl3);

        gifImageView = findViewById(R.id.gif_image);
        // mipmap 中有动态图 timg.gif

        GifDrawable gifDrawable = null;
        try {
            gifDrawable = new GifDrawable(getResources(), R.mipmap.good);
        } catch (Exception e){
            e.printStackTrace();
        }

        gifImageView.setImageDrawable(gifDrawable);




        re_game_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(game.this,level_choice.class));
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomizeDialog();
            }
        });




        for(int i=0;i<3;i++){
            Button trash=trash_all.get(i);
            final int x=i;
            trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!trash_flag) return;
                    now_trash_id = x+1;
                    trash_flag = false;
                    bin_flag = true;
                }
            });
        }



        for (int i=0;i<4;i++){
            Button bin= bin_all.get(i);
            final Button current_trash=trash_all.get(now_trash_id-1);
            final int x = i;
//            trash_state.add(new Boolean(true));
//            trash_state.add(new Boolean(true));
//            trash_state.add(new Boolean(true));
//            trash_wrong_state.add(new Boolean(false));
//            trash_wrong_state.add(new Boolean(false));
//            trash_wrong_state.add(new Boolean(false));


//            Boolean _bin_flag = new Boolean(bin_flag),_trash_flag=new Boolean(trash_flag);
//            Integer _now_trash_id=new Integer(now_trash_id),_wrong_times=new Integer(wrong_times);
//            GameRubbishBinInfo game_tmp_var= new GameRubbishBinInfo(_bin_flag,_trash_flag,_now_trash_id,x,_wrong_times,trash_wrong_state,trash_state);
//            GameTrashButtonListener listener = new GameTrashButtonListener(game_tmp_var,game.this,game.this,layout,current_trash);
//            bin.setOnClickListener(listener);



            bin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!bin_flag) return;


                    trash_flag = true;
                    bin_flag = false;
                    if (!(now_trash_id == (1+x))) {
                        wrong_times++;
                        trash_wrong_state[now_trash_id-1]=true;
                        if(wrong_times<3) {
                            showNormalDialog();
                        }
                    }
                    else {
                        trash_all.get(now_trash_id-1).setVisibility(View.INVISIBLE);
                        trash_state[now_trash_id-1]=false;
                    }

                    if (wrong_times == 3) {
                        showFailedDialog();
//                        Toast fail_toast = Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_LONG);
//                        fail_toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//                        fail_toast.show();
                        return ;
                        // TODO: jump!!!!!!!!!!
                    }

                    boolean success = true;
                    int j = 0;
                    for (j = 0; j <= 2; j++) {
                        if (trash_state[j]) {
                            success = false;
                            break;
                        }
                    }
                    if (success) {
                        UserControl.levelup();
                        showSuccessDialog();
//                        Toast success_toast = Toast.makeText(getApplicationContext(), "Congratulations!", Toast.LENGTH_LONG);
//                        success_toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//                        success_toast.show();
                        // TODO: jump!!!!!!!!!!
                    }

                }
            });
        }
    }




    // TODO : NEED PARAMETERS
    private void showNormalDialog( ){
            /* @setIcon 设置对话框图标
             * @setTitle 设置对话框标题
             * @setMessage 设置对话框消息提示
             * setXXX方法返回Dialog对象，因此可以链式设置属性
             */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(game.this);
        final View failed_view1 = LayoutInflater.from(game.this).inflate(R.layout.failed_view_3, null);
        normalDialog.setIcon(R.drawable.ic_launcher_foreground);
        normalDialog.setView(failed_view1);
        normalDialog.setTitle("Failed!");
        normalDialog.setMessage("亲，这边建议再考虑考虑呢~~");
        normalDialog.setPositiveButton("好哒",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        normalDialog.show();
    }

    private void showCustomizeDialog() {
    /* @setView 装入自定义View ==> R.layout.dialog_customize
     * 由于dialog_customize.xml只放置了一个EditView，因此和图8一样
     * dialog_customize.xml可自定义更复杂的View
     */
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(game.this);
        final View dialogView = LayoutInflater.from(game.this)
                .inflate(R.layout.failed_view_3,null);
        customizeDialog.setTitle("成为高手的武林秘籍");
        customizeDialog.setView(dialogView);
        customizeDialog.setMessage("在本关卡中，你需要为丢弃的垃圾选择合适的垃圾桶。如果你选择成功，垃圾就会消失不见啦；否则垃圾将仍然留在原地。当你成功清除所有垃圾，就恭喜你挑战成功。请认真思考后做出选择哦，因为你只有三次错误机会。");
        customizeDialog.setPositiveButton("已阅",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
        customizeDialog.show();
    }


    private void showFailedDialog( ){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(game.this);
        final View failed_view1 = LayoutInflater.from(game.this).inflate(R.layout.failed_view_3, null);
        normalDialog.setIcon(R.drawable.ic_launcher_foreground);
        normalDialog.setView(failed_view1);
        normalDialog.setTitle("Failed!");
        normalDialog.setMessage("游戏失败，您可以选择重新闯关或者返回");
        normalDialog.setPositiveButton("重新闯关",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(game.this,game.class));
                    }
                });
        normalDialog.setNegativeButton("返回",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(game.this,level_choice.class));
                    }
                });
        // 显示
        normalDialog.show();
    }


    private void showSuccessDialog( ){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(game.this);
        final View failed_view1 = LayoutInflater.from(game.this).inflate(R.layout.failed_view_3, null);
        normalDialog.setIcon(R.drawable.ic_launcher_foreground);
        normalDialog.setView(failed_view1);
        normalDialog.setTitle("Success!");


        normalDialog.setMessage("游戏成功，您可以选择进入下一关或者返回");
        normalDialog.setPositiveButton("下一关",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SelectControl.next_level();
                        startActivity(new Intent(game.this,game.class));
                    }
                });
        normalDialog.setNegativeButton("返回",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(game.this,level_choice.class));
                    }
                });
        // 显示
        normalDialog.show();
    }
}
