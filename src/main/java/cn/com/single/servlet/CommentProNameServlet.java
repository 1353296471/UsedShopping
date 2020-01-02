package cn.com.single.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.single.service.ProductService;
import cn.com.single.service.Impl.ProductServiceImpl;

public class CommentProNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService proservice = new ProductServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int proId = Integer.parseInt(request.getParameter("proId"));
		request.setCharacterEncoding("UTF-8");
		String proName = this.proservice.findProName(proId);

		request.setAttribute("proName", proName);

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.getRequestDispatcher("/WEB-INF/jsp/commentProName.jsp").forward(request, response);
	}

}
