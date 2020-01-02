package cn.com.demo.javaweb.shopping.dao;

import org.apache.ibatis.annotations.Select;

import cn.com.demo.javaweb.shopping.entity.Img;

public interface IImgDao {
	@Select({ "select * from img where proId = #{proId} and type = 1" })
	public Img getMainImg(int proId);

}
