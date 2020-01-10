package cn.com.demo.javaweb.shopping.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.com.demo.javaweb.shopping.entity.Catalog;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowOrderList;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowProductAdmin;

public interface IPersonalService {
	public List<ShowOrderList> getShowOrderLists(int userId);

	public List<ShowOrderList> getShowOrderListsByPage(int userId, int pageNum, int pageSize);

	public int getMaxPage(int userId, int pageSize);

	public double getCash(int userId);

	public boolean chargeMoney(int userId, double money);

	public List<ShowProductAdmin> getAllShowMyPostedByPage(int pageNo, int pageSize, int userId);

	public int getProMaxPage(int pageSize, int userId);

	public List<ShowOrderList> getAllShowMySoldByPage(int pageNo, int pageSize, int userId);

	public int getMySoldMaxPage(int pageSize, int userId);

	public Boolean releaseProduct(ShowProductAdmin showProductAdmin, MultipartFile img) throws Exception;

	public List<Catalog> getAllCatalogs();
}
