package test.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import test.service.AdminException;
import test.service.AdminService;
import test.service.TeacherException;
import test.service.TeacherService;

public class UpdateClassServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		//修改教授班级
		String tid = request.getParameter("tid");
		String tclasses = request.getParameter("tclasses");
		String tsubjects = request.getParameter("tsubjects");
		TeacherService teacherService = new TeacherService();
		try {
			if (teacherService.UpdateTeacherClasses(tid, tclasses, tsubjects) == true) {
				response.getWriter().write(JSONObject.toJSONString("success"));
			}else {
				response.getWriter().write(JSONObject.toJSONString("error"));
			}
		} catch (TeacherException e) {
			// TODO Auto-generated catch block
		}
	}

	//修改前先查看班级是否都存在，不存在的返回提醒
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String tclasses = request.getParameter("tclasses");
		
		String [] tclassesstr = tclasses.split(",|，| ");
		AdminService adminservice = new AdminService();
		try {
			boolean flag = true;
			String [] noclass = new String[tclassesstr.length];
			int j = 0;
			for (int i = 0; i < tclassesstr.length; i++) {
				if(adminservice.FindClassById(tclassesstr[i])){
				}else {
					flag = false;
					noclass[j] = tclassesstr[i];
					j++;
				}
			}
			String noclassstrString = "";
			for (int k = 0; k < noclass.length; k++) {
				if(k < noclass.length-1){
					noclassstrString = noclassstrString + noclass[k] + "，";
				}else {
					noclassstrString = noclassstrString + noclass[k];
				}
			}
			if(flag == true){
				response.getWriter().write(JSONObject.toJSONString("success"));
			}else {
				response.getWriter().write(JSONObject.toJSONString(noclassstrString));
			}
		} catch (AdminException e) {
			// TODO Auto-generated catch block
		}
	}
}
