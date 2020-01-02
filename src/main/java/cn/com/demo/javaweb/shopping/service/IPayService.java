package cn.com.demo.javaweb.shopping.service;

import java.util.List;

import cn.com.demo.javaweb.shopping.entity.Receive;
import cn.com.demo.javaweb.shopping.entity.ShopCar;
import cn.com.demo.javaweb.shopping.entity.User;

public interface IPayService {
	public boolean payShopCar(ShopCar shopcar, Receive receive);

	public boolean payShopCars(List<ShopCar> shopCars, Receive receive);

	public boolean paySingle(User user, int warehouseId, int num, Receive receive);
}
