package cn.com.demo.mvc.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.demo.javaweb.shopping.entity.User;
import cn.com.demo.javaweb.shopping.service.IRegisterService;

@Controller
public class RegisterController {

	@Autowired
	private IRegisterService registerService;

	@ResponseBody
	@RequestMapping("/register")
	public boolean register(@RequestBody @Valid User user, BindingResult result) {
		boolean flag = false;
		if (result.getErrorCount() > 0) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
		} else {
			if (registerService.addUser(user)) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 检查邮箱是否已经被注册
	 * 
	 * @param user
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/isCheck/{email}")
	public String isCheck(@PathVariable("email") String email) {
		String msg = "isCheck";
		if (!registerService.isCheck(email)) {
			msg = "notCheck";
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping("/isCode/{code}")
	public boolean isCode(@PathVariable("code") String code, HttpSession session) {
		boolean falg = false;
		String codeOfsession = (String) session.getAttribute("code");
		if (code.equalsIgnoreCase(codeOfsession)) {
			falg = true;
		}
		return falg;
	}

	@ResponseBody
	@RequestMapping("/sendCode/{email}")
	public boolean sendCode(@PathVariable("email") @Email String email, HttpSession session) {
		boolean falg = false;
		String code = registerService.createCode();
		if (registerService.sendEmailCode(email, code)) {
			session.setAttribute("code", code);
			falg = true;
		}
		return falg;
	}
}
