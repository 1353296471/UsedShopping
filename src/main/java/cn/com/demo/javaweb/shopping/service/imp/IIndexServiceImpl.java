package cn.com.demo.javaweb.shopping.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.demo.javaweb.shopping.dao.ICatalogDao;
import cn.com.demo.javaweb.shopping.dao.IColorDao;
import cn.com.demo.javaweb.shopping.dao.IImgDao;
import cn.com.demo.javaweb.shopping.dao.IProDao;
import cn.com.demo.javaweb.shopping.dao.IShopCarDao;
import cn.com.demo.javaweb.shopping.dao.ISizeDao;
import cn.com.demo.javaweb.shopping.dao.IWarehouseDao;
import cn.com.demo.javaweb.shopping.entity.Product;
import cn.com.demo.javaweb.shopping.entity.ShopCar;
import cn.com.demo.javaweb.shopping.entity.Warehouse;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowProduct;
import cn.com.demo.javaweb.shopping.entity.toshow.ShowShopCar;
import cn.com.demo.javaweb.shopping.service.IIndexService;

@Service
public class IIndexServiceImpl implements IIndexService {
	@Autowired
	private IProDao proDao;
	@Autowired
	private IImgDao imgDao;
	@Autowired
	private IColorDao colorDao;
	@Autowired
	private ISizeDao sizeDao;
	@Autowired
	private IShopCarDao shopCarDao;
	@Autowired
	private ICatalogDao catalogDao;
	@Autowired
	private IWarehouseDao warehouseDao;

	@Override
	public List<ShowProduct> getShowProduct() {
		// TODO 自动生成的方法存根
		List<ShowProduct> showProducts = new ArrayList<ShowProduct>();
		List<Product> proList = proDao.getIndexProduct();
		for (Product pro : proList) {
			ShowProduct showProduct = new ShowProduct();
			showProduct.setPro(pro);
			showProduct.setMainImg(imgDao.getMainImg(pro.getId()));
			showProduct.setCatalog(catalogDao.getCatalog(pro.getCatalogId()));
			showProducts.add(showProduct);
		}
		return showProducts;
	}

	@Override
	public boolean addShopCarItem(int userId, int warehouseId, int num) {
		ShopCar shopCar = new ShopCar();
		shopCar.setUserId(userId);
		shopCar.setWarehouseId(warehouseId);
		shopCar.setNum(num);

		if (shopCarDao.queryShopCar(shopCar.getUserId(), shopCar.getWarehouseId())) {
			return shopCarDao.addNumShopCar(shopCar);
		} else {
			return shopCarDao.insertShopCar(shopCar);
		}
	}

	@Override
	public List<ShowShopCar> getShopCar(int userId) {
		List<ShowShopCar> items = new ArrayList<ShowShopCar>();
		List<ShopCar> shopCars = shopCarDao.getShopCarById(userId);
		for (ShopCar shopCar : shopCars) {
			ShowShopCar item = new ShowShopCar();
			ShowProduct showProduct = new ShowProduct();
			System.out.println("getWarehouseId = " + shopCar.getWarehouseId());
			Warehouse warehouse = warehouseDao.getWarehouse(shopCar.getWarehouseId());
			int proId = warehouse.getProId();
			showProduct.setPro(proDao.getProduct(proId));
			showProduct.setMainImg(imgDao.getMainImg(proId));
			item.setShowProduct(showProduct);
			item.setNum(shopCar.getNum());
			String colorType = colorDao.getColor(warehouse.getColorId()).getColorType();
			String sizeType = sizeDao.getSize(warehouse.getSizeId()).getSizeType();
			item.setColorType(colorType);
			item.setSizeType(sizeType);
			item.setWarehouseId(shopCar.getWarehouseId());
			items.add(item);
		}
		return items;
	}

	@Override
	public boolean removeShopCarItem(int userId, int warehouseId, int num) {
		if (num <= 0) {
			return deleteShopCarItem(userId, warehouseId);
		} else {
			ShopCar shopCar = new ShopCar();
			shopCar.setUserId(userId);
			shopCar.setWarehouseId(warehouseId);
			shopCar.setNum(num);

			ShopCar sc = shopCarDao.getShopCar(userId, warehouseId);
			if (sc.getNum() <= 1) {
				return shopCarDao.deleteShopCar(shopCar);
			} else {
				return shopCarDao.removeNumShopCar(shopCar);
			}

		}
	}

	@Override
	public boolean deleteShopCarItem(int userId, int warehouseId) {
		ShopCar shopCar = new ShopCar();
		shopCar.setUserId(userId);
		shopCar.setWarehouseId(warehouseId);
		return shopCarDao.deleteShopCar(shopCar);
	}

}
