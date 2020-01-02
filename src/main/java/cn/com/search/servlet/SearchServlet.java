package cn.com.search.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.search.entity.Product1;
import cn.com.search.service.SearchService;
import cn.com.search.service.impl.SearchServiceImpl;

public class SearchServlet extends HttpServlet {
	private SearchService searchService = new SearchServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String proName = req.getParameter("proName");

		List<Product1> proList = this.searchService.searchProduct(proName);
		req.setAttribute("searchPro", proList);
		req.getRequestDispatcher("/WEB-INF/jsp/searchResult.jsp").forward(req, resp);
	}

}
