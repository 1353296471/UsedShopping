package cn.com.search.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.com.search.dao.SearchDAO;
import cn.com.search.db.DBConnection;
import cn.com.search.entity.Product1;

public class SearchDAOImpl implements SearchDAO {
	private DBConnection dbConn = DBConnection.getInstance();

	@Override
	public List<Product1> findByName(String whereSql) {
		List<Product1> list = null;
	    Connection conn = dbConn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String where="%"+whereSql+"%";
		System.out.println(where);
		String sql = "select product.id,  proName, price, imgUrl from product INNER JOIN img"+
				"  on img.proId=product.id"+"  where  proName LIKE ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1,where);
			
			rs = pstmt.executeQuery();
			int i = 0;
			if(rs !=  null) {
				list = new ArrayList<Product1>();
				Product1 pro = null;
				while(rs.next()) {
					System.out.println(i++);
					pro = new Product1();
					pro.setId(rs.getInt("id"));
					pro.setProName(rs.getString("proName"));
					pro.setPrice(rs.getDouble("price"));
					pro.setImgUrl(rs.getString("imgUrl"));
					System.out.println(pro.toString());
					list.add(pro);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			dbConn.close(conn, pstmt, rs);
		}
	
		return list;
	}


}
