package com.android.ticket.test.login;

import java.util.Vector;

import com.android.test.info.handle.LoadUtil;

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

public class QuerybyDistrictActivity extends Activity{
	String visitor = null;						//声明表示访问者，即使用搜索功能的用户ID
	Button btnGo1 = null;
    BaseAdapter ba=null;
	String[][]msgg=new String[][]{{""}};//声明引用
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		visitor = i.getStringExtra("visitor");//visitor==username
		CreatTable.creattable();
		goQuery();
	}
	
	public void goQuery() {//按国家查询
		setContentView(R.layout.querybydistrict);
		btnGo1 = (Button)findViewById(R.id.query2);
        btnGo1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(validate()){
				 EditText etKeyword1 = (EditText)findViewById(R.id.district_label1);
				 String district = etKeyword1.getText().toString().trim();
				 Vector<Vector<String>> Query_district;
				 Query_district = LoadUtil.getDistrict1(district);
				 if(Query_district.size()==0){
					Toast.makeText(QuerybyDistrictActivity.this, "对不起，没有相关信息!!!", Toast.LENGTH_SHORT).show();
						//etKeyword1.setText("");etKeyword2.setText("");
					return;
				}else{
				 String[][] msgInfo=new String[Query_district.elementAt(0).size()][Query_district.size()];//新建和结果向量对应的数组
				 for(int i=0;i<Query_district.size();i++){//for循环将结果向量中的数据导入数组
					for(int j=0;j<Query_district.elementAt(0).size();j++){
						msgInfo[j][i]=(String)Query_district.get(i).get(j);
					}
				}
				goToListView(msgInfo);//切换到查询结果显示界面ListView界面
			}	
		}
	}

		public boolean validate() {
			EditText etKeyword1 = (EditText)findViewById(R.id.district_label1);
			String district = etKeyword1.getText().toString().trim();
			if(district.equals(""))
			{
				Toast.makeText(QuerybyDistrictActivity.this, "请输入国家", Toast.LENGTH_SHORT).show();
				// DialogUtil.showDialog(this,"用户项必须填写！",false);
				 return false;
			}
			return  true;
		}
        });		
	}
	
	public void goToListView(String[][]mssg) {
		msgg=mssg;//赋值引用给全局数组，用来实现返回按钮功能
        setContentView(R.layout.detail);//切换界面
        final String[][]msg=mssg;//新建数组，并赋值
        ListView lv_detail=(ListView)this.findViewById(R.id.ListView_detail);//拿到ListView的引用
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
				LinearLayout ll_detail=new LinearLayout(QuerybyDistrictActivity.this);
				ll_detail.setOrientation(LinearLayout.HORIZONTAL);		//设置朝向	
				ll_detail.setPadding(5,5,5,5);//四周留白
				for(int i=0;i<msg.length;i++)//为每一行设置显示的数据
				{					    
					TextView s= new TextView(QuerybyDistrictActivity.this);
					s.setText(msg[i][arg0]);//TextView中显示的文字
					s.setTextSize(11);//字体大小
					s.setPadding(1,2,2,1);//四周留白
					s.setWidth(48);//宽度
				    s.setGravity(Gravity.CENTER);
				    ll_detail.addView(s);//放入LinearLayout
				}
				return ll_detail;//将此LinearLayout返回
			}        	
        };    
        lv_detail.setAdapter(ba_detail);//将适配器添加进ListView
	}
}
