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

/**
 * 管理员删除电影
 * @author leilei
 *
 */
public class DeleteMovieActivity extends Activity {

	Button btn = null;
	Button btn_goupdate = null;
	Button btn_update = null;
	
	TextView rtv1 = null;
	String rptv1 = null;
	private String[][] msgg;
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		CreatTable.creattable();
		godelete();
	}

	private void godelete() {
		setContentView(R.layout.deletemovie);
		btn = (Button)this.findViewById(R.id.btn_delete);
		rtv1 = (TextView)this.findViewById(R.id.returntext1);//电影名
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				rptv1 = rtv1.getText().toString();
				if(validate())
				{
					Vector<Vector<String>> Query_movie;
					Query_movie = LoadUtil.queryMovie(rptv1);//根据电影名查询
					 if(Query_movie.size()==0)
					  {
						Toast.makeText(DeleteMovieActivity.this, "对不起，没有相关的电影信息!!!", Toast.LENGTH_SHORT).show();
							//etKeyword1.setText("");etKeyword2.setText("");
						return;
					 }
					 else
					 {
					 String[][] msgInfo=new String[Query_movie.elementAt(0).size()][Query_movie.size()];//新建和结果向量对应的数组
					 for(int i=0;i<Query_movie.size();i++)
					  {//for循环将结果向量中的数据导入数组
						for(int j=0;j<Query_movie.elementAt(0).size();j++)
							{
								msgInfo[j][i]=(String)Query_movie.get(i).get(j);
							}
						}
						goToListView(msgInfo);//切换到查询结果显示界面ListView界面
					}
				}
			}
		});
		
		btn_goupdate = (Button)this.findViewById(R.id.btn_goupdate);
		rtv1 = (TextView)this.findViewById(R.id.returntext1);//电影名
		btn_goupdate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				rptv1 = rtv1.getText().toString();
				if(validate())
				{
					Vector<Vector<String>> Query_movie;
					Query_movie = LoadUtil.queryMovie(rptv1);//根据电影名查询
					 if(Query_movie.size()==0)
					  {
						Toast.makeText(DeleteMovieActivity.this, "对不起，没有相关的电影信息!!!", Toast.LENGTH_SHORT).show();
							//etKeyword1.setText("");etKeyword2.setText("");
						return;
					 }
					 else
					 {
					 String[][] msgInfo=new String[Query_movie.elementAt(0).size()][Query_movie.size()];//新建和结果向量对应的数组
					 for(int i=0;i<Query_movie.size();i++)
					  {//for循环将结果向量中的数据导入数组
						for(int j=0;j<Query_movie.elementAt(0).size();j++)
							{
								msgInfo[j][i]=(String)Query_movie.get(i).get(j);
							}
						}
						goToListView_update(msgInfo);//切换到查询结果显示界面ListView界面
					}
				}
			}
		});
	}
	
	
	protected void goToListView_update(String[][] mssg){
		msgg=mssg;
		setContentView(R.layout.updatefilm);
		System.out.println(msgg[0][0]);
		EditText editText1 = (EditText) findViewById(R.id.label11);
		editText1.setText(msgg[0][0]);
		EditText editText2 = (EditText) findViewById(R.id.label21);
		editText2.setText(msgg[1][0]);
		EditText editText3 = (EditText) findViewById(R.id.label31);
		editText3.setText(msgg[2][0]);
		EditText editText4 = (EditText) findViewById(R.id.label41);
		editText4.setText(msgg[3][0]);
		EditText editText5 = (EditText) findViewById(R.id.label51);
		editText5.setText(msgg[4][0]);
		EditText editText6 = (EditText) findViewById(R.id.label61);
		editText6.setText(msgg[5][0]);
		
		
		
		btn_update = (Button)this.findViewById(R.id.btn_update);
		btn_update.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				EditText et1 = null;
				EditText et2 = null;
				EditText et3 = null;
				EditText et4 = null;
				EditText et5 = null;
				EditText et6 = null;
				et1 = (EditText)findViewById(R.id.label11);
				et2 = (EditText)findViewById(R.id.label21);
				et3 = (EditText)findViewById(R.id.label31);
				et4 = (EditText)findViewById(R.id.label41);
				et5 = (EditText)findViewById(R.id.label51);
				et6 = (EditText)findViewById(R.id.label61);
				System.out.println(et6.getText().toString().trim());
				if(LoadUtil.updateFilm(et1.getText().toString().trim(), et2.getText().toString().trim(), 
						et3.getText().toString().trim(), et4.getText().toString().trim(),
						et5.getText().toString().trim(), et6.getText().toString().trim()))
				{
					Toast.makeText(DeleteMovieActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
					Intent it = new Intent();
					
					it.putExtra("username", "admin");
					it.setClass(DeleteMovieActivity.this, MainmenuActivity1.class);
					
					startActivity(it);
				}
			}
		});
	}

	protected void goToListView(String[][]mssg) {
		msgg=mssg;//赋值引用给全局数组，用来实现返回按钮功能
        setContentView(R.layout.returnfilm);//切换界面
        final String[][]msg=mssg;//新建数组，并赋值  
        ListView lv_detail=(ListView)this.findViewById(R.id.ListView_returnfilm);//拿到ListView的引用
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
				LinearLayout ll_detail=new LinearLayout(DeleteMovieActivity.this);
				ll_detail.setOrientation(LinearLayout.HORIZONTAL);		//设置朝向,水平	
				ll_detail.setPadding(5,5,5,5);//四周留白
				for(int i=0;i<msg.length;i++)//为每一行设置显示的数据
				{			
					TextView s= new TextView(DeleteMovieActivity.this);
					s.setText(msg[i][arg0]);//TextView中显示的文字
					s.setTextSize(14);//字体大小
					s.setPadding(1,2,2,1);//四周留白
					s.setWidth(45);//宽度
				    s.setGravity(Gravity.CENTER);
				    ll_detail.addView(s);//放入LinearLayout
				}
		
				Button b = new Button(DeleteMovieActivity.this);
				b.setText("下档");
				b.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {//跳转到管理员界面
						if(LoadUtil.deleteFilm(msg[0][arg0]))
						{
							Toast.makeText(DeleteMovieActivity.this, "成功下档！", Toast.LENGTH_SHORT).show();
							Intent it = new Intent();
							
							it.putExtra("username", "admin");
							it.setClass(DeleteMovieActivity.this, MainmenuActivity1.class);
							
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
			Toast.makeText(DeleteMovieActivity.this, "请输入电影名", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
}
