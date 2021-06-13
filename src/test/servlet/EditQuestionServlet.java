package test.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import test.dao.ExamBean;
import test.service.AdminException;
import test.service.AdminService;
import test.service.ExamException;
import test.service.ExamService;
import test.service.TeacherException;
import test.service.TeacherService;

public class EditQuestionServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String tid = request.getParameter("tid");
		String subject = request.getParameter("subject");
		String id = request.getParameter("id");
		String topic = request.getParameter("topic");
		String knowledge = request.getParameter("knowledge");
		String answer = request.getParameter("answer");
		String point = request.getParameter("point");
		
		ExamBean question = new ExamBean(subject, id, topic, knowledge, answer, point);
		
		ExamService examservice = new ExamService();
		AdminService adminservice = new AdminService();
		TeacherService teacherservice = new TeacherService();
		try {
			String classstr = teacherservice.FindTeacherClass(tid, subject);
			//班级列表
			String [] classses = classstr.split(",|，| ");
			//在所教科目的每个班级的题库根据题号编辑题目 
			boolean successflag = true;
			for(int i=0; i<classses.length; i++){
				if(examservice.UpdateQuestion(adminservice.SelectQBankDbName(classses[i]), question) == false){
					successflag = false;
				}
			}
			if(successflag == true){
				response.getWriter().write(JSONObject.toJSONString("success"));
			}
			else {
				response.getWriter().write(JSONObject.toJSONString("error"));
			}
		} catch (ExamException | TeacherException | AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}