package edu.grocery.services;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.grocery.model.Bill;
import edu.grocery.model.BillProduct;
import edu.grocery.model.Client;
import edu.grocery.model.Product;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BillProductServiceTest {

	@Autowired
	BillProductService service;
	@Autowired
	ProductService servicep;
	@Autowired
	ClientService servicec;
	@Autowired
	BillService serviceb;
	
	@Test @Order(1)
	public void testGet() {
		Assertions.assertEquals(10,service.getBills().size());
	}
	
	
	@Test @Order(2)
	public void testPost() {
		Product product = new Product("Harina", 150);
		Assertions.assertTrue(servicep.insert(product));
		Client client = new Client(14556332, "Caren", "Perez");
		Assertions.assertTrue(servicec.insert(client));
		Bill bill = new Bill(client);
		Assertions.assertTrue(serviceb.insert(bill));
		BillProduct billP = new BillProduct(product, null, LocalDate.of(2020, 1, 8), 5);
		
		Assertions.assertTrue(service.insert(billP));
	}
	
	@Test @Order(4)
	public void testDelete() {
		Assertions.assertTrue(service.delete(12));
	}
	
	@Test @Order(3)
	public void testPut() {
		Product product = new Product("Harina", 150);
		Assertions.assertTrue(servicep.insert(product));
		Client client = new Client(14556332, "Caren", "Perez");
		Assertions.assertTrue(servicec.insert(client));
		Bill bill = new Bill(client);
		Assertions.assertTrue(serviceb.insert(bill));
		
		Assertions.assertTrue(service.update(product, bill, LocalDate.of(2021, 1, 8), 5, (long)11));
	}
}
