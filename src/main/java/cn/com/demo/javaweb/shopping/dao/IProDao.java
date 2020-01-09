package cn.com.demo.javaweb.shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.com.demo.javaweb.shopping.entity.Product;

public interface IProDao {
	@Select({ "SELECT * from product where id = 1 or id = 2 or id = 3 " })
	public List<Product> getIndexProduct();

	@Select({ "SELECT * from product where id = #{proId}" })
	public Product getProduct(int proId);

	@Update({ "update product set proName = #{proName},price = #{price} where id = #{id}" })
	public boolean updateProduct(Product product);

	@Delete({ "delete from product where id = #{proId}" })
	public boolean toDeletePro(int proId);

}
