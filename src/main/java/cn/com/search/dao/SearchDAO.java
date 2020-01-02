package cn.com.search.dao;

import java.util.List;

import cn.com.search.entity.Product1;

public interface SearchDAO {
	public List<Product1> findByName(String whereSql);

}
