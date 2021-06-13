package test.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import test.dao.VOBean;
import test.service.TeacherException;
import test.service.TeacherService;

public class TShowInfoServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		String pageString = request.getParameter("page");
		String limitString = request.getParameter("limit");
		TeacherService teacherService = new TeacherService();
		//首次加载表，参数全为空
		if(pageString == null && limitString == null){
			pageString = "1";
			limitString = "12";
		}
		//分页
		try {
			int teachercount = teacherService.FindTeacherCount();
			List<Object> teachers = teacherService.getTeachersToPages(Integer.parseInt(pageString), Integer.parseInt(limitString));
			VOBean vo = new VOBean();
			vo.setCode(0);
			vo.setMsg("");
			vo.setCount(teachercount);
			vo.setData(teachers);
			response.getWriter().write(JSONObject.toJSONString(vo));
		} catch (TeacherException e) {
			// TODO Auto-generated catch block
			//无教师返回提示信息
			request.setAttribute("error_noteacher", e.getMessage());
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
	}
	
	//发布题目时根据选择的科目显示所教班级
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String tid = request.getParameter("tid");
		String subject = request.getParameter("subject");
		
		TeacherService teacherservice = new TeacherService();
		try {
			String classstr = teacherservice.FindTeacherClass(tid, subject);
			//班级列表
			String [] classses = classstr.split(",|，| ");
			response.getWriter().write(JSONObject.toJSONString(classses));
		} catch (TeacherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
