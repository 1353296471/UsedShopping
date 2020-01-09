package cn.com.demo.javaweb.shopping.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.com.demo.javaweb.shopping.entity.ProDes;

public interface IProDesDao {

	@Update({ "update prodes set proDes = #{proDes} where proDesPkid = #{proDesPkid}" })
	public boolean updateProDes(ProDes proDes);

	@Select({ "select * from proDes where proDesPkid = #{proId}" })
	public ProDes getProDes(int proId);

	@Insert({ "insert into proDes values (#{proDesPkid},#{proDes})" })
	public boolean addProDes(ProDes proDes);

}
