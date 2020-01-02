package cn.com.single.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.single.entity.Imags;
import cn.com.single.service.RelatedImgService;
import cn.com.single.service.Impl.RelatedUrlServiceImpl;

public class RelatedImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RelatedImgService relatedservice = new RelatedUrlServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int proId = Integer.parseInt((String) request.getSession().getAttribute("proId"));
		List<Imags> imgList = this.relatedservice.findImgUrls(proId);
		request.setAttribute("imgList", imgList);
		request.getRequestDispatcher("WEB-INF/jsp/relatedPro.jsp").forward(request, response);
	}

}
