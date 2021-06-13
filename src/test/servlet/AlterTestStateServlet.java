package test.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import test.service.AdminException;
import test.service.AdminService;
import test.service.ExamException;
import test.service.ExamService;
import test.service.StudentException;
import test.service.StudentService;

public class AlterTestStateServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String subject = request.getParameter("subject");
		String classes = request.getParameter("sclass");
		
		ExamService examservice = new ExamService();
		AdminService adminservice = new AdminService();
		StudentService studentservice = new StudentService();
		try {
			String QBankDbName = adminservice.SelectQBankDbName(classes);
			String ABankDbName = adminservice.SelectABankDbName(classes);
			//表行数 = 题目数 × 该班级学生数
			int qcount = examservice.getFullQuestionCount(QBankDbName, subject);
			int studentcount = studentservice.FindCountByClass(classes);
			int allcount = qcount*studentcount;
			int markcount = examservice.FindMarkCount(QBankDbName, ABankDbName, classes, subject);
			if(allcount == markcount){
				if(examservice.FindPcheckover(classes, subject).equals("no")){
					if(examservice.UpdatePcheckover(classes, subject) == true){
						response.getWriter().write(JSONObject.toJSONString("提交成功，请点击查看成绩单！"));
					}else {
						response.getWriter().write(JSONObject.toJSONString("提交失败，请重新提交！"));
					}
				}else {
					response.getWriter().write(JSONObject.toJSONString("提交成功，请点击查看成绩单！"));
				}
			}else {
				response.getWriter().write(JSONObject.toJSONString("提交失败，因为还存在未评阅的题目，请检查并评阅！"));
			}
		} catch (ExamException | AdminException | StudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
