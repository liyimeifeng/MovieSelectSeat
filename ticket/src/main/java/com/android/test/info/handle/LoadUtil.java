package com.android.test.info.handle;

import java.util.Date;
import java.util.Random;
import java.util.Vector;

import com.android.test.info.MovieInfo;
import com.android.test.info.Purchase;
import com.android.test.info.Release;
import com.android.test.info.Sold;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class LoadUtil {

    private final static String TAG = LoadUtil.class.getSimpleName();
    private Context context;

    public static SQLiteDatabase createOrOpenDatabase()//连接数据库
    {
        SQLiteDatabase sld = null;
        try {
            sld = SQLiteDatabase.openDatabase//连接并创建数据库，如果不存在则创建
                    (
                            "/data/data/com.android.ticket.test.login/databases/mydb",
                            null,
                            SQLiteDatabase.OPEN_READWRITE | SQLiteDatabase.CREATE_IF_NECESSARY);
        } catch (Exception e) {
            Log.e(TAG, "createOrOpenDatabase: " + e.getMessage() );
            e.printStackTrace();
        }
        return sld;//返回该连接
    }

    public static void createTable(String sql) {//创建表
        SQLiteDatabase sld = createOrOpenDatabase();//连接数据库
        try {
            sld.execSQL(sql);//执行SQL语句
            sld.close();//关闭连接
        } catch (Exception e) {
        }
    }

    public static boolean insert(String sql)//插入数据
    {
        SQLiteDatabase sld = createOrOpenDatabase();//连接数据库
        try {
            sld.execSQL(sql);

            sld.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean update(String sql)//更新数据
    {
        SQLiteDatabase sld = createOrOpenDatabase();//连接数据库
        try {
            sld.execSQL(sql);
            sld.close();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "update: e " + e.getMessage() );
            return false;
        }

    }

    public static boolean delete(String sql)//更新数据
    {
        SQLiteDatabase sld = createOrOpenDatabase();//连接数据库
        try {
            sld.execSQL(sql);
            sld.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Vector<Vector<String>> query(String sql)//查询
    {
        Vector<Vector<String>> vector = new Vector<Vector<String>>();//新建存放查询结果的向量
        SQLiteDatabase sld = createOrOpenDatabase();//得到连接数据库的连接
        try {
            Cursor cur = sld.rawQuery(sql, new String[]{});//得到结果集
            while (cur.moveToNext())//如果存在下一条
            {
                Vector<String> v = new Vector<String>();
                int col = cur.getColumnCount();
                //将其放入向量
                for (int i = 0; i < col; i++) {
                    v.add(cur.getString(i));
                }
                vector.add(v);
            }
            cur.close();//关闭结果集
            sld.close();//关闭连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }

    public static Vector<Vector<Float>> query1(String sql)//查询
    {
        Vector<Vector<Float>> vector = new Vector<Vector<Float>>();//新建存放查询结果的向量
        SQLiteDatabase sld = createOrOpenDatabase();//得到连接数据库的连接
        try {
            Cursor cur = sld.rawQuery(sql, new String[]{});//得到结果集
            while (cur.moveToNext())//如果存在下一条
            {
                Vector<Float> v = new Vector<Float>();
                int col = cur.getColumnCount();        //将其放入向量
                for (int i = 0; i < col; i++) {
                    v.add(cur.getFloat(i));
                }
                vector.add(v);
            }
            cur.close();//关闭结果集
            sld.close();//关闭连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }

    public static Vector<Vector<Integer>> query2(String sql)//查询
    {
        Vector<Vector<Integer>> vector = new Vector<Vector<Integer>>();//新建存放查询结果的向量
        SQLiteDatabase sld = createOrOpenDatabase();//得到连接数据库的连接
        try {
            Cursor cur = sld.rawQuery(sql, new String[]{});//得到结果集
            while (cur.moveToNext())//如果存在下一条
            {
                Vector<Integer> v = new Vector<Integer>();
                int col = cur.getColumnCount();        //将其放入向量
                for (int i = 0; i < col; i++) {
                    v.add(cur.getInt(i));
                }
                vector.add(v);
            }
            cur.close();//关闭结果集
            sld.close();//关闭连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }

    //按电影名查询
    public static Vector<Vector<String>> getfilmname1(String filmname) {
        String sql = "select FilmName,Type,District,Director,Length,Summary from movieinfo where FilmName='" + filmname + "'";
        Vector<Vector<String>> temp = query(sql);
        return temp;
    }

    //按国家查询
    public static Vector<Vector<String>> getDistrict1(String district) {
        String sql = "select FilmName,Type,District,Director,Length,Summary from movieinfo where District='" + district + "'";
        Vector<Vector<String>> temp = query(sql);
        return temp;
    }

    //从管理员添加的影片中查询电影名、日期查询1
    public static Vector<Vector<String>> getSameVector1(String filmname, String date) {
        String sql = "select FilmName,Type,District,Director,Length,Summary from movieinfo where FilmName in (select FilmName from release where FilmName='" + filmname + "'and LoginActivity='" + date + "')";
        Vector<Vector<String>> temp = query(sql);
        return temp;
    }

    /**
     * 从排片信息中查询，根据电影名、日期
     * @param filmname
     * @param date
     * @return
     */
    public static Vector<Vector<String>> getSameVector2(String filmname, String date) {
        String sql = "select FilmName,Hall,LoginActivity,Time,Money,Number from release where FilmName='" + filmname + "'and LoginActivity='" + date + "'";
        Vector<Vector<String>> temp = query(sql);
        return temp;
    }

    public static String getSoldSeat(String filmname, String date,String hall,String time){
        String sql = "select SeatNumber from sold where FilmName='" + filmname + "'and hall='" + hall + "'and date='" + date + "'and time='" + time + "'";
        String temp = querySeat(sql);
        return temp;
    }

    public static String querySeat(String sql)//查询
    {
        SQLiteDatabase sld = createOrOpenDatabase();//得到连接数据库的连接
        try {
            Cursor cur = sld.rawQuery(sql, new String[]{});//得到结果集
            while (cur.moveToNext())//如果存在下一条
            {
                int col = cur.getColumnCount();
                //将其放入向量
                for (int i = 0; i < col; i++) {
                    return cur.getString(i);
                }

            }
            cur.close();//关闭结果集
            sld.close();//关闭连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 向数据库中添加已售座位
     * @param filmname
     * @param date
     * @param hall
     * @param time
     * @param seat
     * @return
     */
    public static Sold addSold(String filmname, String date,String hall,String time ,String seat){
        Sold sold = new Sold();
        String sql1 = "update sold set SeatNumber='" + seat+ "' where FilmName='" + filmname + "' and Hall='" + hall+ "' and Date='" + date + "' and Time='" + time + "'" ;
        update(sql1);
        return sold;
    }

    //添加购买信息
    public static Purchase addTicket(String userName, String fileName, String number,String hall, String seatNUmber,String price,String time) {
        SQLiteDatabase sld = createOrOpenDatabase();//得到连接数据库的连接
        Purchase purchase = new Purchase();
        Random r1 = new Random();
        Random r2 = new Random();
        int a = r1.nextInt() * r1.nextInt() / 99 + r2.nextInt() / 100000;
        String sql = "insert into Purchase values ('" + a + "','" + userName + "','" + fileName + "','" + number + "','" + hall+ "','" + seatNUmber+ "','" + price+ "','" + time+"')";
        insert(sql);
        return purchase;
    }

    //订单查询（用户名）
    public static Vector<Vector<String>> viewOrder(String username) {
        String sql = "select Purchaseid,purchase.FilmName,LoginActivity,purchase.Hall,SeatNumber,purchase.Time,purchase.Number from release,purchase where release.FilmName=purchase.FilmName and release.Time=purchase.Time and Userid='" + username + "'";
        Vector<Vector<String>> temp = query(sql);
        return temp;
    }

    //订单查询（订单号）
    public static Vector<Vector<String>> treturn(String purchaseid) {
        String sql = "select Purchaseid,Userid,purchase.FilmName,LoginActivity,Time,purchase.Number from release,purchase where release.FilmName=purchase.FilmName and Purchaseid='" + purchaseid + "'";
        Vector<Vector<String>> temp = query(sql);
        return temp;
    }

    //退票
    public static boolean returnticket(String msg0, String msg1, String msg2, String msg5) {
        String sql = "delete from purchase where Purchaseid='" + msg0 + "' and Userid='" + msg1 + "' and FilmName='" + msg2 + "'and Number='" + msg5 + "'";
        delete(sql);
        return true;
    }

    //查询所有
    public static Vector<Vector<String>> getall() {
        String sql = "select FilmName,Type,District,Director,Length,Summary from movieinfo";
        Vector<Vector<String>> temp = query(sql);
        return temp;
    }

    //管理员插入电影
    public static MovieInfo addfilm(String key0, String key1, String key2, String key3, String key4, String key5) {
        SQLiteDatabase sld = createOrOpenDatabase();//得到连接数据库的连接
        MovieInfo movieInfo = new MovieInfo();
        Random r1 = new Random();
        Random r2 = new Random();
        int a = r1.nextInt() * r1.nextInt() / 99 + r2.nextInt() / 100000;
        String sql = "insert into MovieInfo values ('" + a + "','" + key0 + "','" + key1 + "','" + key2 + "','" + key3 + "','" + key4 + "','" + key5 + "')";
        insert(sql);
        return movieInfo;
    }

    // 电影查询（电影名）
    public static Vector<Vector<String>> queryMovie(String filmname) {
        String sql = "select filmname,type,district,director,length,summary from movieinfo where filmname='"
                + filmname + "'";
        Vector<Vector<String>> temp = query(sql);
        return temp;
    }

    // 删除电影
    public static boolean deleteFilm(String msg0) {
        String sql = "delete from movieinfo where filmname='" + msg0 + "'";
        delete(sql);
        return true;
    }

    // 更改电影
    public static boolean updateFilm(String msg0, String msg1, String msg2, String msg3, String msg4, String msg5) {
        String sql = "delete from movieinfo where filmname='" + msg0 + "'";
        delete(sql);
        /* String sql = "update movieinfo set type='" + msg1 + "' and district='"+msg2+
                "' and director="+msg3+"' and length='"+msg4+"' and summary='"+msg5+
				"' where filmname='"+msg0+"'"; */
        // update(sql);
        SQLiteDatabase sld = createOrOpenDatabase();//得到连接数据库的连接
        MovieInfo movieInfo = new MovieInfo();
        Random r1 = new Random();
        Random r2 = new Random();
        int a = r1.nextInt() * r1.nextInt() / 99 + r2.nextInt() / 100000;
        String sql1 = "insert into MovieInfo values ('" + a + "','" + msg0 + "','" + msg1 + "','" + msg2 + "','" + msg3 + "','" + msg4 + "','" + msg5 + "')";
        insert(sql1);
        return true;
    }

}
