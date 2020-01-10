package cn.com.demo.javaweb.shopping.entity.toshow;

public class ShowProductAdmin {
	private String imgUrl;
	private int proId;
	private String proName;
	private double price;
	private int catalogId;
	private String sex;
	private String catalogTypeOne;
	private String catalogTypeTwo;
	private String proDes;
	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ShowProductAdmin [imgUrl=" + imgUrl + ", proId=" + proId + ", proName=" + proName + ", price=" + price
				+ ", catalogId=" + catalogId + ", sex=" + sex + ", catalogTypeOne=" + catalogTypeOne
				+ ", catalogTypeTwo=" + catalogTypeTwo + ", proDes=" + proDes + ", userId=" + userId + "]";
	}

	public String getProDes() {
		return proDes;
	}

	public void setProDes(String proDes) {
		this.proDes = proDes;
	}

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCatalogTypeOne() {
		return catalogTypeOne;
	}

	public void setCatalogTypeOne(String catalogTypeOne) {
		this.catalogTypeOne = catalogTypeOne;
	}

	public String getCatalogTypeTwo() {
		return catalogTypeTwo;
	}

	public void setCatalogTypeTwo(String catalogTypeTwo) {
		this.catalogTypeTwo = catalogTypeTwo;
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

}
