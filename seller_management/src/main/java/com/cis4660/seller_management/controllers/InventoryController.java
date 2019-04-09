package com.cis4660.seller_management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cis4660.seller_management.model.Inventory;
import com.cis4660.seller_management.service.InventoryService;

@Controller
public class InventoryController {
	/*@RequestMapping("/inventory")
	public ModelAndView inventoryPage() {
		return new ModelAndView("inventory");
	}*/
	@Autowired
	InventoryService inventoryService;
	@RequestMapping("/inventory")
	public ModelAndView getInventories() {
		List<Inventory> inventory = inventoryService.getInventory();
		ModelAndView model = new ModelAndView("inventory");
		model.addObject("inventories",inventory);
		return model;
	}
	@RequestMapping("editInventory")
	public ModelAndView editInventory() {
		return new ModelAndView("editInventory");
	}
}
