package cn.com.demo.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.com.demo.javaweb.shopping.entity.Catalog;
import cn.com.demo.javaweb.shopping.entity.User;
import cn.com.demo.javaweb.shopping.entity.toshow.Page;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowOrderList;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowProductAdmin;
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

	@RequestMapping("/showMyPosted/{pageNo}")
	public ModelAndView showMyPosted(HttpSession session, @PathVariable("pageNo") int pageNo) {
		ModelAndView model = new ModelAndView();
		User user = (User) session.getAttribute("user");
		model.setViewName("proList");
		List<ShowProductAdmin> items = personalService.getAllShowMyPostedByPage(pageNo, pageSize, user.getId());
		Page page = new Page(pageNo, personalService.getProMaxPage(pageSize, user.getId()));
		model.addObject("items", items);
		model.addObject("page", page);
		return model;
	}

	@RequestMapping("/showMySold/{pageNo}")
	public ModelAndView showMySold(HttpSession session, @PathVariable("pageNo") int pageNo) {
		ModelAndView model = new ModelAndView();
		User user = (User) session.getAttribute("user");
		int userId = user.getId();
		model.setViewName("orderListSold");
		List<ShowOrderList> items = personalService.getAllShowMySoldByPage(pageNo, pageSize, userId);
		Page page = new Page(pageNo, personalService.getMySoldMaxPage(pageSize, userId));
		model.addObject("items", items);
		model.addObject("page", page);
		return model;
	}

	@RequestMapping("/toReleasePro")
	public ModelAndView toReleasePro(HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("releasePro");
		List<Catalog> types = personalService.getAllCatalogs();
		model.addObject("types", types);
		return model;
	}

	@ResponseBody
	@RequestMapping("/releasePro")
	public Boolean releaseProduct(HttpSession session, ShowProductAdmin showProductAdmin, MultipartFile img)
			throws Exception {

		User user = (User) session.getAttribute("user");
		int userId = user.getId();
		System.out.println("userId = " + userId);
		showProductAdmin.setUserId(userId);
		System.out.println(showProductAdmin);
		return personalService.releaseProduct(showProductAdmin, img);
	}

}
