package cn.com.demo.javaweb.shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.com.demo.javaweb.shopping.entity.toshow.ShowProductAdmin;

public interface IShowProductAdminDao {

	@Select({ "SELECT *  " + "FROM  product " + "Left JOIN img ON img.proId = product.id "
			+ "inner JOIN catalog ON catalog.catalogid = product.catalogid " + "ORDER BY product.id" })
	public List<ShowProductAdmin> getAllShowProductAdmin();

	@Select({ "SELECT *  " + "FROM  product " + "Left JOIN img ON img.proId = product.id "
			+ "inner JOIN catalog ON catalog.catalogid = product.catalogid " + "ORDER BY product.id "
			+ "limit #{param1},#{param2}" })
	public List<ShowProductAdmin> getAllShowProductAdminByPage(int index, int pageSize);

	@Select({ "SELECT *  " + "FROM  product " + "Left JOIN img ON img.proId = product.id "
			+ "inner JOIN catalog ON catalog.catalogid = product.catalogid " + "where product.id = #{proId} "
			+ "ORDER BY product.id" })
	public ShowProductAdmin getShowProductAdmin(int proId);

	@Update({ "update product set proName = #{proName},price = #{price} where id = #{proId}" })
	public boolean updateShowProductAdmin(ShowProductAdmin showProductAdmin);

	@Delete({ "delete from product where id = #{proId}" })
	public boolean toDeletePro(int proId);

}
