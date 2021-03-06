package com.android.test.info;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.android.test.info.LoginInfo;
import com.android.test.info.Sold;

import com.android.test.info.LoginInfoDao;
import com.android.test.info.SoldDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig loginInfoDaoConfig;
    private final DaoConfig soldDaoConfig;

    private final LoginInfoDao loginInfoDao;
    private final SoldDao soldDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        loginInfoDaoConfig = daoConfigMap.get(LoginInfoDao.class).clone();
        loginInfoDaoConfig.initIdentityScope(type);

        soldDaoConfig = daoConfigMap.get(SoldDao.class).clone();
        soldDaoConfig.initIdentityScope(type);

        loginInfoDao = new LoginInfoDao(loginInfoDaoConfig, this);
        soldDao = new SoldDao(soldDaoConfig, this);

        registerDao(LoginInfo.class, loginInfoDao);
        registerDao(Sold.class, soldDao);
    }
    
    public void clear() {
        loginInfoDaoConfig.getIdentityScope().clear();
        soldDaoConfig.getIdentityScope().clear();
    }

    public LoginInfoDao getLoginInfoDao() {
        return loginInfoDao;
    }

    public SoldDao getSoldDao() {
        return soldDao;
    }

}
