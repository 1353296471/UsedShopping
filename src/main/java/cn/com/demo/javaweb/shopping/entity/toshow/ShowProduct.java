package cn.com.demo.javaweb.shopping.entity.toshow;

import cn.com.demo.javaweb.shopping.entity.Catalog;
import cn.com.demo.javaweb.shopping.entity.Img;
import cn.com.demo.javaweb.shopping.entity.Product;

public class ShowProduct {
	private Product pro;
	private Catalog catalog;
	private Img mainImg;

	public Product getPro() {
		return pro;
	}

	public void setPro(Product pro) {
		this.pro = pro;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public Img getMainImg() {
		return mainImg;
	}

	public void setMainImg(Img mainImg) {
		this.mainImg = mainImg;
	}

	public ShowProduct() {
		// TODO 自动生成的构造函数存根
	}
}
