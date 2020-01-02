package cn.com.search.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
	private static DBConnection DBCONN;
	private String userName;
	private String password;
	private String url;
	
	private DBConnection(){
		init();
	}
	
	public static DBConnection getInstance(){
		if(DBCONN == null){
			DBCONN = new DBConnection();
		}
		return DBCONN;
	}

	private void init() {
		Properties props = new Properties();
		InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			props.load(in);
			this.password = props.getProperty("jdbc.password");
			this.url = props.getProperty("jdbc.url");
			this.userName = props.getProperty("jdbc.userName");
			
			String driverName = props.getProperty("jdbc.driverName");
			Class.forName(driverName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,userName,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return conn;
	}
	
	public void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
