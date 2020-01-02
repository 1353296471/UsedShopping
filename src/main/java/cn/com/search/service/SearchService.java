package cn.com.search.service;

import java.util.List;

import cn.com.search.entity.Product1;

public interface SearchService {
	public List<Product1> searchProduct(String name);

}
