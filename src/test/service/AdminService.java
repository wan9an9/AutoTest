package test.service;

import java.util.List;

import test.dao.AdminBean;
import test.dao.AdminDAO;
import test.dao.ClassBankBean;
import test.dao.DAOFactory;

public class AdminService {
	private  AdminDAO adminDAO = DAOFactory.getAdminDAOInstance();
	
	//登录
	public  AdminBean Login(AdminBean admin) throws AdminException{
		AdminBean Admin = null;
		if(adminDAO.FindAdmin(admin)){
			Admin = admin;
		}else{
			throw new AdminException("学工号或密码错误！");
			}
		return Admin;
		}
	
	//修改密码
	public  void UpdateAdminPwd(String aid, String newpwd) throws AdminException{
		if(adminDAO.UpdatePwd(aid, newpwd)){
		}else{
			throw new AdminException("修改失败！");
			}
		}

	//查找班级库班级个数
	public int FindClassCount() throws AdminException {
		int num = 0;
		int count = adminDAO.FindAllClass();
		if(count != 0){
			num = count;
		}else{
			throw new AdminException("未注册任何教师！");
			}
		return num;
		}
	
	//班级库列表分页显示
	public List<Object> getClassToPages(int page, int limit) throws AdminException{
		List<Object> classes = adminDAO.FindClassToPage(page, limit);
		return classes;
	}
	
	//查看某班级库是否存在
	public boolean FindClassById(String classname) throws AdminException{
		boolean flag = false;
		if(adminDAO.FindClassById(classname)){
			flag = true;
		}
		return flag;
	}
	
	//移除某班级库
	public boolean DeleteClass(String delclassname) throws AdminException{
		boolean flag = false;
		String qbankdbname = adminDAO.SelectQBankDbName(delclassname);
		String abankdbname = adminDAO.SelectABankDbName(delclassname);
		if(adminDAO.DeleteQBankTable(qbankdbname) && adminDAO.DeleteABankTable(abankdbname) && adminDAO.DeleteClass(delclassname)){
			flag = true;
		}else{
			throw new AdminException("移除失败！");
			}
		return flag;
		}
	
	//添加某班级库
	public void AddClass(ClassBankBean bank) throws AdminException{
		if(adminDAO.CreateQTable(bank.getQbankdbname()) && adminDAO.CreateATable(bank.getAbankdbname()) && adminDAO.AddClass(bank)){
		}else{
			throw new AdminException("添加失败！");
			}
	}
	
	//题目库表名称是否重复
	public boolean SameQBankDbName(String qbankdbname) throws AdminException{
		boolean flag = true;
		if(adminDAO.SameQBankDbName(qbankdbname) == false){
			flag = false;
		}
		return flag;
	}
	
	//作答库表名称是否重复
	public boolean SameABankDbName(String abankdbname) throws AdminException{
		boolean flag = true;
		if(adminDAO.SameABankDbName(abankdbname) == false){
			flag = false;
		}
		return flag;
	}
	
	//查询某班级的题表名
	public String SelectQBankDbName(String classname) throws AdminException{
		return adminDAO.SelectQBankDbName(classname);
	}
	
	//查询某班级的作答表名
	public String SelectABankDbName(String classname) throws AdminException{
		return adminDAO.SelectABankDbName(classname);
	}
	
	//查状态表找考试个数
	public int FindTestCount() throws AdminException{
		int num = 0;
		int count = adminDAO.FindAllTest();
		if(count != 0){
			num = count;
		}else{
			throw new AdminException("未发布任何考试！");
			}
		return num;
		}
	
	//考试列表分页
	public List<Object> getTestToPages(int page, int limit) throws AdminException{
		List<Object> tests = adminDAO.FindTestToPage(page, limit);
		return tests;
	}
	
	//删除教师
	public boolean DeleteTeacher(String tid, String tsubjects) throws AdminException{
		boolean flag = false;
		if(adminDAO.DeleteTeacher(tid, tsubjects) == true){
			flag = true;
		}
		return flag;
	}
}