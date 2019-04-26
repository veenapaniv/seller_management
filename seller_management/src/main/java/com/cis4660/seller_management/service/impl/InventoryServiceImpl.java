package com.cis4660.seller_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cis4660.seller_management.dao.InventoryDao;
import com.cis4660.seller_management.model.Inventory;
import com.cis4660.seller_management.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService{
	
	@Autowired
	InventoryDao inventoryDao;

	@Override
	public List<Inventory> getInventory() {
		return inventoryDao.getInventory();
	}

	@Override
	public void insertProduct(Inventory inventory) {
		inventoryDao.insertProduct(inventory);
	}

	@Override
	public Inventory getProductById(int productId) {
		return inventoryDao.getProductById(productId);
	}

	@Override
	public void updateProduct(Inventory inventory) {
		inventoryDao.updateProduct(inventory);
		
	}

	@Override
	public void deleteProduct(int productId) {
		inventoryDao.deleteProduct(productId);
		
	}

}
