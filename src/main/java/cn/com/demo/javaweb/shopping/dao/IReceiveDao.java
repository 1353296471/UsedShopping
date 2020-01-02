package cn.com.demo.javaweb.shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import cn.com.demo.javaweb.shopping.entity.Receive;

public interface IReceiveDao {
	@Select({ "select * from receive where userPkid = #{userId}" })
	public List<Receive> getReceives(int userId);

	@Select({
			"select * from receive where userPkid=#{userPkid} and sheng=#{sheng} and shi=#{shi} and qu=#{qu} and userAddress =#{userAddress} and userPhone=#{userPhone}" })

	public Receive getReceive(Receive receive);

	@Insert({
			"insert into receive (userPkid,sheng,shi,qu,userAddress,userPhone) values (#{userPkid},#{sheng},#{shi},#{qu},#{userAddress},#{userPhone})" })
	public boolean add(Receive receive);

	@Delete({ "delete from receive where receivePkid = #{receivePkid}" })
	public boolean remove(Receive receive);

	@ResultType(Boolean.class)
	@Select({
			"select count(receivePkid) from receive where userPkid=#{userPkid} and sheng=#{sheng} and shi=#{shi} and qu=#{qu} and userAddress =#{userAddress} and userPhone=#{userPhone}" })
	public boolean queryReceive(Receive receive);
}
