package edu.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pojo.BillProduct;
import edu.services.BillProductService;

@RestController
@RequestMapping("/bills")
public class BillProductController {
	public static Logger LOG = LoggerFactory.getLogger(BillProductController.class); 
	@Autowired
	private BillProductService serviceBill;
	
	@GetMapping("")
	public List<BillProduct> getAll() {
		return this.serviceBill.getBills();
	}
}