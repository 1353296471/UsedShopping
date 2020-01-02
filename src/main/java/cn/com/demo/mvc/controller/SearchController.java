package cn.com.demo.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.search.servlet.SearchServlet;

@Controller
public class SearchController {
	@RequestMapping("/searchServlet")
	public void commentCountServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SearchServlet s = new SearchServlet();
		System.out.println("searchServlet");
		s.service(request, response);
	}
}
