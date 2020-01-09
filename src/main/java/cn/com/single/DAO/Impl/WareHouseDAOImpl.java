package cn.com.single.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.demo.javaweb.shopping.db.DaoUtils;
import cn.com.demo.javaweb.shopping.entity.Warehouse;
import cn.com.single.DAO.WareHouseDAO;
import cn.com.single.db.DBConnection;

public class WareHouseDAOImpl implements WareHouseDAO {
	DBConnection DBConn = DBConnection.getInstance();

	@Override
	public int findNum(int proId, int colorId, int sizeId) {
		int num = 0;
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select num from warehouse where proId=? and colorId=? and sizeId=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, proId);
			pstm.setInt(2, colorId);
			pstm.setInt(3, sizeId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				num = rs.getInt("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstm, rs);
		}
		return num;
	}

	@Override
	public List<Integer> findColors(int proId) {
		List<Integer> colorList = null;
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select distinct(colorId) from warehouse where proId=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, proId);
			rs = pstm.executeQuery();
			colorList = new ArrayList<Integer>();
			while (rs.next()) {
				colorList.add(rs.getInt("colorId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstm, rs);
		}
		return colorList;
	}

	@Override
	public List<Integer> findSizes(int proId) {
		List<Integer> sizeList = null;
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select distinct(sizeId) from warehouse where proId=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, proId);
			rs = pstm.executeQuery();
			sizeList = new ArrayList<Integer>();
			while (rs.next()) {
				sizeList.add(rs.getInt("sizeId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstm, rs);
		}
		return sizeList;
	}

	@Override
	public int findColor(String colorType) {
		int colorPkid = 0;
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select colorPkid from color where colorType=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, colorType);
			rs = pstm.executeQuery();
			while (rs.next()) {
				colorPkid = rs.getInt("colorPkid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstm, rs);
		}
		return colorPkid;
	}

	@Override
	public int findSize(String sizeType) {
		int sizePkid = 0;
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select sizePkid from size where sizeType=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, sizeType);
			rs = pstm.executeQuery();
			while (rs.next()) {
				sizePkid = rs.getInt("sizePkid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstm, rs);
		}
		return sizePkid;
	}

	@Override
	public int findWareId(String colorType, String sizeType, int proId) {
		int wareId = 0;
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select warehouse.id from warehouse" + " inner join size on warehouse.sizeId=size.sizePkid"
				+ "  inner join color on color.colorPkid=warehouse.colorId"
				+ "  where color.colorType=? and  size.sizeType=? and warehouse.proId=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, colorType);
			pstm.setString(2, sizeType);
			pstm.setInt(3, proId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				wareId = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstm, rs);
		}
		return wareId;
	}

	@Override
	public int findWareId(int proId) {
		String sql = "select * from warehouse where proId = ?";
		return DaoUtils.getListBySql(Warehouse.class, sql, proId).get(0).getId();
	}

}
