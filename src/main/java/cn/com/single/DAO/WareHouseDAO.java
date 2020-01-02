package cn.com.single.DAO;

import java.util.List;

public interface WareHouseDAO {

	public int findNum(int proId, int colorId, int sizeId);

	public List<Integer> findColors(int proId);

	public List<Integer> findSizes(int proId);

	public int findColor(String colorType);

	public int findSize(String sizeTye);

	public int findWareId(String colorType, String sizeType, int proId);
}
