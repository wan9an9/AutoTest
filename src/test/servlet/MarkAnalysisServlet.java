package test.servlet;

import java.io.IOException;
import java.util.List;

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

public class MarkAnalysisServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String classname = request.getParameter("classname");
		String subject = request.getParameter("subject");
		ExamService examservice = new ExamService();
		AdminService adminservice = new AdminService();
		try {
			String QBankDbName = adminservice.SelectQBankDbName(classname);
			String ABankDbName = adminservice.SelectABankDbName(classname);
			if(examservice.FindCcheckover(classname, subject).equals("yes") && examservice.FindPcheckover(classname, subject).equals("yes")){
				List<Object> xy = examservice.ShowMarkECharts(QBankDbName, ABankDbName, classname, subject);
				response.getWriter().write(JSONObject.toJSONString(xy));
			}else {
				response.getWriter().write(JSONObject.toJSONString("【网络17k1 思修】老师或机器还未批改完毕，请稍后再查看！"));
			}
		} catch (ExamException | AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
