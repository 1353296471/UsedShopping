package cn.com.demo.javaweb.shopping.dao;

import org.apache.ibatis.annotations.Select;

import cn.com.demo.javaweb.shopping.entity.Size;

public interface ISizeDao {
	@Select({ "select * from size where sizePkid = #{sizePkid}" })
	public Size getSize(int sizePkid);
}
