package cn.com.demo.javaweb.shopping.entity.toshow;

public class ShowWarehouse {
	private int id;
	private String imgUrl;
	private int proId;
	private String proName;
	private double price;
	private String sizeType;
	private String colorType;
	private int num;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "ShowWarehouse [id=" + id + ", imgUrl=" + imgUrl + ", proId=" + proId + ", proName=" + proName
				+ ", price=" + price + ", sizeType=" + sizeType + ", colorType=" + colorType + ", num=" + num + "]";
	}

}
