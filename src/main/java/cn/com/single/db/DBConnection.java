package cn.com.single.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
	private static DBConnection DBConn;
	private String userName;
	private String url;

	private DBConnection() {
		init();
	}

	public static DBConnection getInstance() {
		if (DBConn == null) {
			DBConn = new DBConnection();
		}
		return DBConn;
	}

	private void init() {
		Properties props = new Properties();
		InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			props.load(in);
			this.url = props.getProperty("jdbc.url");
			this.userName = props.getProperty("jdbc.userName");
			String driverName = props.getProperty("jdbc.driverName");
			Class.forName(driverName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Connection getDBConn() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, userName, "root");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return conn;
	}

	public void close(Connection conn, Statement pstm, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (pstm != null)
				pstm.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
