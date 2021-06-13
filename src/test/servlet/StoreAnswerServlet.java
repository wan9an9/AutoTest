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

public class StoreAnswerServlet extends HttpServlet {
	//学生提交答案
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String sid = request.getParameter("sid");
		String sclass = request.getParameter("sclass");
		String subject = request.getParameter("subject");
		String id = request.getParameter("id");
		String myanswer = request.getParameter("myanswer");
		
		ExamService examservice = new ExamService();
		AdminService adminservice = new AdminService();
		try {
			String ABankDbName = adminservice.SelectABankDbName(sclass);
			if(examservice.UpdateAnswer(ABankDbName, sid, subject, id, myanswer) == true){
				response.getWriter().write(JSONObject.toJSONString("提交保存成功！"));
			}else {
				response.getWriter().write(JSONObject.toJSONString("提交保存失败，请重新保存！"));
			}
		} catch (ExamException | AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//老师人工评分
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String sid = request.getParameter("sid");
		String subject = request.getParameter("subject");
		String id = request.getParameter("id");
		String ppoint = request.getParameter("ppoint");
		String sclass = request.getParameter("sclass");
		
		ExamService examservice = new ExamService();
		AdminService adminservice = new AdminService();
		try{
			String ABankDbName = adminservice.SelectABankDbName(sclass);
			if(examservice.UpdatePpoint(ABankDbName, sid, subject, id, ppoint) == true){
				response.getWriter().write(JSONObject.toJSONString("评分提交成功！"));
			}else {
				response.getWriter().write(JSONObject.toJSONString("评分提交失败，请重新评分！"));
			}
		} catch (ExamException | AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
