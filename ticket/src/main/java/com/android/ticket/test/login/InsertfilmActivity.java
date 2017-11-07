package com.android.ticket.test.login;

import com.android.test.info.MovieInfo;
import com.android.test.info.handle.LoadUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 管理员添加影片
 */
public class InsertfilmActivity extends Activity {
    Button btn = null;
    private String[] key;
    TextView tv1 = null;
    TextView tv2 = null;
    TextView tv3 = null;
    TextView tv4 = null;
    TextView tv5 = null;
    TextView tv6 = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertfilm);
        btn = (Button) findViewById(R.id.btn);
        goSummit();
        //ButtonClickListener();
    }

    public void goSummit() {
        btn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                tv1 = (TextView) findViewById(R.id.label1);
                tv2 = (TextView) findViewById(R.id.label2);
                tv3 = (TextView) findViewById(R.id.label3);
                tv4 = (TextView) findViewById(R.id.label4);
                tv5 = (TextView) findViewById(R.id.label5);
                tv6 = (TextView) findViewById(R.id.label6);
                MovieInfo film = LoadUtil.addfilm(tv1.getText().toString().trim(), tv2.getText().toString().trim(), tv3.getText().toString().trim(), tv4.getText().toString().trim(), tv5.getText().toString().trim(), tv6.getText().toString().trim());
                if (film != null) {
                    Toast.makeText(InsertfilmActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putStringArray("username", new String[]{"admin"});
                    it.setClass(InsertfilmActivity.this, AdminMenuActivity.class);
                    it.putExtra("username", "admin");
                    startActivity(it);
                } else {
                    Toast.makeText(InsertfilmActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent();
                    it.putExtra("username", new String[]{"admin"});
                    it.setClass(InsertfilmActivity.this, AdminMenuActivity.class);
                    startActivity(it);
                }
            }
        });
    }
}