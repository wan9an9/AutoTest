package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.chnlp.AHANLP;

public class ExamDAOImpl implements ExamDAO {

	@Override //从某个班级题表中分页显示题目（老师发题）
	public List<Object> getQuestionsToPages(String qBankDbName, String subject, int page, int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select * from " + qBankDbName  + " where subject=? ORDER BY CAST(id AS UNSIGNED INT) limit ?,?";
		// 3.定义返回值
		List<Object> qs = new ArrayList<Object>();
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			pstmt.setInt(2, (page-1)*limit);
			pstmt.setInt(3, limit);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ExamBean q = new ExamBean();
				q.setSubject(rs.getString(1));
				q.setId(rs.getString(2));
				q.setTopic(rs.getString(3));
				q.setKnowledge(rs.getString(4));
				q.setAnswer(rs.getString(5));
				q.setPoint(rs.getString(6));
				qs.add(q);
			}
			return qs;
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

	@Override //从某个班级题表中查询某科目的完整题目数目
	public int getFullQuestionCount(String dbname, String tsubject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select count(*) from " + dbname + " where subject=? and id!='' and topic!='' and knowledge!='' and answer!='' and point!=''";
		// 3.定义返回值
		int count = 0;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, tsubject);
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
	
	@Override //从某个班级题表中查询某科目的不完整题目数目
	public int getUnFullQuestionCount(String dbname, String tsubject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select count(*) from " + dbname + " where subject=? and id!='' and topic='' and knowledge='' and answer='' and point='' ";
		// 3.定义返回值
		int count = 0;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, tsubject);
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
	
	@Override //从某个班级题表中查询某科目的题目数目
	public int getQuestionCount(String dbname, String tsubject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select count(*) from " + dbname + " where subject=? and id!='' ";
		// 3.定义返回值
		int count = 0;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, tsubject);
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
	
	@Override //在所教科目的每个班级的题库根据题目数量创建题表头
	public void InsertQHead(String qBankDbName, String tsubject, int qcount) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "insert into " + qBankDbName + " (subject,id,topic,knowledge,answer,point)" + " values(?,?,?,?,?,?)";
		// 3.定义执行成功与否的标志变量flag
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			for(int i=0; i<qcount; i++){
				pstmt.setString(1, tsubject);
				pstmt.setString(2, Integer.toString(i+1));
				pstmt.setString(3, "");
				pstmt.setString(4, "");
				pstmt.setString(5, "");
				pstmt.setString(6, "");
				// 4.3 利用prepareStatement，执行SQL语句
				pstmt.executeUpdate();
			}
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
	
	@Override //在所教科目的每个班级的题库  附加创建  题表头
	public void InsertRestQHead(String QBankDbName, String tsubject, int begin, int count) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "insert into " + QBankDbName + " (subject,id,topic,knowledge,answer,point)" + " values(?,?,?,?,?,?)";
		// 3.定义执行成功与否的标志变量flag
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			for(int i=begin; i<=count; i++){
				pstmt.setString(1, tsubject);
				pstmt.setString(2, Integer.toString(i));
				pstmt.setString(3, "");
				pstmt.setString(4, "");
				pstmt.setString(5, "");
				pstmt.setString(6, "");
				// 4.3 利用prepareStatement，执行SQL语句
				pstmt.executeUpdate();
			}
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

