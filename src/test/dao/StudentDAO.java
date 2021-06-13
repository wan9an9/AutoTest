package test.dao;

import java.util.List;

import test.dao.StudentBean;

public interface StudentDAO {
	//查询
	//1.查找某一具体账号和密码的用户是否存在
	public boolean FindStudent(StudentBean student);
	//2.实例化对象全部信息
	public StudentBean SetStudent(String sid);
	//3.根据学号查找学生是否存在
	public boolean FindStudentBySid(String sid);
	//4.查找指定班级的学生个数
	public int FindAllByClass(String sclass);
	//5.查找指定班级的所有学生信息(分页显示)
	public List<Object> FindStudentsByClassToPage(String chooseclass, int page, int limit);
	//6.查询学生的科目信息
	public String FindSubjectById(String sid);
	//7.查询学生姓名
	public String FindSnameById(String sid);
	//8.查询学生性别
	public String FindSsexById(String sid);
	
	//修改
	//1.学生修改密码
	public boolean UpdateStudentPwd(String sid, String newpwd);
	//2.更新学生的科目信息
	public boolean RegisterStudentSubjects(String subjects, String sid);
	
	
	//插入
	//1.学生注册
	public boolean RegisterStudent(StudentBean student);
	
	
}