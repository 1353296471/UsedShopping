package cn.com.demo.javaweb.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.demo.javaweb.shopping.entity.Admin;
import cn.com.demo.javaweb.shopping.entity.User;

public interface ILoginService {
	public boolean isRight(User user);

	public User getUser(User user);

	public boolean isRight(Admin admin);

	public Admin getAdmin(Admin admin);

	public void getCodeImg(HttpServletRequest request, HttpServletResponse response);
}
