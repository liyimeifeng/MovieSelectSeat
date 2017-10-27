package com.android.ticket.test.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


/**
 * 后台管理接口，影片的曾删改查
 */
public class MainmenuActivity1 extends Activity {
	TextView tv_name=null;
	Button b1=null;
	Button b2=null;
	Button b3=null;
	Button b4=null;
	Button bt_quitapp=null;
	String username = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu1);
		Intent intent = getIntent();		//获得启动该Activity的Intent
		username = intent.getStringExtra("username");
		TextView t1 = (TextView)findViewById(R.id.text1);
		TextView t2 = (TextView)findViewById(R.id.text2);
		t1.setText("当前管理员："+username);
		t2.setText("欢迎进入管理员系统");
		InitMenuEvent();
		QuitApp();
	}	
	
	private void InitMenuEvent() {
		b1=(Button)findViewById(R.id.button1);//查看所有电影信息
		b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				button1_handle();
			}
			private void button1_handle() {
				Intent it=new Intent();
				it.putExtra("username", username);
				it.setClass(MainmenuActivity1.this,DetailActivity.class);
		    	startActivity(it);
			}
		});
		
		b2=(Button)findViewById(R.id.button2);//添加
		b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				button2_handle();
			}
			private void button2_handle() {
				Intent it=new Intent();
				it.putExtra("username", username);
				it.setClass(MainmenuActivity1.this,InsertfilmActivity.class);
		    	startActivity(it);
			}
		});
		
		b3=(Button)findViewById(R.id.button3);//单个查询
		b3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				button3_handle();
			}
			private void button3_handle() {
				Intent it=new Intent();
				it.putExtra("username", username);
				it.setClass(MainmenuActivity1.this,SelectQueryActivity.class);
		    	startActivity(it);
			}
		});
		
		b4=(Button)findViewById(R.id.button4);//删除
		b4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				button4_handle();
			}
			private void button4_handle() {
				Intent it=new Intent();
				it.putExtra("username", username);
				it.setClass(MainmenuActivity1.this,DeleteMovieActivity.class);
		    	startActivity(it);
			}
		});
		
	}
	
	private void QuitApp() {//退出
		bt_quitapp=(Button)findViewById(R.id.quitapp);
		if(bt_quitapp!=null)
		bt_quitapp.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
}
