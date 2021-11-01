package edu.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pojo.Product;
import edu.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	public static Logger LOG = LoggerFactory.getLogger(ProductController.class); 
	@Autowired
	private ProductService serviceProduct;
	
	@GetMapping("")
	public List<Product> getAll() {
		return this.serviceProduct.getProducts();
	}
}