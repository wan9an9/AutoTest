package test.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import test.dao.*;
import test.service.*;

public class SShowInfoServlet extends HttpServlet {
	
	static String chooseclass = null;
	static int studentscount = 0;
	StudentService studentService = new StudentService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		chooseclass = new String(request.getParameter("tbsviewclasschoose").getBytes("ISO-8859-1"),"UTF-8");
		try {
			studentscount = studentService.FindCountByClass(chooseclass);
			//有学生，显示表
			request.setAttribute("classinfotbtitle", chooseclass);
			request.getRequestDispatcher("thome.jsp").forward(request, response);
		} catch (StudentException e) {
			// TODO: handle exception
			//无学生返回提示信息
			request.setAttribute("error_classnostudent", e.getMessage());
			request.getRequestDispatcher("thome.jsp").forward(request, response);
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
		//分页
		try {
			List<Object> students = studentService.getStudentsByClassToPages(chooseclass, Integer.parseInt(pageString), Integer.parseInt(limitString));
			VOBean vo = new VOBean();
			vo.setCode(0);
			vo.setMsg("");
			vo.setCount(studentscount);
			vo.setData(students);
			response.getWriter().write(JSONObject.toJSONString(vo));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}