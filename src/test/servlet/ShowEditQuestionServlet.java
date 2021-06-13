package test.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import test.dao.VOBean;
import test.service.AdminException;
import test.service.AdminService;
import test.service.ExamException;
import test.service.ExamService;
import test.service.TeacherException;
import test.service.TeacherService;

public class ShowEditQuestionServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		String pageString = request.getParameter("page");
		String limitString = request.getParameter("limit");
		String tid = request.getParameter("tid");
		String tsubject = request.getParameter("tsubject");
		String qcount = request.getParameter("qcount");
		
		try {
			TeacherService teacherservice = new TeacherService();
			String classstr = teacherservice.FindTeacherClass(tid, tsubject);
			//班级列表
			String [] classses = classstr.split(",|，| ");
			AdminService adminservice = new AdminService();
			//第一个班级对应的题表名
			String QBankDbName = adminservice.SelectQBankDbName(classses[0]);
			ExamService examservice = new ExamService();
			//化解异常操作
			//1.该科目有记录
			int con = examservice.FindIsFullSubjectRecord(QBankDbName, tsubject);
			//1.1 该科目只有完整记录
			if(con == 1){
				//查询某科目的完整题目数目
				int fullqcount = examservice.getFullQuestionCount(QBankDbName, tsubject);
				//输入小于等于实际，显示实际
				if(Integer.parseInt(qcount) <= fullqcount){
					//分页显示
					List<Object> questions = examservice.getQuestionsToPages(QBankDbName, tsubject, Integer.parseInt(pageString), Integer.parseInt(limitString));
					VOBean vo = new VOBean();
					vo.setCode(0);
					vo.setMsg("");
					vo.setCount(fullqcount);
					vo.setData(questions);
					response.getWriter().write(JSONObject.toJSONString(vo));
				}
				//输入大于实际，先给多出部分附加头，再显示全部
				else {
					for(int i=0; i<classses.length; i++){
						//在所教科目的每个班级的题库  附加创建  题表头
						examservice.InsertRestQHead(adminservice.SelectQBankDbName(classses[i]), tsubject, fullqcount+1, Integer.parseInt(qcount));
					}
					//分页显示
					List<Object> questions = examservice.getQuestionsToPages(QBankDbName, tsubject, Integer.parseInt(pageString), Integer.parseInt(limitString));
					VOBean vo = new VOBean();
					vo.setCode(0);
					vo.setMsg("");
					vo.setCount(Integer.parseInt(qcount));
					vo.setData(questions);
					response.getWriter().write(JSONObject.toJSONString(vo));
				}
			}
			//1.2 该科目只有不完整记录
			else if(con == 2){
				int unfullqcount = examservice.getUnFullQuestionCount(QBankDbName, tsubject);
				//输入小于等于实际，显示实际
				if(Integer.parseInt(qcount) <= unfullqcount){
					//分页显示
					List<Object> questions = examservice.getQuestionsToPages(QBankDbName, tsubject, Integer.parseInt(pageString), Integer.parseInt(limitString));
					VOBean vo = new VOBean();
					vo.setCode(0);
					vo.setMsg("");
					vo.setCount(unfullqcount);
					vo.setData(questions);
					response.getWriter().write(JSONObject.toJSONString(vo));
				}
				//输入大于实际，先给多出部分附加头，再显示全部
				else {
					for(int i=0; i<classses.length; i++){
						//在所教科目的每个班级的题库  附加创建  题表头
						examservice.InsertRestQHead(adminservice.SelectQBankDbName(classses[i]), tsubject, unfullqcount+1, Integer.parseInt(qcount));
					}
					//分页显示
					List<Object> questions = examservice.getQuestionsToPages(QBankDbName, tsubject, Integer.parseInt(pageString), Integer.parseInt(limitString));
					VOBean vo = new VOBean();
					vo.setCode(0);
					vo.setMsg("");
					vo.setCount(Integer.parseInt(qcount));
					vo.setData(questions);
					response.getWriter().write(JSONObject.toJSONString(vo));
				}
			}
			//1.3 该科目有完整记录和不完整记录
			else if(con == 3){
				int count = examservice.getQuestionCount(QBankDbName, tsubject);
				//输入小于等于实际，显示实际
				if(Integer.parseInt(qcount) <= count){
					//分页显示
					List<Object> questions = examservice.getQuestionsToPages(QBankDbName, tsubject, Integer.parseInt(pageString), Integer.parseInt(limitString));
					VOBean vo = new VOBean();
					vo.setCode(0);
					vo.setMsg("");
					vo.setCount(count);
					vo.setData(questions);
					response.getWriter().write(JSONObject.toJSONString(vo));
				}
				//输入大于实际，先给多出部分附加头，再显示全部
				else {
					for(int i=0; i<classses.length; i++){
						//在所教科目的每个班级的题库  附加创建  题表头
						examservice.InsertRestQHead(adminservice.SelectQBankDbName(classses[i]), tsubject, count+1, Integer.parseInt(qcount));
					}
					//分页显示
					List<Object> questions = examservice.getQuestionsToPages(QBankDbName, tsubject, Integer.parseInt(pageString), Integer.parseInt(limitString));
					VOBean vo = new VOBean();
					vo.setCode(0);
					vo.setMsg("");
					vo.setCount(Integer.parseInt(qcount));
					vo.setData(questions);
					response.getWriter().write(JSONObject.toJSONString(vo));
				}
			}
			//2.该科目无任何记录
			else if(examservice.FindSubjectRecord(QBankDbName, tsubject) == false){
				for(int i=0; i<classses.length; i++){
					//在所教科目的每个班级的题库根据题目数量创建题表头
					examservice.InsertQHead(adminservice.SelectQBankDbName(classses[i]), tsubject, Integer.parseInt(qcount));
				}
				//分页显示
				List<Object> questions = examservice.getQuestionsToPages(QBankDbName, tsubject, Integer.parseInt(pageString), Integer.parseInt(limitString));
				VOBean vo = new VOBean();
				vo.setCode(0);
				vo.setMsg("");
				vo.setCount(Integer.parseInt(qcount));
				vo.setData(questions);
				response.getWriter().write(JSONObject.toJSONString(vo));
				}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TeacherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String pageString = request.getParameter("page");
		String limitString = request.getParameter("limit");
		String tid = request.getParameter("tid");
		String tsubject = request.getParameter("tsubject");
		
		TeacherService teacherservice = new TeacherService();
		AdminService adminservice = new AdminService();
		ExamService examservice = new ExamService();
		try {
			String classstr = teacherservice.FindTeacherClass(tid, tsubject);
			//班级列表
			String [] classses = classstr.split(",|，| ");
			//第一个班级对应的题表名
			String QBankDbName = adminservice.SelectQBankDbName(classses[0]);
			int fullquestioncount = examservice.getFullQuestionCount(QBankDbName, tsubject);
			List<Object> questions = examservice.getFullQuestionsToPages(QBankDbName, tsubject, Integer.parseInt(pageString), Integer.parseInt(limitString));
			VOBean vo = new VOBean();
			vo.setCode(0);
			vo.setMsg("");
			vo.setCount(fullquestioncount);
			vo.setData(questions);
			response.getWriter().write(JSONObject.toJSONString(vo));
		} catch (ExamException | TeacherException | AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}