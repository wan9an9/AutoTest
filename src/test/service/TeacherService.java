package test.service;

import java.util.List;

import test.dao.*;

public class TeacherService {
	private  TeacherDAO teacherDAO = DAOFactory.getTeacherDAOInstance();
	
	//登录验证
	public  List<Object> Login(TeacherBean teacher) throws TeacherException{
		List<Object> teacherinfo = null;
		if(teacherDAO.FindTeacher(teacher)){
			//密码正确，实例化获取老师的全部数据表项属性
			teacherinfo = teacherDAO.SetTeacher(teacher.getTid());
		}else{
			throw new TeacherException("学工号或密码错误！");
			}
		return teacherinfo;
		}
	
	//老师首页表格信息
	public List<Object> TeacherInfo(String id, int page,int limit) throws TeacherException{
		List<Object> teacher = teacherDAO.getTeacherInfo(id, page, limit);
		return teacher;
	}
	
	//修改老师密码
	public  void UpdateTeacherPwd(String tid, String newpwd) throws TeacherException{
		if(teacherDAO.UpdateTeacherPwd(tid, newpwd)){
		}else{
			throw new TeacherException("修改失败！");
			}
		}
	
	//修改老师班级
	public  boolean UpdateTeacherClasses(String tid, String tclasses, String tsubjects) throws TeacherException{
		boolean flag = true;
		if(teacherDAO.UpdateTeacherClasses(tid, tclasses, tsubjects)){
		}else{
			flag = false;
			}
		return flag;
		}

	//检查是否存在该老师
	public  boolean FindTeacherById(String id, String subject) throws TeacherException{
		boolean flag = false;
		if(teacherDAO.FindTeacherById(id, subject)){
			flag = true;
		}else{
			throw new TeacherException("无此教师！");
			}
		return flag;
		}
	
	//老师注册
	public  void RegisterTeacher(TeacherBean teacherregister) throws TeacherException{
		if(teacherDAO.RegisterTeacher(teacherregister)){
		}else{
			throw new TeacherException("注册失败！");
			}
		}
	
	//查找教师个数
	public int FindTeacherCount() throws TeacherException {
		int num = 0;
		int count = teacherDAO.FindAllTeacher();
		if(count != 0){
			num = count;
		}else{
			throw new TeacherException("未注册任何教师！");
			}
		return num;
		}
	
	//
	public int FindTeacherCountById(String id) throws TeacherException {
		return teacherDAO.FindTeacherCountById(id);
		}
	
	//教师列表分页显示
	public List<Object> getTeachersToPages(int page, int limit) throws TeacherException{
		List<Object> teachers = teacherDAO.FindTeachersToPage(page, limit);
		return teachers;
	}
	
	//查询老师所教的班级
	public String FindTeacherClass(String tid, String tsubject) throws TeacherException{
		return teacherDAO.FindTeacherClass(tid, tsubject);
	}

	//查询老师所教的科目
	public String[] FindTeacherSubjects(String tid) throws TeacherException{
		return teacherDAO.FindTeacherSubjects(tid);
	}

	//某老师发布的所有考试数目
	public int FindTestCount(String tid) throws TeacherException{
		return teacherDAO.FindTestCount(tid);
	}

	//分页显示某老师发布的所有考试数目
	public List<Object> getTestToPages(String tid, int page, int limit) throws TeacherException{
		return teacherDAO.getTestToPages(tid, page, limit);
	}

	//学生根据班级和科目寻找老师id
	public String FindTidByClassAndSubject(String classname, String subject) throws TeacherException{
		return teacherDAO.getTestToPages(classname, subject);
	}
}
