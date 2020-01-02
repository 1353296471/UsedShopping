package cn.com.single.DAO;

import java.util.List;

import cn.com.single.entity.Comment;

public interface ProCommentDAO {
	public List<Comment> findAllComment();

	public List<Comment> findByProPkid(int proPkid);

	public int commentCount(int proId);

	public boolean addComment(int proId, String commentDes, int userId, String commentTime);

	public int getCommentPkid(int proId, String commentDes, int userId, String commentTime);

	public boolean updateOrder(int commentPkid, int orderPkid);
}
