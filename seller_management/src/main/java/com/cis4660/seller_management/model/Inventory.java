package com.cis4660.seller_management.model;

import java.io.File;
import java.sql.Blob;
import java.util.List;

public class Inventory {
	private int productId;
	private String productName;
	private Blob productImage;
	private String image;
	private int quantity;
	private float amount;
	private float shippingRate;
	private java.sql.Timestamp lastUpdated;
	private List<String> channels;
	private int sold;
	private int returned;
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<String> getChannels() {
		return channels;
	}
	public void setChannels(List<String> channels) {
		this.channels = channels;
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public int getReturned() {
		return returned;
	}
	public void setReturned(int returned) {
		this.returned = returned;
	}
	private File uploadedFile;
	
	public File getUploadedFile() {
		return uploadedFile;
	}
	public void setUploadedFile(File uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
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
