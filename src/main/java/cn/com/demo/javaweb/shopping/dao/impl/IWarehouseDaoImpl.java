package cn.com.demo.javaweb.shopping.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import cn.com.demo.javaweb.shopping.dao.IWarehouseDao;
import cn.com.demo.javaweb.shopping.db.DaoUtils;
import cn.com.demo.javaweb.shopping.entity.Warehouse;

@Repository
public class IWarehouseDaoImpl implements IWarehouseDao {

	@Override
	public boolean remove(int warehouseId, int num) {
		String sql = "update warehouse set num = num - ? where id = ?";
		return DaoUtils.insertOrUpdate(sql, num, warehouseId);
	}

	@Override
	public boolean add(int warehouseId, int num) {
		String sql = "update warehouse set num = num + ? where id = ?";
		return DaoUtils.insertOrUpdate(sql, num, warehouseId);
	}

	@Override
	public boolean removeByConn(Connection conn, int warehouseId, int num) throws SQLException {
		String sql = "update warehouse set num = num - ? where id = ?";
		return DaoUtils.insertOrUpdate(conn, sql, num, warehouseId);
	}

	@Override
	public Warehouse getWarehouse(int id) {
		String sql = "select * from warehouse where id = ?";
		return DaoUtils.getListBySql(Warehouse.class, sql, id).get(0);
	}

	@Override
	public Integer getWarehouseNum(int id) {
		// TODO 自动生成的方法存根
		return null;
	}

}
