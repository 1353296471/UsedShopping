package cn.com.demo.javaweb.shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.com.demo.javaweb.shopping.entity.toshow.ShowOrderList;

public interface IShowOrderListDao {
	// #{(param2-1)* param3},#{param3}
	@Select({ "SELECT *  " + "FROM orderlist  " + "INNER JOIN receive ON receive.receivePkid = orderlist.receivePkid "
			+ "INNER JOIN warehouse ON warehouse.id = orderlist.warehouseId "
			+ "INNER JOIN color ON warehouse.colorId = color.colorPkid "
			+ "INNER JOIN size ON warehouse.sizeId = size.sizePkid "
			+ "INNER JOIN product ON product.id = warehouse.proId " + "INNER JOIN img ON img.proId = product.id "
			+ "INNER JOIN `user` ON `user`.id = receive.userPkid "
			+ "INNER JOIN ordercondition ON ordercondition.orderConditionPkid = orderlist.orderConditionPkid "
			+ "LEFT JOIN `comment` ON `comment`.commentPkid = orderlist.commentPkid " + "WHERE `user`.id = #{param1}  "
			+ "ORDER BY orderlist.orderTime DESC " + "limit #{param2},#{param3} " })
	public List<ShowOrderList> getShowOrderListsByPage(int userId, int index, int pageSize);

	@Select({ "SELECT *  " + "FROM orderlist  " + "INNER JOIN receive ON receive.receivePkid = orderlist.receivePkid "
			+ "INNER JOIN warehouse ON warehouse.id = orderlist.warehouseId "
			+ "INNER JOIN color ON warehouse.colorId = color.colorPkid "
			+ "INNER JOIN size ON warehouse.sizeId = size.sizePkid "
			+ "INNER JOIN product ON product.id = warehouse.proId " + "INNER JOIN img ON img.proId = product.id "
			+ "INNER JOIN `user` ON `user`.id = receive.userPkid "
			+ "INNER JOIN ordercondition ON ordercondition.orderConditionPkid = orderlist.orderConditionPkid "
			+ "LEFT JOIN `comment` ON `comment`.commentPkid = orderlist.commentPkid "
			+ "ORDER BY orderlist.orderTime DESC " + "limit #{param1},#{param2} " })
	public List<ShowOrderList> getAllShowOrderListsByPage(int index, int pageSize);

	@Select({ "SELECT *  " + "FROM orderlist  " + "INNER JOIN receive ON receive.receivePkid = orderlist.receivePkid "
			+ "INNER JOIN warehouse ON warehouse.id = orderlist.warehouseId "
			+ "INNER JOIN color ON warehouse.colorId = color.colorPkid "
			+ "INNER JOIN size ON warehouse.sizeId = size.sizePkid "
			+ "INNER JOIN product ON product.id = warehouse.proId " + "INNER JOIN img ON img.proId = product.id "
			+ "INNER JOIN `user` ON `user`.id = receive.userPkid "
			+ "INNER JOIN ordercondition ON ordercondition.orderConditionPkid = orderlist.orderConditionPkid "
			+ "LEFT JOIN `comment` ON `comment`.commentPkid = orderlist.commentPkid " + "WHERE `user`.id = #{userId}  "
			+ "ORDER BY orderlist.orderTime DESC " })
	public List<ShowOrderList> getShowOrderLists(int userId);

	@Select({ "SELECT *  " + "FROM orderlist  " + "INNER JOIN receive ON receive.receivePkid = orderlist.receivePkid "
			+ "INNER JOIN warehouse ON warehouse.id = orderlist.warehouseId "
			+ "INNER JOIN color ON warehouse.colorId = color.colorPkid "
			+ "INNER JOIN size ON warehouse.sizeId = size.sizePkid "
			+ "INNER JOIN product ON product.id = warehouse.proId " + "INNER JOIN img ON img.proId = product.id "
			+ "INNER JOIN `user` ON `user`.id = receive.userPkid "
			+ "INNER JOIN ordercondition ON ordercondition.orderConditionPkid = orderlist.orderConditionPkid "
			+ "LEFT JOIN `comment` ON `comment`.commentPkid = orderlist.commentPkid "
			+ "ORDER BY orderlist.orderTime DESC " })
	public List<ShowOrderList> getAllShowOrderLists();

	@Select({ "SELECT *  " + "FROM orderlist  " + "INNER JOIN receive ON receive.receivePkid = orderlist.receivePkid "
			+ "INNER JOIN warehouse ON warehouse.id = orderlist.warehouseId "
			+ "INNER JOIN color ON warehouse.colorId = color.colorPkid "
			+ "INNER JOIN size ON warehouse.sizeId = size.sizePkid "
			+ "INNER JOIN product ON product.id = warehouse.proId " + "INNER JOIN img ON img.proId = product.id "
			+ "INNER JOIN `user` ON `user`.id = receive.userPkid "
			+ "INNER JOIN ordercondition ON ordercondition.orderConditionPkid = orderlist.orderConditionPkid "
			+ "LEFT JOIN `comment` ON `comment`.commentPkid = orderlist.commentPkid "
			+ "where product.userId = #{param3} " + "ORDER BY orderlist.orderTime DESC "
			+ "limit #{param1},#{param2} " })
	public List<ShowOrderList> getAllShowMySoldByPage(int index, int pageSize, int userId);

	@Select({ "SELECT *  " + "FROM orderlist  " + "INNER JOIN receive ON receive.receivePkid = orderlist.receivePkid "
			+ "INNER JOIN warehouse ON warehouse.id = orderlist.warehouseId "
			+ "INNER JOIN color ON warehouse.colorId = color.colorPkid "
			+ "INNER JOIN size ON warehouse.sizeId = size.sizePkid "
			+ "INNER JOIN product ON product.id = warehouse.proId " + "INNER JOIN img ON img.proId = product.id "
			+ "INNER JOIN `user` ON `user`.id = receive.userPkid "
			+ "INNER JOIN ordercondition ON ordercondition.orderConditionPkid = orderlist.orderConditionPkid "
			+ "LEFT JOIN `comment` ON `comment`.commentPkid = orderlist.commentPkid "
			+ "where product.userId = #{param1} " + "ORDER BY orderlist.orderTime DESC " })
	public List<ShowOrderList> getAllShowMySold(int userId);

}
