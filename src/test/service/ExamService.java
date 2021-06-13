package test.service;

import java.util.List;

import test.dao.*;

public class ExamService {
	private  ExamDAO examDAO = DAOFactory.getExamDAOInstance();
	
	//从某个班级题表中分页显示题目（老师发题）
	public List<Object> getQuestionsToPages(String qBankDbName, String subject, int page, int limit) throws ExamException{
		List<Object> questions = examDAO.getQuestionsToPages(qBankDbName, subject, page, limit);
		return questions;
	}
	
	//从某个班级题表中分页显示题目（学生答题）
	public List<Object> getQuestionsToPagesForStudent(String qBankDbName, String subject, int page, int limit) throws ExamException{
		List<Object> questions = examDAO.getQuestionsToPagesForStudent(qBankDbName, subject, page, limit);
		return questions;
	}
	
	//从某个班级题表中查询某科目的完整题目数目
	public int getFullQuestionCount(String dbname, String tsubject) throws ExamException{
		return examDAO.getFullQuestionCount(dbname, tsubject);
	}
	
	//从某个班级题表中查询某科目的不完整题目数目
	public int getUnFullQuestionCount(String dbname, String tsubject) throws ExamException{
		return examDAO.getUnFullQuestionCount(dbname, tsubject);
	}
	
	//从某个班级题表中查询某科目的题目数目
	public int getQuestionCount(String dbname, String tsubject) throws ExamException{
		return examDAO.getQuestionCount(dbname, tsubject);
	}
	
	//在所教科目的每个班级的题库根据题目数量创建题表头
	public void InsertQHead(String qBankDbName, String tsubject, int qcount) throws ExamException{
		examDAO.InsertQHead(qBankDbName, tsubject, qcount);
	}
	
	//判断该科目有无记录
	public boolean FindSubjectRecord(String qBankDbName, String tsubject) throws ExamException{
		boolean flag = false;
		if(examDAO.FindSubjectRecord(qBankDbName, tsubject)){
			flag = true;
		}
		return flag;
	}
	
	//判断该科目有无完整记录
	public int FindIsFullSubjectRecord(String qBankDbName, String tsubject) throws ExamException{
		return examDAO.FindIsFullSubjectRecord(qBankDbName, tsubject);
	}
	
	//在所教科目的每个班级的题库  附加创建  题表头
	public void InsertRestQHead(String QBankDbName, String tsubject, int begin, int count) throws ExamException{
		examDAO.InsertRestQHead(QBankDbName, tsubject, begin, count);
	}
	
	//在所教科目的每个班级的题库根据题号编辑题目 
	public boolean UpdateQuestion(String QBankDbName, ExamBean question) throws ExamException{
		boolean flag = false;
		if(examDAO.UpdateQuestion(QBankDbName, question) == true){
			flag = true;
		}
		return flag;
	}
	
	//从某个班级题表中分页显示完整的题目
	public List<Object> getFullQuestionsToPages(String qBankDbName, String subject, int page, int limit) throws ExamException{
		List<Object> questions = examDAO.getFullQuestionsToPages(qBankDbName, subject, page, limit);
		return questions;
	}
	
	//发布题目，修改状态表
	public boolean PublishQuestion(TestStateBean testState) throws ExamException{
		boolean flag = false;
		if(examDAO.PublishQuestion(testState) == true){
			flag = true;
		}
		return flag;
	}
	
	//查询状态表，题目是否已经发布(选择单个班级)
	public boolean FindPublishedTestInfo(String classes, String subject) throws ExamException{
		boolean flag = false;
		if(examDAO.FindPublishedTestInfo(classes, subject) == true){
			flag = true;
		}
		return flag;
	}
	
	//得到已发布的某个考试的状态信息(选择单个班级)
	public List<Object> ReturnPublishedTestInfo(String classes, String subject) throws ExamException{
		return examDAO.ReturnPublishedTestInfo(classes,subject);
	}
	
	//查询状态表，题目是否已经发布(选择全部班级)
	public boolean FindPublishedTestInfoList(String[] classslist, String subject) throws ExamException{
		boolean flag = false;
		if(examDAO.FindPublishedTestInfoList(classslist, subject) == true){
			flag = true;
		}
		return flag;
	}
	
	//得到已发布的某个考试的状态信息(选择全部班级)
	public List<Object> ReturnPublishedTestInfoList(String subject) throws ExamException{
		return examDAO.ReturnPublishedTestInfoList(subject);
	}
	
	//获取某科目的开始考试时间
	public String FindBeginTime(String sclass, String subject) throws ExamException{
		return examDAO.FindBeginTime(sclass, subject);
	}

	//获取某科目的结束考试时间
	public String FindEndTime(String sclass, String subject) throws ExamException{
		return examDAO.FindEndTime(sclass, subject);
	}
	
	//查询考试是否已发布
	public boolean FindTest(String sclass, String subject) throws ExamException{
		boolean flag = false;
		if(examDAO.FindTest(sclass, subject) == true){
			flag = true;
		}
		return flag;
	}
	
	//在题表中计算某科目的总分
	public String FindTotalPoints(String qBankDbName, String subject) throws ExamException{
		return examDAO.FindTotalPoints(qBankDbName, subject);
	}
	
