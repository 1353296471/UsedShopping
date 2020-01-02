//package Test;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.junit.jupiter.api.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.stereotype.Component;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//
//import cn.com.demo.javaweb.shopping.dao.IShowWarehouseDao;
//import cn.com.demo.javaweb.shopping.dao.IUserDao;
//import cn.com.demo.javaweb.shopping.entity.User;
//import cn.com.demo.javaweb.shopping.entity.toshow.ShowWarehouse;
//import cn.com.demo.mvc.initailizer.SpringConfig;
//
//@Component
//public class TestDemo {
//	private IUserDao userDao;
//	private IShowWarehouseDao showWarehouseDao;
//
//	public SqlSessionFactory getSqlSessionFactory() throws IOException {
//		String resource = "mybatis-config.xml";
//		InputStream inputStream = Resources.getResourceAsStream(resource);
//		return new SqlSessionFactoryBuilder().build(inputStream);
//	}
//
//	public void initApplicationContext() {
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
//		userDao = ctx.getBean(IUserDao.class);
//		showWarehouseDao = ctx.getBean(IShowWarehouseDao.class);
//
//	}
//
//	@Test
//	public void test05() {
//		initApplicationContext();
//		PageHelper.startPage(3, 5);
//		// startPage后面紧跟的这个查询就是一个分页查询
//		List<ShowWarehouse> s = showWarehouseDao.getAllShowWarehouse();
//		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
//		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
//		PageInfo page = new PageInfo(s, 5);
//		System.out.println(page.getList().size());
//
//	}
//
//	@Test
//	public void test04() {
//		initApplicationContext();
//		User user = userDao.getUserById(1);
//		User user1 = userDao.getUser("13@qq.com", "123");
//		System.out.println(user);
//		System.out.println(userDao.isCheck("13@qq.com"));
//
//	}
//
//	@Test
//	public void test03() throws Exception {
//		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//		SqlSession openSession = sqlSessionFactory.openSession();
//		try {
//			IUserDao userDao = openSession.getMapper(IUserDao.class);
//			User user = userDao.getUserById(1);
//			System.out.println(user);
//			System.out.println(userDao.payMoney(user, 20));
//		} finally {
//			openSession.close();
//		}
//	}
//}
