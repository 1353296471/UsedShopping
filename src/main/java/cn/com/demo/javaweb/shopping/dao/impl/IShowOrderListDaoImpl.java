package cn.com.demo.javaweb.shopping.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.demo.javaweb.shopping.dao.IShowOrderListDao;
import cn.com.demo.javaweb.shopping.db.DaoUtils;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowOrderList;

@Repository
public class IShowOrderListDaoImpl implements IShowOrderListDao {

	@Override
	public List<ShowOrderList> getShowOrderListsByPage(int userId, int index, int pageSize) {
//		int index = (pageNum - 1) * pageSize;
		String sql = "SELECT *  " + "FROM orderlist  "
				+ "INNER JOIN receive ON receive.receivePkid = orderlist.receivePkid "
				+ "INNER JOIN warehouse ON warehouse.id = orderlist.warehouseId "
				+ "INNER JOIN color ON warehouse.colorId = color.colorPkid "
				+ "INNER JOIN size ON warehouse.sizeId = size.sizePkid "
				+ "INNER JOIN product ON product.id = warehouse.proId " + "INNER JOIN img ON img.proId = product.id "
				+ "INNER JOIN `user` ON `user`.id = receive.userPkid "
				+ "INNER JOIN ordercondition ON ordercondition.orderConditionPkid = orderlist.orderConditionPkid "
				+ "LEFT JOIN `comment` ON `comment`.commentPkid = orderlist.commentPkid " + "WHERE `user`.id = ?  "
				+ "ORDER BY orderlist.orderTime DESC " + "limit ?,? ";
		return DaoUtils.getListBySql(ShowOrderList.class, sql, userId, index, pageSize);
	}

	@Override
	public List<ShowOrderList> getShowOrderLists(int userId) {
		String sql = "SELECT *  " + "FROM orderlist  "
				+ "INNER JOIN receive ON receive.receivePkid = orderlist.receivePkid "
				+ "INNER JOIN warehouse ON warehouse.id = orderlist.warehouseId "
				+ "INNER JOIN color ON warehouse.colorId = color.colorPkid "
				+ "INNER JOIN size ON warehouse.sizeId = size.sizePkid "
				+ "INNER JOIN product ON product.id = warehouse.proId " + "INNER JOIN img ON img.proId = product.id "
				+ "INNER JOIN `user` ON `user`.id = receive.userPkid "
				+ "INNER JOIN ordercondition ON ordercondition.orderConditionPkid = orderlist.orderConditionPkid "
				+ "LEFT JOIN `comment` ON `comment`.commentPkid = orderlist.commentPkid " + "WHERE `user`.id = ?  "
				+ "ORDER BY orderlist.orderTime DESC ";
		return DaoUtils.getListBySql(ShowOrderList.class, sql, userId);
	}

	@Override
	public List<ShowOrderList> getAllShowOrderListsByPage(int index, int pageSize) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public List<ShowOrderList> getAllShowOrderLists() {
		// TODO 自动生成的方法存根
		return null;
	}

}
