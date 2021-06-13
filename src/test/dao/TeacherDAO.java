package test.dao;

import java.util.List;

public interface TeacherDAO {
	//查找用户信息
	//1.查找某一具体账号和密码的用户是否存在
	public boolean FindTeacher(TeacherBean teacher);
	//2.实例化对象全部信息
	public List<Object> SetTeacher(String tid);
	//3.老师首页信息
	public List<Object> getTeacherInfo(String id, int page, int limit);
	//4.查看是否存在该老师
	public boolean FindTeacherById(String id, String subject);
	//5.教师总个数
	public int FindAllTeacher();
	//6.某教课教师记录数，为了首页分页
	public int FindTeacherCountById(String id);
	//7.分页显示教师列表
	public List<Object> FindTeachersToPage(int page, int limit);
	//8.查询老师所教的班级
	public String FindTeacherClass(String tid, String tsubject);
	//9.查询老师所教的科目
	public String[] FindTeacherSubjects(String tid);
	//10.某老师发布的所有考试数目
	public int FindTestCount(String tid);
	//11.分页显示某老师发布的所有考试数目
	public List<Object> getTestToPages(String tid, int page, int limit);
	//12.学生根据班级和科目寻找老师id
	public String getTestToPages(String classname, String subject);
	
	
	//修改
	//1.修改老师密码
	public boolean UpdateTeacherPwd(String tid, String newpwd);
	//2.修改老师班级
	public boolean UpdateTeacherClasses(String tid, String tclasses, String tsubjects);
	//3.教师注册
	public boolean RegisterTeacher(TeacherBean teacherregister);
	
	
	
	//删除

	

}