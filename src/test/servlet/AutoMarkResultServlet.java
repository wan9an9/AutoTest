package test.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import test.dao.StudentTestBean;
import test.dao.VOBean;
import test.service.AdminException;
import test.service.AdminService;
import test.service.ExamException;
import test.service.ExamService;
import test.service.StudentException;
import test.service.StudentService;
import test.service.TeacherException;
import test.service.TeacherService;

public class AutoMarkResultServlet extends HttpServlet {
	//评阅结果表(自动评阅)
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
			List<Object> res = new ArrayList<Object>();
			for (int i = 0; i < classslist.length; i++) {
				if(examservice.FindCcheckover(classslist[i], subject).equals("yes")){
					String QBankDbName = adminservice.SelectQBankDbName(classslist[i]);
					String ABankDbName = adminservice.SelectABankDbName(classslist[i]);
					List<Object> tmp = examservice.ShowAutoMarkResult(QBankDbName, ABankDbName, classslist[i], subject);
					res.addAll(tmp);
				}
			}
			VOBean vo = new VOBean();
			vo.setCode(0);
			vo.setMsg("");
			vo.setCount(res.size());
			vo.setData(res);
			response.getWriter().write(JSONObject.toJSONString(vo));
		} catch (ExamException | AdminException | TeacherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//成绩单表格(自动评阅)
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String tid = request.getParameter("tid");
		String subject = request.getParameter("subject");
		ExamService examservice = new ExamService();
		AdminService adminservice = new AdminService();
		TeacherService teacherservice = new TeacherService();
		StudentService studentservice = new StudentService();
		try {
			String classstr = teacherservice.FindTeacherClass(tid, subject);
			String [] classslist = classstr.split(",|，| ");
			List<Object> res = new ArrayList<Object>();
			int studentcount = 0;
			for (int i = 0; i < classslist.length; i++) {
				if(examservice.FindCcheckover(classslist[i], subject).equals("yes")){
					studentcount += studentservice.FindCountByClass(classslist[i]);
					String QBankDbName = adminservice.SelectQBankDbName(classslist[i]);
					String ABankDbName = adminservice.SelectABankDbName(classslist[i]);
					List<Object> ctestcard = examservice.ShowCTestCard(QBankDbName, ABankDbName, classslist[i], subject);
					res.addAll(ctestcard);
				}
			}
			res.sort(new Comparator<Object>(){
				@Override //按总分排序
				public int compare(Object o1, Object o2) {
					float s1 = Float.parseFloat(((StudentTestBean) o1).getCtotalpoint());
					float s2 = Float.parseFloat(((StudentTestBean) o2).getCtotalpoint());
					if(s1 -s2 < 0)
						return 1;
					else if(s1 -s2 == 0)
						return 0;
					else
						return -1;
				}
				
			});
			//加入总排名属性
			for (int k = 0; k < res.size(); k++) {
				StudentTestBean r = (StudentTestBean)res.get(k);
				r.setSname(studentservice.FindSnameById(r.getSid()));
				r.setSsex(studentservice.FindSsexById(r.getSid()));
				r.setCranking(Integer.toString(k+1));
			}
			VOBean vo = new VOBean();
			vo.setCode(0);
			vo.setMsg("");
			vo.setCount(studentcount);
			vo.setData(res);
			response.getWriter().write(JSONObject.toJSONString(vo));
		} catch (ExamException | AdminException | TeacherException | StudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
