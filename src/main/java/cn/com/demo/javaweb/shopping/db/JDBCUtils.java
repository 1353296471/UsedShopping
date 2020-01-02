package cn.com.demo.javaweb.shopping.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 简易的数据库连接池,为DaoUtils提供链接
 * 
 * @author Cool文
 *
 */
public class JDBCUtils {
	private static String userName;
	private static String password;
	private static String url;
	private static String driverName;
	private static ArrayList<Connection> conns = new ArrayList<Connection>();

	private final static int LINKNUM = 5; // 最小连接数
	private final static int MAX_LINKNUM = 50; // 最大连接数
	static {
		// 在根目录下存放db.properties文件即可
		InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");

		Connection conn = null;
		try {
			Properties props = new Properties();
			props.load(in);
			password = props.getProperty("jdbc.password");
			url = props.getProperty("jdbc.url");
			userName = props.getProperty("jdbc.userName");
			driverName = props.getProperty("jdbc.driverName");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < LINKNUM; i++) {
			try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, userName, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conns.add(conn);
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public synchronized static Connection getConnectionByPool() {
		Connection conn = null;
		int index = conns.size() - 1;
		conn = conns.get(index);
		conns.remove(index);
		return conn;
	}

	public synchronized static void releaseConnection(Connection connection) {
		try {
			if (conns.size() >= MAX_LINKNUM && connection != null) {
				connection.close();
			} else {
				conns.add(connection);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 私有化构造器
	private JDBCUtils() {
		// TODO 自动生成的构造函数存根
	}

	public static void main(String[] args) {
		Connection conn = null;
		long stime = System.currentTimeMillis();
		for (int i = 0; i < 1; i++) {
			conn = getConnection();
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		long etime = System.currentTimeMillis();
		System.out.println(etime - stime);

		stime = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			conn = getConnectionByPool();
			System.out.println(conn);
			releaseConnection(conn);
		}
		etime = System.currentTimeMillis();
		System.out.println(etime - stime);
	}
}
