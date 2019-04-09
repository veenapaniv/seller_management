package com.cis4660.seller_management.model;

import java.sql.Blob;

public class Inventory {
	private int productId;
	private String productName;
	private Blob productImage;
	private String image;
	private int quantity;
	private float amount;
	private float shippingRate;
	private java.sql.Timestamp lastUpdated;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Blob getProductImage() {
		return productImage;
	}
	public void setProductImage(Blob productImage) {
		this.productImage = productImage;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getShippingRate() {
		return shippingRate;
	}
	public void setShippingRate(float shippingRate) {
		this.shippingRate = shippingRate;
	}
	public java.sql.Timestamp getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(java.sql.Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
}