	//题目答案提交
	public boolean UpdateAnswer(String aBankDbName, String sid, String subject, String id, String myanswer) throws ExamException{
		boolean flag = false;
		if(examDAO.UpdateAnswer(aBankDbName, sid, subject, id, myanswer) == true){
			flag = true;
		}
		return flag;
	}
	
	//分页显示 单个班级 答题卡
	public List<Object> getQAToPages(String qBankDbName, String aBankDbName, String sclass, String subject,
			int page, int limit) throws ExamException{
		return examDAO.getQAToPages(qBankDbName, aBankDbName, sclass, subject, page, limit);
	}
	
	//查询某班题表中的完整题目的题号是否连续
	public boolean CheckIdLianXu(String qBankDbName, String subject) throws ExamException{
		boolean flag = true;
		if(examDAO.CheckIdLianXu(qBankDbName, subject) == false){
			flag = false;
		}
		return flag;
	}
	
	//在班级作答表建立空答题卡
	public void CreateNullAnswerSheet(String QBankDbName, String ABankDbName, String subject, String classes) throws ExamException{
		examDAO.CreateNullAnswerSheet(QBankDbName, ABankDbName, subject, classes);
	}
	
	//查询输入的分数格式是否正确
	public String [] checkPointFormat(String qBankDbName, String subject) throws ExamException{
		return examDAO.checkPointFormat(qBankDbName, subject);
	}
	
	//老师评分提交
	public boolean UpdatePpoint(String aBankDbName, String sid, String subject, String id, String ppoint) throws ExamException{
		boolean flag = false;
		if(examDAO.UpdatePpoint(aBankDbName, sid, subject, id, ppoint) == true){
			flag = true;
		}
		return flag;
	}

	//修改考试时间
	public boolean UpdateTestTime(String classname, String subject, String begintime, String endtime) throws ExamException{
		boolean flag = false;
		if(examDAO.UpdateTestTime(classname, subject, begintime, endtime) == true){
			flag = true;
		}
		return flag;
	}

	//某班人工已评分条数
	public int FindMarkCount(String qBankDbName, String aBankDbName, String classes, String subject) throws ExamException{
		return examDAO.FindMarkCount(qBankDbName, aBankDbName, classes, subject);
	}

	//获取某考试人工评分是否完成标志
	public String FindPcheckover(String classes, String subject) throws ExamException{
		return examDAO.FindPcheckover(classes, subject);
	}

	//设置某考试人工评分否完成标志
	public boolean UpdatePcheckover(String classes, String subject) throws ExamException{
		boolean flag = false;
		if(examDAO.UpdatePcheckover(classes, subject) == true){
			flag = true;
		}
		return flag;
	}

	//显示人工评分某班的成绩单
	public List<Object> ShowPTestCard(String qBankDbName, String aBankDbName, String classes, String subject) throws ExamException{
		return examDAO.ShowPTestCard(qBankDbName, aBankDbName, classes, subject);
	}

	//自动评分
	public boolean AutoMark(String qBankDbName, String aBankDbName, String classname, String subject) throws ExamException{
		boolean flag = false;
		if(examDAO.AutoMark(qBankDbName, aBankDbName, classname, subject) == true){
			flag = true;
		}
		return flag;
	}

	//获取某考试机器评分是否完成标志
	public String FindCcheckover(String classes, String subject) throws ExamException{
		return examDAO.FindCcheckover(classes, subject);
	}

	//设置某考试机器评分否完成标志
	public boolean UpdateCcheckover(String classes, String subject) throws ExamException{
		boolean flag = false;
		if(examDAO.UpdateCcheckover(classes, subject) == true){
			flag = true;
		}
		return flag;
	}

	//自动评阅结果表显示
	public List<Object> ShowAutoMarkResult(String qBankDbName, String aBankDbName, String classname, String subject) throws ExamException{
		return examDAO.ShowAutoMarkResult(qBankDbName, aBankDbName, classname, subject);
	}

	//显示自动评分某班的成绩单
	public List<Object> ShowCTestCard(String qBankDbName, String aBankDbName, String classname, String subject) throws ExamException{
		return examDAO.ShowCTestCard(qBankDbName, aBankDbName, classname, subject);
	}

	//评分分析
	public List<Object> ShowMarkECharts(String qBankDbName, String aBankDbName, String classname, String subject) throws ExamException{
		return examDAO.ShowMarkECharts(qBankDbName, aBankDbName, classname, subject);
	}

	//学生查阅试卷评阅结果
	public List<Object> ShowStudentTestResult(String qBankDbName, String aBankDbName, String sid, String classname, String subject,
			int page, int limit) throws ExamException{
		return examDAO.ShowStudentTestResult(qBankDbName, aBankDbName, sid, classname, subject, page, limit);
	}

	//机器评分知识点雷达图显示
	public List<Object> ShowCScoreAnalysis(String qBankDbName, String aBankDbName, String sid, String classname,
			String subject) throws ExamException{
		return examDAO.ShowStudentTestResult(qBankDbName, aBankDbName, sid, classname, subject);
	}

	//教师评分知识点雷达图显示
	public List<Object> ShowPScoreAnalysis(String qBankDbName, String aBankDbName, String sid, String classname,
			String subject) throws ExamException{
		return examDAO.ShowPScoreAnalysis(qBankDbName, aBankDbName, sid, classname, subject);
	}
}