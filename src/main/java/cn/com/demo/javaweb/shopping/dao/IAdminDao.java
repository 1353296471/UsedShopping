package cn.com.demo.javaweb.shopping.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.com.demo.javaweb.shopping.entity.Admin;

public interface IAdminDao {

	@Select({ "select count(id) from admin where email = #{param1} and password = #{param2}" })
	public boolean isRight(String email, String password);

	@Insert({ "insert into admin (adminName , password , email) values (#{adminName},#{password},#{email}) " })
	public boolean addAdmin(Admin admin);

	@Select({ "select count(id) from admin where email = #{email}" })
	public boolean isCheck(String email);

	@Select({ "select * from admin where email = #{param1} and password = #{param2}" })
	public Admin getAdmin(String email, String password);

	@Select({ "select * from admin where id = #{id,jdbcType=INTEGER}" })
	public Admin getAdminById(int adminId);
}
