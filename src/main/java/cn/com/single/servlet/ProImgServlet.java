package cn.com.single.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.single.service.RelatedImgService;
import cn.com.single.service.Impl.RelatedUrlServiceImpl;

public class ProImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RelatedImgService relatedService = new RelatedUrlServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int proId = Integer.parseInt((String) request.getSession().getAttribute("proId"));
		String proImagUrl = this.relatedService.findProImag(proId);
		request.setAttribute("proImagUrl", proImagUrl);
		request.getRequestDispatcher("/WEB-INF/jsp/proImag.jsp").forward(request, response);
	}

}
