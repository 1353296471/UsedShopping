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
public class GetWareIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WareHouseService wareService = new WarehouseServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String colorType = request.getParameter("colorType");
		String sizeType = request.getParameter("sizeType");
		int proId = Integer.parseInt((String) request.getSession().getAttribute("proId"));
		int wareId = this.wareService.findWareId(colorType, sizeType, proId);
		request.setAttribute("wareId", wareId);
		request.getRequestDispatcher("/WEB-INF/jsp/wareId.jsp").forward(request, response);

	}

}
