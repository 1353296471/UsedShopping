package cn.com.single.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.single.DAO.RelatedImgDAO;
import cn.com.single.db.DBConnection;
import cn.com.single.entity.Imags;

public class RelatedImgDAOImpl implements RelatedImgDAO {
	DBConnection DBConn = DBConnection.getInstance();

	@Override
	public List<Imags> findImgUrls(int proId) {
		List<Imags> imgList = null;
		Imags imags = null;
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select product.catalogId,imgUrl,product.id,product.price,proName from img "
				+ " inner join product on product.id=img.proId "
				+ "  where proId in (select id from product where catalogId=(select catalogId from product where id=?)) limit 4";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, proId);
			rs = pstm.executeQuery();
			imgList = new ArrayList<Imags>();
			while (rs.next()) {
				imags = new Imags();
				imags.setCatalogId(rs.getInt("catalogId"));
				imags.setImgUrl(rs.getString("imgUrl"));
				imags.setPrice(rs.getDouble("price"));
				imags.setProId(rs.getInt("id"));
				imags.setProName(rs.getString("proName"));
				imgList.add(imags);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstm, rs);
		}
		return imgList;
	}

	@Override
	public String findProImag(int proId) {
		String url = null;
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select imgUrl from img where proId=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, proId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				url = rs.getString("imgUrl");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstm, rs);
		}
		return url;
	}

}
