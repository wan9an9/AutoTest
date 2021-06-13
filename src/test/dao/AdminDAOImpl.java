package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import test.dao.JdbcUtils;
import test.dao.ClassBankBean;

public class AdminDAOImpl implements AdminDAO {
	
	@Override //登录
	public boolean FindAdmin(AdminBean admin) {
		// 1.定义Connection和PreparedStatement和ResultSet
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean flag = false;
		// 2.定义查询用户的SQL语句
		String sql = "select * from db_admininfo where aid=? "
				+ " and apwd = ?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, admin.getAid());
			pstmt.setString(2, admin.getApwd());
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

	@Override //修改密码
	public boolean UpdatePwd(String aid, String newpwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "update db_admininfo set apwd=? where aid=?";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, newpwd);
			pstmt.setString(2, aid);
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

	@Override //班级个数
	public int FindAllClass() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select * from db_classbank";
		// 3.定义返回值
		int classcount = 0;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				classcount = classcount + 1;
			}
			return classcount;
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

	@Override //班级分页
	public List<Object> FindClassToPage(int page, int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select * from db_classbank limit ?,?";
		// 3.定义返回值
		List<Object> classes = new ArrayList<Object>();
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
				ClassBankBean classbank = new ClassBankBean();
				classbank.setClassname(rs.getString(1));
				classbank.setQbankdbname(rs.getString(2));
				classbank.setAbankdbname(rs.getString(3));
				classes.add(classbank);
			}
			return classes;
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
	
	@Override //某班级库是否存在
	public boolean FindClassById(String classname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean flag = false;
		// 2.定义查询用户的SQL语句
		String sql = "select * from db_classbank where classname=?";
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, classname);
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
	
	@Override //查询某题库表名称
	public String SelectQBankDbName(String delclassname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String classdbname = null;
		// 2.定义查询用户的SQL语句
		String sql = "select qbankdbname from db_classbank where classname =?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, delclassname);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				classdbname = rs.getString(1);
			}
			return classdbname;
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
	
	@Override //查询某作答表名称
	public String SelectABankDbName(String classname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String classdbname = null;
		// 2.定义查询用户的SQL语句
		String sql = "select abankdbname from db_classbank where classname =?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, classname);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				classdbname = rs.getString(1);
			}
			return classdbname;
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
	
	@Override //删除题目库表
	public boolean DeleteQBankTable(String qbankdbname) {
		// 1.定义Connection和PreparedStatement
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "drop table " + qbankdbname;
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			// 4.3 利用prepareStatement，执行SQL语句
			if (pstmt.executeUpdate() == 0) {
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
	
	@Override //删除作答库表
	public boolean DeleteABankTable(String abankdbname) {
		// 1.定义Connection和PreparedStatement
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "drop table " + abankdbname;
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			// 4.3 利用prepareStatement，执行SQL语句
			if (pstmt.executeUpdate() == 0) {
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
	
	@Override //移除某班级
	public boolean DeleteClass(String classname) {
		// 1.定义Connection和PreparedStatement
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "delete from db_classbank where classname =?";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, classname);
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

	@Override //创建题库表
	public boolean CreateQTable(String qbankdbname) {
		// 1.定义Connection和PreparedStatement
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "create table " + qbankdbname +"(subject varchar(100) NOT NULL,id varchar(100) NOT NULL,topic varchar(5000),"
		+ "knowledge varchar(5000),answer varchar(5000),point varchar(100),PRIMARY KEY(subject,id))";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			// 4.3 利用prepareStatement，执行SQL语句
			if (pstmt.executeUpdate() == 0) {
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
	
	@Override //创建作答库表
	public boolean CreateATable(String abankdbname) {
		// 1.定义Connection和PreparedStatement
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "create table " + abankdbname +"(sid varchar(20) NOT NULL,subject varchar(100) NOT NULL,id varchar(100) NOT NULL,myanswer varchar(5000),"
				+ "cpoint varchar(100) NOT NULL,ppoint varchar(100) NOT NULL,PRIMARY KEY(sid,subject,id))";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			// 4.3 利用prepareStatement，执行SQL语句
			if (pstmt.executeUpdate() == 0) {
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
	
	@Override //在班级库表插入一条记录
	public boolean AddClass(ClassBankBean bank) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "insert into db_classbank (classname,qbankdbname,abankdbname)" + " values(?,?,?)";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, bank.getClassname());
			pstmt.setString(2, bank.getQbankdbname());
			pstmt.setString(3, bank.getAbankdbname());
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

	@Override //题目库表名称是否重复
	public boolean SameQBankDbName(String qbankdbname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = true;
		int count = 0;
		// 2.定义查询用户的SQL语句
		String sql = "select count(*) from db_classbank where qbankdbname = ?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, qbankdbname);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if(count == 0){
				flag = false;
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

	@Override //作答库表名称是否重复
	public boolean SameABankDbName(String abankdbname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = true;
		int count = 0;
		// 2.定义查询用户的SQL语句
		String sql = "select count(*) from db_classbank where abankdbname =?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, abankdbname);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if(count == 0){
				flag = false;
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

	@Override //查状态表找考试个数
	public int FindAllTest() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select * from db_teststate";
		// 3.定义返回值
		int classcount = 0;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				classcount = classcount + 1;
			}
			return classcount;
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

	@Override //考试列表分页
	public List<Object> FindTestToPage(int page, int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select * from db_teststate limit ?,?";
		// 3.定义返回值
		List<Object> tests = new ArrayList<Object>();
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setInt(1, (page-1)*limit);
			pstmt.setInt(2, limit);
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

	@Override //删除老师
	public boolean DeleteTeacher(String tid, String tsubjects) {
		// 1.定义Connection和PreparedStatement
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "delete from db_teacherinfo where tid =? and tsubjects=?";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, tid);
			pstmt.setString(2, tsubjects);
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
}
