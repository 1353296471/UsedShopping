package cn.com.demo.javaweb.shopping.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.com.demo.javaweb.shopping.entity.OrderList;

public interface IOrderDao {
	@Insert({
			"insert into orderList (warehouseId,receivePkid,orderConditionPkid,orderTime,proNum) values (#{warehouseId},#{receivePkid},#{orderConditionPkid},#{orderTime},#{proNum}) " })
	public boolean add(OrderList order);

	@Select({ "select * from orderList where receivePkid = #{receivePkid}" })
	public List<OrderList> getOrders(int receivePkid);

	@Select({ "select * from orderList where receivePkid = #{param1} and warehouseId = #{param2}" })
	public OrderList getOrder(int receivePkid, int warehouseId);

	@Insert({
			"insert into orderList (warehouseId,receivePkid,orderConditionPkid,orderTime,proNum) values (#{param2.warehouseId},#{param2.receivePkid},#{param2.orderConditionPkid},#{param2.orderTime},#{param2.proNum}) " })
	public boolean addByConn(Connection conn, OrderList order) throws SQLException;

	@Update({ "update orderList set orderConditionPkid = 2 where orderPkid = #{orderPkid}" })
	public boolean sendOrder(int orderPkid);

}
