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
import test.service.StudentException;
import test.service.StudentService;

public class ShowQAToTeacherServlet extends HttpServlet {
	
	//分页显示 单个班级 答题卡
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String pageString = request.getParameter("page");
		String limitString = request.getParameter("limit");
		String sclass = request.getParameter("classes");
		String subject = request.getParameter("subject");
		
		ExamService examservice = new ExamService();
		AdminService adminservice = new AdminService();
		StudentService studentservice = new StudentService();
		try {
			String QBankDbName = adminservice.SelectQBankDbName(sclass);
			String ABankDbName = adminservice.SelectABankDbName(sclass);
			//表行数 = 题目数 × 该班级学生数
			int qcount = examservice.getFullQuestionCount(QBankDbName, subject);
			int studentcount = studentservice.FindCountByClass(sclass);
			List<Object> questions = examservice.getQAToPages(QBankDbName, ABankDbName, sclass, subject, Integer.parseInt(pageString), Integer.parseInt(limitString));
			VOBean vo = new VOBean();
			vo.setCode(0);
			vo.setMsg("");
			vo.setCount(qcount*studentcount);
			vo.setData(questions);
			response.getWriter().write(JSONObject.toJSONString(vo));
		} catch (ExamException | AdminException | StudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
