package cn.com.single.service.Impl;

import java.util.List;

import cn.com.single.DAO.ProCommentDAO;
import cn.com.single.DAO.Impl.ProCommentDAOImpl;
import cn.com.single.entity.Comment;
import cn.com.single.service.ProCommentService;

public class ProCommentServiceImpl implements ProCommentService {
	private ProCommentDAO commentDAO = new ProCommentDAOImpl();

	@Override
	public int commentCount(int proId) {
		return this.commentDAO.commentCount(proId);
	}

	@Override
	public List<Comment> findByProPkid(int proPkid) {
		return this.commentDAO.findByProPkid(proPkid);
	}

	@Override
	public boolean addComment(int proId, String commentDes, int userId, String commentTime) {
		// TODO Auto-generated method stub
		return this.commentDAO.addComment(proId, commentDes, userId, commentTime);
	}

	@Override
	public int getCommentPkid(int proId, String commentDes, int userId, String commentTime) {
		// TODO 自动生成的方法存根
		return this.commentDAO.getCommentPkid(proId, commentDes, userId, commentTime);
	}

	@Override
	public boolean updateOrder(int commentPkid, int orderPkid) {
		// TODO 自动生成的方法存根
		return this.commentDAO.updateOrder(commentPkid, orderPkid);
	}

}
