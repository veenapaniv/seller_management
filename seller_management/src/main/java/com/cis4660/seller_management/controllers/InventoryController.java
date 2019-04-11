package com.cis4660.seller_management.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cis4660.seller_management.model.Inventory;
import com.cis4660.seller_management.service.InventoryService;


@Controller
public class InventoryController {
	@Autowired
	InventoryService inventoryService;
	
	private List<String> initialChannels;
	private File initialFile;
	private int deleteId;
	@RequestMapping("/inventory")
	public ModelAndView getInventories() {
		List<Inventory> inventory = inventoryService.getInventory();
		ModelAndView model = new ModelAndView("inventory");
		model.addObject("inventories",inventory);
		return model;
	}
	@RequestMapping(value="/editInventory", method=  RequestMethod.GET)
	public ModelAndView editInventory(@RequestParam("id") int id) {
		List<Inventory> inventory = new ArrayList<Inventory>();
		Inventory myInventory = inventoryService.getProductById(id);
		inventory.add(myInventory);
		initialChannels = new ArrayList<String>();
		initialChannels = myInventory.getChannels();
		initialFile = myInventory.getUploadedFile();
		ModelAndView model = new ModelAndView("editInventory");
		model.addObject("inventories",inventory);
		return model;
	}
	@RequestMapping(value = "/editInventory", method = RequestMethod.POST)
	public String editInventory(@RequestParam(value="productId") int productId, @RequestParam("quantity") int quantity, @RequestParam("file") File file, @RequestParam("amount") float amount, @RequestParam("shipping") float shipping, @RequestParam("productName") String productName, @RequestParam(value="channels", required=false) String[] channels, @ModelAttribute("inventory") Inventory inventory) {
		
		List<String> channelList = new ArrayList<String>();
		if(channels !=  null) {
			for(int i=0;i<channels.length;i++) {
				channelList.add(channels[i]);
				inventory.setChannels(channelList);
			}
		}else {
			inventory.setChannels(initialChannels);
		}
		if(file != null) {
			inventory.setUploadedFile(file);
		}else {
			inventory.setUploadedFile(initialFile);
		}
		inventory.setProductId(productId);
		inventory.setAmount(amount);
		inventory.setQuantity(quantity);
		inventory.setShippingRate(shipping);
		inventory.setProductName(productName);
		inventoryService.updateProduct(inventory);
		return "redirect:/inventory";
	}
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public ModelAndView show() {
		return new ModelAndView("addProduct", "inventory", new Inventory());
	}
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String processRequest(@RequestParam("quantity") int quantity, @RequestParam("file") File file, @RequestParam("amount") float amount, @RequestParam("shipping") float shipping, @RequestParam("productName") String productName, @RequestParam("channels") String[] channels, @ModelAttribute("inventory") Inventory inventory) {
		inventory = new Inventory();
		List<String> channelList = new ArrayList<String>();
		for(int i=0;i<channels.length;i++) {
			channelList.add(channels[i]);
			inventory.setChannels(channelList);
		}
		inventory.setAmount(amount);
		inventory.setQuantity(quantity);
		inventory.setUploadedFile(file);
		inventory.setShippingRate(shipping);
		inventory.setProductName(productName);
		inventoryService.insertProduct(inventory);
		return "redirect:/inventory";
	}
	
	@RequestMapping("/deleteInventory")
	public ModelAndView deleteInventory(@RequestParam("id") int id) {
		deleteId = id;
		ModelAndView model = new ModelAndView("deleteInventory");
		return model;
	}
	@RequestMapping(value="/deleteInventory", method = RequestMethod.POST)
	public String deleteProduct() {
		inventoryService.deleteProduct(deleteId);
		return "redirect:/inventory";
	}
}
