package cn.com.demo.javaweb.shopping.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.com.demo.javaweb.shopping.entity.Img;

public interface IImgDao {
	@Select({ "select * from img where proId = #{proId} and type = 1" })
	public Img getMainImg(int proId);

	@Select({ "select count(id) from img where imgUrl = #{imgUrl}" })
	public Boolean getImgUrlSame(String imgUrl);

	@Insert({ "insert into img (imgUrl,type,proId) values (#{imgUrl},#{type},#{proId})" })
	public Boolean addImg(Img img);
}
