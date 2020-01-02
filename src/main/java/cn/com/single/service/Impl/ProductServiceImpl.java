package cn.com.single.service.Impl;

import java.util.List;

import cn.com.single.DAO.ProductDAO;
import cn.com.single.DAO.Impl.ProductDAOImpl;
import cn.com.single.entity.Product;
import cn.com.single.service.ProductService;

public class ProductServiceImpl implements ProductService {
	private ProductDAO productDAO = new ProductDAOImpl();

	@Override
	public String findProName(int proId) {

		return this.productDAO.findProName(proId);
	}

	@Override
	public double findPrice(int proId) {
		return this.productDAO.findPrice(proId);
	}

	@Override
	public List<Product> findByProId(int proId) {

		return this.productDAO.findByPkid(proId);
	}

}
