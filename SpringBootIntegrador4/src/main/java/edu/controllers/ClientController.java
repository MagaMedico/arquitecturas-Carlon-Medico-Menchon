package edu.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pojo.Client;
import edu.pojo.Product;
import edu.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	public static Logger LOG = LoggerFactory.getLogger(ClientController.class); 
	@Autowired
	private ClientService serviceClient;
	
	@GetMapping("")
	public List<Client> getAll() {
		return this.serviceClient.getClients();
	}
	
	@PostMapping("")
	public ResponseEntity<?> addClient(@RequestBody Client client) {
		boolean ok = this.serviceClient.insert(client);
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(client, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
		boolean ok = this.serviceClient.delete(id);
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(id, HttpStatus.OK);
	}
	/*
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable( "id" ) Long id, @RequestBody Product product) {
		boolean ok = false;
		if(product != null) {
			ok = this.serviceProduct.update(product.getName(), product.getUnitPrice(), id);
		}
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(id, HttpStatus.OK);
	}*/
}