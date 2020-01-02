package cn.com.demo.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.demo.javaweb.shopping.entity.Admin;
import cn.com.demo.javaweb.shopping.entity.User;
import cn.com.demo.javaweb.shopping.service.ILoginService;
import cn.com.demo.javaweb.shopping.service.servlet.ValidateColorServlet;

@Validated
@Controller
public class LoginController {
	@Autowired // 自动装配类 装配之前需要在配置中包含扫面的包，且需要在类名上加上注解
	private ILoginService loginService;

	/**
	 * 登录方法的简单测试
	 * 
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/login")
	public boolean login(@RequestBody @Valid User user, BindingResult result, HttpSession session) {
		System.out.println(user);
		boolean flag = false;
		if (result.getErrorCount() > 0) {
			System.out.println("出错了!");
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
		} else if (!loginService.isRight(user)) {
			System.out.println(result);
		} else {
			session.setAttribute("user", loginService.getUser(user));
			session.setAttribute("status", "user");
			flag = true;
		}
		return flag;
	}

	@ResponseBody
	@RequestMapping("/loginAdmin")
	public boolean loginAdmin(@RequestBody @Valid Admin user, BindingResult result, HttpSession session) {
		System.out.println(user);
		boolean flag = false;
		if (result.getErrorCount() > 0) {
			System.out.println("出错了!");
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
		} else if (!loginService.isRight(user)) {
			System.out.println(result);
		} else {
			session.setAttribute("admin", loginService.getAdmin(user));
			session.setAttribute("status", "admin");
			flag = true;
		}
		return flag;
	}

	@RequestMapping("/getCodeImg")
	public void getCodeImg(HttpServletRequest request, HttpServletResponse response) {
		loginService.getCodeImg(request, response);
	}

	@ResponseBody
	@RequestMapping("/checkValidCode/{validCode}")
	public boolean checkVaildCode(HttpSession session, @PathVariable("validCode") String validCode) {
		boolean flag = false;
		String code = (String) session.getAttribute(ValidateColorServlet.CHECK_CODE_KEY);
		if (code.equalsIgnoreCase(validCode)) {
			flag = true;
		}
		return flag;
	}

}
