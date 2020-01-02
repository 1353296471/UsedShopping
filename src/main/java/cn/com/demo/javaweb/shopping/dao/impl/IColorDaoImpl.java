package cn.com.demo.javaweb.shopping.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.demo.javaweb.shopping.dao.IColorDao;
import cn.com.demo.javaweb.shopping.db.DaoUtils;
import cn.com.demo.javaweb.shopping.entity.Color;

@Repository
public class IColorDaoImpl implements IColorDao {

	@Override
	public Color getColor(int colorPkid) {
		String sql = "select * from color where colorPkid = ?";
		return DaoUtils.getListBySql(Color.class, sql, colorPkid).get(0);
	}

}
