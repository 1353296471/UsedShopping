package cn.com.demo.javaweb.shopping.service;

import cn.com.demo.javaweb.shopping.entity.User;

public interface IRegisterService {
	public boolean addUser(User user);

	public boolean isCheck(String email);

	public String createCode();

	boolean sendEmailCode(String email, String code);
}
