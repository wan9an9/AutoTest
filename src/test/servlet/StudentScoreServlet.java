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

public class StudentScoreServlet extends HttpServlet {
	//学生查询试卷评阅结果
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String sid = request.getParameter("sid");
		String classname = request.getParameter("classname");
		String subject = request.getParameter("subject");
		String pageString = request.getParameter("page");
		String limitString = request.getParameter("limit");
		ExamService examservice = new ExamService();
		AdminService adminservice = new AdminService();
		try {
			String QBankDbName = adminservice.SelectQBankDbName(classname);
			String ABankDbName = adminservice.SelectABankDbName(classname);
			List<Object> tmp = examservice.ShowStudentTestResult(QBankDbName, ABankDbName, sid, classname, subject, Integer.parseInt(pageString), Integer.parseInt(limitString));
			VOBean vo = new VOBean();
			vo.setCode(0);
			vo.setMsg("");
			vo.setCount(tmp.size());
			vo.setData(tmp);
			response.getWriter().write(JSONObject.toJSONString(vo));
		} catch (ExamException | AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//学生查询成绩单
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String sid = request.getParameter("sid");
		String classname = request.getParameter("classname");
		String subject = request.getParameter("subject");
		ExamService examservice = new ExamService();
		AdminService adminservice = new AdminService();
		TeacherService teacherservice = new TeacherService();
		StudentService studentservice = new StudentService();
		try {
			StudentTestBean studentTest = new StudentTestBean();
			//找出老师id
			String tid = teacherservice.FindTidByClassAndSubject(classname, subject);
			String classstr = teacherservice.FindTeacherClass(tid, subject);
			String [] classslist = classstr.split(",|，| ");
			List<Object> res = new ArrayList<Object>();
			for (int i = 0; i < classslist.length; i++) {
				if(examservice.FindCcheckover(classslist[i], subject).equals("yes")){
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
			for (int h = 0; h < res.size(); h++) {
				StudentTestBean s = (StudentTestBean)res.get(h);
				if(sid.equals(s.getSid())){
					studentTest.setCranking(s.getCranking());
					studentTest.setPranking(s.getPranking());
					studentTest.setSclass(s.getSclass());
					studentTest.setSid(s.getSid());
					studentTest.setSname(s.getSname());
					studentTest.setSsex(s.getSsex());
					studentTest.setCtotalpoint(s.getCtotalpoint());
					break;
				}
			}
			List<Object> tmp = new ArrayList<Object>();
			tmp.add(studentTest);
			VOBean vo = new VOBean();
			vo.setCode(0);
			vo.setMsg("");
			vo.setCount(1);
			vo.setData(tmp);
			response.getWriter().write(JSONObject.toJSONString(vo));
		} catch (ExamException | AdminException | TeacherException | StudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
