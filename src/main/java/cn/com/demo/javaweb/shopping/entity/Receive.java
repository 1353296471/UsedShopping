package cn.com.demo.javaweb.shopping.entity;

public class Receive {
	private int receivePkid;
	private int userPkid;
	private String sheng;
	private String shi;
	private String qu;
	private String userAddress;
	private String userPhone;

	public int getReceivePkid() {
		return receivePkid;
	}

	public void setReceivePkid(int receivePkid) {
		this.receivePkid = receivePkid;
	}

	public int getUserPkid() {
		return userPkid;
	}

	public void setUserPkid(int userPkid) {
		this.userPkid = userPkid;
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

	@Override
	public String toString() {
		return "Receive [receivePkid=" + receivePkid + ", userPkid=" + userPkid + ", sheng=" + sheng + ", shi=" + shi
				+ ", qu=" + qu + ", userAddress=" + userAddress + ", userPhone=" + userPhone + "]";
	}

	public Receive() {
		// TODO 自动生成的构造函数存根
	}
}
