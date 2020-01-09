package cn.com.demo.javaweb.shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.com.demo.javaweb.shopping.entity.toshow.ShowOrderList;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowProductAdmin;

public interface IShowProductAdminDao {

	@Select({ "SELECT *  " + "FROM  product " + "Left JOIN img ON img.proId = product.id "
			+ "inner JOIN catalog ON catalog.catalogid = product.catalogid "
			+ "left join prodes on prodes.proDesPkid = product.id " + "ORDER BY product.id" })
	public List<ShowProductAdmin> getAllShowProductAdmin();

	@Select({ "SELECT *  " + "FROM  product " + "Left JOIN img ON img.proId = product.id "
			+ "inner JOIN catalog ON catalog.catalogid = product.catalogid "
			+ "left join prodes on prodes.proDesPkid = product.id " + "ORDER BY product.id "
			+ "limit #{param1},#{param2}" })
	public List<ShowProductAdmin> getAllShowProductAdminByPage(int index, int pageSize);

	@Select({ "SELECT *  " + "FROM  product " + "Left JOIN img ON img.proId = product.id "
			+ "inner JOIN catalog ON catalog.catalogid = product.catalogid "
			+ "left join prodes on prodes.proDesPkid = product.id " + "where product.id = #{proId} "
			+ "ORDER BY product.id" })
	public ShowProductAdmin getShowProductAdmin(int proId);

//	@Update(value = { "update product set proName = #{proName},price = #{price} where id = #{proId}",
//			"update prodes set proDes = #{proDes} where proDesPkid = #{proId}" })
//	public boolean updateShowProductAdmin(ShowProductAdmin showProductAdmin);
//
//	@Delete({ "delete from product where id = #{proId}" })
//	public boolean toDeletePro(int proId);

	@Select({ "SELECT *  " + "FROM  product " + "Left JOIN img ON img.proId = product.id "
			+ "inner JOIN catalog ON catalog.catalogid = product.catalogid  "
			+ "left join prodes on prodes.proDesPkid = product.id " + "INNER JOIN `user` ON product.userId = `user`.id "
			+ "WHERE product.userId = #{param3} " + "ORDER BY product.id " + "limit #{param1},#{param2}" })
	public List<ShowProductAdmin> getAllShowMyPostedByPage(int index, int pageSize, int userId);

	@Select({ "SELECT *  " + "FROM  product " + "Left JOIN img ON img.proId = product.id "
			+ "inner JOIN catalog ON catalog.catalogid = product.catalogid  "
			+ "left join prodes on prodes.proDesPkid = product.id " + "INNER JOIN `user` ON product.userId = `user`.id "
			+ "WHERE product.userId = #{param1} " + "ORDER BY product.id" })
	public List<ShowOrderList> getAllShowMyPosted(int userId);

}
