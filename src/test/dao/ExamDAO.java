package test.dao;

import java.util.List;

public interface ExamDAO {
	
	//查询
	//1.从某个班级题表中分页显示题目（老师发题）
	public List<Object> getQuestionsToPages(String qBankDbName, String subject, int page, int limit);
	//2.从某个班级题表中查询某科目的完整题目数目
	public int getFullQuestionCount(String dbname, String tsubject);
	//3.从某个班级题表中查询某科目的不完整题目数目
	public int getUnFullQuestionCount(String dbname, String tsubject);
	//4.从某个班级题表中查询某科目的题目数目
	public int getQuestionCount(String dbname, String tsubject);
	//5.判断该科目有无记录
	public boolean FindSubjectRecord(String qBankDbName, String tsubject);
	//6.判断该科目有无完整记录
	public int FindIsFullSubjectRecord(String qBankDbName, String tsubject);
	//7.从某个班级题表中分页显示完整的题目
	public List<Object> getFullQuestionsToPages(String qBankDbName, String subject, int page, int limit);
	//8.查询状态表，题目是否已经发布(选择单个班级)
	public boolean FindPublishedTestInfo(String classes, String subject);
	//9.得到已发布的某个考试的状态信息(选择单个班级)
	public List<Object> ReturnPublishedTestInfo(String classes, String subject);
	//10.查询状态表，题目是否已经发布(选择全部班级)
	public boolean FindPublishedTestInfoList(String[] classslist, String subject);
	//11.得到已发布的某个考试的状态信息(选择全部班级)
	public List<Object> ReturnPublishedTestInfoList(String subject);
	//12.获取某科目的开始考试时间
	public String FindBeginTime(String sclass, String subject);
	//13.获取某科目的结束考试时间
	public String FindEndTime(String sclass, String subject);
	//14.查询考试是否已发布
	public boolean FindTest(String sclass, String subject);
	//15.在题表中计算某科目的总分
	public String FindTotalPoints(String qBankDbName, String subject);
	//16.从某个班级题表中分页显示题目（学生答题）
	public List<Object> getQuestionsToPagesForStudent(String qBankDbName, String subject, int page, int limit);
	//17.分页显示 单个班级 答题卡
	public List<Object> getQAToPages(String qBankDbName, String aBankDbName, String sclass, String subject, int page,
			int limit);
	//18.查询某班题表中的完整题目的题号是否连续
	public boolean CheckIdLianXu(String qBankDbName, String subject);
	//19.查询输入的分数格式是否正确
	public String [] checkPointFormat(String qBankDbName, String subject);
	//20.某班人工已评分条数
	public int FindMarkCount(String qBankDbName, String aBankDbName, String classes, String subject);
	//21.获取某考试人工评分是否完成标志
	public String FindPcheckover(String classes, String subject);
	//22.显示人工评分某班的成绩单
	public List<Object> ShowPTestCard(String qBankDbName, String aBankDbName, String classes, String subject);
	//23.获取某考试机器评分是否完成标志
	public String FindCcheckover(String classes, String subject);
	//24.自动评阅结果表显示
	public List<Object> ShowAutoMarkResult(String qBankDbName, String aBankDbName, String classname, String subject);
	//25.显示自动评分某班的成绩单
	public List<Object> ShowCTestCard(String qBankDbName, String aBankDbName, String classname, String subject);
	//26.评分分析
	public List<Object> ShowMarkECharts(String qBankDbName, String aBankDbName, String classname, String subject);
	//27.学生查阅试卷评阅结果
	public List<Object> ShowStudentTestResult(String qBankDbName, String aBankDbName, String sid, String classname, String subject,
			int page, int limit);
	//28.机器评分知识点雷达图显示
	public List<Object> ShowStudentTestResult(String qBankDbName, String aBankDbName, String sid, String classname,
			String subject);
	//29.教师评分知识点雷达图显示
	public List<Object> ShowPScoreAnalysis(String qBankDbName, String aBankDbName, String sid, String classname,
			String subject);
	
	
	
	//插入
	//1.在所教科目的每个班级的题库根据题目数量创建题表头
	public void InsertQHead(String qBankDbName, String tsubject, int qcount);
	//2.//在所教科目的每个班级的题库  附加创建  题表头
	public void InsertRestQHead(String QBankDbName, String tsubject, int begin, int count);
	//3.发布题目，修改状态表
	public boolean PublishQuestion(TestStateBean testState);
	//4.在班级作答表建立空答题卡
	public void CreateNullAnswerSheet(String QBankDbName, String ABankDbName, String subject, String classes);
	
	//修改
	//1.在所教科目的每个班级的题库根据题号编辑题目 
	public boolean UpdateQuestion(String qBankDbName, ExamBean question);
	//2.题目答案提交
	public boolean UpdateAnswer(String aBankDbName, String sid, String subject, String id, String myanswer);
	//3.老师评分提交
	public boolean UpdatePpoint(String aBankDbName, String sid, String subject, String id, String ppoint);
	//4.修改考试时间
	public boolean UpdateTestTime(String classname, String subject, String begintime, String endtime);
	//5.自动评分1
	public boolean AutoMark(String qBankDbName, String aBankDbName, String classname, String subject);
	//6.自动评分2
	boolean UpdateCpoint(String aBankDbName, String sid, String subject, String id, String cpoint);
	//7.设置某考试人工评分否完成标志
	public boolean UpdatePcheckover(String classes, String subject);
	//8.设置某考试机器评分否完成标志
	public boolean UpdateCcheckover(String classes, String subject);

}
