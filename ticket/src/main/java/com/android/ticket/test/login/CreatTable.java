package com.android.ticket.test.login;
import android.content.Context;

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
				"(Purchaseid int primary key,Userid char(20),FilmName char(20),Number char(20),Hall char(20), SeatNumber char(20),Price char(20),Time char(20))",
				"create table if not exists sold " + //建立已售表
				"(SoldId int primary key,FILM_NAME char(20),HALL char(20), DATE char(20),TIME char(20),SEAT char(200))",

				//插入初始化数据
				"insert into movieinfo values('10001','雷神','科幻','美国','塔伊加·维迪提','120','该片讲述在《复仇者联盟2：奥创纪元》结尾飞走之后，雷神托尔终于听到了来自阿斯加德的麻烦：洛基一直假扮着他们失踪的父亲奥丁，但洛基治国相当懈怠，导致本被关押的海拉再度现身..')",
				"insert into movieinfo values('10002','银翼杀手','科幻','美国','丹尼斯·维伦纽瓦','163','海平面上升，空气质量持续恶化，失控的废弃物使洛杉矶变成了举目无际的垃圾堆。2049年聚焦一个更年轻、 机器人化得更明晰的复制人K（瑞恩·高斯林饰），他的工作是猎杀旧型号的复制人，受命于洛城警署坚韧的陆军中尉乔茜..')",
				"insert into movieinfo values('10003','正义联盟','科幻','美国','扎克·施奈德','121','故事发生在《蝙蝠侠大战超人：正义黎明》之后，主要讲述了面对一个全新的世界威胁，超人、蝙蝠侠、神奇女侠、闪电侠、海王和钢骨六位英雄聚首，与这股未知的威胁对抗..')",
				"insert into movieinfo values('10005','测试用','测试用','测试用','测试用','测试用','测试用')",

				"insert into release values('1001','10001','雷神','1','10月27日','14:00','40','125')",
					"insert into release values('1005','10005','雷神','2','10月27日','20:00','40','125')",

					"insert into release values('1002','10002','银翼杀手','3','10月27日','15:00','35','163')",
					"insert into release values('1008','10008','银翼杀手','4','10月27日','12:00','35','163')",
					"insert into release values('1007','10007','银翼杀手','5','10月27日','17:35','35','163')",

					"insert into release values('1003','10003','正义联盟','6','10月27日','20:00','40','135')",
					"insert into release values('1003','10003','正义联盟','7','10月27日','19:40','40','135')",
					"insert into release values('1004','10004','测试用','0','1月2日','00:00','0','0')",

				"insert into purchase values(1,1,'测试用','2','8','5排5座','66','20:00')",
				"insert into sold values(1,'雷神','1','10月27日','14:00','[{9-4},{9-3},{9-4},{9-5},{9-6}]')",
					"insert into sold values(2,'雷神','2','10月27日','20:00','[{7-4},{7-3},{7-3},{7-5},{7-6},{7-7}]')",
					"insert into sold values(3,'银翼杀手','3','10月27日','15:00','[{8-4},{8-3},{8-5},{7-3},{7-4},{7-5}]')",
					"insert into sold values(4,'银翼杀手','4','10月27日','12:00','[{5-4},{5-3}]')",
					"insert into sold values(5,'正义联盟','6','10月27日','20:00','[{9-4},{9-3}]')"

			};			
			for(String o:sqll){//循环所有SQL语句，进行建表和初始化一些数据操作
				LoadUtil.createTable(o);
			}
		}catch(Exception e){		
			e.printStackTrace();			
		}
	}
}
