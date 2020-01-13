package cn.com.demo.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.demo.javaweb.shopping.entity.User;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowShopCar;
import cn.com.demo.javaweb.shopping.service.ICheckoutService;
import cn.com.demo.javaweb.shopping.service.IIndexService;
import cn.com.demo.javaweb.shopping.service.ILoginService;

@Controller
public class CheckoutController {
	@Autowired
	private ILoginService loginService;

	@Autowired
	private IIndexService indexService;

	@Autowired
	private ICheckoutService checkoutService;

	@RequestMapping("/updateBuyList")
	public ModelAndView updateBuyList(HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("toBuyList");
		User user = (User) session.getAttribute("user");
		List<ShowShopCar> itemList = indexService.getShopCar(user.getId());
		model.addObject("itemList", itemList);
		model.addObject("itemSize", itemList.size());
		return model;
	}

	@RequestMapping("/toPay")
	public ModelAndView toPay(HttpSession session, @RequestParam("warehouseIds") String[] warehouseIds) {
		ModelAndView model = new ModelAndView();

		User user = (User) session.getAttribute("user");
		user = loginService.getUser(user);
		session.setAttribute("warehouseIds", warehouseIds);
		List<ShowShopCar> itemList = indexService.getShopCar(user.getId());
		double price = checkoutService.getPrice(warehouseIds, itemList);
		if (price > user.getMoney()) {
			model.setViewName("msg");
			model.addObject("type", "price");
			model.addObject("msg", "您的账户余额不足，请先充值！");
		} else {
			model.setViewName("pay");
			model.addObject("price", price);
			model.addObject("userId", user.getId());
		}
		return model;
	}

	@RequestMapping("/toSinglePay/{warehouseId}/{num}")
	public ModelAndView toSinglePay(HttpSession session, @PathVariable("warehouseId") Integer warehouseId,
			@PathVariable("num") Integer num) {
		ModelAndView model = new ModelAndView();
		User user = (User) session.getAttribute("user");
		user = loginService.getUser(user);
		session.setAttribute("warehouseId", warehouseId);
		session.setAttribute("num", num);
		double price = checkoutService.getPrice(warehouseId, num);
		if (price > user.getMoney()) {
			model.setViewName("msg");
			model.addObject("type", "price");
			model.addObject("msg", "您的账户余额不足，请先充值！");
		} else {
			model.setViewName("singlePay");
			model.addObject("price", price);
			model.addObject("userId", user.getId());
		}
		return model;
	}

	@ResponseBody
	@RequestMapping("/getPrice/{warehouseIds}")
	public String getPrice(HttpSession session, @PathVariable("warehouseIds") String[] warehouseIds) {
		User user = (User) session.getAttribute("user");
		session.setAttribute("warehouseIds", warehouseIds);
		List<ShowShopCar> itemList = indexService.getShopCar(user.getId());
		double price = checkoutService.getPrice(warehouseIds, itemList);
		return String.valueOf(price);
	}

	@ResponseBody
	@RequestMapping("/getWarehouseNum/{warehouseId}")
	public Integer getWarehouseNum(@PathVariable("warehouseId") Integer warehouseId) {

		return checkoutService.getWarehouseNumById(warehouseId);
	}

	@ResponseBody
	@RequestMapping("/sureMoney/{warehouseId}/{num}")
	public Boolean sureMoney(HttpSession session, @PathVariable("warehouseId") Integer warehouseId,
			@PathVariable("num") Integer num) {
		Boolean flag = false;
		User user = (User) session.getAttribute("user");
		double price = checkoutService.getPrice(warehouseId, num);
		if (price <= user.getMoney()) {
			flag = true;
		}
		return flag;
	}
}
