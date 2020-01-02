package cn.com.demo.javaweb.shopping.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.com.demo.javaweb.shopping.entity.Warehouse;

public interface IWarehouseDao {
	@Update({ "update warehouse set num = num - #{param2} where id = #{param1}" })
	public boolean remove(int proId, int num);

	@Update({ "update warehouse set num = num + #{param2} where id = #{param1}" })
	public boolean add(int proId, int num);

	@Update({ "update warehouse set num = num - #{param3} where id = #{param2}" })
	public boolean removeByConn(Connection conn, int proId, int num) throws SQLException;

	@Select({ "select * from warehouse where id = #{id}" })
	public Warehouse getWarehouse(int id);

	@Select({ "select num from warehouse where id = #{id}" })
	public Integer getWarehouseNum(int id);
}
