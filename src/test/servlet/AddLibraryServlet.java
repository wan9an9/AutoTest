package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.ClassBankBean;
import test.service.AdminException;
import test.service.AdminService;

public class AddLibraryServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String classname = new String(request.getParameter("classname").getBytes("ISO-8859-1"),"UTF-8");
		String qbankdbname = new String(request.getParameter("qbankdbname").getBytes("ISO-8859-1"),"UTF-8");
		String abankdbname = new String(request.getParameter("abankdbname").getBytes("ISO-8859-1"),"UTF-8");
		ClassBankBean bank = new ClassBankBean();
		bank.setClassname(classname);
		bank.setQbankdbname(qbankdbname);
		bank.setAbankdbname(abankdbname);
		AdminService admin = new AdminService();
		try {
			if(!admin.FindClassById(classname)){
				//班级未被添加过
				if(admin.SameQBankDbName(bank.getQbankdbname())){
					//题目库表名称重复
					request.setAttribute("SameQBankDbName", "题库表名重复！");
					request.getRequestDispatcher("admin.jsp").forward(request, response);
				}else if(admin.SameABankDbName(bank.getAbankdbname())){
					//作答库表名称重复
					request.setAttribute("SameABankDbName", "作答表名重复！");
					request.getRequestDispatcher("admin.jsp").forward(request, response);
				}else{
					admin.AddClass(bank);
					request.setAttribute("result_addclass", "添加成功！");
					request.getRequestDispatcher("admin.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("result_havethisclass", "该班级库已存在！");
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			}
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
	}
}