package cn.com.demo.javaweb.shopping.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.com.demo.javaweb.shopping.entity.User;

public interface IUserDao {

	@Select({ "select count(id) from user where email = #{param1} and password = #{param2}" })
	public boolean isRight(String email, String password);

	@Insert({ "insert into user (userName , password , email) values (#{userName},#{password},#{email}) " })
	public boolean addUser(User user);

	@Update({ "update user set money = money + #{param2} where id = #{param1}" })
	public boolean chargeMoney(int userId, double money);

	@Select({ "select count(id) from user where email = #{email}" })
	public boolean isCheck(String email);

	@Update({ "update user set money = money - #{param2} where id = #{param1.id}" })
	public boolean payMoney(User user, double price);

	@Select({ "select * from user where email = #{param1} and password = #{param2}" })
	public User getUser(String email, String password);

	@Select({ "select * from user where id = #{id,jdbcType=INTEGER}" })
	public User getUserById(int userId);

	@Update({ "update user set money = money - #{param3} where id = #{param2.id}" })
	public boolean payMoneyByConn(Connection conn, User user, double price) throws SQLException;
}
