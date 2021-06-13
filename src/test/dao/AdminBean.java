package test.dao;

import java.io.Serializable;

public class AdminBean implements Serializable{
	private String aid;
	private String apwd;
	
	public AdminBean(){
		
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getApwd() {
		return apwd;
	}

	public void setApwd(String apwd) {
		this.apwd = apwd;
	}

	public AdminBean(String aid, String apwd) {
		super();
		this.aid = aid;
		this.apwd = apwd;
	}

}
