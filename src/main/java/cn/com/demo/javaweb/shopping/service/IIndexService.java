package cn.com.demo.javaweb.shopping.service;

import java.util.List;

import cn.com.demo.javaweb.shopping.entity.toshow.ShowProduct;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowShopCar;

public interface IIndexService {
	public List<ShowProduct> getShowProduct();

	public List<ShowShopCar> getShopCar(int userId);

	public boolean addShopCarItem(int userId, int proId, int num);

	public boolean removeShopCarItem(int userId, int proId, int num);

	public boolean deleteShopCarItem(int userId, int proId);

	public List<ShowProduct> searchProName(String proName, Integer pageNo, Integer pageSize);

	public List<ShowProduct> searchType(Integer catalogId, Integer pageNo, Integer pageSize);

	public int getProMaxPageType(Integer catalogId, int pageSize);

	public int getProMaxPageProName(String proName, int pageSize);
}
