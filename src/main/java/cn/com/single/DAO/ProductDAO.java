package cn.com.single.DAO;

import java.util.List;

import cn.com.single.entity.Product;

public interface ProductDAO {
	public List<Product> findByPkid(int proPkid);

	public String findProName(int proId);

	public double findPrice(int proId);
}
