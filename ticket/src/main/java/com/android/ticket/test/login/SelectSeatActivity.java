package com.android.ticket.test.login;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.SeatTable;
import com.android.test.info.handle.LoadUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class SelectSeatActivity extends Activity {
    public SeatTable seatTableView;
    private Button confirmButton;
    private TextView priceTextview;
    private final static String TAG = SelectSeatActivity.class.getSimpleName();
    private String[] key;
    private int number = 0;
    private String totalPrice,fileName,hall,userName,date,time,price;

    private List<String> seatList = new ArrayList<>();   //用于存放选中的座位(几排几列)
    private List<Integer> rowList = new ArrayList<>();    //用于存放“行”数字
    private List<Integer> columnList = new ArrayList<>();  //用于存放“列”数字
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_seat);

        key = this.getIntent().getExtras().getStringArray("key");  //分别接受片名、影厅、日期、时间、票价、用户名

        seatTableView = (SeatTable) findViewById(R.id.seatView);
        confirmButton = (Button) findViewById(R.id.confirmButton);
        priceTextview = (TextView) findViewById(R.id.price);
        this.key = this.getIntent().getExtras().getStringArray("key");
        seatTableView.setData(10, 15);  //10排15座


        fileName = key[0];
        hall = key[1];
        date = key[2];
        time = key[3];
         price = key[4];
        userName = key[5];
        String Query_seat = LoadUtil.getSoldSeat(fileName,date,hall,time);

        if (!TextUtils.isEmpty(Query_seat)){
            String demo = Query_seat.substring(1,Query_seat.length()-1);
            Log.e(TAG, "Query_seat: " + demo );
            int rowSold = 0;
            int columnSold = 0;
            String[] arr = demo.split(",");
            List<String> list = Arrays.asList(arr);

            for (String s : list) {
//            Log.e(TAG, "s: " + s );
                String[] temp = s.split("-");
                rowSold = Integer.valueOf(temp[0].substring(1,temp[0].length()));
                columnSold = Integer.valueOf(temp[1].substring(0,temp[1].length()-1));
                rowList.add(rowSold);
                columnList.add(columnSold);
//            Log.e(TAG, "onCreate: 行" + rowSold + "列" + columnSold );
            }
        }

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
                if (row == 1 && column == 6) {
                    return true;
                }
                for (int i = 0; i < rowList.size(); i++) {
                    if (row == rowList.get(i)-1 && column == columnList.get(i)){
                        return true;
                    }
                }

                return false;
            }

            @Override
            public void checked(int row, int column) {
                number = number + 1;
                setTotalPrice(number);
                String seat = row + "排" + column + "座";
                if (!seatList.contains(seat)) {
                    seatList.add(seat);
                }

//                Log.e(TAG, "checked: " + row + "/" + column );
            }

            @Override
            public void unCheck(int row, int column) {
                number = number - 1;
                setTotalPrice(number);
                String seat = row + "排" + column + "座";
                if (seatList.contains(seat)) {
                    seatList.remove(seat);
                }

            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {

                return null;
            }

        });

        confirmBuy();
    }

    private void setTotalPrice(int seatNumber){
        totalPrice = Integer.toString(seatNumber * Integer.valueOf(price));
        priceTextview.setText(totalPrice);
    }

    private void confirmBuy() {
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                Bundle bundle = new Bundle();
                String temp = seatList.toString().substring(1,seatList.toString().length()-1);
                String[] arr = temp.split(", ");
                List<String> list = Arrays.asList(arr);

                bundle.putStringArray("key", new String[]{fileName,hall,seatList.toString() ,date,time,Integer.toString(number),totalPrice,userName});   //分别发送片名、影厅、座位、日期、时间、票数、总票价、用户名
                it.setClass(SelectSeatActivity.this, SummitOrderActivity.class);
                it.putExtras(bundle);
                startActivity(it);
            }
        });
    }
}
