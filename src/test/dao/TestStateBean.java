package test.dao;

public class TestStateBean {
	private String classname;
	private String subject;
	private String begintime;
	private String endtime;
	private String ccheckover;
	private String pcheckover;

	public TestStateBean() {

	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getCcheckover() {
		return ccheckover;
	}

	public void setCcheckover(String ccheckover) {
		this.ccheckover = ccheckover;
	}

	public String getPcheckover() {
		return pcheckover;
	}

	public void setPcheckover(String pcheckover) {
		this.pcheckover = pcheckover;
	}

	public TestStateBean(String classname, String subject, String begintime, String endtime, String ccheckover,
			String pcheckover) {
		super();
		this.classname = classname;
		this.subject = subject;
		this.begintime = begintime;
		this.endtime = endtime;
		this.ccheckover = ccheckover;
		this.pcheckover = pcheckover;
	}

}
