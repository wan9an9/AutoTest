package test.dao;

public class ExamBean {
	private String subject;
	private String id;
	private String topic;
	private String knowledge;
	private String answer;
	private String point;

	public ExamBean() {

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

	public ExamBean(String subject, String id, String topic, String knowledge, String answer, String point) {
		super();
		this.subject = subject;
		this.id = id;
		this.topic = topic;
		this.knowledge = knowledge;
		this.answer = answer;
		this.point = point;
	}
}
