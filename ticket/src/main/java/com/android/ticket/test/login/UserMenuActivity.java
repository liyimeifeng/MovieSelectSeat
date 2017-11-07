package com.android.ticket.test.login;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


import com.android.test.info.DBManager;
import com.android.test.info.LoginInfo;

import java.util.Arrays;
import java.util.List;

public class UserMenuActivity extends Activity {
    TextView tv_name = null;
    Button b1 = null;
    Button b2 = null;
    Button b3 = null;
    Button b4 = null;
    Button bt_quitapp = null;
    String username = null;
    private final static String TAG = UserMenuActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        //	Toast.makeText(UserMenuActivity.this, username, Toast.LENGTH_SHORT).show();
        TextView t1 = (TextView) findViewById(R.id.text1);
        TextView t2 = (TextView) findViewById(R.id.text2);
        b1 = (Button) findViewById(R.id.button1);//购票
//        b2 = (Button) findViewById(R.id.button2);//退票
        b3 = (Button) findViewById(R.id.button3);//查询
        b4 = (Button) findViewById(R.id.button4);//查看订单
        bt_quitapp = (Button) findViewById(R.id.quitapp); //退出

        Intent intent = getIntent();        //获得启动该Activity的Intent
        username = intent.getStringExtra("username");
        t1.setText("当前用户：" + username);
        t2.setText("欢迎进入电影订票系统");
        InitMenuEvent();
        QuitApp();
//        List<LoginInfo> list = DBManager.getInstance(this).queryLoginInfoList();
        final DBManager dbManager = DBManager.getInstance(this);


//        Log.e(TAG, "onCreate: " + list);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    LoginInfo user = new LoginInfo();
                    user.setId( i + "");
                    user.setEmail("1qq");
                    user.setPwd("qqq");
                    user.setTel("139");
                    user.setUser_id(1000);
                    dbManager.insertLoginInfo(user);
                }
            }
        }).start();
    }

    private void InitMenuEvent() {
        b1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent();
                it.putExtra("username", username);
                it.setClass(UserMenuActivity.this, ToBuyActivity.class);
                startActivity(it);
            }
        });

        b4.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent();
                it.putExtra("username", username);
                Log.e(TAG, "username: " + username );
                it.setClass(UserMenuActivity.this, ViewOrederActivity.class);
                startActivity(it);            }
        });


        /**
         *去掉退票、查询
         */
//        b2.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                Intent it = new Intent();
//                it.putExtra("username", username);
//                it.setClass(UserMenuActivity.this, ToReturnActivity.class);
//                startActivity(it);            }
//        });

        b3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent();
                it.putExtra("username", username);
                it.setClass(UserMenuActivity.this, SelectQueryActivity.class);
                startActivity(it);            }
        });
    }

    private void QuitApp() {//退出
        if (bt_quitapp != null)
            bt_quitapp.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(UserMenuActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            });
    }
}