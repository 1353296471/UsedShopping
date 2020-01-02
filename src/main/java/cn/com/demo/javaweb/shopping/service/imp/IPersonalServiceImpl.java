package cn.com.demo.javaweb.shopping.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.demo.javaweb.shopping.dao.IImgDao;
import cn.com.demo.javaweb.shopping.dao.IOrderDao;
import cn.com.demo.javaweb.shopping.dao.IProDao;
import cn.com.demo.javaweb.shopping.dao.IReceiveDao;
import cn.com.demo.javaweb.shopping.dao.IShopCarDao;
import cn.com.demo.javaweb.shopping.dao.IShowOrderListDao;
import cn.com.demo.javaweb.shopping.dao.IUserDao;
import cn.com.demo.javaweb.shopping.dao.IWarehouseDao;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowOrderList;
import cn.com.demo.javaweb.shopping.service.IPersonalService;

@Service
public class IPersonalServiceImpl implements IPersonalService {

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

	@Override
	public List<ShowOrderList> getShowOrderLists(int userId) {
		List<ShowOrderList> items = new ArrayList<ShowOrderList>();

//		// 1.根据userId获取所有receive
//		List<Receive> res = receiveDao.getReceives(userId);
//
//		for (Receive re : res) {
//			// 2.根据receiveId获取所有的order
//			for (OrderList o : orderDao.getOrders(re.getReceivePkid())) {
//				// 3.根据order中的proPkid获取对应的商品product
//				Product pro = proDao.getProduct(o.getProPkid());
//				// 4.根据获取product得到对应的图片
//				Img mainImg = imgDao.getMainImg(pro.getId());
//				// 5.将数据封装在ShowOrderList中，用来显示
//				ShowOrderList so = new ShowOrderList();
//				so.setMainImg(mainImg);
//				so.setPro(pro);
//				so.setOrderList(o);
//				so.setReceive(re);
//				if (o.getOrderConditionPkid() == 1) {
//					so.setConditionType("订单正在处理");
//				} else {
//					so.setConditionType("已发货");
//				}
//
//				items.add(so);
//			}
//		}
//		// 6.根据时间顺序排序，倒序
//		items.sort(new Comparator<ShowOrderList>() {
//			@Override
//			public int compare(ShowOrderList o1, ShowOrderList o2) {
//				if (o1.getOrderList().getOrderTime().getTime() >= o2.getOrderList().getOrderTime().getTime()) {
//					return -1; // 负整数表示将往前移动
//				}
//				return 1;// 正整数表示将往后移动
//			}
//
//		});
		items = showOrderListDao.getShowOrderLists(userId);
		return items;
	}

	@Override
	public boolean chargeMoney(int userId, double money) {
		boolean falg = false;
		if (money >= 0) {
			falg = userDao.chargeMoney(userId, money);
		}
		return falg;
	}

	@Override
	public List<ShowOrderList> getShowOrderListsByPage(int userId, int pageNum, int pageSize) {
		int index = (pageNum - 1) * pageSize;
		List<ShowOrderList> itemsPage = showOrderListDao.getShowOrderListsByPage(userId, index, pageSize);
		return itemsPage;
	}

	@Override
	public int getMaxPage(int userId, int pageSize) {
		int maxPage = (showOrderListDao.getShowOrderLists(userId).size() + pageSize - 1) / pageSize;
		return maxPage;
	}

	public static void main(String[] args) {

	}

	@Override
	public double getCash(int userId) {
		double cash = 0;
		cash = userDao.getUserById(userId).getMoney();
		return cash;
	}
}
