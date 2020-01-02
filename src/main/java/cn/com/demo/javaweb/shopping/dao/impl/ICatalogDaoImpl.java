package cn.com.demo.javaweb.shopping.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.demo.javaweb.shopping.dao.ICatalogDao;
import cn.com.demo.javaweb.shopping.db.DaoUtils;
import cn.com.demo.javaweb.shopping.entity.Catalog;

@Repository
public class ICatalogDaoImpl implements ICatalogDao {

	@Override
	public Catalog getCatalog(int catalogId) {
		String sql = "select * from catalog where catalogId = ?";
		return DaoUtils.getListBySql(Catalog.class, sql, catalogId).get(0);
	}

}
