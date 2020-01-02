package cn.com.demo.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.single.servlet.AddCommentServlet;
import cn.com.single.servlet.CommentCountServlet;
import cn.com.single.servlet.CommentProNameServlet;
import cn.com.single.servlet.GetWareIdServlet;
import cn.com.single.servlet.ProCommentServlet;
import cn.com.single.servlet.ProImgServlet;
import cn.com.single.servlet.ProductDesServlet;
import cn.com.single.servlet.ProductServlet;
import cn.com.single.servlet.RelatedImgServlet;
import cn.com.single.servlet.WareHouseColorServlet;
import cn.com.single.servlet.WareHouseServlet;
import cn.com.single.servlet.WarehouseNumServlet;

@Controller
public class SingleController {

	@RequestMapping("/commentCountServlet")
	public void commentCountServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommentCountServlet s = new CommentCountServlet();
		s.service(request, response);
	}

	@RequestMapping("/proCommentServlet")
	public void proCommentServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProCommentServlet s = new ProCommentServlet();
		s.service(request, response);
	}

	@RequestMapping("/productDesServlet")
	public void productDesServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductDesServlet s = new ProductDesServlet();
		s.service(request, response);
	}

	@RequestMapping("/productServlet")
	public void productServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductServlet s = new ProductServlet();
		s.service(request, response);
	}

	@RequestMapping("/wareHouseColorServlet")
	public void wareHouseColorServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		WareHouseColorServlet s = new WareHouseColorServlet();
		s.service(request, response);
	}

	@RequestMapping("/warehouseNumServlet")
	public void warehouseNumServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		WarehouseNumServlet s = new WarehouseNumServlet();
		s.service(request, response);
	}

	@RequestMapping("/wareHouseServlet")
	public void wareHouseServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		WareHouseServlet s = new WareHouseServlet();
		s.service(request, response);
	}

	@RequestMapping("/relatedImgServlet")
	public void relatedImgServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatedImgServlet s = new RelatedImgServlet();
		s.service(request, response);
	}

	@RequestMapping("/proImgServlet")
	public void proImgServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProImgServlet s = new ProImgServlet();
		s.service(request, response);
	}

	@RequestMapping("/addCommentServlet")
	public void addCommentServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AddCommentServlet s = new AddCommentServlet();
		s.service(request, response);
	}

	@RequestMapping("/commentProNameServlet")
	public void commentProNameServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommentProNameServlet s = new CommentProNameServlet();
		s.service(request, response);
	}

	@RequestMapping("/getWareIdServlet")
	public void getWareIdServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		GetWareIdServlet s = new GetWareIdServlet();
		s.service(request, response);
	}
}
