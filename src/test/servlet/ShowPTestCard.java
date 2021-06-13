package test.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import test.dao.StudentTestBean;
import test.dao.VOBean;
import test.service.AdminException;
import test.service.AdminService;
import test.service.ExamException;
import test.service.ExamService;
import test.service.StudentException;
import test.service.StudentService;
import test.service.TeacherException;
import test.service.TeacherService;

public class ShowPTestCard extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String subject = request.getParameter("subject");
		String classes = request.getParameter("classes");
		
		ExamService examservice = new ExamService();
		AdminService adminservice = new AdminService();
		StudentService studentservice = new StudentService();
		try {
			String QBankDbName = adminservice.SelectQBankDbName(classes);
			String ABankDbName = adminservice.SelectABankDbName(classes);
			int studentcount = studentservice.FindCountByClass(classes);
			List<Object> ptestcard = examservice.ShowPTestCard(QBankDbName, ABankDbName, classes, subject);
			for (int i = 0; i < ptestcard.size(); i++) {
				StudentTestBean s = (StudentTestBean)ptestcard.get(i);
				s.setSname(studentservice.FindSnameById(s.getSid()));
				s.setSsex(studentservice.FindSsexById(s.getSid()));
			}
			VOBean vo = new VOBean();
			vo.setCode(0);
			vo.setMsg("");
			vo.setCount(studentcount);
			vo.setData(ptestcard);
			response.getWriter().write(JSONObject.toJSONString(vo));
		} catch (ExamException | AdminException | StudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
