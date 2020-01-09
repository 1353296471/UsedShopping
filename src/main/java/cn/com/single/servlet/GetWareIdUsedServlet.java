package cn.com.single.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.single.service.WareHouseService;
import cn.com.single.service.Impl.WarehouseServiceImpl;

/**
 * Servlet implementation class GetWareIdServlet
 */
public class GetWareIdUsedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WareHouseService wareService = new WarehouseServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int proId = Integer.parseInt((String) request.getSession().getAttribute("proId"));
		int wareId = this.wareService.findWareId(proId);
		request.setAttribute("wareId", wareId);
		request.getRequestDispatcher("/WEB-INF/jsp/wareId.jsp").forward(request, response);

	}

}
