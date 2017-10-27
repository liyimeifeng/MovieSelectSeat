package com.android.test.info.handle;

public interface IDatabaseHandle {
	public void createDatabase();
	public void upgrateDatabase();
	public void insert(Object obj);
	public void update(Object obj);
	public void query();
	public void delete();
}
