package com.cis4660.seller_management.controllers;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public ModelAndView show() {
		return new ModelAndView("addProduct", "inventory", new Inventory());
	}
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView processRequest(@RequestParam("quantity") int quantity, @RequestParam("file") File file, @RequestParam("amount") float amount, @RequestParam("shipping") float shipping, @RequestParam("productName") String productName, @ModelAttribute("inventory") Inventory inventory) {
		inventory = new Inventory();
		inventory.setAmount(amount);
		inventory.setQuantity(quantity);
		inventory.setUploadedFile(file);
		inventory.setShippingRate(shipping);
		inventory.setProductName(productName);
		inventoryService.insertProduct(inventory);
		List<Inventory> inventories = inventoryService.getInventory();
		ModelAndView model = new ModelAndView("addProduct");
		model.addObject("inventories", inventories);
		return model;
	}
}
