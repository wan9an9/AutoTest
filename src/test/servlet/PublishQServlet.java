package test.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import test.dao.TestStateBean;
import test.service.AdminException;
import test.service.AdminService;
import test.service.ExamException;
import test.service.ExamService;
import test.service.StudentService;
import test.service.TeacherException;
import test.service.TeacherService;

public class PublishQServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String tid = request.getParameter("tid");
		String subject = request.getParameter("subject");
		String classes = request.getParameter("classes");
		String begintime = request.getParameter("begintime");
		String endtime = request.getParameter("endtime");
		
		TestStateBean testState = new TestStateBean(classes, subject, begintime, endtime, "no", "no");
		ExamService examservice = new ExamService();
		AdminService adminservice = new AdminService();
		TeacherService teacherservice = new TeacherService();
		try {
			//选择全部班级
			if(classes.equals("allclasses")){
				String classstr = teacherservice.FindTeacherClass(tid, subject);
				String [] classslist = classstr.split(",|，| ");
				//已经发布
				if(examservice.FindPublishedTestInfoList(classslist, subject) == true){
					List<Object> testinfos = examservice.ReturnPublishedTestInfoList(subject);
					response.getWriter().write(JSONObject.toJSONString(testinfos));
				}else{
					boolean successflag = true;
					for(int i=0; i<classslist.length; i++){
						testState.setClassname(classslist[i]);
						if(examservice.PublishQuestion(testState) == false){
							successflag = false;
							}
						}
					if(successflag == true){
						for (int j = 0; j < classslist.length; j++) {
							//在班级作答表建立空答题卡
							String QBankDbName = adminservice.SelectQBankDbName(classslist[j]);
							String ABankDbName = adminservice.SelectABankDbName(classslist[j]);
							examservice.CreateNullAnswerSheet(QBankDbName, ABankDbName, subject, classslist[j]);
						}
						response.getWriter().write(JSONObject.toJSONString("success"));
					}
					else {
						response.getWriter().write(JSONObject.toJSONString("error"));
					}
				}
			}
			//选择某个班级
			else {
				//已经发布
				if(examservice.FindPublishedTestInfo(classes, subject) == true){
					List<Object> testinfo = examservice.ReturnPublishedTestInfo(classes, subject);
					response.getWriter().write(JSONObject.toJSONString(testinfo));
				}else{
					if(examservice.PublishQuestion(testState) == false){
							response.getWriter().write(JSONObject.toJSONString("error"));
						}
					else {
						//在班级作答表建立空答题卡
						String QBankDbName = adminservice.SelectQBankDbName(classes);
						String ABankDbName = adminservice.SelectABankDbName(classes);
						examservice.CreateNullAnswerSheet(QBankDbName, ABankDbName, subject, classes);
						response.getWriter().write(JSONObject.toJSONString("success"));
						}
					}
			}
		} catch (ExamException | TeacherException | AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//设置点击编辑题目时，若题目已发布，不可编辑
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String tid = request.getParameter("tid");
		String subject = request.getParameter("subject");
		TeacherService teacherservice = new TeacherService();
		ExamService examservice = new ExamService();
		try {
			String classstr = teacherservice.FindTeacherClass(tid, subject);
			String [] classslist = classstr.split(",|，| ");
			if(examservice.FindTest(classslist[0], subject) == true){
				response.getWriter().write(JSONObject.toJSONString("考试已经发布，不可再编辑！"));
			}else {
				response.getWriter().write(JSONObject.toJSONString("canedit"));
			}
		} catch (ExamException | TeacherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}