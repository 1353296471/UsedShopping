package cn.com.demo.javaweb.shopping.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.demo.javaweb.shopping.dao.IReceiveDao;
import cn.com.demo.javaweb.shopping.db.DaoUtils;
import cn.com.demo.javaweb.shopping.entity.Receive;

@Repository
public class IReceiveDaoImpl implements IReceiveDao {

	@Override
	public List<Receive> getReceives(int userId) {
		String sql = "select * from receive where userPkid = ?";
		return DaoUtils.getListBySql(Receive.class, sql, userId);
	}

	@Override
	public Receive getReceive(Receive receive) {
		String sql = "select * from receive where userPkid=? and sheng=? and shi=? and qu=? and userAddress =? and userPhone=?";
		return DaoUtils.getListBySql(Receive.class, sql, receive.getUserPkid(), receive.getSheng(), receive.getShi(),
				receive.getQu(), receive.getUserAddress(), receive.getUserPhone()).get(0);
	}

	@Override
	public boolean add(Receive receive) {
		String sql = "insert into receive (userPkid,sheng,shi,qu,userAddress,userPhone) values (?,?,?,?,?,?) ";
		return DaoUtils.insertOrUpdate(sql, receive.getUserPkid(), receive.getSheng(), receive.getShi(),
				receive.getQu(), receive.getUserAddress(), receive.getUserPhone());
	}

	@Override
	public boolean remove(Receive receive) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean queryReceive(Receive receive) {
		boolean falg = false;
		String sql = "select * from receive where userPkid=? and sheng=? and shi=? and qu=? and userAddress =? and userPhone=?";
		falg = DaoUtils.executeQuery(sql, receive.getUserPkid(), receive.getSheng(), receive.getShi(), receive.getQu(),
				receive.getUserAddress(), receive.getUserPhone());
		return falg;
	}

}
