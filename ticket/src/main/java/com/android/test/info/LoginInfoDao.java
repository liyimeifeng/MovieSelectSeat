package com.android.test.info;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LOGIN_INFO".
*/
public class LoginInfoDao extends AbstractDao<LoginInfo, Long> {

    public static final String TABLENAME = "LOGIN_INFO";

    /**
     * Properties of entity LoginInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property User_id = new Property(0, Long.class, "user_id", true, "_id");
        public final static Property Id = new Property(1, String.class, "id", false, "ID");
        public final static Property Pwd = new Property(2, String.class, "pwd", false, "PWD");
        public final static Property Tel = new Property(3, String.class, "tel", false, "TEL");
        public final static Property Email = new Property(4, String.class, "email", false, "EMAIL");
    };


    public LoginInfoDao(DaoConfig config) {
        super(config);
    }
    
    public LoginInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LOGIN_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: user_id
                "\"ID\" TEXT," + // 1: id
                "\"PWD\" TEXT," + // 2: pwd
                "\"TEL\" TEXT," + // 3: tel
                "\"EMAIL\" TEXT);"); // 4: email
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LOGIN_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LoginInfo entity) {
        stmt.clearBindings();
 
        Long user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindLong(1, user_id);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String pwd = entity.getPwd();
        if (pwd != null) {
            stmt.bindString(3, pwd);
        }
 
        String tel = entity.getTel();
        if (tel != null) {
            stmt.bindString(4, tel);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(5, email);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LoginInfo entity) {
        stmt.clearBindings();
 
        Long user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindLong(1, user_id);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String pwd = entity.getPwd();
        if (pwd != null) {
            stmt.bindString(3, pwd);
        }
 
        String tel = entity.getTel();
        if (tel != null) {
            stmt.bindString(4, tel);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(5, email);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LoginInfo readEntity(Cursor cursor, int offset) {
        LoginInfo entity = new LoginInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // user_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // pwd
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // tel
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // email
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LoginInfo entity, int offset) {
        entity.setUser_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPwd(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTel(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEmail(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LoginInfo entity, long rowId) {
        entity.setUser_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LoginInfo entity) {
        if(entity != null) {
            return entity.getUser_id();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
