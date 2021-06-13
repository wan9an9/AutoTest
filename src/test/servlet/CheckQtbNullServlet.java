package test.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import test.service.AdminException;
import test.service.AdminService;
import test.service.ExamException;
import test.service.ExamService;
import test.service.TeacherException;
import test.service.TeacherService;

public class CheckQtbNullServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String tid = request.getParameter("tid");
		String subject = request.getParameter("subject");
		String classes = request.getParameter("classes");
		
		TeacherService teacherservice = new TeacherService();
		AdminService adminservice = new AdminService();
		ExamService examservice = new ExamService();
		
		try {
			String QBankDbName = "";
			if(classes.equals("allclasses")){
				String classstr = teacherservice.FindTeacherClass(tid, subject);
				//班级列表
				String [] classses = classstr.split(",|，| ");
				//第一个班级对应的题表名
				QBankDbName = adminservice.SelectQBankDbName(classses[0]);
			}
			//选择单个班级
			else {
				QBankDbName = adminservice.SelectQBankDbName(classes);
			}
			//先查题表是否为空
			if(examservice.getFullQuestionCount(QBankDbName, subject) > 0){
				//再查询题表中的完整题目的题号是否连续
				if(examservice.CheckIdLianXu(QBankDbName, subject) == true){
					int allcount = examservice.getQuestionCount(QBankDbName, subject) ;
					int fullcount = examservice.getFullQuestionCount(QBankDbName, subject);
					int begincount = examservice.getUnFullQuestionCount(QBankDbName, subject);
					//再查询是否有未编辑完成的不完整题目
					if(allcount - fullcount - begincount == 0){
						//再查询输入的分数格式是否正确（ajax完成）
						String [] pointlist = examservice.checkPointFormat(QBankDbName, subject);
						response.getWriter().write(JSONObject.toJSONString(pointlist));
					}
					//不空，连续，但有未编辑完成的不完整题目
					else if(allcount - fullcount - begincount > 0){
						response.getWriter().write(JSONObject.toJSONString("存在不完整题目，请点击“编辑试题”进行检查修改！"));
					}
				}
				//不空，不连续
				else {
					response.getWriter().write(JSONObject.toJSONString("存在题号不连续，请点击“编辑试题”进行检查修改！"));
				}
			}
			else {
				response.getWriter().write(JSONObject.toJSONString("题表为空，请先点击“编辑试题”编辑题目！"));
			}
		} catch (ExamException | TeacherException | AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//编辑题目页提示已创建的题目数量（所有的，包括完整的和不完整的）
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String tid = request.getParameter("tid");
		String subject = request.getParameter("subject");
		
		TeacherService teacherservice = new TeacherService();
		AdminService adminservice = new AdminService();
		ExamService examservice = new ExamService();
		
		try {
			String classstr = teacherservice.FindTeacherClass(tid, subject);
			//班级列表
			String [] classses = classstr.split(",|，| ");
			//第一个班级对应的题表名
			String QBankDbName = adminservice.SelectQBankDbName(classses[0]);
			int count = examservice.getQuestionCount(QBankDbName, subject);
			response.getWriter().write(JSONObject.toJSONString(count));
		} catch (ExamException | TeacherException | AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}