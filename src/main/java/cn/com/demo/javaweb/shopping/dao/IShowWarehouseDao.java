package cn.com.demo.javaweb.shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.com.demo.javaweb.shopping.entity.toshow.ShowWarehouse;

public interface IShowWarehouseDao {

	@Select({ "SELECT *  " + "FROM warehouse  " + "INNER JOIN color ON warehouse.colorId = color.colorPkid "
			+ "INNER JOIN size ON warehouse.sizeId = size.sizePkid "
			+ "INNER JOIN product ON product.id = warehouse.proId " + "Left JOIN img ON img.proId = product.id "
			+ "ORDER BY product.id " })
	public List<ShowWarehouse> getAllShowWarehouse();

	@Select({ "SELECT *  " + "FROM warehouse  " + "INNER JOIN color ON warehouse.colorId = color.colorPkid "
			+ "INNER JOIN size ON warehouse.sizeId = size.sizePkid "
			+ "INNER JOIN product ON product.id = warehouse.proId " + "Left JOIN img ON img.proId = product.id "
			+ "ORDER BY product.id " + "limit #{param1},#{param2}" })
	public List<ShowWarehouse> getAllShowWarehouseByPage(int index, int pageSize);

}
