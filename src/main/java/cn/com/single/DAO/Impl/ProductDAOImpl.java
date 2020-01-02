package cn.com.single.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.single.DAO.ProductDAO;
import cn.com.single.db.DBConnection;
import cn.com.single.entity.Product;

public class ProductDAOImpl implements ProductDAO {
	private DBConnection DBConn = DBConnection.getInstance();

	@Override
	public String findProName(int proId) {
		String proName = null;
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select proName from product where id=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, proId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				proName = rs.getString("proName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstm, rs);
		}
		return proName;
	}

	@Override
	public double findPrice(int proId) {
		double price = 0;
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select price from product where id=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, proId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				price = rs.getDouble("price");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstm, rs);
		}
		return price;
	}

	@Override
	public List<Product> findByPkid(int proPkid) {
		List<Product> productList = null;
		Product product = null;
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from product where id=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, proPkid);
			rs = pstm.executeQuery();
			productList = new ArrayList<Product>();
			while (rs.next()) {
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setProName(rs.getString("proName"));
				product.setPrice(rs.getDouble("price"));
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstm, rs);
		}
		return productList;
	}

}
