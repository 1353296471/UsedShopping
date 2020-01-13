package cn.com.demo.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.demo.javaweb.shopping.entity.Admin;
import cn.com.demo.javaweb.shopping.entity.User;
import cn.com.demo.javaweb.shopping.entity.toshow.Page;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowProduct;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowShopCar;
import cn.com.demo.javaweb.shopping.service.IIndexService;

@Controller
public class IndexController {
	@Autowired
	private IIndexService indexService;

	private int pageSize = 4;

	@RequestMapping("/showProduct")
	public ModelAndView showProduct() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		model.addObject("showProducts", indexService.getShowProduct());
		return model;
	}

	@RequestMapping("/showShopCar")
	public ModelAndView showShopCar(HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("shopCarItemList");
		User user = (User) session.getAttribute("user");
		List<ShowShopCar> itemList = indexService.getShopCar(user.getId());
		model.addObject("itemList", itemList);
		model.addObject("itemSize", itemList.size());
		return model;
	}

	@ResponseBody
	@RequestMapping("/addShopCarItem/{warehouseId}")
	public String addShopCarItem(@PathVariable("warehouseId") Integer warehouseId, HttpSession session)
			throws Exception {
		String msg = null;
		System.out.println(warehouseId);

		User user = (User) session.getAttribute("user");
		if (warehouseId == 0) {
			msg = "操作失败！";
			return msg;
		}
		if (indexService.addShopCarItem(user.getId(), warehouseId, 1)) {
			msg = "操作成功！";
		} else {
			msg = "操作失败！";
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping("/addShopCarItem/{warehouseId}/{num}")
	public String addShopCarItem(@PathVariable("warehouseId") Integer warehouseId, @PathVariable("num") Integer num,
			HttpSession session) throws Exception {
		String msg = null;
		System.out.println(warehouseId);

		User user = (User) session.getAttribute("user");
		if (warehouseId == 0) {
			msg = "操作失败！";
			return msg;
		}
		if (indexService.addShopCarItem(user.getId(), warehouseId, num)) {
			msg = "操作成功！";
		} else {
			msg = "操作失败！";
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping("/removeShopCarItem/{warehouseId}")
	public String removeShopCarItem(@PathVariable("warehouseId") Integer warehouseId, HttpSession session)
			throws Exception {
		String msg = null;
		User user = (User) session.getAttribute("user");
		if (indexService.removeShopCarItem(user.getId(), warehouseId, 1)) {
			msg = "操作成功！";
		} else {
			msg = "操作失败！";
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping("/deleteShopCarItem/{warehouseId}")
	public String deleteShopCarItem(@PathVariable("warehouseId") Integer warehouseId, HttpSession session)
			throws Exception {
		String msg = null;
		User user = (User) session.getAttribute("user");
		if (indexService.deleteShopCarItem(user.getId(), warehouseId)) {
			msg = "操作成功！";
		} else {
			msg = "操作失败！";
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping("/isLogin")
	public String isLogin(HttpSession session) throws IOException {
		User user = (User) session.getAttribute("user");
		String msg = null;
		if (user != null) {
//			response.getWriter().print("isLogin");
			msg = "isLogin";
		} else {
//			response.getWriter().print("noLogin");
			msg = "noLogin";
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping("/isAdmin")
	public String isAdmin(HttpSession session) throws IOException {
		Admin admin = (Admin) session.getAttribute("admin");
		String msg = null;
		if (admin != null) {
			msg = "isAdmin";
		} else {
			msg = "noAdmin";
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping("/getStatus")
	public String getStatus(HttpSession session) throws IOException {
		String status = (String) session.getAttribute("status");
		return status;
	}

	@RequestMapping("/getUser")
	@ResponseBody
	public User getUser(HttpSession session, HttpServletResponse response) throws IOException {
		User user = (User) session.getAttribute("user");
		return user;
	}

	@RequestMapping("/getAdmin")
	@ResponseBody
	public Admin getAdmin(HttpSession session, HttpServletResponse response) throws IOException {
		Admin admin = (Admin) session.getAttribute("admin");
		return admin;
	}

	@RequestMapping("/tosingle/{proId}")
	public ModelAndView tosingle(@PathVariable("proId") String proId, HttpSession session) {
		ModelAndView model = new ModelAndView();
		// 根路径：/single.html 相对路径： single.html
		model.setViewName("redirect:/singleUsed.html");
		session.setAttribute("proId", proId);
		return model;
	}

	@RequestMapping("/logOut")
	public ModelAndView logOut(HttpSession session) {
		ModelAndView model = new ModelAndView();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			session.removeAttribute("user");
		}
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin != null) {
			session.removeAttribute("admin");
		}
		model.setViewName("redirect:/login.html");
		return model;
	}

	@RequestMapping("/searchProName")
	public ModelAndView commentCountServlet(HttpSession session, @RequestParam("proName") String proName)
			throws Exception {
		List<ShowProduct> proList = indexService.searchProName(proName, 1, pageSize);
		ModelAndView model = new ModelAndView();
		model.setViewName("searchResultUsed");
		model.addObject("searchPro", proList);

		Page page = new Page();
		page = new Page(1, indexService.getProMaxPageProName(proName, pageSize));
		model.addObject("page", page);

		session.setAttribute("searchProName", proName);
		session.removeAttribute("searchCatalogId");
		return model;
	}

	@RequestMapping("/searchType/{catalogId}")
	public ModelAndView searchType(HttpSession session, @PathVariable("catalogId") Integer catalogId) {
		List<ShowProduct> proList = indexService.searchType(catalogId, 1, pageSize);
		ModelAndView model = new ModelAndView();
		model.setViewName("searchResultUsed");
		model.addObject("searchPro", proList);
		Page page = new Page();
		page = new Page(1, indexService.getProMaxPageType(catalogId, pageSize));
		model.addObject("page", page);

		session.setAttribute("searchCatalogId", catalogId);
		session.removeAttribute("searchProName");
		return model;
	}

	@RequestMapping("/toSearchPage/{pageNo}")
	public ModelAndView toSearchPage(HttpSession session, @PathVariable("pageNo") Integer pageNo) {
		Integer catalogId = (Integer) session.getAttribute("searchCatalogId");
		String proName = (String) session.getAttribute("searchProName");

		List<ShowProduct> proList = new ArrayList<ShowProduct>();
		Page page = new Page();

		if (proName != null) {
			proList = indexService.searchProName(proName, pageNo, pageSize);
			page = new Page(pageNo, indexService.getProMaxPageProName(proName, pageSize));
		} else {
			proList = indexService.searchType(catalogId, pageNo, pageSize);
			page = new Page(pageNo, indexService.getProMaxPageType(catalogId, pageSize));
		}

		ModelAndView model = new ModelAndView();
		model.setViewName("searchResultPageUsed");

		model.addObject("page", page);
		model.addObject("searchPro", proList);
		return model;
	}

	@RequestMapping("/initSearch")
	public ModelAndView initSearch() {
		ModelAndView model = new ModelAndView();
		model.setViewName("searchUsed");
		return model;
	}
}
