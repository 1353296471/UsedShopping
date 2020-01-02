package cn.com.demo.javaweb.shopping.service.imp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.demo.javaweb.shopping.dao.IAdminDao;
import cn.com.demo.javaweb.shopping.dao.IUserDao;
import cn.com.demo.javaweb.shopping.entity.Admin;
import cn.com.demo.javaweb.shopping.entity.User;
import cn.com.demo.javaweb.shopping.service.ILoginService;
import cn.com.demo.javaweb.shopping.service.servlet.ValidateColorServlet;

@Service
public class ILoginServiceImpl implements ILoginService {
	@Autowired // 自动装配类 装配之前需要在配置中包含扫面的包，且需要在类名上加上注解
	private IUserDao userDao;

	@Autowired // 自动装配类 装配之前需要在配置中包含扫面的包，且需要在类名上加上注解
	private IAdminDao adminDao;

	@Override
	public boolean isRight(User user) {
		// TODO 自动生成的方法存根
		return userDao.isRight(user.getEmail(), user.getPassword());
	}

	@Override
	public void getCodeImg(HttpServletRequest request, HttpServletResponse response) {
		ValidateColorServlet v = new ValidateColorServlet();
		try {
			v.service(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public User getUser(User user) {
		// TODO 自动生成的方法存根
		return userDao.getUser(user.getEmail(), user.getPassword());
	}

	@Override
	public boolean isRight(Admin admin) {
		// TODO 自动生成的方法存根
		return adminDao.isRight(admin.getEmail(), admin.getPassword());
	}

	@Override
	public Admin getAdmin(Admin admin) {
		// TODO 自动生成的方法存根
		return adminDao.getAdmin(admin.getEmail(), admin.getPassword());
	}

}
