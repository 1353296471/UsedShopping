package cn.com.demo.javaweb.shopping.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.com.demo.javaweb.shopping.entity.ShopCar;

public interface IShopCarDao {
	@Select({ "select * from shopCar where userId = #{userId}" })
	public List<ShopCar> getShopCarById(int userId);

	@Select({ "select * from shopCar where userId = #{param1} and warehouseId = #{param2}" })
	public ShopCar getShopCar(int userId, int warehouseId);

	@Insert({ "insert into shopCar (userId , warehouseId , num) values (#{userId},#{warehouseId},#{num})" })
	public boolean insertShopCar(ShopCar shopcar);

	@Update({ "update shopCar set num = num - #{num} where userId = #{userId} and warehouseId = #{warehouseId}" })
	public boolean removeNumShopCar(ShopCar shopcar);

	@Delete({ "delete from shopCar where userId = #{userId} and warehouseId = #{warehouseId}" })
	public boolean deleteShopCar(ShopCar shopcar);

	@Delete({ "delete from shopCar where userId = #{param2.userId} and warehouseId = #{param2.warehouseId}" })
	public boolean deleteShopCarByConn(Connection conn, ShopCar shopcar) throws SQLException;

	@Select({ "select count(id) from shopCar where userId = #{param1} and warehouseId = #{param2}" })
	public boolean queryShopCar(int userId, int warehouseId);

	@Update({ "update shopCar set num = num + #{num} where userId = #{userId} and warehouseId = #{warehouseId}" })
	public boolean addNumShopCar(ShopCar shopcar);

}
