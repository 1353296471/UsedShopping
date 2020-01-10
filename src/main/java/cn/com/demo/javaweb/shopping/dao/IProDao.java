package cn.com.demo.javaweb.shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.com.demo.javaweb.shopping.entity.Product;

public interface IProDao {
	@Select({ "SELECT * from product where id = 1 or id = 2 or id = 3 " })
	public List<Product> getIndexProduct();

	@Select({ "SELECT * from product where id = #{proId}" })
	public Product getProduct(int proId);

	@Update({ "update product set proName = #{proName},price = #{price},catalogId = #{catalogId} where id = #{id}" })
	public boolean updateProduct(Product product);

	@Delete({ "delete from product where id = #{proId}" })
	public boolean toDeletePro(int proId);

	@Insert({
			"insert into product (catalogId,proName,price,userId) values (#{catalogId},#{proName},#{price},#{userId}) " })
	public boolean addProduct(Product pro);

	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	@Insert({
			"insert into product (catalogId,proName,price,userId) values (#{catalogId},#{proName},#{price},#{userId}) " })
	public boolean addProductBackId(Product pro);

}