	@Override //判断该科目有无记录
	public boolean FindSubjectRecord(String qBankDbName, String tsubject) {
		// 1.定义Connection和PreparedStatement和ResultSet
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean flag = false;
		// 2.定义查询用户的SQL语句
		String sql = "select * from " + qBankDbName + " where subject=?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, tsubject);
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

	@Override 	//判断该科目有无完整记录
	public int FindIsFullSubjectRecord(String qBankDbName, String tsubject) {
		// 1.定义Connection和PreparedStatement和ResultSet
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		int con = -1;
		boolean flag1 = false;
		boolean flag2 = false;
		//1.判断是有完整记录
		String sql1 = "select * from " + qBankDbName + " where subject=? and id!='' and topic!='' and knowledge!='' and answer!='' and point!='' ";
		//1.判断是有不完整记录
		String sql2 = "select * from " + qBankDbName + " where subject=? and id!='' and topic='' and knowledge='' and answer='' and point='' ";
		// 3.定义返回值
		try {
			conn = JdbcUtils.getConnection();
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, tsubject);
			rs1 = pstmt1.executeQuery();
			if (rs1.next()) {
				flag1 = true;
			}
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, tsubject);
			rs2 = pstmt2.executeQuery();
			if (rs2.next()) {
				flag2 = true;
			}
			if(flag1 == true && flag2 == false) con = 1;
			if(flag1 == false && flag2 == true) con = 2;
			if(flag1 == true && flag2 == true) con = 3;
			return con;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 无论有没有异常，SQL有没有执行成功
			// 都要关闭Connetion和PreparedStatement
			try {
				if (rs1 != null)
					rs1.close();
				if (pstmt1 != null)
					pstmt1.close();
				if (rs2 != null)
					rs2.close();
				if (pstmt2 != null)
					pstmt2.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override //在所教科目的每个班级的题库根据题号编辑题目 
	public boolean UpdateQuestion(String qBankDbName, ExamBean question) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "update " + qBankDbName + " set topic=?,knowledge=?,answer=?,point=? where subject=? and id=?";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, question.getTopic());
			pstmt.setString(2, question.getKnowledge());
			pstmt.setString(3, question.getAnswer());
			pstmt.setString(4, question.getPoint());
			pstmt.setString(5, question.getSubject());
			pstmt.setString(6, question.getId());
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

	@Override //从某个班级题表中分页显示完整的题目
	public List<Object> getFullQuestionsToPages(String qBankDbName, String subject, int page, int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select * from " + qBankDbName  + " where subject=? and id!='' and topic!='' and knowledge!='' and answer!='' and point!='' ORDER BY CAST(id AS UNSIGNED INT) limit ?,?";
		// 3.定义返回值
		List<Object> qs = new ArrayList<Object>();
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			pstmt.setInt(2, (page-1)*limit);
			pstmt.setInt(3, limit);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ExamBean q = new ExamBean();
				q.setSubject(rs.getString(1));
				q.setId(rs.getString(2));
				q.setTopic(rs.getString(3));
				q.setKnowledge(rs.getString(4));
				q.setAnswer(rs.getString(5));
				q.setPoint(rs.getString(6));
				qs.add(q);
			}
			return qs;
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

	@Override //发布题目，修改状态表
	public boolean PublishQuestion(TestStateBean testState) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		// 2.定义添加用户的SQL语句
		String sql = "insert into db_teststate (classname,subject,begintime,endtime,ccheckover,pcheckover)" + " values(?,?,?,?,?,?)";
		// 3.定义执行成功与否的标志变量flag
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, testState.getClassname());
			pstmt.setString(2, testState.getSubject());
			pstmt.setString(3, testState.getBegintime());
			pstmt.setString(4, testState.getEndtime());
			pstmt.setString(5, testState.getCcheckover());
			pstmt.setString(6, testState.getPcheckover());
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

	@Override //查询状态表，题目是否已经发布(选择单个班级)
	public boolean FindPublishedTestInfo(String classes, String subject) {
		// 1.定义Connection和PreparedStatement和ResultSet
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean flag = false;
		// 2.定义查询用户的SQL语句
		String sql = "select * from db_teststate where classname=? and subject=?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, classes);
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

	@Override //得到已发布的某个考试的状态信息(选择单个班级)
	public List<Object> ReturnPublishedTestInfo(String classes, String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select * from db_teststate where classname=? and subject=?";
		// 3.定义返回值
		List<Object> test = new ArrayList<Object>();
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, classes);
			pstmt.setString(2, subject);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				TestStateBean testinfo = new TestStateBean();
				testinfo.setClassname(rs.getString(1));
				testinfo.setSubject(rs.getString(2));
				testinfo.setBegintime(rs.getString(3));
				testinfo.setEndtime(rs.getString(4));
				testinfo.setCcheckover(rs.getString(5));
				testinfo.setPcheckover(rs.getString(6));
				test.add(testinfo);
			}
			return test;
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

