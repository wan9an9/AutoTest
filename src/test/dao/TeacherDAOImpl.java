package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import test.dao.TeacherDAO;
import test.servlet.TShowInfoServlet;
import test.dao.JdbcUtils;
import test.dao.TeacherBean;

public class TeacherDAOImpl implements TeacherDAO {

	@Override
	public boolean FindTeacher(TeacherBean teacher) {
		// 1.定义Connection和PreparedStatement和ResultSet
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean flag = false;
		// 2.定义查询用户的SQL语句
		String sql = "select * from db_teacherinfo where tid=? "
				+ " and tpwd = ?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, teacher.getTid());
			pstmt.setString(2, teacher.getTpwd());
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
	
	@Override  //老师首页表格显示
	public List<Object> getTeacherInfo(String id, int page, int limit){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
				String sql = "select * from db_teacherinfo where tid=? limit ?,?";
				// 3.定义返回值
				List<Object> teachers = new ArrayList<Object>();
				try {
					// 4.1 实例化Connection和PreparedStatement
					conn = JdbcUtils.getConnection();
					pstmt = conn.prepareStatement(sql);
					// 4.2 给SQL语句传递参数
					pstmt.setString(1, id);
					pstmt.setInt(2, (page-1)*limit);
					pstmt.setInt(3, limit);
					// 4.3 利用prepareStatement，执行SQL语句
					rs = pstmt.executeQuery();
					while (rs.next()) {
						TeacherBean teacher = new TeacherBean();
						teacher.setTid(rs.getString(1));
						teacher.setTname(rs.getString(3));
						teacher.setTsex(rs.getString(4));
						teacher.setTclasses(rs.getString(5));
						teacher.setTsubjects(rs.getString(6));
						teachers.add(teacher);
					}
					return teachers;
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
	public List<Object> SetTeacher(String tid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select * from db_teacherinfo where tid=?";
		// 3.定义返回值
		List<Object> teacherinfo = new ArrayList<Object>();
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, tid);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TeacherBean teacher = new TeacherBean();
				teacher.setTid(rs.getString(1));
				teacher.setTname(rs.getString(3));
				teacher.setTsex(rs.getString(4));
				teacher.setTclasses(rs.getString(5));
				teacher.setTsubjects(rs.getString(6));
				teacherinfo.add(teacher);
			}
			return teacherinfo;
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
	
	@Override  //老师修改自己密码
	public boolean UpdateTeacherPwd(String tid, String newpwd){
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "update db_teacherinfo set tpwd=? where tid=?";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, newpwd);
			pstmt.setString(2, tid);
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

	@Override //修改老师班级
	public boolean UpdateTeacherClasses(String tid, String tclasses, String tsubjects) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "update db_teacherinfo set tclasses=? where tid=?" + " and tsubjects =?";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, tclasses);
			pstmt.setString(2, tid);
			pstmt.setString(3, tsubjects);
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

	@Override //老师注册前，检查是否存在该教师
	public boolean FindTeacherById(String id, String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean flag = false;
		// 2.定义查询用户的SQL语句
		String sql = "select * from db_teacherinfo where tid=?" + " and tsubjects =?";
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, id);
			pstmt.setString(2, subject);
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

	@Override
	public boolean RegisterTeacher(TeacherBean teacherregister) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "insert into db_teacherinfo (tid,tpwd,tname,tsex,tclasses,tsubjects)" + " values(?,?,?,?,?,?)";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, teacherregister.getTid());
			pstmt.setString(2, teacherregister.getTpwd());
			pstmt.setString(3, teacherregister.getTname());
			pstmt.setString(4, teacherregister.getTsex());
			pstmt.setString(5, teacherregister.getTclasses());
			pstmt.setString(6, teacherregister.getTsubjects());
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

	@Override //教师总个数
	public int FindAllTeacher() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select * from db_teacherinfo";
		// 3.定义返回值
		int teachercount = 0;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				teachercount = teachercount + 1;
			}
			return teachercount;
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

	@Override //分页显示教师列表
	public List<Object> FindTeachersToPage(int page, int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select * from db_teacherinfo limit ?,?";
		// 3.定义返回值
		List<Object> teachers = new ArrayList<Object>();
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setInt(1, (page-1)*limit);
			pstmt.setInt(2, limit);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TeacherBean teacher = new TeacherBean();
				teacher.setTid(rs.getString(1));
				teacher.setTname(rs.getString(3));
				teacher.setTsex(rs.getString(4));
				teacher.setTclasses(rs.getString(5));
				teacher.setTsubjects(rs.getString(6));
				teachers.add(teacher);
			}
			return teachers;
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
	public int FindTeacherCountById(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		// 2.定义查询用户的SQL语句
		String sql = "select count(*) from db_teacherinfo where tid=?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, id);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
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

	@Override //查询老师所教的班级
	public String FindTeacherClass(String tid, String tsubject) {
		// 1.定义Connection和PreparedStatement和ResultSet
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String classstr = "";
		// 2.定义查询用户的SQL语句
		String sql = "select tclasses from db_teacherinfo where tid=? "
				+ " and tsubjects = ?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, tid);
			pstmt.setString(2, tsubject);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				classstr = rs.getString(1);
			}
			return classstr;
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

	@Override //查询老师所教的科目
	public String[] FindTeacherSubjects(String tid) {
		// 1.定义Connection和PreparedStatement和ResultSet
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String [] subjects = new String[FindTeacherCountById(tid)];
		// 2.定义查询用户的SQL语句
		String sql = "select tsubjects from db_teacherinfo where tid=?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, tid);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				subjects[i] = rs.getString(1);
				i++;
			}
			return subjects;
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

	@Override //某老师发布的所有考试数目
	public int FindTestCount(String tid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		// 2.定义查询用户的SQL语句
		String sql = "select count(*) from db_teacherinfo,db_teststate WHERE db_teacherinfo.tsubjects = db_teststate.subject AND tid=?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, tid);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
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

	@Override //分页显示某老师发布的所有考试数目
	public List<Object> getTestToPages(String tid, int page, int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "SELECT db_teststate.classname,db_teststate.subject,db_teststate.begintime,db_teststate.endtime,db_teststate.ccheckover,db_teststate.pcheckover FROM db_teacherinfo,db_teststate WHERE db_teacherinfo.tsubjects = db_teststate.subject AND tid=? limit ?,?";
		// 3.定义返回值
		List<Object> tests = new ArrayList<Object>();
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, tid);
			pstmt.setInt(2, (page-1)*limit);
			pstmt.setInt(3, limit);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while (rs.next()) {
				TestStateBean test = new TestStateBean();
				test.setClassname(rs.getString(1));
				test.setSubject(rs.getString(2));
				test.setBegintime(dateFormat.format(dateFormat.parse(rs.getString(3))));
				test.setEndtime(dateFormat.format(dateFormat.parse(rs.getString(4))));
				test.setCcheckover(rs.getString(5));
				test.setPcheckover(rs.getString(6));
				tests.add(test);
			}
			return tests;
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

	@Override  //学生根据班级和科目寻找老师id
	public String getTestToPages(String classname, String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String tid = "";
		// 2.定义查询用户的SQL语句
		String sql = "select tid,tclasses from db_teacherinfo where tsubjects=?";
		// 3.定义返回值
		try {
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String [] classslist = rs.getString(2).split(",|，| ");
				for (int i = 0; i < classslist.length; i++) {
					if(classname.equals(classslist[i])){
						break;
					}
				}
				tid = rs.getString(1);
				break;
			}
			return tid;
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