package cn.com.demo.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.demo.javaweb.shopping.entity.Admin;
import cn.com.demo.javaweb.shopping.entity.User;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowShopCar;
import cn.com.demo.javaweb.shopping.service.IIndexService;

@Controller
public class IndexController {
	@Autowired
	private IIndexService indexService;

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

}
