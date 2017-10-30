package com.android.ticket.test.login;

import java.util.Vector;

import com.android.test.info.handle.LoadUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ViewOrederActivity extends Activity {
	private String[][] mssg;
    String username = null;
	private final static String TAG = ViewOrederActivity.class.getSimpleName();
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.vieworder);
	Intent i = getIntent();
	username = i.getStringExtra("username");
	//CreatTable.creattable();
	goviewOrder();
	}

	public void goviewOrder() {
		ListView lv_detail=(ListView)this.findViewById(R.id.ListView_vieworder);//拿到ListView的引用
        Vector<Vector<String>> Query_vieworder;
		Query_vieworder = LoadUtil.viewOrder(username);

		Log.e(TAG, "Query_vieworder: " + Query_vieworder );
		if (Query_vieworder.size() > 0){
			String[][] msgInfo=new String[Query_vieworder.elementAt(0).size()][Query_vieworder.size()];//新建和结果向量对应的数组
			for(int i=0;i<Query_vieworder.size();i++)
			{//for循环将结果向量中的数据导入数组
				for(int j=0;j<Query_vieworder.elementAt(0).size();j++)
				{
					msgInfo[j][i]=(String)Query_vieworder.get(i).get(j);
				}
			}
			//mssg = msgInfo;
			// Toast.makeText(ViewOrederActivity.this, msgInfo[6][0], Toast.LENGTH_SHORT).show();
			final String[][]msg=msgInfo;//新建数组，并赋值
			BaseAdapter ba_detail=new BaseAdapter()//新建适配器
			{
				public int getCount()
				{
					return msg[0].length;//得到列表的长度
				}
				public Object getItem(int arg0){return null;}
				public long getItemId(int arg0){return 0;}
				public View getView(int arg0, View arg1, ViewGroup arg2)//为每一项添加内容
				{
					LinearLayout ll_detail=new LinearLayout(ViewOrederActivity.this);
					ll_detail.setOrientation(LinearLayout.HORIZONTAL);		//设置朝向
					ll_detail.setPadding(5,5,0,5);//四周留白
					for(int i=0;i<msg.length;i++)//为每一行设置显示的数据
					{
						TextView s= new TextView(ViewOrederActivity.this);
						s.setText(msg[i][arg0]);//TextView中显示的文字
						s.setTextSize(14);//字体大小
						s.setPadding(1,2,2,1);//四周留白
						s.setWidth(150);//宽度
						s.setGravity(Gravity.CENTER);
						ll_detail.addView(s);//放入LinearLayout
					}
					return ll_detail;//将此LinearLayout返回
				}
			};
			lv_detail.setAdapter(ba_detail);//将适配器添加进ListView
		}

	}
}
