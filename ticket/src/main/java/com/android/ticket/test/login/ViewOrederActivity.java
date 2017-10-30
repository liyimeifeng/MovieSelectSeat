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
		ListView lv_detail=(ListView)this.findViewById(R.id.ListView_vieworder);//�õ�ListView������
        Vector<Vector<String>> Query_vieworder;
		Query_vieworder = LoadUtil.viewOrder(username);

		Log.e(TAG, "Query_vieworder: " + Query_vieworder );
		if (Query_vieworder.size() > 0){
			String[][] msgInfo=new String[Query_vieworder.elementAt(0).size()][Query_vieworder.size()];//�½��ͽ��������Ӧ������
			for(int i=0;i<Query_vieworder.size();i++)
			{//forѭ������������е����ݵ�������
				for(int j=0;j<Query_vieworder.elementAt(0).size();j++)
				{
					msgInfo[j][i]=(String)Query_vieworder.get(i).get(j);
				}
			}
			//mssg = msgInfo;
			// Toast.makeText(ViewOrederActivity.this, msgInfo[6][0], Toast.LENGTH_SHORT).show();
			final String[][]msg=msgInfo;//�½����飬����ֵ
			BaseAdapter ba_detail=new BaseAdapter()//�½�������
			{
				public int getCount()
				{
					return msg[0].length;//�õ��б�ĳ���
				}
				public Object getItem(int arg0){return null;}
				public long getItemId(int arg0){return 0;}
				public View getView(int arg0, View arg1, ViewGroup arg2)//Ϊÿһ���������
				{
					LinearLayout ll_detail=new LinearLayout(ViewOrederActivity.this);
					ll_detail.setOrientation(LinearLayout.HORIZONTAL);		//���ó���
					ll_detail.setPadding(5,5,0,5);//��������
					for(int i=0;i<msg.length;i++)//Ϊÿһ��������ʾ������
					{
						TextView s= new TextView(ViewOrederActivity.this);
						s.setText(msg[i][arg0]);//TextView����ʾ������
						s.setTextSize(14);//�����С
						s.setPadding(1,2,2,1);//��������
						s.setWidth(150);//���
						s.setGravity(Gravity.CENTER);
						ll_detail.addView(s);//����LinearLayout
					}
					return ll_detail;//����LinearLayout����
				}
			};
			lv_detail.setAdapter(ba_detail);//����������ӽ�ListView
		}

	}
}
