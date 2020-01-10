package cn.com.demo.javaweb.shopping.service.imp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.com.demo.javaweb.shopping.dao.ICatalogDao;
import cn.com.demo.javaweb.shopping.dao.IImgDao;
import cn.com.demo.javaweb.shopping.dao.IOrderDao;
import cn.com.demo.javaweb.shopping.dao.IProDao;
import cn.com.demo.javaweb.shopping.dao.IProDesDao;
import cn.com.demo.javaweb.shopping.dao.IReceiveDao;
import cn.com.demo.javaweb.shopping.dao.IShopCarDao;
import cn.com.demo.javaweb.shopping.dao.IShowOrderListDao;
import cn.com.demo.javaweb.shopping.dao.IShowProductAdminDao;
import cn.com.demo.javaweb.shopping.dao.IUserDao;
import cn.com.demo.javaweb.shopping.dao.IWarehouseDao;
import cn.com.demo.javaweb.shopping.entity.Catalog;
import cn.com.demo.javaweb.shopping.entity.Img;
import cn.com.demo.javaweb.shopping.entity.ProDes;
import cn.com.demo.javaweb.shopping.entity.Product;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowOrderList;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowProductAdmin;
import cn.com.demo.javaweb.shopping.service.IPersonalService;

@Transactional
@Service
public class IPersonalServiceImpl implements IPersonalService {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IProDao proDao;

	@Autowired
	private IReceiveDao receiveDao;

	@Autowired
	private IOrderDao orderDao;

	@Autowired
	private IShopCarDao shopCarDao;

	@Autowired
	private IWarehouseDao warehouseDao;

	@Autowired
	private IImgDao imgDao;

	@Autowired
	private IProDesDao proDesDao;

	@Autowired
	private IShowOrderListDao showOrderListDao;
	@Autowired
	private IShowProductAdminDao showProductAdminDao;
	@Autowired
	private ICatalogDao catalogDao;

	@Override
	public List<ShowOrderList> getShowOrderLists(int userId) {
		List<ShowOrderList> items = new ArrayList<ShowOrderList>();

//		// 1.根据userId获取所有receive
//		List<Receive> res = receiveDao.getReceives(userId);
//
//		for (Receive re : res) {
//			// 2.根据receiveId获取所有的order
//			for (OrderList o : orderDao.getOrders(re.getReceivePkid())) {
//				// 3.根据order中的proPkid获取对应的商品product
//				Product pro = proDao.getProduct(o.getProPkid());
//				// 4.根据获取product得到对应的图片
//				Img mainImg = imgDao.getMainImg(pro.getId());
//				// 5.将数据封装在ShowOrderList中，用来显示
//				ShowOrderList so = new ShowOrderList();
//				so.setMainImg(mainImg);
//				so.setPro(pro);
//				so.setOrderList(o);
//				so.setReceive(re);
//				if (o.getOrderConditionPkid() == 1) {
//					so.setConditionType("订单正在处理");
//				} else {
//					so.setConditionType("已发货");
//				}
//
//				items.add(so);
//			}
//		}
//		// 6.根据时间顺序排序，倒序
//		items.sort(new Comparator<ShowOrderList>() {
//			@Override
//			public int compare(ShowOrderList o1, ShowOrderList o2) {
//				if (o1.getOrderList().getOrderTime().getTime() >= o2.getOrderList().getOrderTime().getTime()) {
//					return -1; // 负整数表示将往前移动
//				}
//				return 1;// 正整数表示将往后移动
//			}
//
//		});
		items = showOrderListDao.getShowOrderLists(userId);
		return items;
	}

	@Override
	public boolean chargeMoney(int userId, double money) {
		boolean falg = false;
		if (money >= 0) {
			falg = userDao.chargeMoney(userId, money);
		}
		return falg;
	}

	@Override
	public List<ShowOrderList> getShowOrderListsByPage(int userId, int pageNum, int pageSize) {
		int index = (pageNum - 1) * pageSize;
		List<ShowOrderList> itemsPage = showOrderListDao.getShowOrderListsByPage(userId, index, pageSize);
		return itemsPage;
	}

