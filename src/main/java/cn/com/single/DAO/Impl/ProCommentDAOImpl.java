package cn.com.single.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.single.DAO.ProCommentDAO;
import cn.com.single.db.DBConnection;
import cn.com.single.entity.Comment;

public class ProCommentDAOImpl implements ProCommentDAO {
	DBConnection DBConn = DBConnection.getInstance();

	@Override
	public int commentCount(int proId) {
		int count = 0;
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select commentDes from comment where proPkid=? ";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, proId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstm, rs);
		}
		return count;
	}

	@Override
	public List<Comment> findByProPkid(int proPkid) {
		List<Comment> commentList = null;
		Comment comment = null;
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from comment where proPkid=? order by commentTime desc";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, proPkid);
			rs = pstm.executeQuery();
			commentList = new ArrayList<Comment>();
			while (rs.next()) {
				comment = new Comment();
				comment.setProPkid(rs.getInt("proPkid"));
				comment.setCommentDes(rs.getString("commentDes"));
				comment.setCommentTime(rs.getString("commentTime"));
				comment.setUserPkid(rs.getInt("userPkid"));
				commentList.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstm, rs);
		}
		return commentList;
	}

	@Override
	public List<Comment> findAllComment() {
		List<Comment> commentList = null;
		Comment comment = null;
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from comment order by commentTime desc ";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			commentList = new ArrayList<Comment>();
			while (rs.next()) {
				comment = new Comment();
				comment.setProPkid(rs.getInt("proPkid"));
				comment.setCommentDes(rs.getString("commentDes"));
				comment.setCommentTime(rs.getString("commentTime"));
				comment.setUserPkid(rs.getInt("userPkid"));
				commentList.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstm, rs);
		}
		return commentList;
	}

	@Override
	public boolean addComment(int proId, String commentDes, int userId, String commentTime) {
		boolean flag = false;
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		int rs = 0;
		String sql = "insert into comment (proPkid,commentDes,userPkid,commentTime) VALUE (?,?,?,?)  ";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, proId);
			pstm.setString(2, commentDes);
			pstm.setInt(3, userId);
			pstm.setString(4, commentTime);
			rs = pstm.executeUpdate();
			if (rs == 1) {
				flag = true;
			}
			System.out.println(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return flag;
	}

	@Override
	public int getCommentPkid(int proId, String commentDes, int userId, String commentTime) {
		int commentPkid = 0;
		String sql = "select * from comment where proPkId=? and commentDes=? and userPkid=? and commentTime=?";
//		Comment comment = DaoUtils.getListBySql(Comment.class, sql, proId, commentDes, userId, commentTime).get(0);
//		commentPkid = comment.getCommentPkid();
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, proId);
			pstm.setString(2, commentDes);
			pstm.setInt(3, userId);
			pstm.setString(4, commentTime);
			rs = pstm.executeQuery();
			while (rs.next()) {
				commentPkid = rs.getInt("commentPkid");
			}
			System.out.println(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstm, rs);
		}
		return commentPkid;
	}

	@Override
	public boolean updateOrder(int commentPkid, int orderPkid) {
		boolean flag = false;
		Connection conn = DBConn.getDBConn();
		PreparedStatement pstm = null;
		int rs = 0;
		String sql = "update orderList set commentPkid=? where orderPkid=? ";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, commentPkid);
			pstm.setInt(2, orderPkid);
			rs = pstm.executeUpdate();
			if (rs == 1) {
				flag = true;
			}
			System.out.println(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return flag;
	}

}
