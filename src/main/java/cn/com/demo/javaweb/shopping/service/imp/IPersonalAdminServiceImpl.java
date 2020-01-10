package cn.com.demo.javaweb.shopping.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.demo.javaweb.shopping.dao.ICatalogDao;
import cn.com.demo.javaweb.shopping.dao.IImgDao;
import cn.com.demo.javaweb.shopping.dao.IOrderDao;
import cn.com.demo.javaweb.shopping.dao.IProDao;
import cn.com.demo.javaweb.shopping.dao.IProDesDao;
import cn.com.demo.javaweb.shopping.dao.IReceiveDao;
import cn.com.demo.javaweb.shopping.dao.IShopCarDao;
import cn.com.demo.javaweb.shopping.dao.IShowOrderListDao;
import cn.com.demo.javaweb.shopping.dao.IShowProductAdminDao;
import cn.com.demo.javaweb.shopping.dao.IShowWarehouseDao;
import cn.com.demo.javaweb.shopping.dao.IUserDao;
import cn.com.demo.javaweb.shopping.dao.IWarehouseDao;
import cn.com.demo.javaweb.shopping.entity.ProDes;
import cn.com.demo.javaweb.shopping.entity.Product;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowOrderList;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowProductAdmin;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowWarehouse;
import cn.com.demo.javaweb.shopping.service.IPersonalAdminService;

@Transactional
@Service
public class IPersonalAdminServiceImpl implements IPersonalAdminService {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IProDao proDao;

	@Autowired
	private IReceiveDao receiveDao;

	@Autowired
	private IOrderDao orderDao;

	@Autowired
	private IShopCarDao shopCarDao;

	@Autowired
	private IWarehouseDao warehouseDao;

	@Autowired
	private IImgDao imgDao;

	@Autowired
	private IShowOrderListDao showOrderListDao;

	@Autowired
	private IShowWarehouseDao showWarehouseDao;

	@Autowired
	private IShowProductAdminDao showProductAdminDao;

	@Autowired
	private IProDesDao proDesDao;

	@Autowired
	private ICatalogDao catalogDao;

	@Override
	public List<ShowOrderList> getShowOrderLists(int userId) {
		List<ShowOrderList> items = new ArrayList<ShowOrderList>();

		items = showOrderListDao.getShowOrderLists(userId);
		return items;
	}

	@Override
	public List<ShowOrderList> getShowOrderListsByPage(int userId, int pageNum, int pageSize) {
		int index = (pageNum - 1) * pageSize;
		List<ShowOrderList> itemsPage = showOrderListDao.getShowOrderListsByPage(userId, index, pageSize);
		return itemsPage;
	}

	@Override
	public int getOrderMaxPage(int pageSize) {
		int maxPage = (showOrderListDao.getAllShowOrderLists().size() + pageSize - 1) / pageSize;
		return maxPage;
	}

	@Override
	public List<ShowOrderList> getAllShowOrderListsByPage(int pageNum, int pageSize) {
		int index = (pageNum - 1) * pageSize;
		List<ShowOrderList> itemsPage = showOrderListDao.getAllShowOrderListsByPage(index, pageSize);
		return itemsPage;
	}

	@Override
	public boolean toSendOrder(int orderPkid) {
		return orderDao.sendOrder(orderPkid);
	}

	@Override
	public List<ShowWarehouse> getAllShowWarehouseByPage(int pageNum, int pageSize) {
		int index = (pageNum - 1) * pageSize;
//		PageHelper.startPage(pageNum, pageSize);
		// startPage后面紧跟的这个查询就是一个分页查询
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
//		List<ShowWarehouse> itemsPage = showWarehouseDao.getAllShowWarehouse();
//		PageInfo<ShowWarehouse> page = new PageInfo<ShowWarehouse>(itemsPage, pageSize);

		List<ShowWarehouse> itemsPage = showWarehouseDao.getAllShowWarehouseByPage(index, pageSize);
		return itemsPage;
	}

	@Override
	public int getProMaxPage(int pageSize) {
		int maxPage = (showProductAdminDao.getAllShowProductAdmin().size() + pageSize - 1) / pageSize;
		return maxPage;
	}

	@Override
	public List<ShowProductAdmin> getAllShowProductAdminByPage(int pageNum, int pageSize) {
		int index = (pageNum - 1) * pageSize;
		List<ShowProductAdmin> itemsPage = showProductAdminDao.getAllShowProductAdminByPage(index, pageSize);
		return itemsPage;

	}

	@Override
	public ShowProductAdmin getShowProductAdmin(int proId) {
		// TODO 自动生成的方法存根
		return showProductAdminDao.getShowProductAdmin(proId);
	}

	@Override
	public boolean updateShowProductAdmin(ShowProductAdmin showProductAdmin) {
		boolean flag = false;
		// flag = showProductAdminDao.updateShowProductAdmin(showProductAdmin);
		Product pro = new Product();
		pro.setId(showProductAdmin.getProId());
		pro.setPrice(showProductAdmin.getPrice());
		pro.setProName(showProductAdmin.getProName());
		pro.setCatalogId(catalogDao.getCatalogByTypeOne(showProductAdmin.getCatalogTypeOne()).getCatalogId());

		ProDes proDes = new ProDes();
		proDes.setProDesPkid(showProductAdmin.getProId());
		proDes.setProDes(showProductAdmin.getProDes());

		if (proDesDao.getProDes(showProductAdmin.getProId()) != null) {
			if (proDesDao.updateProDes(proDes) && proDao.updateProduct(pro)) {
				flag = true;
			}
		} else {
			if (proDesDao.addProDes(proDes) && proDao.updateProduct(pro)) {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public boolean toDeletePro(int proId) {
		// TODO 自动生成的方法存根
		return proDao.toDeletePro(proId);
	}
}
