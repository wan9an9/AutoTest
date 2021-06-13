package test.dao;

import java.util.List;

import test.dao.AdminBean;

public interface AdminDAO {
	//查询
	//1.查找某一具体账号和密码的用户是否存在
	public boolean FindAdmin(AdminBean admin);
	//2.班级库班级个数
	public int FindAllClass();
	//3.班级库班级分页显示
	public List<Object> FindClassToPage(int page, int limit);
	//4.某班级库是否存在
	public boolean FindClassById(String classname);
	//5.查询某题库表名称
	public String SelectQBankDbName(String delclassname);
	//6.查询某作答表名称
	public String SelectABankDbName(String delclassname);
	//7.题目库表名称是否重复
	public boolean SameQBankDbName(String qbankdbname);
	//8.作答库表名称是否重复
	public boolean SameABankDbName(String abankdbname);
	//9.查状态表找考试个数
	public int FindAllTest();
	//10.考试列表分页
	public List<Object> FindTestToPage(int page, int limit);
	
	//修改
	//1.修改密码
	public boolean UpdatePwd(String aid, String newpwd);
	
	//删除
	//1.移除某班级
	public boolean DeleteClass(String delclassname);
	//2.删除题目库表
	public boolean DeleteQBankTable(String qbankdbname);
	//3.删除作答库表
	public boolean DeleteABankTable(String abankdbname);
	//4.删除教师
	public boolean DeleteTeacher(String tid, String tsubjects);
	
	//创建
	//1.创建题库表
	public boolean CreateQTable(String qbankdbname);
	//1.创建作答库表
	public boolean CreateATable(String abankdbname);
	
	//插入
	//1.在班级库表插入一条记录
	public boolean AddClass(ClassBankBean bank);
	
	
}
