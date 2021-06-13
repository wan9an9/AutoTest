package test.dao;

public class ClassBankBean {
	private String classname;
	private String qbankdbname;
	private String abankdbname;

	public ClassBankBean() {

	}

	public ClassBankBean(String classname, String qbankdbname, String abankdbname) {
		super();
		this.classname = classname;
		this.qbankdbname = qbankdbname;
		this.abankdbname = abankdbname;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getQbankdbname() {
		return qbankdbname;
	}

	public void setQbankdbname(String qbankdbname) {
		this.qbankdbname = qbankdbname;
	}

	public String getAbankdbname() {
		return abankdbname;
	}

	public void setAbankdbname(String abankdbname) {
		this.abankdbname = abankdbname;
	}
	
}
