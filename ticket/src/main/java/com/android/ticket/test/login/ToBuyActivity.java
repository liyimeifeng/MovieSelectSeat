package com.android.ticket.test.login;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Vector;

import com.android.test.info.handle.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ToBuyActivity extends Activity {
    private final static String TAG = ToBuyActivity.class.getSimpleName();
    String username = null;    //声明表示访问者，即使用搜索功能的用户ID
    Button btnGo1 = null;
    Button btnGo2 = null;
    Calendar c;
    Button setStartDataButton = null;
    TextView filmnameLabel = null;
    Button setfilmnameLabelButton = null;
    Button searcherButton = null;
    EditText filmnameLabelText = null;
    BaseAdapter ba = null;
    String[][] msgg = new String[][]{{""}};//声明引用
    TextView startDataLabel = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy);
//		btnGo1 = (Button)findViewById(R.id.ticketSearch_button);//查询
//		setStartDataButton = (Button) this.findViewById(R.id.startData_button); //日期设置
//		setfilmnameLabelButton = (Button) this.findViewById(R.id.filmname_button); //电影名设置

        btnGo2 = (Button) findViewById(R.id.ticketBuy_button);//购票
        startDataLabel = (TextView) this.findViewById(R.id.startData_label);//日期
        filmnameLabel = (TextView) this.findViewById(R.id.filmname_label);

        Intent i = getIntent();
        username = i.getStringExtra("username");//visitor==username
        CreatTable.creattable();
        goBuy();
    }

    public void goBuy() {//日期，电影名查询
        c = Calendar.getInstance();
        startDataLabel.setText((c.get(Calendar.MONDAY) + 1) + "月" + c.get(Calendar.DAY_OF_MONTH) + "日");


        /**
         * 去掉日期设置、电影设置
         */
//		setStartDataButton.setOnClickListener(this);
//		setfilmnameLabelButton.setOnClickListener(this);
//        btnGo1.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				if(validate())
//				{
//				 String filmname = filmnameLabel.getText().toString().trim();
//				 String date = startDataLabel.getText().toString().trim();
//				 //displayText(date);
//				 Vector<Vector<String>> Query_filmname;
//				 Query_filmname = LoadUtil.getSameVector1(filmname,date);
//				 if(Query_filmname.size()==0)
//				{
//					Toast.makeText(ToBuyActivity.this, "对不起，没有相关的信息!!!", Toast.LENGTH_SHORT).show();
//						//etKeyword1.setText("");etKeyword2.setText("");
//					return;
//				}
//				else
//				{
//				 String[][] msgInfo=new String[Query_filmname.elementAt(0).size()][Query_filmname.size()];//新建和结果向量对应的数组
//				 for(int i=0;i<Query_filmname.size();i++)
//				  {//for循环将结果向量中的数据导入数组
//					for(int j=0;j<Query_filmname.elementAt(0).size();j++)
//						{
//							msgInfo[j][i]=(String)Query_filmname.get(i).get(j);
//						}
//					}
//					goToListView(msgInfo);//切换到查询结果显示界面ListView界面
//				}
//			}
//		}
//        });

        btnGo2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (validate()) {
                    {
                        String filename = filmnameLabel.getText().toString().trim();
                        String date = startDataLabel.getText().toString().trim();
                        filename = "港囧";
                        date = "10月28日";
                        //displayText(date);
                        Vector<Vector<String>> Query_filmname;
                        Query_filmname = LoadUtil.getSameVector2(filename, date);
//                        if (Query_filmname.size() == 0) {
//                            Toast.makeText(ToBuyActivity.this, "对不起，没有相关的信息!!!", Toast.LENGTH_SHORT).show();
//                            //etKeyword1.setText("");etKeyword2.setText("");
//                            return;
//                        } else {
                        Log.e(TAG, "onClick: Query_filmname\n" + Query_filmname + "\nQuery_filmname.elementAt(0) : \n" + Query_filmname.elementAt(0)
                                + "\nQuery_filmname.elementAt(0).size() : \n" + Query_filmname.elementAt(0).size() + "\nQuery_filmname.size()\n :" + Query_filmname.size());
                            String[][] msgInfo = new String[Query_filmname.elementAt(0).size()][Query_filmname.size()];//新建和结果向量对应的数组
                            for (int i = 0; i < Query_filmname.size(); i++) {//for循环将结果向量中的数据导入数组
                                for (int j = 0; j < Query_filmname.elementAt(0).size(); j++) {
                                    msgInfo[j][i] = (String) Query_filmname.get(i).get(j);
                                    Log.e(TAG, "onClick: msgInfo["+ j + "][" +i + "]---->" +msgInfo[j][i] );
                                }
                            }
                        Log.e(TAG, "msgInfo----> " + msgInfo.toString());
                            goToListView1(msgInfo);//切换到查询结果显示界面ListView界面
//                        }
                    }
                }
            }
        });
    }

    public boolean validate() {
        String filmname = filmnameLabel.getText().toString().trim();
        if (filmname.equals("")) {
            Toast.makeText(ToBuyActivity.this, "请输入电影名", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void goToListView1(final String[][] mssg) {
        msgg = mssg;//赋值引用给全局数组，用来实现返回按钮功能
        setContentView(R.layout.buydetail);//切换界面
        final String[][] msg = mssg;//新建数组，并赋值
        ListView lv_detail = (ListView) this.findViewById(R.id.ListView_detail);//拿到ListView的引用
        BaseAdapter ba_detail = new BaseAdapter()//新建适配器
        {
            public int getCount() {
                Log.e(TAG,"msg: " + Arrays.deepToString(msg) + "   msg[0].length: " + msg[0].length  + " / " + Arrays.toString(msg[0]));
                return msg[0].length;//得到列表的长度
            }

            public Object getItem(int arg0) {
                return null;
            }

            public long getItemId(int arg0) {
                return 0;
            }

            public View getView(final int arg0, View arg1, ViewGroup arg2)//为每一项添加内容
            {
                LinearLayout ll_detail = new LinearLayout(ToBuyActivity.this);
                ll_detail.setOrientation(LinearLayout.HORIZONTAL);//设置朝向,水平
                ll_detail.setPadding(5, 5, 5, 5);//四周留白
                Log.e(TAG, "msg.length: " + msg.length + "   /arg0: " + arg0);
                for (int i = 0; i < msg.length; i++)//为每一行设置显示的数据
                {
                    TextView s = new TextView(ToBuyActivity.this);
                    s.setText(msg[i][arg0]);//TextView中显示的文字
                    s.setTextSize(14);//字体大小
                    s.setPadding(1, 2, 2, 1);//四周留白
                    s.setWidth(150);//宽度
                    s.setGravity(Gravity.CENTER);
                    ll_detail.addView(s);//放入LinearLayout
                }
                Button b = new Button(ToBuyActivity.this);
                b.setText("预定");
                b.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {//跳转到交订单界面
//                        Intent it = new Intent();
//                        Bundle bundle = new Bundle();
//                        bundle.putStringArray("key", new String[]{username, msg[0][arg0], msg[2][arg0], msg[3][arg0]});
//                        it.setClass(ToBuyActivity.this, SummitOrderActivity.class);
//                        it.putExtras(bundle);
//                        startActivity(it);
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putStringArray("key",new String[]{msg[0][arg0],msg[1][arg0], msg[2][arg0], msg[3][arg0],msg[4][arg0],username});  //分别传送片名、影厅、日期、时间、票价、用户名
                        intent.setClass(ToBuyActivity.this,SelectSeatActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                ll_detail.addView(b);//放入LinearLayout
                return ll_detail;//将此LinearLayout返回
            }
        };
        lv_detail.setAdapter(ba_detail);//将适配器添加进ListView    
    }

    /**
     * 取消设置日期、设置电影名功能
     */
//	public void onClick(View v) {
//		switch (v.getId()) {
//			case R.id.startData_button:
//				new DatePickerDialog(ToBuyActivity.this,	new DatePickerDialog.OnDateSetListener() {
//					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//						String startData =(monthOfYear + 1)+ "月" + dayOfMonth+ "日";
//						((TextView) ToBuyActivity.this.startDataLabel).setText(startData);
//					}
//				}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
//				break;
//			case R.id.filmname_button:
//				LayoutInflater factory = LayoutInflater.from(ToBuyActivity.this);
//				View myView = factory.inflate(R.layout.checkfilmname, null);
//				filmnameLabelText = (EditText) myView.findViewById(R.id.filmnameEditText);
//				final AlertDialog dialog = new AlertDialog.Builder(this).setTitle("电影名").setView(myView).setPositiveButton("确定",new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int which) {
//						String filmname = filmnameLabelText.getText().toString();
//						if (filmname.trim().equals("")	|| filmname.trim().equals("")) {
//							displayText("请正确输入");
//						} else {
//							ToBuyActivity.this.filmnameLabel.setText(filmname);
//						}
//					}
//				}).setNegativeButton("取消",	new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int which) {
//						displayText("我取消这样选择了");
//						dialog.cancel();
//					}
//				}).create();
//				dialog.show();
//				break;
//		}
//	}

//    public void goToListView(String[][] mssg) {
//        msgg = mssg;//赋值引用给全局数组，用来实现返回按钮功能
//        setContentView(R.layout.detail);//切换界面
//        final String[][] msg = mssg;//新建数组，并赋值
//        ListView lv_detail = (ListView) this.findViewById(R.id.ListView_detail);//拿到ListView的引用
//        BaseAdapter ba_detail = new BaseAdapter()//新建适配器
//        {
//            public int getCount() {
//                return msg[0].length;//得到列表的长度
//            }
//
//            public Object getItem(int arg0) {
//                return null;
//            }
//
//            public long getItemId(int arg0) {
//                return 0;
//            }
//
//            public View getView(int arg0, View arg1, ViewGroup arg2)//为每一项添加内容
//            {
//                LinearLayout ll_detail = new LinearLayout(ToBuyActivity.this);
//                ll_detail.setOrientation(LinearLayout.HORIZONTAL);        //设置朝向
//                ll_detail.setPadding(5, 5, 5, 5);//四周留白
//                for (int i = 0; i < msg.length; i++)//为每一行设置显示的数据
//                {
//                    TextView s = new TextView(ToBuyActivity.this);
//                    s.setText(msg[i][arg0]);//TextView中显示的文字
//                    s.setTextSize(11);//字体大小
//                    s.setPadding(1, 2, 2, 1);//四周留白
//                    s.setWidth(53);//宽度
//                    s.setGravity(Gravity.CENTER);
//                    ll_detail.addView(s);//放入LinearLayout
//                }
//                return ll_detail;//将此LinearLayout返回
//            }
//        };
//        lv_detail.setAdapter(ba_detail);//将适配器添加进ListView
//    }
}