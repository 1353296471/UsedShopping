package cn.com.demo.javaweb.shopping.service;

import java.util.List;

import cn.com.demo.javaweb.shopping.entity.toshow.ShowOrderList;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowProductAdmin;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowWarehouse;

public interface IPersonalAdminService {
	public List<ShowOrderList> getShowOrderLists(int userId);

	public List<ShowOrderList> getShowOrderListsByPage(int userId, int pageNum, int pageSize);

	public int getOrderMaxPage(int pageSize);

	public int getProMaxPage(int pageSize);

	public List<ShowOrderList> getAllShowOrderListsByPage(int pageNum, int pageSize);

	public boolean toSendOrder(int orderPkid);

	public List<ShowWarehouse> getAllShowWarehouseByPage(int pageNum, int pageSize);

	public List<ShowProductAdmin> getAllShowProductAdminByPage(int pageNum, int pageSize);

	public ShowProductAdmin getShowProductAdmin(int proId);

	public boolean updateShowProductAdmin(ShowProductAdmin showProductAdmin);

	public boolean toDeletePro(int proId);
}
