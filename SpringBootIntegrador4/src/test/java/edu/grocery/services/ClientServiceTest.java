package edu.grocery.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.grocery.model.Client;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ClientServiceTest {

	@Autowired
	ClientService service;
	
	@Test @Order(1)
	public void testGet() {
		Assertions.assertEquals(10,service.getClients().size());
	}
	
	@Test @Order(2)
	public void testPost() {
		Client cliente = new Client(14556332, "Caren", "Perez");
		
		Assertions.assertTrue(service.insert(cliente));
	}
	
	@Test @Order(4)
	public void testDelete() {
		Assertions.assertTrue(service.delete(14556332));
	}
	
	@Test @Order(3)
	public void testPut() {
		Assertions.assertTrue(service.update(5000, "Luis", "Miguel", 14556332));
	}
}
