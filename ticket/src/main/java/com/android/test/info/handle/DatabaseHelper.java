package com.android.test.info.handle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	String createStr;
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version,String createStr) {
		super(context, name, factory, version);
		this.createStr=createStr;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		System.out.println("Creaet a database test_user and table user");
		arg0.execSQL(createStr);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		System.out.println("upgrate a database");
	}

}
