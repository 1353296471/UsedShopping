package cn.com.search.entity;

public class Product1 {
	private int id;
	private String proName;
	private Double price;
	private String imgUrl;
	
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getId() {
		return id;
	}
	public String getProName() {
		return proName;
	}
	public Double getPrice() {
		return price;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product1 [id=" + id + ", proName=" + proName + ", price=" + price + ", imgUrl=" + imgUrl + "]";
	}

	
}
