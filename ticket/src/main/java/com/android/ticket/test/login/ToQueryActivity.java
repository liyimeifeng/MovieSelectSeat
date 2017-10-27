package com.android.ticket.test.login;

import java.util.Vector;

import com.android.test.info.handle.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class ToQueryActivity extends Activity {
	String visitor = null;//������ʾ�����ߣ���ʹ���������ܵ��û�ID
	Button btnGo1 = null;
	BaseAdapter ba=null;
	String[][]msgg=new String[][]{{""}};//��������
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		visitor = i.getStringExtra("visitor");//visitor==username
		CreatTable.creattable();
		goQuery();
	}
	
	public void goQuery() {//����Ӱ����ѯ
		setContentView(R.layout.query);
		btnGo1 = (Button)findViewById(R.id.query1);
        btnGo1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(validate()){
				 EditText etKeyword1 = (EditText)findViewById(R.id.InputOstation1);
				 String filmname = etKeyword1.getText().toString().trim();
				 Vector<Vector<String>> Query_film;
				 Query_film = LoadUtil.getfilmname1(filmname);
				 if(Query_film.size()==0){
					Toast.makeText(ToQueryActivity.this, "�Բ���û�������Ϣ!!!", Toast.LENGTH_SHORT).show();
						//etKeyword1.setText("");etKeyword2.setText("");
					return;
				}else{
				 String[][] msgInfo=new String[Query_film.elementAt(0).size()][Query_film.size()];//�½��ͽ��������Ӧ������
				 for(int i=0;i<Query_film.size();i++){//forѭ������������е����ݵ�������
					for(int j=0;j<Query_film.elementAt(0).size();j++){
						msgInfo[j][i]=(String)Query_film.get(i).get(j);
					}
				}
				goToListView(msgInfo);//�л�����ѯ�����ʾ����ListView����
			}	
		}
	}

	public boolean validate() {
		EditText etKeyword1 = (EditText)findViewById(R.id.InputOstation1);
		String filmname = etKeyword1.getText().toString().trim();
		if(filmname.equals(""))
		{
			Toast.makeText(ToQueryActivity.this, "�������Ӱ��", Toast.LENGTH_SHORT).show();
			// DialogUtil.showDialog(this,"�û��������д��",false);
			 return false;
		}
		return  true;
	}
    });		
}
	
	public void goToListView(String[][]mssg) {
		msgg=mssg;//��ֵ���ø�ȫ�����飬����ʵ�ַ��ذ�ť����
        setContentView(R.layout.detail);//�л�����
        final String[][]msg=mssg;//�½����飬����ֵ
        ListView lv_detail=(ListView)this.findViewById(R.id.ListView_detail);//�õ�ListView������
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
				LinearLayout ll_detail=new LinearLayout(ToQueryActivity.this);
				ll_detail.setOrientation(LinearLayout.HORIZONTAL);		//���ó���	
				ll_detail.setPadding(5,5,5,5);//��������
				for(int i=0;i<msg.length;i++)//Ϊÿһ��������ʾ������
				{					    
					TextView s= new TextView(ToQueryActivity.this);
					s.setText(msg[i][arg0]);//TextView����ʾ������
					s.setTextSize(11);//�����С
					s.setPadding(1,2,2,1);//��������
					s.setWidth(50);//���
				    s.setGravity(Gravity.CENTER);
				    ll_detail.addView(s);//����LinearLayout
				}
				return ll_detail;//����LinearLayout����
			}        	
        };    
        lv_detail.setAdapter(ba_detail);//����������ӽ�ListView
	}
}