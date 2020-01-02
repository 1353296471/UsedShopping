package cn.com.single.service;

import java.util.List;

import cn.com.single.entity.Product;

public interface ProductService {
	public List<Product> findByProId(int proId);

	public String findProName(int proId);

	public double findPrice(int proId);
}
