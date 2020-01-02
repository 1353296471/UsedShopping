package cn.com.demo.javaweb.shopping.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.demo.javaweb.shopping.dao.IImgDao;
import cn.com.demo.javaweb.shopping.db.DaoUtils;
import cn.com.demo.javaweb.shopping.entity.Img;

@Repository
public class IImgDaoImpl implements IImgDao {

	@Override
	public Img getMainImg(int proId) {
		// TODO 自动生成的方法存根
		String sql = "select * from img where proId = ? and type = 1";
		return DaoUtils.getListBySql(Img.class, sql, proId).get(0);
	}

}
