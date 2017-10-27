package com.android.test.info;

public class LoginInfo {
	
	private int user_id;
	private String id;
	private String pwd;
	private String tel;
	private String card;
	
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
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
	public void settel(String tel) {
		this.tel = tel;
	}
}