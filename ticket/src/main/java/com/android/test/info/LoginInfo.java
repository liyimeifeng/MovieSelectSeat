package com.android.test.info;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class LoginInfo {

	@Id(autoincrement = true)
	private long user_id;
	private String id;
	private String pwd;
	private String tel;
	private String email;

	@Generated(hash = 9641946)
	public LoginInfo(long user_id, String id, String pwd, String tel, String email) {
		this.user_id = user_id;
		this.id = id;
		this.pwd = pwd;
		this.tel = tel;
		this.email = email;
	}

	public LoginInfo() {
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String gettel() {
		// TODO Auto-generated method stub
		return tel;
	}

	public String getTel() {
		return this.tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}