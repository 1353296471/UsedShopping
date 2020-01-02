package cn.com.demo.javaweb.shopping.service.imp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.demo.javaweb.shopping.dao.IOrderDao;
import cn.com.demo.javaweb.shopping.dao.IProDao;
import cn.com.demo.javaweb.shopping.dao.IReceiveDao;
import cn.com.demo.javaweb.shopping.dao.IShopCarDao;
import cn.com.demo.javaweb.shopping.dao.IUserDao;
import cn.com.demo.javaweb.shopping.dao.IWarehouseDao;
import cn.com.demo.javaweb.shopping.db.DaoUtils;
import cn.com.demo.javaweb.shopping.entity.OrderList;
import cn.com.demo.javaweb.shopping.entity.Product;
import cn.com.demo.javaweb.shopping.entity.Receive;
import cn.com.demo.javaweb.shopping.entity.ShopCar;
import cn.com.demo.javaweb.shopping.entity.User;
import cn.com.demo.javaweb.shopping.service.IPayService;

@Service
@Transactional
public class IPayServiceImpl implements IPayService {

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

	public int getReceivePkId(Receive receive) {
		int pkId = -1;
		Receive rec;
		if (!receiveDao.queryReceive(receive)) {
			receiveDao.add(receive);

			rec = receiveDao.getReceive(receive);
		} else {
			rec = receiveDao.getReceive(receive);
		}
		pkId = rec.getReceivePkid();
		return pkId;
	}

	/**
	 * 购买操作,添加了事务操作，出错了即可回滚
	 * 
	 * 
	 * @param shopcar
	 * @param receive
	 * @return
	 */
	@Override
	public boolean payShopCar(ShopCar shopcar, Receive receive) {
		boolean falg = false;
		// 判断表中是否存在
		ShopCar sc = shopCarDao.getShopCar(shopcar.getUserId(), shopcar.getWarehouseId());
		if (sc != null) {
			// 获取对应的商品和用户，判断价格是否足够
			User user = userDao.getUserById(shopcar.getUserId());
			int proId = warehouseDao.getWarehouse(shopcar.getWarehouseId()).getProId();
			Product pro = proDao.getProduct(proId);
			double price = shopcar.getNum() * pro.getPrice();
			if (user.getMoney() >= price) {
				// 执行操作，应该采取事务包围，后续需要改进
				// 1.从购物车中删除对应商品信息
//				shopCarDao.deleteShopCar(shopcar);

				// 2.生成对应的订单信息
				int receivePkid = getReceivePkId(receive);
				OrderList order = new OrderList();
				order.setWarehouseId(shopcar.getWarehouseId());
				order.setProNum(shopcar.getNum());
				order.setReceivePkid(receivePkid);
				order.setOrderConditionPkid(1);
				order.setOrderTime(new Timestamp(System.currentTimeMillis()));
//				orderDao.add(order);

				// 3.从库存中减去对应商品数量
//				warehouseDao.remove(shopcar.getProId(), shopcar.getNum());

				// 4.更新用户的余额信息
//				userDao.payMoney(user, price);

				// 用事务包围操作
				Connection conn = DaoUtils.getConn();
				try {
					conn.setAutoCommit(false);
					if (shopCarDao.deleteShopCarByConn(conn, shopcar) && orderDao.addByConn(conn, order)
							&& warehouseDao.removeByConn(conn, shopcar.getWarehouseId(), shopcar.getNum())
							&& userDao.payMoneyByConn(conn, user, price)) {
						falg = true;
					}
					conn.commit();
				} catch (Exception e) {
					e.printStackTrace();
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		}
		return falg;
	}

	@Override
	public boolean payShopCars(List<ShopCar> shopcars, Receive receive) {
		boolean falg = false;
		for (ShopCar sc : shopcars) {
			falg = payShopCar(sc, receive);
			if (!falg) {
				break;
			}
		}
		return falg;
	}

	@Override
	public boolean paySingle(User user, int id, int num, Receive receive) {
		boolean falg = false;
		// 获取对应的商品和用户，判断价格是否足够
		int warehouseId = id;
		int proId = warehouseDao.getWarehouse(warehouseId).getProId();
		Product pro = proDao.getProduct(proId);
		double price = pro.getPrice();
		if (user.getMoney() >= price) {
			// 执行操作，应该采取事务包围，后续需要改进

			// 2.生成对应的订单信息
			int receivePkid = getReceivePkId(receive);
			OrderList order = new OrderList();
			order.setWarehouseId(warehouseId);
			order.setProNum(num);
			order.setReceivePkid(receivePkid);
			order.setOrderConditionPkid(1);
			order.setOrderTime(new Timestamp(System.currentTimeMillis()));
//			orderDao.add(order);

			// 3.从库存中减去对应商品数量
//			warehouseDao.remove(shopcar.getProId(), shopcar.getNum());

			// 4.更新用户的余额信息
//			userDao.payMoney(user, price);

			// 用事务包围操作
			Connection conn = DaoUtils.getConn();
			try {
				conn.setAutoCommit(false);
				if (orderDao.addByConn(conn, order) && warehouseDao.removeByConn(conn, warehouseId, num)
						&& userDao.payMoneyByConn(conn, user, price)) {
					falg = true;
				}
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					falg = false;
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		}
		return falg;
	}

}
