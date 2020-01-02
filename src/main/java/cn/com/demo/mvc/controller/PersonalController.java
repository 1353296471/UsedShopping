package cn.com.demo.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.demo.javaweb.shopping.entity.User;
import cn.com.demo.javaweb.shopping.entity.toshow.Page;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowOrderList;
import cn.com.demo.javaweb.shopping.service.IPersonalService;

@Controller
public class PersonalController {

	@Autowired
	private IPersonalService personalService;

	private int pageSize = 3;

	@RequestMapping("/showOrderList/{pageNo}")
	public ModelAndView showOrderList(HttpSession session, @PathVariable("pageNo") int pageNo) {
		ModelAndView model = new ModelAndView();
		model.setViewName("orderList");
		User user = (User) session.getAttribute("user");
		List<ShowOrderList> items = personalService.getShowOrderListsByPage(user.getId(), pageNo, pageSize);
		Page page = new Page(pageNo, personalService.getMaxPage(user.getId(), pageSize));
		model.addObject("items", items);
		model.addObject("page", page);
		return model;
	}

	@RequestMapping("/toCharge")
	public ModelAndView toCharge(HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("chargeMoney");
		User user = (User) session.getAttribute("user");
		double cash = 0;
		cash = personalService.getCash(user.getId());
		model.addObject("cash", cash);
		return model;
	}

	@ResponseBody
	@RequestMapping("/chargeMoney/{money}")
	public boolean chargeMoney(HttpSession session, @PathVariable("money") double money) {
		boolean flag = false;
		User user = (User) session.getAttribute("user");
		if (personalService.chargeMoney(user.getId(), money)) {
			flag = true;
		}
		return flag;
	}

}
