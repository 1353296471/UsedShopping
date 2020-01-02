package cn.com.demo.javaweb.shopping.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.demo.javaweb.shopping.dao.ISizeDao;
import cn.com.demo.javaweb.shopping.db.DaoUtils;
import cn.com.demo.javaweb.shopping.entity.Size;

@Repository
public class ISizeDaoImpl implements ISizeDao {

	@Override
	public Size getSize(int sizePkid) {
		String sql = "select * from size where sizePkid = ?";
		return DaoUtils.getListBySql(Size.class, sql, sizePkid).get(0);
	}

}
