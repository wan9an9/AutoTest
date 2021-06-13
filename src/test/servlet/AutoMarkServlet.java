package test.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import test.service.AdminException;
import test.service.AdminService;
import test.service.ExamException;
import test.service.ExamService;
import test.service.TeacherException;
import test.service.TeacherService;

public class AutoMarkServlet extends HttpServlet {
	//评阅
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String tid = request.getParameter("tid");
		String subject = request.getParameter("subject");
		
		ExamService examservice = new ExamService();
		AdminService adminservice = new AdminService();
		TeacherService teacherservice = new TeacherService();
		try {
			String classstr = teacherservice.FindTeacherClass(tid, subject);
			String [] classslist = classstr.split(",|，| ");
			boolean markflag = true;
			for (int i = 0; i < classslist.length; i++) {
				if(examservice.FindCcheckover(classslist[i], subject).equals("no")){
					String QBankDbName = adminservice.SelectQBankDbName(classslist[i]);
					String ABankDbName = adminservice.SelectABankDbName(classslist[i]);
					//评阅
					if(examservice.AutoMark(QBankDbName, ABankDbName, classslist[i], subject) == false){
						markflag = false;
					}else {
						//设置评阅完成标志
						if(examservice.UpdateCcheckover(classslist[i], subject) == false){
							markflag = false;
						}
					}
				}
			}
			if(markflag == true){
				response.getWriter().write(JSONObject.toJSONString("评阅成功，请点击查看评阅结果或者成绩单！"));
			}else {
				response.getWriter().write(JSONObject.toJSONString("评阅失败，请重新评阅！"));
			}
		} catch (ExamException | AdminException | TeacherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//查询是否已经评阅了该科目全部班级
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String tid = request.getParameter("tid");
		String subject = request.getParameter("subject");
		
		ExamService examservice = new ExamService();
		TeacherService teacherservice = new TeacherService();
		try {
			String classstr = teacherservice.FindTeacherClass(tid, subject);
			String [] classslist = classstr.split(",|，| ");
			boolean flag = true;
			int conun1 = 0;
			int count2 = 0;
			for (int i = 0; i < classslist.length; i++) {
				if(examservice.FindTest(classslist[i], subject) == true){
					String endtime = examservice.FindEndTime(classslist[i], subject);
					Date nowdate = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String nowtime = dateFormat.format(nowdate);
					Date et = dateFormat.parse(endtime);
					Date now = dateFormat.parse(nowtime);
					//考完了
					if(now.after(et)){
						if(examservice.FindCcheckover(classslist[i], subject).equals("no")){
							flag = false;
							break;
						}
					}else {
						count2 ++;
					}
				}else {
					conun1 ++;
				}

			}
			if(conun1 == classslist.length || count2 == classslist.length){
				response.getWriter().write(JSONObject.toJSONString("考试都还未发布或考试都还未结束，请稍后再进行评阅！"));
			}else {
				if(flag == false){
					response.getWriter().write(JSONObject.toJSONString("还未评阅完成！"));
				}else {
					response.getWriter().write(JSONObject.toJSONString("所有班级都已评阅，请点击查看评阅结果或者成绩单！"));
				}
			}
		} catch (ExamException | TeacherException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
