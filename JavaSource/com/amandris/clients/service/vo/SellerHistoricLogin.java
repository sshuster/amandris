package com.amandris.clients.service.vo;import java.io.Serializable;import java.util.Date;public class SellerHistoricLogin implements Serializable{	private Integer id;	private Integer sellerId;	private Date 	date;	private Integer isLogin;	private String  ipAddress;	private Seller 	seller;			private static final long serialVersionUID = 6526471155622776015L;		public Date getDate() {		return date;	}	public void setDate(Date date) {		this.date = date;	}	public Integer getId() {		return id;	}	public void setId(Integer id) {		this.id = id;	}	public Integer getIsLogin() {		return isLogin;	}	public void setIsLogin(Integer isLogin) {		this.isLogin = isLogin;	}	public Seller getSeller() {		return seller;	}	public void setSeller(Seller seller) {		this.seller = seller;	}	public Integer getSellerId() {		return sellerId;	}	public void setSellerId(Integer sellerId) {		this.sellerId = sellerId;	}	public String getIpAddress() {		return ipAddress;	}	public void setIpAddress(String ipAddress) {		this.ipAddress = ipAddress;	}	}