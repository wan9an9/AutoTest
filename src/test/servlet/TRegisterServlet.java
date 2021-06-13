package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.TeacherBean;
import test.service.TeacherException;
import test.service.TeacherService;

public class TRegisterServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String regtid = request.getParameter("regtid");
		String regtname = new String(request.getParameter("regtname").getBytes("ISO-8859-1"),"UTF-8");
		String regtsex = new String(request.getParameter("regtsex").getBytes("ISO-8859-1"),"UTF-8");
		String regtclasses = new String(request.getParameter("regtclasses").getBytes("ISO-8859-1"),"UTF-8");
		String regtsubjects = new String(request.getParameter("regtsubjects").getBytes("ISO-8859-1"),"UTF-8");
		TeacherService teacherService = new TeacherService();
		try {
			teacherService.FindTeacherById(regtid, regtsubjects);
			//查有此人，不可注册
			request.setAttribute("error_oldteacher", "教授该科目的该教师已注册！");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		} 
		//查无此人，可注册
		catch (TeacherException e) {
			// TODO Auto-generated catch block
			TeacherBean teacherregister = new TeacherBean();
			teacherregister.setTid(regtid);
			teacherregister.setTpwd(regtid);
			teacherregister.setTname(regtname);
			teacherregister.setTsex(regtsex);
			teacherregister.setTclasses(regtclasses);
			teacherregister.setTsubjects(regtsubjects);
			try {
				teacherService.RegisterTeacher(teacherregister);
				request.setAttribute("result_teacherregister", "注册成功！");
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			} catch (TeacherException e1) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e1.getMessage());
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			}
		}
	}

}
