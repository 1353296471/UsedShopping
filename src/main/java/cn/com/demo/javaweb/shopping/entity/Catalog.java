package cn.com.demo.javaweb.shopping.entity;

public class Catalog {
	private int catalogId;
	private String sex;
	private String catalogTypeOne;
	private String catalogTypeTwo;

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

	public Catalog() {
		// TODO 自动生成的构造函数存根
	}
}
