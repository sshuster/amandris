package com.amandris.clients.service.vo;import java.io.Serializable;public class AlbumElement implements Serializable{	private Integer 	id;	private Integer 	sellerId;	private Integer 	mediaTypeConst;	private Integer		code;	private String 		text;	private Integer 	views;	private Integer 	votes;	private Integer 	active;	private Seller 		seller;	private static final long serialVersionUID = 4526471155622776001L;			public Integer getActive() {		return active;	}	public void setActive(Integer active) {		this.active = active;	}	public Integer getCode() {		return code;	}	public void setCode(Integer code) {		this.code = code;	}	public Integer getId() {		return id;	}	public void setId(Integer id) {		this.id = id;	}	public Integer getMediaTypeConst() {		return mediaTypeConst;	}	public void setMediaTypeConst(Integer mediaTypeConst) {		this.mediaTypeConst = mediaTypeConst;	}	public Seller getSeller() {		return seller;	}	public void setSeller(Seller seller) {		this.seller = seller;	}	public Integer getSellerId() {		return sellerId;	}	public void setSellerId(Integer sellerId) {		this.sellerId = sellerId;	}	public String getText() {		return text;	}	public void setText(String text) {		this.text = text;	}	public Integer getViews() {		return views;	}	public void setViews(Integer views) {		this.views = views;	}	public Integer getVotes() {		return votes;	}	public void setVotes(Integer votes) {		this.votes = votes;	}	}