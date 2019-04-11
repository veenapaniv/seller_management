package com.cis4660.seller_management.dao;

import java.util.List;

import com.cis4660.seller_management.model.Inventory;

public interface InventoryDao {
	List<Inventory> getInventory();
	Inventory getProductById(int productId);
	void insertProduct(Inventory inventory);
	void updateProduct(Inventory inventory);
	void deleteProduct(int productId);
}
