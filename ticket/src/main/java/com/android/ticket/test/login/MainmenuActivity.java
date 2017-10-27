package com.android.ticket.test.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainmenuActivity extends Activity {
    TextView tv_name = null;
    Button b1 = null;
    Button b2 = null;
    Button b3 = null;
    Button b4 = null;
    Button bt_quitapp = null;
    String username = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        //	Toast.makeText(MainmenuActivity.this, username, Toast.LENGTH_SHORT).show();
        TextView t1 = (TextView) findViewById(R.id.text1);
        TextView t2 = (TextView) findViewById(R.id.text2);
        b1 = (Button) findViewById(R.id.button1);//购票
        b2 = (Button) findViewById(R.id.button2);//退票
        b3 = (Button) findViewById(R.id.button3);//查询
        b4 = (Button) findViewById(R.id.button4);//查看订单
        bt_quitapp = (Button) findViewById(R.id.quitapp); //退出

        Intent intent = getIntent();        //获得启动该Activity的Intent
        username = intent.getStringExtra("username");
        t1.setText("当前用户：" + username);
        t2.setText("欢迎进入电影订票系统");
        InitMenuEvent();
        QuitApp();
    }

    private void InitMenuEvent() {
        b1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent();
                it.putExtra("username", username);
                it.setClass(MainmenuActivity.this, ToBuyActivity.class);
                startActivity(it);
            }
        });

        b4.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent();
                it.putExtra("username", username);
                it.setClass(MainmenuActivity.this, ViewOrederActivity.class);
                startActivity(it);            }
        });


        /**
         *去掉退票、查询
         */
        b2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent();
                it.putExtra("username", username);
                it.setClass(MainmenuActivity.this, ToReturnActivity.class);
                startActivity(it);            }
        });

        b3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent();
                it.putExtra("username", username);
                it.setClass(MainmenuActivity.this, SelectQueryActivity.class);
                startActivity(it);            }
        });
    }

    private void QuitApp() {//退出
        if (bt_quitapp != null)
            bt_quitapp.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    finish();
                }
            });
    }
}