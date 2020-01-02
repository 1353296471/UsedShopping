package cn.com.demo.javaweb.shopping.entity.toshow;

public class ShowShopCar {
	private ShowProduct showProduct;
	private int warehouseId;
	private String sizeType;
	private String colorType;
	private int num;

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public ShowProduct getShowProduct() {
		return showProduct;
	}

	public void setShowProduct(ShowProduct showProduct) {
		this.showProduct = showProduct;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getSizeType() {
		return sizeType;
	}

	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}

	public String getColorType() {
		return colorType;
	}

	public void setColorType(String colorType) {
		this.colorType = colorType;
	}

	public ShowShopCar() {
		// TODO 自动生成的构造函数存根
	}
}
