package cn.com.demo.mvc.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.demo.javaweb.shopping.entity.Receive;
import cn.com.demo.javaweb.shopping.entity.ShopCar;
import cn.com.demo.javaweb.shopping.entity.User;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowShopCar;
import cn.com.demo.javaweb.shopping.service.ICheckoutService;
import cn.com.demo.javaweb.shopping.service.IIndexService;
import cn.com.demo.javaweb.shopping.service.IPayService;

@Controller
public class PayController {
	@Autowired
	private IIndexService indexService;

	@Autowired
	private ICheckoutService checkoutService;

	@Autowired
	private IPayService payService;

	@RequestMapping("/pay")
	public ModelAndView pay(HttpSession session, Receive receive) {
		System.out.println(receive);
		ModelAndView model = new ModelAndView();
		model.setViewName("msg");
		String msg = "";
		User user = (User) session.getAttribute("user");
		String[] warehouseIds = (String[]) session.getAttribute("warehouseIds");
		System.out.println(Arrays.toString(warehouseIds));
		List<ShowShopCar> itemList = indexService.getShopCar(user.getId());
//		double price = checkoutService.getPrice(warehouseIds, itemList);
		List<ShopCar> shopCars = checkoutService.getShopCars(warehouseIds, itemList, user.getId());
		if (payService.payShopCars(shopCars, receive)) {
			msg = "恭喜您支付成功，请继续浏览商品！";
		} else {
			msg = "支付失败，请重新尝试！";
		}
		model.addObject("msg", msg);
		return model;
	}

	@RequestMapping("/singlePay")
	public ModelAndView singlePay(HttpSession session, Receive receive) {
		System.out.println(receive);
		ModelAndView model = new ModelAndView();
		model.setViewName("msg");
		String msg = "";
		User user = (User) session.getAttribute("user");
		int warehouseId = (int) session.getAttribute("warehouseId");
		int num = (int) session.getAttribute("num");
//		double price = checkoutService.getPrice(Integer.parseInt(warehouseId), num);
		if (payService.paySingle(user, warehouseId, num, receive)) {
			msg = "恭喜您支付成功，请继续浏览商品！";
		} else {
			msg = "支付失败，请重新尝试！";
		}
		model.addObject("msg", msg);
		return model;
	}

}
