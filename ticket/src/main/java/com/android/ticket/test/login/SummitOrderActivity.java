package com.android.ticket.test.login;

import com.android.test.info.Purchase;
import com.android.test.info.Sold;
import com.android.test.info.handle.LoadUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class SummitOrderActivity extends Activity {

	private final static String TAG = SummitOrderActivity.class.getSimpleName();
	private String[] key;
    TextView usernameTextView = null,filenameTextView = null,dateTextView = null,timeTextView = null,
			quantityTextView = null,totalpriceTextView = null,hallTextView = null,seatTextView = null;
	Button btn = null;
	private String totalPrice,fileName,hall,seat,userName,date,time,price,number;
	private boolean hasSoldSeat = false;

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submit);
		usernameTextView = (TextView)findViewById(R.id.username);
		filenameTextView = (TextView)findViewById(R.id.filename);
		hallTextView = (TextView)findViewById(R.id.hall);
		dateTextView = (TextView)findViewById(R.id.date);
		timeTextView = (TextView)findViewById(R.id.time);
		seatTextView = (TextView)findViewById(R.id.seat);
		quantityTextView = (TextView)findViewById(R.id.quantity);
		totalpriceTextView = (TextView)findViewById(R.id.totalprice);
		btn = (Button)findViewById(R.id.sbtn);//提交订单

		this.key = this.getIntent().getExtras().getStringArray("key");  //分别接受片名、影厅、座位、日期、时间、票数、总票价、用户名
		fileName = key[0];
		hall = key[1];
		seat = key[2];
		date = key[3];
		time = key[4];
		number = key[5];
		totalPrice = key[6];
		userName = key[7];
		goSummit();
	}

	private void goSummit() {

		filenameTextView.setText(fileName);
		hallTextView.setText(hall);
		dateTextView.setText(date);
		timeTextView.setText(time);
		seatTextView.setText(seat.substring(1,seat.length()-1));  //去掉前后括号
		totalpriceTextView.setText(totalPrice);
		usernameTextView.setText(userName);
		quantityTextView.setText(number);

		Log.e(TAG, "getSoldSeat: " + seat );
		String soldSeatString = seat.substring(1,seat.length()-1);
		soldSeatString = soldSeatString.replace("排","-");
		soldSeatString = soldSeatString.replace("座","");
		Log.e(TAG, "goSummit: " + soldSeatString );
		String[] arr = soldSeatString.split(", ");
		List<String> list = Arrays.asList(arr);

		final StringBuilder sb  = new StringBuilder();
		sb.append(getSoldSeat());
		for (String s : list) {
			if (TextUtils.isEmpty(sb)){
				sb.append("[{" +s + "}" );
			}else{
				sb.append(",{"+ s+"}");
			}
		}
		sb.append("]");
		Log.e(TAG, "最终: " + sb );

		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (hasSoldSeat) {  //数据库已有怎更新，否则就插入
					Sold sold = LoadUtil.updateSold(fileName,date,hall,time,sb.toString());
				}else{
					Sold sold = LoadUtil.insertSold(userName,fileName,hall,date,time,sb.toString());
				}
			    Purchase film = LoadUtil.addTicket(userName,fileName,number,hall,seat,totalPrice,time);  //分别是用户名、片名、购买数量、影厅、座位、总价、时间
				if(film!=null)
				{
					Toast.makeText(SummitOrderActivity.this,"成功提交订单", Toast.LENGTH_SHORT).show();
					Intent it = new Intent();
					Bundle bundle = new Bundle();
					bundle.putStringArray("username", new String[]{"1"});
					it.setClass(SummitOrderActivity.this,MainmenuActivity.class);
					it.putExtra("username", userName);   //这里传参一定要是username
					startActivity(it);
				}
				else
				{
					Toast.makeText(SummitOrderActivity.this,"失败提交订单", Toast.LENGTH_SHORT).show();
					Intent it = new Intent();
					it.putExtra("username", key[6]);
					it.setClass(SummitOrderActivity.this, MainmenuActivity.class);
					startActivity(it);
				}
			}
		});
	}

	private String getSoldSeat(){
		String Query_seat = LoadUtil.getSoldSeat(fileName,date,hall,time);
		if (!TextUtils.isEmpty(Query_seat)){
			hasSoldSeat = true;
			String sold = Query_seat.substring(0,Query_seat.length()-1);   //查询已售座位，去掉最后一个大括号
			Log.e(TAG, "Query_seat: " + sold );
			return sold;
		}
		return "";
	}
}