	@Override
	public int getMaxPage(int userId, int pageSize) {
		int maxPage = (showOrderListDao.getShowOrderLists(userId).size() + pageSize - 1) / pageSize;
		return maxPage;
	}

	@Override
	public double getCash(int userId) {
		double cash = 0;
		cash = userDao.getUserById(userId).getMoney();
		return cash;
	}

	@Override
	public List<ShowProductAdmin> getAllShowMyPostedByPage(int pageNo, int pageSize, int userId) {
		int index = (pageNo - 1) * pageSize;
		List<ShowProductAdmin> itemsPage = showProductAdminDao.getAllShowMyPostedByPage(index, pageSize, userId);
		return itemsPage;
	}

	@Override
	public int getProMaxPage(int pageSize, int userId) {
		int maxPage = (showProductAdminDao.getAllShowMyPosted(userId).size() + pageSize - 1) / pageSize;
		return maxPage;
	}

	@Override
	public List<ShowOrderList> getAllShowMySoldByPage(int pageNo, int pageSize, int userId) {
		int index = (pageNo - 1) * pageSize;
		List<ShowOrderList> itemsPage = showOrderListDao.getAllShowMySoldByPage(index, pageSize, userId);
		return itemsPage;
	}

	@Override
	public int getMySoldMaxPage(int pageSize, int userId) {
		int maxPage = (showOrderListDao.getAllShowMySold(userId).size() + pageSize - 1) / pageSize;
		return maxPage;
	}

	@Override
	public Boolean releaseProduct(ShowProductAdmin showProductAdmin, MultipartFile imgFile) throws Exception {
		boolean flag = false;
		Product pro = new Product();
		pro.setUserId(showProductAdmin.getUserId());
		pro.setPrice(showProductAdmin.getPrice());
		pro.setProName(showProductAdmin.getProName());
		pro.setCatalogId(catalogDao.getCatalogByTypeOne(showProductAdmin.getCatalogTypeOne()).getCatalogId());

		ProDes proDes = new ProDes();
		proDes.setProDes(showProductAdmin.getProDes());

		proDao.addProductBackId(pro);
		int proId = pro.getId();
		System.out.println("proId" + proId);

		if (proId != 0) {
			proDes.setProDesPkid(proId);
			proDesDao.addProDes(proDes);

		}

		// String imgUrl = "";
		// 为img文件设定唯一名称，保存到images目录下
//		do {
//			imgUrl = UUID.randomUUID().toString();
//		} while (!imgDao.getImgUrlSame(imgUrl));

		MultipartFile file = imgFile;
		String dir = ResourceUtils.getURL("classpath:static/images/").toURI().getPath();
		String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1,
				file.getOriginalFilename().length());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Random r = new Random();
		String imgName = "";
		do {
			if ("jpg".equals(type)) {
				imgName = sdf.format(new Date()) + r.nextInt(100) + ".jpg";
			} else if ("png".equals(type)) {
				imgName = sdf.format(new Date()) + r.nextInt(100) + ".png";
			} else if ("jpeg".equals(type)) {
				imgName = sdf.format(new Date()) + r.nextInt(100) + ".jpeg";
			} else if ("gif".equals(type)) {
				imgName = sdf.format(new Date()) + r.nextInt(100) + ".gif";
			} else {
				return false;
			}
		} while (imgDao.getImgUrlSame(imgName));

		// 在数据库中添加img的文件路径
		Img img = new Img();
		img.setImgUrl(imgName);
		img.setProId(proId);
		img.setType(1);
		imgDao.addImg(img);

		// 将文件流写入到磁盘中
		System.out.println(dir + "--" + imgName);
		FileUtils.writeByteArrayToFile(new File(dir, imgName), file.getBytes());
		flag = true;
		return flag;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			System.out.println(UUID.randomUUID());
		}

	}

	@Override
	public List<Catalog> getAllCatalogs() {
		// TODO 自动生成的方法存根
		return catalogDao.getAllCatalogs();
	}
}
