package com.android.ticket.test.login;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.SeatTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectSeatActivity extends Activity {
    public SeatTable seatTableView;
    private Button confirmButton;
    private TextView priceTextview;
    private final static String TAG = SelectSeatActivity.class.getSimpleName();
    private String[] key;
    private int number;
    private String totalPrice,fileName,hall,userName,date,time,price;

    private List<String> seatList = new ArrayList<>();   //用于存放选中的座位

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_seat);
        key = this.getIntent().getExtras().getStringArray("key");  //分别接受片名、影厅、日期、时间、票价、用户名

        seatTableView = (SeatTable) findViewById(R.id.seatView);
        confirmButton = (Button) findViewById(R.id.confirmButton);
        priceTextview = (TextView) findViewById(R.id.price);
        this.key = this.getIntent().getExtras().getStringArray("key");
        fileName = key[0];
        hall = key[1];
        date = key[2];
        time = key[3];
        price = key[4];
        userName = key[5];

        seatTableView.setScreenName(hall + "号厅荧幕");//设置屏幕名称
        seatTableView.setMaxSelected(8);//设置最多选中

        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if (column == 2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if (row == 6 && column == 6) {
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {

            }

            @Override
            public void unCheck(int row, int column) {

            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                String seat = row + "排" + column + "座";
                Log.e(TAG, "checkedSeatTxt: " + seat);
                for (String s : seatList) {
                    Log.e(TAG, "现在买的座位: " + s);
                }
                if (!seatList.contains(seat)) {
                    seatList.add(seat);
                }
                number = seatList.size();
                Log.e(TAG, "购买数量：" + number);
                totalPrice = Integer.toString(number * Integer.valueOf(price));
                priceTextview.setText(number * (Integer.valueOf(price)));
                return null;
            }

        });
        seatTableView.setData(10, 15);
        confirmBuy();
    }

    private void confirmBuy() {
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                Bundle bundle = new Bundle();
//                key[4] = totalPrice;   //单价替换成总价
                bundle.putStringArray("key", new String[]{fileName,hall,seatList.toString() ,date,time,Integer.toString(number),totalPrice,userName});   //分别发送片名、影厅、座位、日期、时间、票数、总票价、用户名
                it.setClass(SelectSeatActivity.this, SummitOrderActivity.class);
                it.putExtras(bundle);
                startActivity(it);
            }
        });
    }
}
