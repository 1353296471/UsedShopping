package cn.com.single.service.Impl;

import java.util.List;

import cn.com.single.DAO.RelatedImgDAO;
import cn.com.single.DAO.Impl.RelatedImgDAOImpl;
import cn.com.single.entity.Imags;
import cn.com.single.service.RelatedImgService;

public class RelatedUrlServiceImpl implements RelatedImgService {
	private RelatedImgDAO relatedImgDAO = new RelatedImgDAOImpl();

	@Override
	public List<Imags> findImgUrls(int proId) {

		return this.relatedImgDAO.findImgUrls(proId);
	}

	@Override
	public String findProImag(int proId) {
		// TODO Auto-generated method stub
		return this.relatedImgDAO.findProImag(proId);
	}

}
