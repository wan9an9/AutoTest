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

public class ShowQToStudentServlet extends HttpServlet {
	//分页显示题目表
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String pageString = request.getParameter("page");
		String limitString = request.getParameter("limit");
		String sclass = request.getParameter("sclass");
		String subject = request.getParameter("tsubject");
		
		ExamService examservice = new ExamService();
		AdminService adminservice = new AdminService();
		
		try {
			String QBankDbName = adminservice.SelectQBankDbName(sclass);
			int qcount = examservice.getFullQuestionCount(QBankDbName, subject);
			//分页显示
			List<Object> questions = examservice.getQuestionsToPagesForStudent(QBankDbName, subject, Integer.parseInt(pageString), Integer.parseInt(limitString));
			VOBean vo = new VOBean();
			vo.setCode(0);
			vo.setMsg("");
			vo.setCount(qcount);
			vo.setData(questions);
			response.getWriter().write(JSONObject.toJSONString(vo));
		} catch (ExamException | AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	//学生查询成绩前检查试卷是否批改
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String sclass = request.getParameter("sclass");
		String subject = request.getParameter("subject");
		ExamService examservice = new ExamService();
		try {
			if(examservice.FindTest(sclass, subject) == true && examservice.FindCcheckover(sclass, subject).equals("yes")){
				response.getWriter().write(JSONObject.toJSONString("canlook"));
			}else {
				response.getWriter().write(JSONObject.toJSONString("该科目还未发布考试或机器还未批改完毕，请稍后再查询！"));
			}
		} catch (ExamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
