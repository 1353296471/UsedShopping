package cn.com.demo.javaweb.shopping.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 简易的数据库操作工具类，封装了一些基本的CRUD
 * 
 * @author Cool文
 *
 */
public class DaoUtils {
//	private static boolean commit = true; //// 将自动提交设置为false 即开启事务 ，默认true
//
//	public static boolean isCommit() {
//		return commit;
//	}

//	private static Connection connCommit = JDBCUtils.getConnection(); // 为事务设置唯一的conn

//	/**
//	 * 成功后手动提交 失败时回滚
//	 */
//	public static void commit() {
//		DaoUtils.commit = true;
//		try {
//			connCommit.commit();
//		} catch (Exception e) {
//			try {
//				connCommit.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}

//	/**
//	 * 将自动提交设置为falg 开启事务需要将自动提交设置为false
//	 * 
//	 * @param falg
//	 */
//	public static void setAutoCommit(boolean falg) {
//		DaoUtils.commit = falg;
//		try {
//			connCommit.setAutoCommit(falg);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	private DaoUtils() {
		// TODO 自动生成的构造函数存根
	}

	public static Connection getConn() {
		Connection conn = null;
//		if (commit) {
//			conn = JDBCUtils.getConnectionByPool();
//			connCommit = conn;
//		} else {
//			conn = connCommit;
//		}
		conn = JDBCUtils.getConnectionByPool();
		return conn;
	}

	public static void releaseConn(Connection conn) {
//		if (commit) {
		// 事务开启时，暂时不需要将conn释放
		JDBCUtils.releaseConnection(conn);
//		}
	}

	/**
	 * 根据查询语句 和 参数 插入或修改数据库
	 * 
	 * @param sql
	 * @param args
	 * @return 是否插入或修改成功
	 */
	public static boolean insertOrUpdate(String sql, Object... args) {
		boolean bool = false;
		Connection conn = getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				pstm.setObject(i + 1, args[i]);
			}
			int result = pstm.executeUpdate();
			if (result != 0) {
				bool = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			free(rs, pstm, conn);
		}
		return bool;
	}

	/**
	 * 方便进行事务处理重载方法 去除了conn的close
	 * 
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public static boolean insertOrUpdate(Connection connection, String sql, Object... args) throws SQLException {
		boolean bool = false;
		Connection conn = connection;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		pstm = conn.prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			pstm.setObject(i + 1, args[i]);
		}
		int result = pstm.executeUpdate();
		if (result != 0) {
			bool = true;
		}

		free(rs, pstm);

		return bool;
	}

	/**
	 * 输入查询语句返回相对应的数据集合
	 * 
	 * 要求：类属性要和数据库列名一致
	 * 
	 * @param obj  对象类型
	 * @param sql  查询语句
	 * @param args 参数列表
	 * @return 返回相对应的数据集合
	 * @throws SQLException
	 */
	public static <T> ArrayList<T> getListBySql(Class<T> obj, String sql, Object... args) {
		T entity = null;
		ArrayList<T> entityList = new ArrayList<>();
		Connection conn = getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			Field[] field = obj.getDeclaredFields();
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					pstm.setObject(i + 1, args[i]);
				}
			}
			rs = pstm.executeQuery();
			while (rs.next()) {
				// 将数据封装进对象
				try {
					entity = obj.newInstance();
					for (int i = 0; i < field.length; i++) {
						field[i].setAccessible(true);
						if (rs.getObject(field[i].getName()) != null) {
							field[i].set(entity, rs.getObject(field[i].getName()));
						}
					}
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				entityList.add(entity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			free(rs, pstm, conn);
		}
		return entityList;
	}

	/**
	 * 查询是否存在的通用方法
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public static boolean executeQuery(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		boolean bool = true;
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			/* 有可能有参数 */
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			/* 执行 */
			set = ps.executeQuery();
			bool = set.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free(set, ps, conn);
		}
		return bool;
	}

	public static void free(ResultSet rs, Statement pstm, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			releaseConn(conn);
		}
	}

	public static void free(ResultSet rs, Statement pstm) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void free(Statement pstm, Connection conn) {
		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			releaseConn(conn);
		}
	}
}
