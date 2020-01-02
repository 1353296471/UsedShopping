package cn.com.demo.javaweb.shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.com.demo.javaweb.shopping.entity.Product;

public interface IProDao {
	@Select({ "SELECT * from product where id = 1 or id = 2 or id = 3 " })
	public List<Product> getIndexProduct();

	@Select({ "SELECT * from product where id = #{proId}" })
	public Product getProduct(int proId);

}
