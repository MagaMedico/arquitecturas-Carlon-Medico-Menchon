package edu.grocery.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import edu.grocery.model.Product;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

	@Autowired
	ProductService service;
	
	@Test @Order(1)
	public void testGet() {
		Assertions.assertEquals(10,service.getProducts().size());
	}
	
	
	@Test @Order(2)
	public void testPost() {
		Product product = new Product("Harina", 150);
		
		Assertions.assertTrue(service.insert(product));
	}
	
	@Test @Order(4)
	public void testDelete() {
		Assertions.assertTrue(service.delete((long) 21));
	}
	
	@Test @Order(3)
	public void testPut() {
		Assertions.assertTrue(service.update("Palta", 120, 10));
	}
}

