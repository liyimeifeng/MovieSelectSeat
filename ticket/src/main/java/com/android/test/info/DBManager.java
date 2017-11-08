package com.android.test.info;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Lee on 2017/10/31 0031 16:29.
 */

public class DBManager {

    private final static String TAG = DBManager.class.getSimpleName();
    private static DBManager INSTANCE;
    private Context context;
    private final static String DB_NAME = "mydb";
    private DaoMaster.DevOpenHelper openHelper;
    public DBManager(Context context){
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context,DB_NAME,null);
    }

    public static DBManager getInstance(Context context){
        if (INSTANCE == null) {
            synchronized (DBManager.class){
                if (INSTANCE == null) {
                    INSTANCE = new DBManager(context);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    /**
     * 插入一条记录
     *
     * @param user
     */
    public void insertLoginInfo(LoginInfo user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LoginInfoDao userDao = daoSession.getLoginInfoDao();
        userDao.insert(user);
    }

    /**
     * 插入用户集合
     *
     * @param users
     */
    public void insertLoginInfoList(List<LoginInfo> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LoginInfoDao userDao = daoSession.getLoginInfoDao();
        userDao.insertInTx(users);
    }

    /**
     * 删除一条记录
     *
     * @param user
     */
    public void deleteLoginInfo(LoginInfo user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LoginInfoDao userDao = daoSession.getLoginInfoDao();
        userDao.delete(user);
    }

    /**
     * 更新一条记录
     *
     * @param user
     */
    public void updateLoginInfo(LoginInfo user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LoginInfoDao userDao = daoSession.getLoginInfoDao();
        userDao.update(user);
    }

    /**
     * 查询用户列表
     */
    public List<LoginInfo> queryLoginInfoList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LoginInfoDao userDao = daoSession.getLoginInfoDao();
        QueryBuilder<LoginInfo> qb = userDao.queryBuilder();
        List<LoginInfo> list = qb.list();
        return list;
    }

    /**
     * 根据用户名查询用户列表
     * @param userName
     * @return
     */
    public List<LoginInfo> queryLoginInfoList(String userName) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LoginInfoDao userDao = daoSession.getLoginInfoDao();
        QueryBuilder<LoginInfo> qb = userDao.queryBuilder();
        qb.where(LoginInfoDao.Properties.Id.eq(userName)).orderAsc(LoginInfoDao.Properties.Id);
        List<LoginInfo> list = qb.list();
        return list;
    }

    /**
     * 根据输入的账号、密码判断是否存在该用户并且密码是否正确
     * @param id
     * @param pwd
     * @return
     */
    public boolean isPass(String id, String pwd){
        DaoMaster daoMaster  = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LoginInfoDao loginInfoDao = daoSession.getLoginInfoDao();
        QueryBuilder<LoginInfo> qb  =loginInfoDao.queryBuilder();
        qb.where(LoginInfoDao.Properties.Id.eq(id),LoginInfoDao.Properties.Pwd.eq(pwd)).orderAsc(LoginInfoDao.Properties.Id);
        List<LoginInfo> list = qb.list();
        Log.e(TAG, "isPass: " + list.toArray().length);
        if (list.size() >= 1){
            return true;
        }else{
            return false;
        }
    }

    public boolean isAccountExist(String account){
        DaoMaster daoMaster  = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LoginInfoDao loginInfoDao = daoSession.getLoginInfoDao();
        QueryBuilder<LoginInfo> qb  =loginInfoDao.queryBuilder();
        qb.where(LoginInfoDao.Properties.Id.eq(account)).orderAsc(LoginInfoDao.Properties.Id);
        List<LoginInfo> list = qb.list();
        Log.e(TAG, "isPass: " + list.toArray().length);
        if (list.size() >= 1){
            return true;
        }else{
            return false;
        }
    }
}
