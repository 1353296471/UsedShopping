package cn.com.single.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.single.entity.Comment;
import cn.com.single.service.ProCommentService;
import cn.com.single.service.Impl.ProCommentServiceImpl;

public class ProCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProCommentService commentService = new ProCommentServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int proId = Integer.parseInt((String) request.getSession().getAttribute("proId"));
		List<Comment> commentList = this.commentService.findByProPkid(proId);
		request.setAttribute("commentList", commentList);
		request.getRequestDispatcher("/WEB-INF/jsp/proComment.jsp").forward(request, response);
	}

}
