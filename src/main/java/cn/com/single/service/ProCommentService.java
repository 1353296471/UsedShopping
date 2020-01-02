package cn.com.single.service;

import java.util.List;

import cn.com.single.entity.Comment;

public interface ProCommentService {
	public List<Comment> findByProPkid(int proPkid);

	public int commentCount(int proId);

	public boolean addComment(int proId, String commentDes, int userId, String commentTime);

	public int getCommentPkid(int proId, String commentDes, int userId, String commentTime);

	public boolean updateOrder(int commentPkid, int orderPkid);
}
