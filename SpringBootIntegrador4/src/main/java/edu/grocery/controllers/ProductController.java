package edu.grocery.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.grocery.model.Bill;
import edu.grocery.model.Product;
import edu.grocery.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	public static Logger LOG = LoggerFactory.getLogger(ProductController.class); 
	@Autowired
	private ProductService serviceProduct;
	
	@GetMapping("")
	public List<Product> getAll() {
		LOG.info("Ejecutando");
		return this.serviceProduct.getProducts();
	}
	
	@PostMapping("")
	public ResponseEntity<?> addProduct(@RequestBody Product product) {
		boolean ok = this.serviceProduct.insert(product);
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(product, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
		boolean ok = this.serviceProduct.delete(id);
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable( "id" ) Long id, @RequestBody Product product) {
		boolean ok = false;
		if(product != null) {
			ok = this.serviceProduct.update(product.getName(), product.getUnitPrice(), id);
		}
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public Product getProductById(@PathVariable("id") Long id) {
		
		return this.serviceProduct.findById(id);
	}
}