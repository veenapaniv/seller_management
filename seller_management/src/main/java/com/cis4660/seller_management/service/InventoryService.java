package com.cis4660.seller_management.service;

import java.util.List;

import com.cis4660.seller_management.model.Inventory;

public interface InventoryService {
	List<Inventory> getInventory();
	void insertProduct(Inventory inventory);
}
