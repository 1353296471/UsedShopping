package cn.com.single.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.single.service.WareHouseService;
import cn.com.single.service.Impl.WarehouseServiceImpl;

public class WarehouseNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WareHouseService warehouseService = new WarehouseServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int proId = Integer.parseInt((String) request.getSession().getAttribute("proId"));
		String sizeType = request.getParameter("sizeType");
		String colorType = request.getParameter("colorType");
		int sizePkid = this.warehouseService.findSize(sizeType);
		int colorPkid = this.warehouseService.findColor(colorType);
		int num = this.warehouseService.findNum(proId, colorPkid, sizePkid);
		request.setAttribute("wareHouseNum", num);
		request.getRequestDispatcher("WEB-INF/jsp/wareHouseNum.jsp").forward(request, response);
	}

}
