package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import test.service.AdminException;
import test.service.AdminService;
import test.service.ExamException;
import test.service.TeacherException;
import test.service.TeacherService;

public class DeleteLibraryServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String delclassname = request.getParameter("classname");
		AdminService admin = new AdminService();
		try {
			if (admin.DeleteClass(delclassname) == true) {
				response.getWriter().write(JSONObject.toJSONString("success"));
			}else {
				response.getWriter().write(JSONObject.toJSONString("error"));
			}
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
	}
	
	
	
	//删除教师
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String tid = request.getParameter("tid");
		String tsubjects = request.getParameter("subject");
		AdminService admin = new AdminService();
		try {
			if(admin.DeleteTeacher(tid, tsubjects) == true){
				response.getWriter().write(JSONObject.toJSONString("success"));
			}else {
				response.getWriter().write(JSONObject.toJSONString("error"));
			}
		} catch (AdminException e) {
			// TODO Auto-generated catch block
		}
	}
}