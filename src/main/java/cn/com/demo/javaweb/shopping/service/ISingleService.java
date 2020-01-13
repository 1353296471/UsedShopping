package cn.com.demo.javaweb.shopping.service;

import java.util.List;

import cn.com.demo.javaweb.shopping.entity.ShopCar;
import cn.com.demo.javaweb.shopping.entity.Warehouse;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowShopCar;

public interface ISingleService {
	public double getPrice(String[] warehouseIds, List<ShowShopCar> itemList);

	public double getMoney(int userId);

	public List<ShopCar> getShopCars(String[] warehouseIds, List<ShowShopCar> itemList, int userId);

	public double getPrice(String warehouseId, List<ShowShopCar> itemList);

	public double getPrice(Integer warehouseId, Integer num);

	public Warehouse getWarehouseById(int warehouseId);

	public Integer getWarehouseNumById(int warehouseId);

	public Integer getWarehouseIdByProId(int proId);
}
