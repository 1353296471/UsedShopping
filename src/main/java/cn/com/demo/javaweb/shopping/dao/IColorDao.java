package cn.com.demo.javaweb.shopping.dao;

import org.apache.ibatis.annotations.Select;

import cn.com.demo.javaweb.shopping.entity.Color;

public interface IColorDao {
	@Select({ "select * from color where colorPkid = #{colorPkid}" })
	public Color getColor(int colorPkid);
}
