package edu.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pojo.Client;
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
}