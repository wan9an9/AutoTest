package test.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import test.dao.VOBean;
import test.service.ExamException;
import test.service.ExamService;
import test.service.TeacherException;
import test.service.TeacherService;

public class AlterTestTimeServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String tid = request.getParameter("tid");
		String pageString = request.getParameter("page");
		String limitString = request.getParameter("limit");
		TeacherService teacherservice = new TeacherService();
		try {
			int testcount = teacherservice.FindTestCount(tid);
			List<Object> tests = teacherservice.getTestToPages(tid, Integer.parseInt(pageString), Integer.parseInt(limitString));
			VOBean vo = new VOBean();
			vo.setCode(0);
			vo.setMsg("");
			vo.setCount(testcount);
			vo.setData(tests);
			response.getWriter().write(JSONObject.toJSONString(vo));
		} catch (TeacherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String subject = request.getParameter("subject");
		String classname = request.getParameter("classname");
		String begintime = request.getParameter("begintime");
		String endtime = request.getParameter("endtime");
		ExamService examservice = new ExamService();
		try {
			if(examservice.UpdateTestTime(classname, subject, begintime, endtime)){
				response.getWriter().write(JSONObject.toJSONString("success"));
			}else {
				response.getWriter().write(JSONObject.toJSONString("error"));
			}
		} catch (ExamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
