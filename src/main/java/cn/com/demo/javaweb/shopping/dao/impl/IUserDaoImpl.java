package cn.com.demo.javaweb.shopping.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import cn.com.demo.javaweb.shopping.dao.IUserDao;
import cn.com.demo.javaweb.shopping.db.DaoUtils;
import cn.com.demo.javaweb.shopping.entity.User;

@Repository
//为什么 @Repository 只能标注在 DAO 类上呢？这是因为该注解的作用不只是将类识别为Bean，
//同时它还能将所标注的类中抛出的数据访问异常封装为 Spring 的数据访问异常类型。
//Spring本身提供了一个丰富的并且是与具体的数据访问技术无关的数据访问异常结构，
//用于封装不同的持久层框架抛出的异常，使得异常独立于底层的框架。
public class IUserDaoImpl implements IUserDao {

	@Override
	public boolean isRight(String email, String password) {
		// TODO 自动生成的方法存根
		String sql = "select * from user where email = ? and password = ?";
		return DaoUtils.executeQuery(sql, email, password);
	}

	@Override
	public boolean addUser(User user) {
		String sql = "insert into user (userName , password , email) values (?,?,?) ";
		return DaoUtils.insertOrUpdate(sql, user.getUserName(), user.getPassword(), user.getEmail());
	}

	@Override
	public User getUser(String email, String password) {
		String sql = "select * from user where email = ? and password = ?";
		return DaoUtils.getListBySql(User.class, sql, email, password).get(0);
	}

	@Override
	public User getUserById(int userId) {
		String sql = "select * from user where id = ?";
		return DaoUtils.getListBySql(User.class, sql, userId).get(0);
	}

	@Override
	public boolean payMoney(User user, double price) {
		String sql = "update user set money = money - ? where id = ?";
		return DaoUtils.insertOrUpdate(sql, price, user.getId());
	}

	@Override
	public boolean isCheck(String email) {
		String sql = "select * from user where email = ?";
		return DaoUtils.executeQuery(sql, email);
	}

	@Override
	public boolean chargeMoney(int userId, double money) {
		String sql = "update user set money = money + ? where id = ?";
		return DaoUtils.insertOrUpdate(sql, money, userId);
	}

	@Override
	public boolean payMoneyByConn(Connection conn, User user, double price) throws SQLException {
		String sql = "update user set money = money - ? where id = ?";
		return DaoUtils.insertOrUpdate(conn, sql, price, user.getId());
	}

}
