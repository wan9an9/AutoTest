package test.dao;

import java.io.Serializable;

public class TeacherBean implements Serializable{
	private String tid;
	private String tpwd;
	private String tname;
	private String tsex;
	private String tclasses;
	private String tsubjects;
	
	public TeacherBean(){
		
	}
	
	public TeacherBean(String tid, String tpwd, String tname, String tsex, String tclasses, String tsubjects) {
		this.tid = tid;
		this.tpwd = tpwd;
		this.tname = tname;
		this.tsex = tsex;
		this.tclasses = tclasses;
		this.tsubjects = tsubjects;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTpwd() {
		return tpwd;
	}

	public void setTpwd(String tpwd) {
		this.tpwd = tpwd;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTsex() {
		return tsex;
	}

	public void setTsex(String tsex) {
		this.tsex = tsex;
	}

	public String getTclasses() {
		return tclasses;
	}

	public void setTclasses(String tclasses) {
		this.tclasses = tclasses;
	}

	public String getTsubjects() {
		return tsubjects;
	}

	public void setTsubjects(String tsubjects) {
		this.tsubjects = tsubjects;
	}

	@Override
	public String toString() {
		return "TeacherBean [tid=" + tid + ", tpwd=" + tpwd + ", tname=" + tname + ", tsex=" + tsex + ", tclasses="
				+ tclasses + ", tsubjects=" + tsubjects + "]";
	}
	
}
