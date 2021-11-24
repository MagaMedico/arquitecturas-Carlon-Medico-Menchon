package edu.grocery.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.grocery.model.Client;
import edu.grocery.repositories.ClientRepository;
/**
 * 
 * @author Cecilia Carlón: ceciliacarlon2@gmail.com
		   Magalí Médico: magamedico@gmail.com
		   Magalí Menchón: mamenchon@alumnos.exa.unicen.edu.ar	
 * @version 2.0
 * @since 22/11/2021
 * Permite acceder al repositorio @see ClientRepository
 * a fin de desacoplar la implementación del mismo y posibilitar
 * el acceso a los servicios que brindaría la aplicación
 * relacionados a la entidad @see Client
 */
@Service
public class ClientService {
	
	/**
	 * Atributo instanciado por única vez y automáticamente
	 * @see Autowired
	 */
	@Autowired
	private ClientRepository clients;
	
	/**Obtiene todos clientes
	 * @return una lista de @see Client
	 * @see List
	 */
	public List<Client> getClients() {
		return this.clients.findAll();
	}
	
	/**
	 * Agrega el cliente dado por parámetro a la base de datos
	 * @param c entidad de tipo @see Client
	 * @return boolean del resultado de la transacción
	 */
	@Transactional
	public boolean insert(Client c) {
		this.clients.save(c);
		return true;
	}
	
	/**
	 * Reemplaza los datos de un cliente identificado
	 * por su dni con los datos recibidos por parámetro
	 * @param newdni dni a reemplazar
	 * @param name String del nombre a reemplazar
	 * @param lastname String del apellido a reemplazar
	 * @param dni long del cliente al que se le modificaran los datos
	 * @return boolean del resultado de la transacción
	 */
	@Transactional
	public boolean update(long newdni, String name, String lastname, long dni) {
		this.clients.updateClient(newdni, name, lastname, dni);
		return true;
	}
	
	/**
	 * Elimina de la base de datos el cliente con el dni recibido por parámetro
	 * @param dni long del identificador único del cliente
	 * @return boolean del resultado de la transacción
	 */
	@Transactional
	public boolean delete(long dni) {
		this.clients.deleteById(dni);
		return true;
	}
}
