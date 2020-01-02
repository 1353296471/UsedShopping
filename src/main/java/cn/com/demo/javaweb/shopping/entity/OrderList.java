package cn.com.demo.javaweb.shopping.entity;

import java.sql.Timestamp;

public class OrderList {
	private int orderPkid;
	private int warehouseId;
	private int proNum;
	private int receivePkid;
	private int orderConditionPkid;
	private Timestamp orderTime;

	public int getProNum() {
		return proNum;
	}

	public void setProNum(int proNum) {
		this.proNum = proNum;
	}

	public int getOrderPkid() {
		return orderPkid;
	}

	public void setOrderPkid(int orderPkid) {
		this.orderPkid = orderPkid;
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public int getReceivePkid() {
		return receivePkid;
	}

	public void setReceivePkid(int receivePkid) {
		this.receivePkid = receivePkid;
	}

	public int getOrderConditionPkid() {
		return orderConditionPkid;
	}

	public void setOrderConditionPkid(int orderConditionPkid) {
		this.orderConditionPkid = orderConditionPkid;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public OrderList() {
		// TODO 自动生成的构造函数存根
	}

}
