package com.android.ticket.test.login;
import com.android.test.info.handle.*;


public class CreatTable {
	public static void creattable(){		
		try{
			String sqll[]=new String[]{					
					
				"create table if not exists movieinfo " +//建立电影信息表
				"(FilmId char(20) primary key,FilmName char(20),Type char(20),District char(20),Director char(20),Length char(20),Summary char(100))",				
				"create table if not exists release" +//建立上映表
				"(ShowId char(20) primary key,FilmId char(20),FilmName char(20),Hall char(20),LoginActivity char(20),Time char(20),Money char(20),Number char(20))",
				"create table if not exists purchase " +//建立购买表
				"(Purchaseid int primary key,Userid int,FilmName char(20),Number char(20),Hall char(20), SeatNumber char(20),Price char(20),Time char(20))",
						
				//插入初始化数据
				"insert into movieinfo values('10001','港囧','喜剧','中国','徐峥','120','徐来陪伴老婆及家人到香港旅游..')",
				"insert into movieinfo values('10002','百团大战','战争','中国','宁海强','111','百团大战是中国抗日战争时期..')",	
				"insert into movieinfo values('10003','天际浩劫','科幻','美国','科林·施特劳斯','94','人类面临一场浩劫..')",
				"insert into movieinfo values('10004','测试用','测试用','测试用','测试用','测试用','测试用')",
				
				"insert into release values('1001','10001','港囧','1','10月28日','14:00','40','98')",
				"insert into release values('1002','10002','百团大战','3','10月30日','15:00','35','104')",
				"insert into release values('1003','10003','天际浩劫','6','10月29日','20:00','40','112')",
				"insert into release values('1004','10004','测试用','0','1月2日','00:00','0','0')",
				
				"insert into purchase values(1,1,'测试用','2','8','五排五座','66','20:00')",
				
			};			
			for(String o:sqll){//循环所有SQL语句，进行建表和初始化一些数据操作
				LoadUtil.createTable(o);
			}		
		}catch(Exception e){		
			e.printStackTrace();			
		}
	}
}
