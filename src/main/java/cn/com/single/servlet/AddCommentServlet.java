package cn.com.single.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.demo.javaweb.shopping.entity.User;
import cn.com.single.service.ProCommentService;
import cn.com.single.service.Impl.ProCommentServiceImpl;

public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProCommentService proComService = new ProCommentServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("UTF-8");
		String commentDes = request.getParameter("commentDes");
		String commentTime = request.getParameter("commentTime");
		int proId = Integer.parseInt(request.getParameter("proId"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int userId = user.getId();

		boolean flag = this.proComService.addComment(proId, commentDes, userId, commentTime);
		int orderPkid = Integer.parseInt(request.getParameter("orderPkid"));
		System.out.println("orderPkid = " + orderPkid);
		int commentPkid = this.proComService.getCommentPkid(proId, commentDes, userId, commentTime);
		System.out.println("commentPkid = " + commentPkid);
		boolean bol = this.proComService.updateOrder(commentPkid, orderPkid);
		System.out.println(bol);

//		if (flag) {
//			request.setAttribute("msg", "评价成功，谢谢您的参与！");
//			request.getRequestDispatcher("/WEB-INF/jsp/boolean.jsp").forward(request, response);
//		} else {
//			request.setAttribute("msg", "评价失败！");
//			request.getRequestDispatcher("/WEB-INF/jsp/boolean.jsp").forward(request, response);
//		}
		String msg = "";
		if (flag) {
			msg = "评价成功，谢谢您的参与！";
		} else {
			msg = "评价失败！";
		}
		System.out.println(msg);
		response.getWriter().print(msg);

	}

}
