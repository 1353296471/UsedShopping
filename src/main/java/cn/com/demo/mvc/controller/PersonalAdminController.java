package cn.com.demo.mvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.demo.javaweb.shopping.entity.toshow.Page;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowOrderList;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowProductAdmin;
import cn.com.demo.javaweb.shopping.service.IPersonalAdminService;

@Controller
public class PersonalAdminController {

	@Autowired
	private IPersonalAdminService personalAdminService;

	private int pageSize = 3;

	@RequestMapping("/showAllOrderList/{pageNo}")
	public ModelAndView showOrderList(@PathVariable("pageNo") int pageNo) {
		ModelAndView model = new ModelAndView();
		model.setViewName("orderList");
		List<ShowOrderList> items = personalAdminService.getAllShowOrderListsByPage(pageNo, pageSize);
		Page page = new Page(pageNo, personalAdminService.getOrderMaxPage(pageSize));
		model.addObject("items", items);
		model.addObject("page", page);
		return model;
	}

	@RequestMapping("/toProsPage/{pageNo}")
	public ModelAndView toProsPage(@PathVariable("pageNo") int pageNo) {
		ModelAndView model = new ModelAndView();
		model.setViewName("proList");
		List<ShowProductAdmin> items = personalAdminService.getAllShowProductAdminByPage(pageNo, pageSize);
		Page page = new Page(pageNo, personalAdminService.getProMaxPage(pageSize));
		model.addObject("items", items);
		model.addObject("page", page);
		return model;
	}

	@ResponseBody
	@RequestMapping("/toSendOrder/{orderPkid}")
	public Boolean toSendOrder(@PathVariable("orderPkid") int orderPkid) {
		boolean falg = false;
		falg = personalAdminService.toSendOrder(orderPkid);
		return falg;
	}

	@ResponseBody
	@RequestMapping("/getShowProductAdmin/{proId}")
	public ShowProductAdmin getShowProductAdmin(@PathVariable("proId") int proId) {
		ShowProductAdmin pro = personalAdminService.getShowProductAdmin(proId);
		return pro;
	}

	@ResponseBody
	@RequestMapping("/updateShowProductAdmin")
	public Boolean updateShowProductAdmin(@RequestBody @Valid ShowProductAdmin showProductAdmin) {
		return personalAdminService.updateShowProductAdmin(showProductAdmin);
	}

	@ResponseBody
	@RequestMapping("/toDeletePro/{proId}")
	public Boolean toDeletePro(@PathVariable("proId") int proId) {
		boolean falg = false;
		falg = personalAdminService.toDeletePro(proId);
		return falg;
	}

}
