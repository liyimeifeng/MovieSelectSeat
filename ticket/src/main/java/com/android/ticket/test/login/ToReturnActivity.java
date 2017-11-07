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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ToReturnActivity extends Activity {//退票
	Button btn = null;
	TextView rtv1 = null;
	String rptv1 = null;
	private String[][] msgg;
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		CreatTable.creattable();
		goreturn();
	}

	private void goreturn() {
		setContentView(R.layout.treturn);
		btn = (Button)this.findViewById(R.id.btn_return);
		rtv1 = (TextView)this.findViewById(R.id.returntext1);//订单号
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				rptv1 = rtv1.getText().toString();
				if(validate())
				{
					Vector<Vector<String>> Query_retrain;
					Query_retrain = LoadUtil.treturn(rptv1);//根据订单号查询
					 if(Query_retrain.size()==0)
					  {
						Toast.makeText(ToReturnActivity.this, "对不起，没有相关的订单信息!!!", Toast.LENGTH_SHORT).show();
							//etKeyword1.setText("");etKeyword2.setText("");
						return;
					 }
					 else
					 {
					 String[][] msgInfo=new String[Query_retrain.elementAt(0).size()][Query_retrain.size()];//新建和结果向量对应的数组
					 for(int i=0;i<Query_retrain.size();i++)
					  {//for循环将结果向量中的数据导入数组
						for(int j=0;j<Query_retrain.elementAt(0).size();j++)
							{
								msgInfo[j][i]=(String)Query_retrain.get(i).get(j);
							}
						}
						goToListView(msgInfo);//切换到查询结果显示界面ListView界面
					}
				}
			}
		});
	}

	protected void goToListView(String[][]mssg) {
		msgg=mssg;//赋值引用给全局数组，用来实现返回按钮功能
        setContentView(R.layout.returndetail);//切换界面
        final String[][]msg=mssg;//新建数组，并赋值  
        ListView lv_detail=(ListView)this.findViewById(R.id.ListView_returndetail);//拿到ListView的引用
        BaseAdapter ba_detail=new BaseAdapter()//新建适配器
        {
			public int getCount() 
			{
				return msg[0].length;//得到列表的长度
			}
			public Object getItem(int arg0){return null;}
			public long getItemId(int arg0){return 0;}
			public View getView( final int arg0, View arg1, ViewGroup arg2)//为每一项添加内容
			{
				LinearLayout ll_detail=new LinearLayout(ToReturnActivity.this);
				ll_detail.setOrientation(LinearLayout.HORIZONTAL);		//设置朝向,水平	
				ll_detail.setPadding(5,5,5,5);//四周留白
				for(int i=0;i<msg.length;i++)//为每一行设置显示的数据
				{			
					TextView s= new TextView(ToReturnActivity.this);
					s.setText(msg[i][arg0]);//TextView中显示的文字
					s.setTextSize(14);//字体大小
					s.setPadding(1,2,2,1);//四周留白
					s.setWidth(45);//宽度
				    s.setGravity(Gravity.CENTER);
				    ll_detail.addView(s);//放入LinearLayout
				}
				Button b = new Button(ToReturnActivity.this);
				b.setText("退订");
				b.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {//跳转到提交订单界面
						if(LoadUtil.returnticket(msg[0][arg0],msg[1][arg0],msg[2][arg0],msg[5][arg0]))
						{
							Toast.makeText(ToReturnActivity.this, "成功退票！", Toast.LENGTH_SHORT).show();
							Intent it = new Intent();
							it.setClass(ToReturnActivity.this, UserMenuActivity.class);
							it.putExtra("username", msg[1][arg0]);
							startActivity(it);
						}
	            }
				});
				ll_detail.addView(b);//放入LinearLayout
				return ll_detail;//将此LinearLayout返回
			}        	
        };    
        lv_detail.setAdapter(ba_detail);
	}

	protected boolean validate() {
		if(rptv1.equals(""))
		{
			Toast.makeText(ToReturnActivity.this, "请输入订单号", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
}
