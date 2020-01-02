package cn.com.demo.javaweb.shopping.dao;

import org.apache.ibatis.annotations.Select;

import cn.com.demo.javaweb.shopping.entity.Catalog;

public interface ICatalogDao {
	@Select({ "select * from catalog where catalogId = #{catalogId}" })
	public Catalog getCatalog(int catalogId);
}
