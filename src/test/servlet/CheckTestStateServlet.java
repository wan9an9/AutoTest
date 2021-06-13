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

import test.dao.VOBean;
import test.service.AdminException;
import test.service.AdminService;
import test.service.ExamException;
import test.service.ExamService;
import test.service.TeacherException;
import test.service.TeacherService;

public class CheckTestStateServlet extends HttpServlet {
	//学生答题
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String sclass = request.getParameter("sclass");
		String subject = request.getParameter("subject");
		ExamService examservice = new ExamService();
		AdminService adminservice = new AdminService();
		try {
			//先查状态表看是否已发布题目
			if(examservice.FindTest(sclass, subject) == true){
				String begintime = examservice.FindBeginTime(sclass, subject);
				String endtime = examservice.FindEndTime(sclass, subject);
				//获取当前时间
				Date nowdate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowtime = dateFormat.format(nowdate);
				//生成时间对象以作比较
				Date bt = dateFormat.parse(begintime);
				Date et = dateFormat.parse(endtime);
				Date now = dateFormat.parse(nowtime);
				if(now.after(bt) && now.before(et)){
					String [] res = new String[3];
					res[0] = begintime;
					res[1] = endtime;
					String QBankDbName = adminservice.SelectQBankDbName(sclass);
					//总分
					res[2] = examservice.FindTotalPoints(QBankDbName, subject);
					response.getWriter().write(JSONObject.toJSONString(res));
				}else {
					response.getWriter().write(JSONObject.toJSONString("非考试时间！"));
				}
			}else {
				response.getWriter().write(JSONObject.toJSONString("题目暂未发布！"));
			}
		} catch (ExamException | ParseException | AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//评阅
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String sclass = request.getParameter("sclass");
		String subject = request.getParameter("subject");
		
		ExamService examservice = new ExamService();
		try {
			//先查状态表看是否已发布题目
			if(examservice.FindTest(sclass, subject) == true){
				String endtime = examservice.FindEndTime(sclass, subject);
				//获取当前时间
				Date nowdate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowtime = dateFormat.format(nowdate);
				//生成时间对象以作比较
				Date et = dateFormat.parse(endtime);
				Date now = dateFormat.parse(nowtime);
				if(now.before(et)){
					response.getWriter().write(JSONObject.toJSONString("考试还未结束！"));
				}else {
					response.getWriter().write(JSONObject.toJSONString("canmark"));
				}
			}else {
				response.getWriter().write(JSONObject.toJSONString("考试还未发布！"));
			}
		} catch (ExamException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
