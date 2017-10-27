package com.android.ticket.test.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectQueryActivity extends Activity {
	Button btn1 = null;
	Button btn2 = null;
	String username = null;
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.selectquery);
			Intent intent = getIntent();		//获得启动该Activity的Intent
			username = intent.getStringExtra("username");
			btn1 = (Button)this.findViewById(R.id.btn_selequery1);
			btn2 = (Button)this.findViewById(R.id.btn_selequery2);
			btn1.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					Intent it = new Intent();
					it.putExtra("username", username);
					it.setClass(SelectQueryActivity.this, ToQueryActivity.class);
					startActivity(it);
				}
			});
			
			btn2.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					Intent it = new Intent();
					it.putExtra("username", username);
					it.setClass(SelectQueryActivity.this, QuerybyDistrictActivity.class);
					startActivity(it);	
				}
			});
		}
}
