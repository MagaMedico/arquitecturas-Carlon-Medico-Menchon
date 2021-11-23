package edu.grocery.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.grocery.model.Product;
import edu.grocery.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/products")
@Api(value= "ProductController", description= "Api of the product controller")
public class ProductController {
	public static Logger LOG = LoggerFactory.getLogger(ProductController.class); 
	@Autowired
	private ProductService serviceProduct;
	
	@ApiOperation(value="Get all products", response= List.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	@GetMapping("")
	public List<Product> getAll() {
		LOG.info("Ejecutando");
		return this.serviceProduct.getProducts();
	}
	
	@ApiOperation(value="Add a product", response= ResponseEntity.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	@PostMapping("")
	public ResponseEntity<?> addProduct(@RequestBody Product product) {
		boolean ok = this.serviceProduct.insert(product);
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(product, HttpStatus.CREATED);
	}
	
	@ApiOperation(value="Delete a product", response= ResponseEntity.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
		boolean ok = this.serviceProduct.delete(id);
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value="Update a product", response= ResponseEntity.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
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