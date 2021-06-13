package test.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import test.dao.VOBean;
import test.service.AdminException;
import test.service.AdminService;
import test.service.ExamException;
import test.service.ExamService;

public class MyScoreAnalysisServlet extends HttpServlet {
	//机器评分知识点雷达图显示
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String sid = request.getParameter("sid");
		String classname = request.getParameter("sclass");
		String subject = request.getParameter("subject");
		ExamService examservice = new ExamService();
		AdminService adminservice = new AdminService();
		try {
			String QBankDbName = adminservice.SelectQBankDbName(classname);
			String ABankDbName = adminservice.SelectABankDbName(classname);
			List<Object> tmp = examservice.ShowCScoreAnalysis(QBankDbName, ABankDbName, sid, classname, subject);
			response.getWriter().write(JSONObject.toJSONString(tmp));
		} catch (ExamException | AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//教师评分知识点雷达图显示
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String sid = request.getParameter("sid");
		String classname = request.getParameter("sclass");
		String subject = request.getParameter("subject");
		ExamService examservice = new ExamService();
		AdminService adminservice = new AdminService();
		try {
			if(examservice.FindPcheckover(classname, subject).equals("yes")){
				String QBankDbName = adminservice.SelectQBankDbName(classname);
				String ABankDbName = adminservice.SelectABankDbName(classname);
				List<Object> tmp = examservice.ShowPScoreAnalysis(QBankDbName, ABankDbName, sid, classname, subject);
				response.getWriter().write(JSONObject.toJSONString(tmp));
			}else {
				response.getWriter().write(JSONObject.toJSONString("error"));
			}
		} catch (ExamException | AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
