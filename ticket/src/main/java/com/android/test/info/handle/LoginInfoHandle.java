package com.android.test.info.handle;

import com.android.test.info.LoginInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LoginInfoHandle implements IDatabaseHandle {


    private final static String createStr = "create table loginInfo (user_id int,id varchar(50),pwd varchar(50),tel varchar(11),card varchar(18))";
    private final static String TABLE = "loginInfo";
    private final static String DB_NAME = "mydb";
    Context mContext;

    public LoginInfoHandle(Context context) {
        mContext = context;
        DatabaseHelper dbHelper = new DatabaseHelper(mContext, DB_NAME, null, 1, createStr);
    }

    public void createDatabase() {
        // TODO Auto-generated method stub
        DatabaseHelper dbHelper = new DatabaseHelper(mContext, DB_NAME, null, 1, createStr);
        dbHelper.getReadableDatabase();
    }

    public void upgrateDatabase() {
        // TODO Auto-generated method stub
        DatabaseHelper dbHelper = new DatabaseHelper(mContext, DB_NAME, null, 1, createStr);
        dbHelper.getReadableDatabase();
    }

    public void insert(Object obj) {
        // TODO Auto-generated method stub
        DatabaseHelper dbHelper = new DatabaseHelper(mContext, DB_NAME, null, 1, createStr);
        ContentValues values = new ContentValues();
        LoginInfo info = (LoginInfo) obj;
        info.setUser_id(getNumber() + 1L);
        //插入键值对，注意值的类型必须和数据库表中字段的类型一致
        values.put("user_id", info.getUser_id());
        values.put("id", info.getId());
        values.put("pwd", info.getPwd());
        values.put("tel", info.gettel());
        values.put("card", info.getEmail());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(TABLE, null, values);
    }

    /**
     * 获取数据库数据总个数
     * @return
     */
    private int getNumber() {
        // TODO Auto-generated method stub
        DatabaseHelper dbHelper = new DatabaseHelper(mContext, DB_NAME, null, 1, createStr);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.query(TABLE, new String[]{"id"}, null, null, null, null, null);

            return cursor.getCount();
        } catch (Exception e) {

            return 0;
        }

    }

    public void update(Object obj) {
        // TODO Auto-generated method stub
        DatabaseHelper dbHelper = new DatabaseHelper(mContext, DB_NAME, null, 1, createStr);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //生成ContentValues对象，并设定键值对
        LoginInfo info = (LoginInfo) obj;
        ContentValues values = new ContentValues();
        values.put("id", info.getId());
        values.put("pwd", info.getPwd());
        values.put("tel", info.gettel());
        values.put("card", info.getEmail());
        db.update(TABLE, values, " user_id=?", new String[]{"" + info.getUser_id()});
    }

    public boolean CorrectOfAccountAndPassword(String id, String pwd) {
        // TODO Auto-generated method stub
        boolean ret = false;
        DatabaseHelper dbHelper = new DatabaseHelper(mContext, DB_NAME, null, 1, createStr);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.query(TABLE, new String[]{"id", "pwd"}, " id =? and  pwd =? ", new String[]{id, pwd}, null, null, null);
            if (cursor.moveToNext())
                ret = true;
        } catch (Exception e) {
            return false;
        }
        return ret;
    }

    public boolean isExist(String id) {
        // TODO Auto-generated method stub
        boolean ret = false;
        DatabaseHelper dbHelper = new DatabaseHelper(mContext, DB_NAME, null, 1, createStr);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.query(TABLE, new String[]{"id"}, " id =?", new String[]{id}, null, null, null);
            while (cursor.moveToNext()) {
                String tid = cursor.getString(cursor.getColumnIndex("id"));
                if (id.equals(tid))
                    ret = true;
                // cursor.close();
            }
        } catch (Exception e) {

            return false;
        }
        return ret;
    }

    public void query() {
        // TODO Auto-generated method stub

    }

    public void delete() {
        // TODO Auto-generated method stub

    }
}

