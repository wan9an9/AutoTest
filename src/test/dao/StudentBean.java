package test.dao;

import java.io.Serializable;

public class StudentBean implements Serializable{
	private String sid;
	private String spwd;
	private String sname;
	private String ssex;
	private String sclass;
	private String ssubjects;
	
	public StudentBean(){
		
	}
	
	public StudentBean(String sid, String spwd, String sname, String ssex, String sclass, String ssubjects) {
		this.sid = sid;
		this.spwd = spwd;
		this.sname = sname;
		this.ssex = ssex;
		this.sclass = sclass;
		this.ssubjects = ssubjects;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSpwd() {
		return spwd;
	}

	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSsex() {
		return ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public String getSclass() {
		return sclass;
	}

	public void setSclass(String sclass) {
		this.sclass = sclass;
	}

	public String getSsubjects() {
		return ssubjects;
	}

	public void setSsubjects(String ssubjects) {
		this.ssubjects = ssubjects;
	}

	@Override
	public String toString() {
		return "StudentBean [sid=" + sid + ", spwd=" + spwd + ", sname=" + sname + ", ssex=" + ssex + ", sclass="
				+ sclass + ", ssubjects=" + ssubjects + "]";
	}
	
}
