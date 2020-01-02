package cn.com.single.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.single.entity.Product;
import cn.com.single.service.ProductService;
import cn.com.single.service.Impl.ProductServiceImpl;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int proId = Integer.parseInt((String) request.getSession().getAttribute("proId"));
		List<Product> productList = this.productService.findByProId(proId);
		String proName = this.productService.findProName(proId);
		double price = this.productService.findPrice(proId);
		request.setAttribute("productList", productList);
		request.setAttribute("proName", proName);
		request.setAttribute("price", price);
		request.getRequestDispatcher("/WEB-INF/jsp/proSingle.jsp").forward(request, response);
	}

}
