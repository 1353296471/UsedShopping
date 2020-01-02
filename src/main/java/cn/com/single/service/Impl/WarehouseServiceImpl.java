package cn.com.single.service.Impl;

import java.util.List;

import cn.com.single.DAO.WareHouseDAO;
import cn.com.single.DAO.Impl.WareHouseDAOImpl;
import cn.com.single.service.WareHouseService;

public class WarehouseServiceImpl implements WareHouseService {
	private WareHouseDAO warehouseDao = new WareHouseDAOImpl();

	@Override
	public List<Integer> findColors(int proId) {
		return this.warehouseDao.findColors(proId);
	}

	@Override
	public List<Integer> findSizes(int proId) {
		return this.warehouseDao.findSizes(proId);
	}

	@Override
	public int findNum(int proId, int colorId, int sizeId) {
		return this.warehouseDao.findNum(proId, colorId, sizeId);
	}

	@Override
	public int findColor(String colorType) {
		return this.warehouseDao.findColor(colorType);
	}

	@Override
	public int findSize(String sizeTye) {
		return this.warehouseDao.findSize(sizeTye);
	}

	@Override
	public int findWareId(String colorType, String sizeType, int proId) {
		return this.warehouseDao.findWareId(colorType, sizeType, proId);
	}

}
