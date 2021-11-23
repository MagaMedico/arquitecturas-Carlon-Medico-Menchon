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

import edu.grocery.model.Client;
import edu.grocery.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/clients")
@Api(value= "ClientController", description= "Api of the client controller")
public class ClientController {
	public static Logger LOG = LoggerFactory.getLogger(ClientController.class); 
	@Autowired
	private ClientService serviceClient;
	
	@ApiOperation(value="Get all clients", response= List.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	@GetMapping("")
	public List<Client> getAll() {
		return this.serviceClient.getClients();
	}
	
	@ApiOperation(value="Add a client", response= ResponseEntity.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	@PostMapping("")
	public ResponseEntity<?> addClient(@RequestBody Client client) {
		boolean ok = this.serviceClient.insert(client);
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(client, HttpStatus.CREATED);
	}
	
	@ApiOperation(value="Delete a client", response= ResponseEntity.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	@DeleteMapping(value = "/{DNI}")
	public ResponseEntity<?> deleteClient(@PathVariable("DNI") Long dni) {
		boolean ok = this.serviceClient.delete(dni);
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(dni, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value="Update a client", response= ResponseEntity.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
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