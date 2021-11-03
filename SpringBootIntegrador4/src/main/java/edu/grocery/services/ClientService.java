package edu.grocery.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.grocery.irepositories.ClientRepository;
import edu.grocery.pojo.Client;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clients;
	
	public List<Client> getClients() {
		return this.clients.findAll();
	}
	
	@Transactional
	public boolean insert(Client c) {
		this.clients.save(c);
		return true;
	}
	@Transactional
	public boolean update(long newdni, String name, String lastname, long dni) {
		this.clients.updateClient(newdni, name, lastname, dni);
		return true;
	}
	@Transactional
	public boolean delete(long dni) {
		this.clients.deleteById(dni);
		return true;
	}
}
