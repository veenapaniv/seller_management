package com.cis4660.seller_management.model;

public class Order {
	
	private int orderId;
	private int productId;
	private String status;
	private java.sql.Timestamp ordered_date; 
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public java.sql.Timestamp getOrdered_date() {
		return ordered_date;
	}
	public void setOrdered_date(java.sql.Timestamp ordered_date) {
		this.ordered_date = ordered_date;
	}
	

	

}