	@Override //查询状态表，题目是否已经发布(选择全部班级)
	public boolean FindPublishedTestInfoList(String[] classslist, String subject) {
		// 1.定义Connection和PreparedStatement和ResultSet
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		int count = 0;
		// 2.定义查询用户的SQL语句
		String sql = "select * from db_teststate where classname=? and subject=?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			for(int i=0; i<classslist.length; i++){
				pstmt.setString(1, classslist[i]);
				pstmt.setString(2, subject);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = count + 1;
				}
			}
			if(count == classslist.length){
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

	@Override //得到已发布的某个考试的状态信息(选择全部班级)
	public List<Object> ReturnPublishedTestInfoList(String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select * from db_teststate where subject=?";
		// 3.定义返回值
		List<Object> testinfolist = new ArrayList<Object>();
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TestStateBean testinfo = new TestStateBean();
				testinfo.setClassname(rs.getString(1));
				testinfo.setSubject(rs.getString(2));
				testinfo.setBegintime(rs.getString(3));
				testinfo.setEndtime(rs.getString(4));
				testinfo.setCcheckover(rs.getString(5));
				testinfo.setPcheckover(rs.getString(6));
				testinfolist.add(testinfo);
			}
			return testinfolist;
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

	@Override //获取某科目的开始考试时间
	public String FindBeginTime(String sclass, String subject) {
		// 1.定义Connection和PreparedStatement和ResultSet
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String begintime = "";
		// 2.定义查询用户的SQL语句
		String sql = "select begintime from db_teststate where classname=? and subject=?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, sclass);
			pstmt.setString(2, subject);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				begintime = rs.getString(1);
			}
			return begintime;
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

	@Override //获取某科目的结束考试时间
	public String FindEndTime(String sclass, String subject) {
		// 1.定义Connection和PreparedStatement和ResultSet
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String endtime = "";
		// 2.定义查询用户的SQL语句
		String sql = "select endtime from db_teststate where classname=? and subject=?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, sclass);
			pstmt.setString(2, subject);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				endtime = rs.getString(1);
			}
			return endtime;
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

	@Override //查询考试是否已发布
	public boolean FindTest(String sclass, String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		// 2.定义查询用户的SQL语句
		String sql = "select * from db_teststate where classname=? and subject=?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, sclass);
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

	@Override //在题表中计算某科目的总分
	public String FindTotalPoints(String qBankDbName, String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		// 2.定义查询用户的SQL语句
		String sql = "select point from " + qBankDbName + " where subject=? and id!='' and topic!='' and knowledge!='' and answer!= '' and point!=''";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				total = total + Integer.parseInt(rs.getString(1));
			}
			return Integer.toString(total);
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

	@Override //从某个班级题表中分页显示题目（学生答题）
	public List<Object> getQuestionsToPagesForStudent(String qBankDbName, String subject, int page, int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.定义添加用户的SQL语句
		String sql = "select * from " + qBankDbName  + " where subject=? and id!='' and topic!='' and knowledge!='' and answer!= '' and point!='' ORDER BY CAST(id AS UNSIGNED INT) limit ?,?";
		// 3.定义返回值
		List<Object> qs = new ArrayList<Object>();
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			pstmt.setInt(2, (page-1)*limit);
			pstmt.setInt(3, limit);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentTestBean q = new StudentTestBean();
				q.setId(rs.getString(2));
				q.setPoint(rs.getString(6));
				q.setTopic(rs.getString(3));
				q.setKnowledge(rs.getString(4));
				q.setMyanswer("");
				qs.add(q);
			}
			return qs;
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

	@Override //题目答案提交
	public boolean UpdateAnswer(String aBankDbName, String sid, String subject, String id, String myanswer) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "update " + aBankDbName + " set myanswer=? where sid=? and subject=? and id=?";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, myanswer);
			pstmt.setString(2, sid);
			pstmt.setString(3, subject);
			pstmt.setString(4, id);
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

	@Override //分页显示 单个班级 答题卡
	public List<Object> getQAToPages(String qBankDbName, String aBankDbName, String sclass, String subject, int page,
			int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object> qa = new ArrayList<Object>();
		String sql = "SELECT " + aBankDbName + ".sid," + aBankDbName +".id," + qBankDbName + ".point," + qBankDbName + ".topic,"
				+ qBankDbName +".answer," + aBankDbName + ".myanswer," + aBankDbName + ".ppoint FROM " + qBankDbName + "," + aBankDbName 
				+ " WHERE " + aBankDbName +".subject =? AND " + aBankDbName + ".id = " + qBankDbName 
				+ ".id AND "+ aBankDbName + ".subject = " + qBankDbName + ".subject AND " + aBankDbName 
				+ ".sid IN (SELECT sid FROM db_studentinfo where sclass =?) ORDER BY CASt(" + aBankDbName + ".id AS UNSIGNED INT)," + aBankDbName + ".sid limit ?,?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			pstmt.setString(2, sclass);
			pstmt.setInt(3, (page-1)*limit);
			pstmt.setInt(4, limit);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentTestBean q = new StudentTestBean();
				q.setSid(rs.getString(1));
				q.setId(rs.getString(2));
				q.setPoint(rs.getString(3));
				q.setTopic(rs.getString(4));
				q.setAnswer(rs.getString(5));
				q.setMyanswer(rs.getString(6));
				q.setPpoint(rs.getString(7));
				qa.add(q);
			}
			return qa;
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

	@Override //查询某班题表中的完整题目的题号是否连续
	public boolean CheckIdLianXu(String qBankDbName, String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean flag = true;
		//完整题目个数
		int fullq = getFullQuestionCount(qBankDbName, subject);
		String [] idlist = new String[fullq];
		// 2.定义查询用户的SQL语句
		String sql = "select id from " + qBankDbName + " where subject=? and id!='' and topic!='' and knowledge!='' and answer!='' and point!='' ORDER BY CAST(id AS UNSIGNED INT)";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				idlist[i] = rs.getString(1);
				i = i+1;
			}
			int index = 0;
			int start = Integer.parseInt(idlist[index]);
			for (int j = 1; j < idlist.length; j++) {
				if(Integer.parseInt(idlist[j]) - start != 1){
					flag = false;
					break;
				}
				start = Integer.parseInt(idlist[j]);
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

	@Override //在班级作答表建立空答题卡
	public void CreateNullAnswerSheet(String QBankDbName, String ABankDbName, String subject, String classes) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		PreparedStatement pstmt2 = null;
		String sql1 = "SELECT db_studentinfo.sid,id FROM db_studentinfo," + QBankDbName + " WHERE sclass =? and " + QBankDbName + ".subject=? ORDER BY CAST(id AS UNSIGNED INT)";
		String sql2 = "insert into " + ABankDbName + " (sid,subject,id,myanswer,cpoint,ppoint)" + " values(?,?,?,?,?,?)";
		// 3.定义执行成功与否的标志变量flag
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, classes);
			pstmt1.setString(2, subject);
			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, rs1.getString(1));
				pstmt2.setString(2, subject);
				pstmt2.setString(3, rs1.getString(2));
				pstmt2.setString(4, "");
				pstmt2.setString(5, "");
				pstmt2.setString(6, "");
				pstmt2.executeUpdate();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 无论有没有异常，SQL有没有执行成功
			// 都要关闭Connetion和PreparedStatement
			try {
				if (pstmt1 != null)
					pstmt1.close();
				if (pstmt2 != null)
					pstmt2.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override //查询输入的分数格式是否正确
	public String [] checkPointFormat(String qBankDbName, String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//完整题目个数
		int fullq = getFullQuestionCount(qBankDbName, subject);
		String [] pointlist = new String[fullq + 1];
		// 2.定义查询用户的SQL语句
		String sql = "select point from " + qBankDbName + " where subject=? and id!='' and topic!='' and knowledge!='' and answer!='' and point!=''";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			int i = 1;
			pointlist[0] = "check";
			while (rs.next()) {
				pointlist[i] = rs.getString(1);
				i = i+1;
			}
			return pointlist;
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

	@Override //老师评分提交
	public boolean UpdatePpoint(String aBankDbName, String sid, String subject, String id, String ppoint) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "update " + aBankDbName + " set ppoint=? where sid=? and subject=? and id=?";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, ppoint);
			pstmt.setString(2, sid);
			pstmt.setString(3, subject);
			pstmt.setString(4, id);
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

	@Override //修改考试时间
	public boolean UpdateTestTime(String classname, String subject, String begintime, String endtime) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "update db_teststate set begintime=?,endtime=? where classname=? and subject=?";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, begintime);
			pstmt.setString(2, endtime);
			pstmt.setString(3, classname);
			pstmt.setString(4, subject);
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

	@Override //某班人工已评分条数
	public int FindMarkCount(String qBankDbName, String aBankDbName, String classes, String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "SELECT count(*) FROM " + qBankDbName + "," + aBankDbName 
				+ " WHERE " + aBankDbName +".subject =? AND " + aBankDbName + ".id = " + qBankDbName 
				+ ".id AND "+ aBankDbName + ".subject = " + qBankDbName + ".subject AND " + aBankDbName + ".ppoint !='' and " + aBankDbName 
				+ ".sid IN (SELECT sid FROM db_studentinfo where sclass =?) ORDER BY CAST(" + aBankDbName + ".id AS UNSIGNED INT)," + aBankDbName + ".sid";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			pstmt.setString(2, classes);
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

	@Override //获取某考试人工评分是否完成标志
	public String FindPcheckover(String classes, String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String flag = "";
		// 2.定义查询用户的SQL语句
		String sql = "select pcheckover from db_teststate where subject=? and classname=?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			pstmt.setString(2, classes);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = rs.getString(1);
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

	@Override //设置某考试人工评分否完成标志
	public boolean UpdatePcheckover(String classes, String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "update db_teststate set pcheckover='yes' where subject=? and classname=?";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			pstmt.setString(2, classes);
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

	@Override //显示人工评分某班的成绩单
	public List<Object> ShowPTestCard(String qBankDbName, String aBankDbName, String classes, String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object> qa = new ArrayList<Object>();
		String sql = "SELECT ptestcard.sid,SUM(CAST(ptestcard.ppoint AS DECIMAL)) as score FROM(SELECT " + aBankDbName + ".sid," + aBankDbName 
				+ ".ppoint FROM "+ qBankDbName +"," + aBankDbName +" WHERE " + aBankDbName +".subject =? AND " + aBankDbName +".id = "
				+ qBankDbName + ".id AND "+ aBankDbName +".subject = "+ qBankDbName + ".subject AND " + aBankDbName + ".sid IN (SELECT sid FROM db_studentinfo where sclass =?) ORDER BY "
				+ aBankDbName + ".sid, CAST(" + aBankDbName + ".id AS UNSIGNED INT))AS ptestcard GROUP BY ptestcard.sid ORDER BY score DESC,ptestcard.sid";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			pstmt.setString(2, classes);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				StudentTestBean q = new StudentTestBean();
				q.setPranking(Integer.toString(i)); //排名
				i++;
				q.setSid(rs.getString(1)); //学号
				q.setPtotalpoint(rs.getString(2)); //总分
				qa.add(q);
			}
			return qa;
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

	@Override //自动评分
	public boolean AutoMark(String qBankDbName, String aBankDbName, String classname, String subject) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		boolean flag = true;
		//找出两个答案
		String sql1 = "SELECT " + aBankDbName + ".sid," + aBankDbName + ".id," + qBankDbName + ".answer," + aBankDbName + ".myanswer,"
				+ qBankDbName + ".point FROM " + qBankDbName +"," + aBankDbName + " WHERE " 
				+ aBankDbName + ".subject =? AND " + aBankDbName +".id = " + qBankDbName + ".id AND " + aBankDbName 
				+ ".subject = " + qBankDbName + ".subject AND " + aBankDbName + ".sid IN (SELECT sid FROM db_studentinfo where sclass =?) ORDER BY "
				+ aBankDbName +".sid,CAST(" + aBankDbName + ".id AS UNSIGNED INT)";
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt1 = conn.prepareStatement(sql1);
			// 4.2 给SQL语句传递参数
			pstmt1.setString(1, subject);
			pstmt1.setString(2, classname);
			// 4.3 利用prepareStatement，执行SQL语句
			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				StudentTestBean q = new StudentTestBean();
				q.setSid(rs1.getString(1)); //学号
				q.setId(rs1.getString(2)); //题号
				q.setAnswer(rs1.getString(3)); //标准答案
				q.setMyanswer(rs1.getString(4)); //我的作答
				q.setPoint(rs1.getString(5)); //本题总分
				//机器评分
				//1.总的来比较
				float res1 = AHANLP.sentenceSimilarity("NLP", q.getAnswer(), q.getMyanswer());
				
				//2.摘要后比较
				float res2 = 0;
				if(q.getMyanswer().length() != 0){
					String summary = AHANLP.extractSummary(q.getMyanswer(), q.getAnswer().length());
					res2 = AHANLP.sentenceSimilarity("NLP", q.getAnswer(), summary);
				}else {
					res2 = 0;
				}
				
				//3.取二者平均值作为相似度
				float res3 = ((res1 + res2) / 2) * Float.parseFloat(q.getPoint());
				
				//四舍五入保留一位小数
				if(res3 < 1.0){
					res3 = 0;
				}
				String resstr = new java.text.DecimalFormat("#.0").format(res3);
				if(resstr.equals(".0")){
					resstr = "0";
				}
				q.setCpoint(resstr);
				if(UpdateCpoint(aBankDbName, q.getSid(), subject, q.getId(), q.getCpoint()) == false){
					flag = false;
				}
			}
			return flag;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 无论有没有异常，SQL有没有执行成功
			// 都要关闭Connetion和PreparedStatement
			try {
				if (rs1 != null)
					rs1.close();
				if (pstmt1 != null)
					pstmt1.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override //获取某考试机器评分是否完成标志
	public String FindCcheckover(String classes, String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String flag = "";
		// 2.定义查询用户的SQL语句
		String sql = "select ccheckover from db_teststate where subject=? and classname=?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			pstmt.setString(2, classes);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = rs.getString(1);
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
	
	@Override //自动评分提交
	public boolean UpdateCpoint(String aBankDbName, String sid, String subject, String id, String cpoint) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "update " + aBankDbName + " set cpoint=? where sid=? and subject=? and id=?";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, cpoint);
			pstmt.setString(2, sid);
			pstmt.setString(3, subject);
			pstmt.setString(4, id);
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

	@Override  //设置某考试机器评分否完成标志
	public boolean UpdateCcheckover(String classes, String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 2.定义添加用户的SQL语句
		String sql = "update db_teststate set ccheckover='yes' where subject=? and classname=?";
		// 3.定义执行成功与否的标志变量flag
		boolean flag = false;
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			pstmt.setString(2, classes);
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

	@Override //自动评阅结果表显示
	public List<Object> ShowAutoMarkResult(String qBankDbName, String aBankDbName, String classname, String subject) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		List<Object> qa = new ArrayList<Object>();
		String sql1 = "SELECT " + aBankDbName + ".sid," + qBankDbName + ".id," + qBankDbName + ".topic," + qBankDbName 
				+ ".knowledge," + qBankDbName + ".answer," + aBankDbName + ".myanswer," + aBankDbName + ".cpoint," +
				qBankDbName + ".point FROM " + qBankDbName + "," + aBankDbName +" WHERE " + aBankDbName + ".subject =? AND " 
				 + aBankDbName + ".id = " + qBankDbName + ".id AND " + aBankDbName + ".subject = " + qBankDbName 
				 + ".subject AND " + aBankDbName + ".sid IN (SELECT sid FROM db_studentinfo where sclass =?) ORDER BY "
				 + aBankDbName + ".sid,CAST(" + aBankDbName + ".id AS UNSIGNED INT)";
		String sql2 = "SELECT sname from db_studentinfo where sid=?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt1 = conn.prepareStatement(sql1);
			// 4.2 给SQL语句传递参数
			pstmt1.setString(1, subject);
			pstmt1.setString(2, classname);
			// 4.3 利用prepareStatement，执行SQL语句
			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				StudentTestBean q = new StudentTestBean();
				q.setSclass(classname);
				q.setSid(rs1.getString(1));
				
				try {
					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setString(1, q.getSid());
					rs2 = pstmt2.executeQuery();
					if(rs2.next()){
						q.setSname(rs2.getString(1));
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				} finally {
					// 无论有没有异常，SQL有没有执行成功
					// 都要关闭Connetion和PreparedStatement
					try {
						if (rs2 != null)
							rs2.close();
						if (pstmt2 != null)
							pstmt2.close();
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
				}
				
				q.setId(rs1.getString(2));
				q.setTopic(rs1.getString(3));
				q.setKnowledge(rs1.getString(4));
				q.setAnswer(rs1.getString(5));
				q.setMyanswer(rs1.getString(6));
				q.setCpoint(rs1.getString(7));
				q.setPoint(rs1.getString(8));
				qa.add(q);
			}
			return qa;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 无论有没有异常，SQL有没有执行成功
			// 都要关闭Connetion和PreparedStatement
			try {
				if (rs1 != null)
					rs1.close();
				if (pstmt1 != null)
					pstmt1.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override  //显示自动评分某班的成绩单
	public List<Object> ShowCTestCard(String qBankDbName, String aBankDbName, String classname, String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object> qa = new ArrayList<Object>();
		String sql = "SELECT ptestcard.sid,SUM(CAST(ptestcard.cpoint AS DECIMAL)) as score FROM(SELECT " + aBankDbName + ".sid," + aBankDbName 
				+ ".cpoint FROM "+ qBankDbName +"," + aBankDbName +" WHERE " + aBankDbName +".subject =? AND " + aBankDbName +".id = "
				+ qBankDbName + ".id AND "+ aBankDbName +".subject = "+ qBankDbName + ".subject AND " + aBankDbName + ".sid IN (SELECT sid FROM db_studentinfo where sclass =?) ORDER BY "
				+ aBankDbName + ".sid,CAST(" + aBankDbName + ".id AS UNSIGNED INT))AS ptestcard GROUP BY ptestcard.sid ORDER BY score DESC,ptestcard.sid";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			pstmt.setString(2, classname);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				StudentTestBean q = new StudentTestBean();
				q.setPranking(Integer.toString(i)); //班级排名
				q.setSclass(classname); //班级
				i++;
				q.setSid(rs.getString(1)); //学号
				q.setCtotalpoint(rs.getString(2)); //总分
				qa.add(q);
			}
			return qa;
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

	@Override //评分分析
	public List<Object> ShowMarkECharts(String qBankDbName, String aBankDbName, String classname, String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object> qa = new ArrayList<Object>();
		String sql = "SELECT ptestcard.id,ROUND(AVG(CAST(ptestcard.ppoint AS DECIMAL)),1) AS pavgpoint,ROUND(AVG(CAST(ptestcard.cpoint AS DECIMAL)),1) AS cavgpoint FROM(SELECT "
					+ aBankDbName + ".id," + aBankDbName + ".ppoint," + aBankDbName + ".cpoint FROM " + qBankDbName + "," 
					+ aBankDbName + " WHERE " + aBankDbName + ".subject =? AND " + aBankDbName + ".id = " 
					+ qBankDbName + ".id AND " + aBankDbName + ".subject = " + qBankDbName + ".subject AND "
					+ aBankDbName + ".sid IN (SELECT sid FROM db_studentinfo where sclass =?))AS ptestcard GROUP BY CAST(ptestcard.id AS UNSIGNED INT)";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			pstmt.setString(2, classname);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentTestBean q = new StudentTestBean();
				q.setId(rs.getString(1)); //题号
				q.setPavgpoint(rs.getString(2)); //人工平均分
				q.setCavgpoint(rs.getString(3)); //机器平均分
				qa.add(q);
			}
			return qa;
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

	@Override //学生查阅试卷评阅结果
	public List<Object> ShowStudentTestResult(String qBankDbName, String aBankDbName, String sid, String classname, String subject,
			int page, int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object> qa = new ArrayList<Object>();
		String sql = "SELECT " + qBankDbName + ".id," + qBankDbName + ".topic," + qBankDbName + ".knowledge," 
				+ qBankDbName + ".answer," + aBankDbName + ".myanswer," + aBankDbName + ".ppoint," + aBankDbName +
				".cpoint," + qBankDbName + ".point FROM " + qBankDbName + "," + aBankDbName + " WHERE " + 
				aBankDbName + ".subject =? AND " + aBankDbName + ".sid=? AND " + aBankDbName + ".id = " + 
				qBankDbName + ".id AND " + aBankDbName + ".subject = " + qBankDbName + ".subject AND " + 
				aBankDbName + ".sid IN (SELECT sid FROM db_studentinfo where sclass =?) ORDER BY CAST(" + aBankDbName + ".id AS UNSIGNED INT) limit ?,?";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			pstmt.setString(2, sid);
			pstmt.setString(3, classname);
			pstmt.setInt(4, (page-1)*limit);
			pstmt.setInt(5, limit);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentTestBean q = new StudentTestBean();
				q.setId(rs.getString(1));
				q.setTopic(rs.getString(2));
				q.setKnowledge(rs.getString(3));
				q.setAnswer(rs.getString(4));
				q.setMyanswer(rs.getString(5));
				q.setPpoint(rs.getString(6));
				q.setCpoint(rs.getString(7));
				q.setPoint(rs.getString(8));
				qa.add(q);
			}
			return qa;
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

	@Override //机器评分知识点雷达图显示
	public List<Object> ShowStudentTestResult(String qBankDbName, String aBankDbName, String sid, String classname,
			String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object> qa = new ArrayList<Object>();
		String sql = "SELECT " + qBankDbName + ".knowledge," + qBankDbName + ".point," + aBankDbName + ".cpoint FROM "
				+ qBankDbName + "," + aBankDbName + " WHERE " + aBankDbName + ".subject =? AND " + aBankDbName 
				+ ".sid =? AND " + aBankDbName + ".id = " + qBankDbName + ".id AND " + aBankDbName + ".subject = "
				+ qBankDbName + ".subject AND " + aBankDbName + ".sid IN (SELECT sid FROM db_studentinfo where sclass =?) ORDER BY CAST(" + aBankDbName + ".id AS UNSIGNED INT)";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			pstmt.setString(2, sid);
			pstmt.setString(3, classname);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentTestBean q = new StudentTestBean();
				q.setKnowledge(rs.getString(1));
				q.setPoint(rs.getString(2));
				q.setCpoint(rs.getString(3));
				qa.add(q);
			}
			return qa;
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

	@Override //教师评分知识点雷达图显示
	public List<Object> ShowPScoreAnalysis(String qBankDbName, String aBankDbName, String sid, String classname,
			String subject) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object> qa = new ArrayList<Object>();
		String sql = "SELECT " + qBankDbName + ".knowledge," + qBankDbName + ".point," + aBankDbName + ".ppoint FROM "
				+ qBankDbName + "," + aBankDbName + " WHERE " + aBankDbName + ".subject =? AND " + aBankDbName 
				+ ".sid =? AND " + aBankDbName + ".id = " + qBankDbName + ".id AND " + aBankDbName + ".subject = "
				+ qBankDbName + ".subject AND " + aBankDbName + ".sid IN (SELECT sid FROM db_studentinfo where sclass =?) ORDER BY CAST(" + aBankDbName + ".id AS UNSIGNED INT)";
		// 3.定义返回值
		try {
			// 4.1 实例化Connection和PreparedStatement
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 4.2 给SQL语句传递参数
			pstmt.setString(1, subject);
			pstmt.setString(2, sid);
			pstmt.setString(3, classname);
			// 4.3 利用prepareStatement，执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentTestBean q = new StudentTestBean();
				q.setKnowledge(rs.getString(1));
				q.setPoint(rs.getString(2));
				q.setPpoint(rs.getString(3));
				qa.add(q);
			}
			return qa;
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