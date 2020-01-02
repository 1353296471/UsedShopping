package cn.com.search.service.impl;

import java.util.List;

import cn.com.search.dao.SearchDAO;
import cn.com.search.dao.impl.SearchDAOImpl;
import cn.com.search.entity.Product1;
import cn.com.search.service.SearchService;

public class SearchServiceImpl implements SearchService {
	private SearchDAO proDao = new SearchDAOImpl();

	@Override
	public List<Product1> searchProduct(String name) {
		return this.proDao.findByName(name);
	}

}
