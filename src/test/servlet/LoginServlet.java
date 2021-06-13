package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import test.dao.*;
import test.service.*;

public class LoginServlet extends HttpServlet {
	
	static String ID = null;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//学生身份登录
		if(request.getParameter("character").equals("student")){
			//1. 依赖UserService，实例化一个UserService
			StudentService studentService = new StudentService();
			//2. 将request传过来的参数id，pwd封装成一个实例
			StudentBean studentlogin = new StudentBean();
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			studentlogin.setSid(id);
			studentlogin.setSpwd(pwd);
			try {
				StudentBean student = studentService.Login(studentlogin);
				request.getSession().setAttribute("session_student", student);
				response.sendRedirect("shome.jsp");
			} catch (StudentException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		//教师身份登录
		else if(request.getParameter("character").equals("teacher")){
			TeacherService teacherService = new TeacherService();
			TeacherBean teacherlogin = new TeacherBean();
			String id = request.getParameter("id");
			ID = id;
			String pwd = request.getParameter("pwd");
			teacherlogin.setTid(id);
			teacherlogin.setTpwd(pwd);
			try {
				List<Object> teacher = teacherService.Login(teacherlogin);
				request.getSession().setAttribute("session_teacher", teacher);
				response.sendRedirect("thome.jsp");
			} catch (TeacherException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		//管理员身份登录
		else if(request.getParameter("character").equals("admin")){
			AdminService adminService = new AdminService();
			//2. 将request传过来的参数id，pwd封装成一个实例
			AdminBean adminlogin = new AdminBean();
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			adminlogin.setAid(id);
			adminlogin.setApwd(pwd);
			try {
				AdminBean admin = adminService.Login(adminlogin);
				request.getSession().setAttribute("session_admin", admin);
				response.sendRedirect("admin.jsp");
			} catch (AdminException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String pageString = request.getParameter("page");
		String limitString = request.getParameter("limit");
		//System.out.println("1." + chooseclass +"和" + pageString + "和" + limitString);
		//首次加载表，参数全为空
		if(pageString == null && limitString == null){
			pageString = "1";
			limitString = "11";
		}
		TeacherService teacherService = new TeacherService();
		int teacherinfocount = 0;
		try {
			teacherinfocount = teacherService.FindTeacherCountById(ID);
			List<Object> teacher = teacherService.TeacherInfo(ID, Integer.parseInt(pageString), Integer.parseInt(limitString));
			VOBean vo = new VOBean();
			vo.setCode(0);
			vo.setMsg("");
			vo.setCount(teacherinfocount);
			vo.setData(teacher);
			response.getWriter().write(JSONObject.toJSONString(vo));
		} catch (TeacherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
