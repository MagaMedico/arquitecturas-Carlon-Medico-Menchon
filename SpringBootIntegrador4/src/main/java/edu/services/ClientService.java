package edu.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.irepositories.ClientRepository;
import edu.pojo.Client;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clients;
	
	public List<Client> getClients() {
		return this.clients.findAll();
	}
	
	@Transactional
	public boolean insert() {
		return true;
	}
	@Transactional
	public boolean update() {
		return true;
	}
	@Transactional
	public boolean delete() {
		return true;
	}
}
