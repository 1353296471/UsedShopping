package cn.com.demo.javaweb.shopping.service.imp;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcz.register.utils.MailUtil;

import cn.com.demo.javaweb.shopping.dao.IUserDao;
import cn.com.demo.javaweb.shopping.entity.User;
import cn.com.demo.javaweb.shopping.service.IRegisterService;

@Service
public class IRegisterServiceImpl implements IRegisterService {
	@Autowired // 自动装配类 装配之前需要在配置中包含扫面的包，且需要在类名上加上注解
	private IUserDao userDao;

	// 验证码由哪些字符组成
	char[] codeSequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz23456789".toCharArray();
	private int codeCount = 4;

	@Override
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public boolean isCheck(String email) {
		return userDao.isCheck(email);
	}

	@Override
	public String createCode() {
		StringBuffer randomCode;
		randomCode = new StringBuffer();
		Random random = null;
		random = new Random();
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字
			String strRand = null;
			strRand = String.valueOf(codeSequence[random.nextInt(36)]);

			// 把正在产生的随机字符放入到 StringBuffer 中
			randomCode.append(strRand);
		}
		return randomCode.toString();
	}

	@Override
	public boolean sendEmailCode(String email, String code) {
		boolean falg = false;
		if (email != null) {
			new Thread(new MailUtil(email, code)).start();
			falg = true;
		}
		return falg;
	}

	public static void main(String[] args) {
		System.out.println(new IRegisterServiceImpl().createCode());
	}

}
