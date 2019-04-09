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
		// TODO Auto-generated method stub
		return inventoryDao.getInventory();
	}
}
