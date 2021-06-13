package test.service;

import java.util.List;

import test.dao.DAOFactory;
import test.dao.StudentDAO;
import test.dao.StudentBean;

public class StudentService {
	private  StudentDAO studentDAO = DAOFactory.getStudentDAOInstance();
	
	public  StudentBean Login(StudentBean student) throws StudentException{
		StudentBean studentinfo = null;
		if(studentDAO.FindStudent(student)){
			//密码正确，实例化获取学生的全部数据表项属性
			studentinfo = studentDAO.SetStudent(student.getSid());
		}else{
			throw new StudentException("学工号或密码错误！");
			}
		return studentinfo;
		}
	
	//老师修改学生密码前检查是否存在该学生
	public  boolean FindStudentById(String id) throws StudentException{
		boolean flag = false;
		if(studentDAO.FindStudentBySid(id)){
			flag = true;
		}else{
			throw new StudentException("无此学生！");
			}
		return flag;
		}
	
	//修改学生密码
	public  void UpdateStudentPwd(String sid, String newpwd) throws StudentException{
		if(studentDAO.UpdateStudentPwd(sid, newpwd)){
		}else{
			throw new StudentException("修改失败！");
			}
		}
	
	//学生注册
	public  void RegisterStudent(StudentBean student) throws StudentException{
		if(studentDAO.RegisterStudent(student)){
		}else{
			throw new StudentException("注册失败！");
			}
		}
	
	//查找指定班级的学生个数
	public int FindCountByClass(String sclass) throws StudentException {
		int num = 0;
		int count = studentDAO.FindAllByClass(sclass);
		if(count != 0){
			num = count;
		}else{
			throw new StudentException("该班级未注册任何学生！");
			}
		return num;
		}
	
	//查找指定班级的学生信息并分页显示
	public List<Object> getStudentsByClassToPages(String chooseclass, int page,int limit) throws StudentException{
		List<Object> students = studentDAO.FindStudentsByClassToPage(chooseclass, page, limit);
		return students;
	}
	
	//获取学生的科目信息
	public String FindSubjectById(String sid) throws StudentException{
		return studentDAO.FindSubjectById(sid);
	}
	
	//更新学生的科目信息
	public void RegisterStudentSubjects(String subjects, String sid) throws StudentException{
		if(studentDAO.RegisterStudentSubjects(subjects, sid)){
		}else{
			throw new StudentException("注册失败！");
			}
		}

	//查询学生姓名
	public String FindSnameById(String sid) throws StudentException{
		return studentDAO.FindSnameById(sid);
	}

	//查询学生性别
	public String FindSsexById(String sid) throws StudentException{
		return studentDAO.FindSsexById(sid);
	}
}