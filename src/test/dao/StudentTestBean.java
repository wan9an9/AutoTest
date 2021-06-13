package test.dao;

public class StudentTestBean {
	private String sid;
	private String sclass;
	private String subject;
	private String id;
	private String myanswer;
	private String cpoint;
	private String ppoint;

	private String topic;
	private String knowledge;
	private String answer;
	private String point;

	private String ptotalpoint;
	private String ctotalpoint;
	private String pranking; //
	private String cranking; //

	private String sname;
	private String ssex;

	private String cavgpoint;
	private String pavgpoint;

	public StudentTestBean() {

	}

	public String getSid() {
		return sid;
	}

	public StudentTestBean(String sid, String sclass, String subject, String id, String myanswer, String cpoint,
			String ppoint, String topic, String knowledge, String answer, String point, String ptotalpoint,
			String ctotalpoint, String pranking, String cranking, String sname, String ssex, String cavgpoint,
			String pavgpoint) {
		super();
		this.sid = sid;
		this.sclass = sclass;
		this.subject = subject;
		this.id = id;
		this.myanswer = myanswer;
		this.cpoint = cpoint;
		this.ppoint = ppoint;
		this.topic = topic;
		this.knowledge = knowledge;
		this.answer = answer;
		this.point = point;
		this.ptotalpoint = ptotalpoint;
		this.ctotalpoint = ctotalpoint;
		this.pranking = pranking;
		this.cranking = cranking;
		this.sname = sname;
		this.ssex = ssex;
		this.cavgpoint = cavgpoint;
		this.pavgpoint = pavgpoint;
	}

	public String getCavgpoint() {
		return cavgpoint;
	}

	public void setCavgpoint(String cavgpoint) {
		this.cavgpoint = cavgpoint;
	}

	public String getPavgpoint() {
		return pavgpoint;
	}

	public void setPavgpoint(String pavgpoint) {
		this.pavgpoint = pavgpoint;
	}

	public String getPtotalpoint() {
		return ptotalpoint;
	}

	public void setPtotalpoint(String ptotalpoint) {
		this.ptotalpoint = ptotalpoint;
	}

	public String getCtotalpoint() {
		return ctotalpoint;
	}

	public void setCtotalpoint(String ctotalpoint) {
		this.ctotalpoint = ctotalpoint;
	}

	public String getSclass() {
		return sclass;
	}

	public void setSclass(String sclass) {
		this.sclass = sclass;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMyanswer() {
		return myanswer;
	}

	public void setMyanswer(String myanswer) {
		this.myanswer = myanswer;
	}

	public String getCpoint() {
		return cpoint;
	}

	public void setCpoint(String cpoint) {
		this.cpoint = cpoint;
	}

	public String getPpoint() {
		return ppoint;
	}

	public void setPpoint(String ppoint) {
		this.ppoint = ppoint;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getPranking() {
		return pranking;
	}

	public void setPranking(String pranking) {
		this.pranking = pranking;
	}

	public String getCranking() {
		return cranking;
	}

	public void setCranking(String cranking) {
		this.cranking = cranking;
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

}
