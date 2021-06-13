package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.dao.StudentDAO;
import test.dao.JdbcUtils;
import test.dao.StudentBean;

public class StudentDAOImpl implements StudentDAO {
	
	@Override //登录密码是否正确
	public boolean FindStudent(StudentBean student) {
		// 1.定义Connection和PreparedStatement和ResultSet
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean flag = false;
		// 2.定义查询用户的SQL语句
		String sql = "select * from db_studentinfo where sid=? "
				+ " and spwd = ?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, student.getSid());
			pstmt.setString(2, student.getSpwd());
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			return flag;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 无论有没有异常，SQL有没有执行成功
			// 都要关闭Connetion和PreparedStatement
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	@Override  //老师修改学生密码前，检查是否存在该学生
	public boolean FindStudentBySid(String sid){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean flag = false;
		// 2.定义查询用户的SQL语句
		String sql = "select * from db_studentinfo where sid=?";
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, sid);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			return flag;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 无论有没有异常，SQL有没有执行成功
			// 都要关闭Connetion和PreparedStatement
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	@Override //显示指定班级的学生个数
	public int FindAllByClass(String sclass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select * from db_studentinfo where sclass=?";
		// 3.定义返回值
		int studentcount = 0;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, sclass);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				studentcount = studentcount + 1;
			}
			return studentcount;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 无论有没有异常，SQL有没有执行成功
			// 都要关闭Connetion和PreparedStatement
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	@Override  //查找指定班级的所有学生信息(分页显示)
	public List<Object> FindStudentsByClassToPage(String chooseclass, int page, int limit){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select * from db_studentinfo where sclass=? limit ?,?";
		// 3.定义返回值
		List<Object> students = new ArrayList<Object>();
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, chooseclass);
			pstmt.setInt(2, (page-1)*limit);
			pstmt.setInt(3, limit);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentBean student = new StudentBean();
				student.setSid(rs.getString(1));
				student.setSname(rs.getString(3));
				student.setSsex(rs.getString(4));
				student.setSclass(rs.getString(5));
				student.setSsubjects(rs.getString(6));
				students.add(student);
			}
			return students;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 无论有没有异常，SQL有没有执行成功
			// 都要关闭Connetion和PreparedStatement
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	@Override  //登录密码正确，实例化全部属性
	public StudentBean SetStudent(String sid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select * from db_studentinfo where sid=?";
		// 3.定义返回值
		StudentBean student = null;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, sid);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				student =new StudentBean();
				student.setSid(rs.getString(1));
				student.setSpwd(rs.getString(2));
				student.setSname(rs.getString(3));
				student.setSsex(rs.getString(4));
				student.setSclass(rs.getString(5));
				student.setSsubjects(rs.getString(6));
			}
			return student;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 无论有没有异常，SQL有没有执行成功
			// 都要关闭Connetion和PreparedStatement
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	@Override  //老师修改学生密码
	public boolean UpdateStudentPwd(String sid, String newpwd){
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "update db_studentinfo set spwd=? where sid=?";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, newpwd);
			pstmt.setString(2, sid);
			// 4.3 利用prepareStatement，执行SQL语句
			if (pstmt.executeUpdate() > 0) {
				flag = true;
			}
			return flag;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 无论有没有异常，SQL有没有执行成功
			// 都要关闭Connetion和PreparedStatement
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	@Override  //学生注册
	public boolean RegisterStudent(StudentBean student){
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "insert into db_studentinfo (sid,spwd,sname,ssex,sclass,ssubjects)" + " values(?,?,?,?,?,?)";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, student.getSid());
			pstmt.setString(2, student.getSpwd());
			pstmt.setString(3, student.getSname());
			pstmt.setString(4, student.getSsex());
			pstmt.setString(5, student.getSclass());
			pstmt.setString(6, student.getSsubjects());
			// 4.3 利用prepareStatement，执行SQL语句
			if (pstmt.executeUpdate() > 0) {
				flag = true;
			}
			return flag;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 无论有没有异常，SQL有没有执行成功
			// 都要关闭Connetion和PreparedStatement
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override //查询学生的科目信息
	public String FindSubjectById(String sid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String subject = "";
		// 2.定义查询用户的SQL语句
		String sql = "select ssubjects from db_studentinfo where sid=?";
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, sid);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				subject = rs.getString(1);
			}
			return subject;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 无论有没有异常，SQL有没有执行成功
			// 都要关闭Connetion和PreparedStatement
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public boolean RegisterStudentSubjects(String subjects, String sid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "update db_studentinfo set ssubjects=? where sid=?";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subjects);
			pstmt.setString(2, sid);
			// 4.3 利用prepareStatement，执行SQL语句
			if (pstmt.executeUpdate() > 0) {
				flag = true;
			}
			return flag;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 无论有没有异常，SQL有没有执行成功
			// 都要关闭Connetion和PreparedStatement
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override //查询学生姓名
	public String FindSnameById(String sid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = "";
		// 2.定义查询用户的SQL语句
		String sql = "select sname from db_studentinfo where sid=?";
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, sid);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);
			}
			return name;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 无论有没有异常，SQL有没有执行成功
			// 都要关闭Connetion和PreparedStatement
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override //查询学生性别
	public String FindSsexById(String sid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sex = "";
		// 2.定义查询用户的SQL语句
		String sql = "select ssex from db_studentinfo where sid=?";
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, sid);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sex = rs.getString(1);
			}
			return sex;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 无论有没有异常，SQL有没有执行成功
			// 都要关闭Connetion和PreparedStatement
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
}
