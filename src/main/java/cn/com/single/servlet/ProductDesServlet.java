package cn.com.single.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.single.service.ProDesService;
import cn.com.single.service.Impl.ProDesServiceImp;

public class ProductDesServlet extends HttpServlet {
	private ProDesService prodesService = new ProDesServiceImp();
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int proId = Integer.parseInt((String) request.getSession().getAttribute("proId"));
		List<String> describeList = this.prodesService.proDes(proId);
		request.setAttribute("describeList", describeList);
		request.getRequestDispatcher("/WEB-INF/jsp/ProDes.jsp").forward(request, response);

	}
}
