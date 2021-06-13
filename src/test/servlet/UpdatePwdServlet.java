package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.service.*;

public class UpdatePwdServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//修改老师密码
		String tnewpwd = request.getParameter("tnewpassword");
		if(tnewpwd != null){
			String tid = (String) request.getSession().getAttribute("tid");
			try {
				TeacherService teacherService = new TeacherService();
				teacherService.UpdateTeacherPwd(tid, tnewpwd);
				request.setAttribute("result_tuppwd", "修改成功！");
				request.getRequestDispatcher("thome.jsp").forward(request, response);
			} catch (TeacherException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("thome.jsp").forward(request, response);
			}
		}
		
		//学生修改密码
		String snewpwd = request.getParameter("snewpassword");
		if(snewpwd != null){
			String sid = (String)request.getSession().getAttribute("sid");
			try {
				StudentService studentService = new StudentService();
				studentService.UpdateStudentPwd(sid, snewpwd);
				request.setAttribute("result_suppwd", "修改成功！");
				request.getRequestDispatcher("shome.jsp").forward(request, response);
			} catch (StudentException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("thome.jsp").forward(request, response);
			}
		}
		
		//老师修改学生密码
		String sidudpwd = request.getParameter("sidudpwd");
		String tsnewpassword = request.getParameter("tsnewpassword");
		if(sidudpwd != null && tsnewpassword != null){
			try {
				StudentService studentService = new StudentService();
				//查有此人
				if(studentService.FindStudentById(sidudpwd)){
					studentService.UpdateStudentPwd(sidudpwd, tsnewpassword);
					request.setAttribute("result_tsuppwd", "修改成功！");
					request.getRequestDispatcher("thome.jsp").forward(request, response);
				}
			} catch (StudentException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error_nostudent", e.getMessage());
				request.getRequestDispatcher("thome.jsp").forward(request, response);
			}
		}
		
		//管理员修改密码 
		String anewpassword = request.getParameter("anewpassword");
		if(anewpassword != null){
			String aid = (String) request.getSession().getAttribute("aid");
			try {
				AdminService adminService = new AdminService();
				adminService.UpdateAdminPwd(aid, anewpassword);
				request.setAttribute("result_auppwd", "修改成功！");
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			} catch (AdminException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			}
		}
		
	}
}
