package edu.grocery.controllers;

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

import edu.grocery.pojo.Client;
import edu.grocery.services.ClientService;

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
		else return new ResponseEntity<>(client, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{DNI}")
	public ResponseEntity<?> deleteClient(@PathVariable("DNI") Long dni) {
		boolean ok = this.serviceClient.delete(dni);
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(dni, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(value = "/{DNI}")
	public ResponseEntity<?> updateClient(@PathVariable("DNI") long dni, @RequestBody Client client) {
		boolean ok = false;
		if(client != null) {
			ok = this.serviceClient.update(client.getDNI(), client.getName(), client.getLastname(), dni);
		}
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(dni, HttpStatus.OK);
	}
}