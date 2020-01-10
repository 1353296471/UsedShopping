package cn.com.demo.javaweb.shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.com.demo.javaweb.shopping.entity.Catalog;

public interface ICatalogDao {
	@Select({ "select * from catalog where catalogId = #{catalogId}" })
	public Catalog getCatalog(int catalogId);

	@Select({ "select * from catalog where catalogTypeOne = #{catalogTypeOne}" })
	public Catalog getCatalogByTypeOne(String catalogTypeOne);

	@Select({ "select * from catalog" })
	public List<Catalog> getAllCatalogs();
}
