package test.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import test.dao.JdbcUtils;

/*
    //1.导入驱动jar包  复制包，右键add as library
	//2.编写代码注册驱动
	//3.获取数据库连接对象Connection(桥梁)
	//4.定义sql
	//5.获取执行sql语句的对象Statement
	//6.执行sql，接受返回结果
	//7.处理结果
	//8.释放资源
 */
public class JdbcUtils {
	private static String dbconfig = "dbconfig.properties";
	private static Properties props = null;
	static {
		try {
			InputStream in = JdbcUtils.class.getClassLoader()
					.getResourceAsStream(dbconfig);
			props = new Properties();
			props.load(in);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws Exception {
		// 获取与数据库的连接
		// 1.注册驱动

		Class.forName(props.getProperty("driverClassName"));
		return (DriverManager.getConnection(props.getProperty("url"),
				props.getProperty("user"), props.getProperty("password")));

	}
}
