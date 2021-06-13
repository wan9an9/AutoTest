package test.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import test.dao.VOBean;
import test.service.AdminException;
import test.service.AdminService;

public class CShowInfoServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		String pageString = request.getParameter("page");
		String limitString = request.getParameter("limit");
		AdminService adminService = new AdminService();
		//首次加载表，参数全为空
		if(pageString == null && limitString == null){
			pageString = "1";
			limitString = "12";
		}
		//分页
		try {
			int classcount = adminService.FindClassCount();
			List<Object> classes = adminService.getClassToPages(Integer.parseInt(pageString), Integer.parseInt(limitString));
			VOBean vo = new VOBean();
			vo.setCode(0);
			vo.setMsg("");
			vo.setCount(classcount);
			vo.setData(classes);
			response.getWriter().write(JSONObject.toJSONString(vo));
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			//无题库返回提示信息
			request.setAttribute("error_noclass", e.getMessage());
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		String pageString = request.getParameter("page");
		String limitString = request.getParameter("limit");
		AdminService adminService = new AdminService();
		//首次加载表，参数全为空
		if(pageString == null && limitString == null){
			pageString = "1";
			limitString = "12";
		}
		//分页
		try {
			int testcount = adminService.FindTestCount();
			List<Object> tests = adminService.getTestToPages(Integer.parseInt(pageString), Integer.parseInt(limitString));
			VOBean vo = new VOBean();
			vo.setCode(0);
			vo.setMsg("");
			vo.setCount(testcount);
			vo.setData(tests);
			response.getWriter().write(JSONObject.toJSONString(vo));
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			//无教师返回提示信息
			request.setAttribute("error_notest", e.getMessage());
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
	}
}
