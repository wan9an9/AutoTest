package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.*;
import test.service.*;

public class SRegisterServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String sid = request.getParameter("regid");
		String sname = new String(request.getParameter("regname").getBytes("ISO-8859-1"),"UTF-8");
		String ssex = new String(request.getParameter("regsex").getBytes("ISO-8859-1"),"UTF-8");
		String sclass = new String(request.getParameter("regclass").getBytes("ISO-8859-1"),"UTF-8");
		String [] ssubjects = request.getParameterValues("checksubjects");
		String subjects = "";
		for(int i=0; i<ssubjects.length; i++){
			if(i < ssubjects.length-1){
				subjects = subjects + new String(ssubjects[i].getBytes("ISO-8859-1"),"UTF-8") + ",";
			}
			else {
				subjects = subjects + new String(ssubjects[i].getBytes("ISO-8859-1"),"UTF-8");
			}
		}
		StudentService studentService = new StudentService();
		try {
			studentService.FindStudentById(sid);
			//查有此人，注册时附加所修科目即可
			String substr = studentService.FindSubjectById(sid);
			subjects = substr + "," + subjects;
			//去重
	      	String [] sub = subjects.split(",|，| ");
	      	List sublist = new ArrayList();
	      	for(int j=0; j<sub.length; j++){
	      		if(!sublist.contains(sub[j])){
	      			sublist.add(sub[j]);
	      		}
	      	}
	      	Object[] subObjects = sublist.toArray();
	      	String subjectsstr = "";
			for(int i=0; i<subObjects.length; i++){
				if(i < subObjects.length-1){
					subjectsstr = subjectsstr + subObjects[i] + ",";
				}
				else {
					subjectsstr = subjectsstr + subObjects[i];
				}
			}
			try {
				studentService.RegisterStudentSubjects(subjectsstr, sid);
				request.setAttribute("result_studentregister", "注册成功！");
				request.getRequestDispatcher("thome.jsp").forward(request, response);
			} catch (StudentException e1) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e1.getMessage());
				request.getRequestDispatcher("thome.jsp").forward(request, response);
			}
		} 
		//查无此人，可注册
		catch (StudentException e) {
			// TODO Auto-generated catch block
			StudentBean studentregister = new StudentBean();
			studentregister.setSid(sid);
			studentregister.setSpwd(sid);
			studentregister.setSname(sname);
			studentregister.setSsex(ssex);
			studentregister.setSclass(sclass);
			studentregister.setSsubjects(subjects);
			try {
				studentService.RegisterStudent(studentregister);
				request.setAttribute("result_studentregister", "注册成功！");
				request.getRequestDispatcher("thome.jsp").forward(request, response);
			} catch (StudentException e1) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e1.getMessage());
				request.getRequestDispatcher("thome.jsp").forward(request, response);
			}
		}
	}
}