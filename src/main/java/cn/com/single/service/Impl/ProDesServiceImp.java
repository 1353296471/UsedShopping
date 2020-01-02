package cn.com.single.service.Impl;

import java.util.List;

import cn.com.single.DAO.ProDesDAO;
import cn.com.single.DAO.Impl.ProDesDAOImpl;
import cn.com.single.service.ProDesService;

public class ProDesServiceImp implements ProDesService {
	private ProDesDAO productDesDAO = new ProDesDAOImpl();

	@Override
	public List<String> proDes(int proPkid) {
		return this.productDesDAO.findProDes(proPkid);
	}

}
