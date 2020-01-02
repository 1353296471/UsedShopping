package cn.com.demo.javaweb.shopping.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.demo.javaweb.shopping.dao.IProDao;
import cn.com.demo.javaweb.shopping.db.DaoUtils;
import cn.com.demo.javaweb.shopping.entity.Product;

@Repository
public class IProDaoImpl implements IProDao {

	@Override
	public List<Product> getIndexProduct() {
		// TODO 自动生成的方法存根
		String sql = "SELECT * from product where id = 1 or id = 2 or id = 3 ";
		return DaoUtils.getListBySql(Product.class, sql);
	}

	@Override
	public Product getProduct(int proId) {
		String sql = "SELECT * from product where id = ?";
		return DaoUtils.getListBySql(Product.class, sql, proId).get(0);
	}

}
