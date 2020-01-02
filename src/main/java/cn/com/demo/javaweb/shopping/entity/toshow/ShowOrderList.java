package cn.com.demo.javaweb.shopping.entity.toshow;

import java.sql.Timestamp;

public class ShowOrderList {
	private String userName;
	private String imgUrl;
	private int orderPkid;
	private int proId;
	private String proName;
	private String sizeType;
	private String colorType;
	private int proNum;
	private double price;
	private String sheng;
	private String shi;
	private String qu;
	private String userAddress;
	private String userPhone;
	private int orderConditionPkid;
	private String conditionType;
	private Timestamp orderTime;
	private int commentPkid;
	private String commentDes;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getOrderPkid() {
		return orderPkid;
	}

	public void setOrderPkid(int orderPkid) {
		this.orderPkid = orderPkid;
	}

	public String getCommentDes() {
		return commentDes;
	}

	public void setCommentDes(String commentDes) {
		this.commentDes = commentDes;
	}

	public int getCommentPkid() {
		return commentPkid;
	}

	public void setCommentPkid(int commentPkid) {
		this.commentPkid = commentPkid;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public int getOrderConditionPkid() {
		return orderConditionPkid;
	}

	public void setOrderConditionPkid(int orderConditionPkid) {
		this.orderConditionPkid = orderConditionPkid;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public int getProNum() {
		return proNum;
	}

	public void setProNum(int proNum) {
		this.proNum = proNum;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSheng() {
		return sheng;
	}

	public void setSheng(String sheng) {
		this.sheng = sheng;
	}

	public String getShi() {
		return shi;
	}

	public void setShi(String shi) {
		this.shi = shi;
	}

	public String getQu() {
		return qu;
	}

	public void setQu(String qu) {
		this.qu = qu;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public ShowOrderList() {
		// TODO 自动生成的构造函数存根
	}
}
