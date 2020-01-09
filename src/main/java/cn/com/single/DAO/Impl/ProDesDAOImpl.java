package cn.com.single.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.single.DAO.ProDesDAO;
import cn.com.single.db.DBConnection;

public class ProDesDAOImpl implements ProDesDAO {
	private DBConnection DBConn = DBConnection.getInstance();

	@Override
	public List<String> findProDes(int proId) {
		List<String> describeList = null;
		String des = null;
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			String sql = "select proDes from prodes where proDesPkid=?  ";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, proId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				des = rs.getString("proDes");
			}
			describeList = new ArrayList<String>();
//			String arg[] = des.split("；");
//			for (int i = 0; i < arg.length; i++) {
//				describeList.add(arg[i]);
//			}
			describeList.add(des);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstm, rs);
		}
		return describeList;
	}

}
