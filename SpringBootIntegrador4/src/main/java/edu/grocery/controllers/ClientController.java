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
/**
 * @author Cecilia Carlón: ceciliacarlon2@gmail.com
		   Magalí Médico: magamedico@gmail.com
		   Magalí Menchón: mamenchon@alumnos.exa.unicen.edu.ar	
 * @version 2.0
 * @since 22/11/2021
 * El RestController de @see Client da acceso al manejo de operaciones
 * CRUD por medio de las URLs donde cada método retorna el tipo de respuesta
 * obtenida luego de cada transacción mediante el servicio @see ClientService
 */
@RestController
@RequestMapping("/clients")
@Api(value= "ClientController", description= "Api of the client controller")
public class ClientController {
	/**
	 * Atributos
	 */
	/**
	 * Logs interno de la aplicación reflejados en consola
	 * @see Logger
	 */
	public static Logger LOG = LoggerFactory.getLogger(ClientController.class); 
	/**
	 * Atributo instanciado por única vez y automáticamente
	 */
	@Autowired
	private ClientService serviceClient;
	
	/**
	 * Obtiene todos los clientes de la base de datos mediante el servicio
	 * @return Lista de @see Client
	 */
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
	
	/**
	 * Retorna la respuesta HTTP al pedido de inserción de un cliente
	 * @param client entidad del tipo @see Client dada en el body de la petición
	 * @return HTTP response, incluyendo headers, body y status.
	 * @see ResposeEntity
	 */
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
	
	/**
	 * Retorna la respuesta HTTP al pedido de eliminación de un cliente
	 * @param dni identificador único del cliente a eliminar dado por el path de la URL
	 * @return HTTP response, incluyendo headers, body y status.
	 * @see ResposeEntity
	 */
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
	
	/**
	 * Retorna la respuesta HTTP al pedido de edición de un cliente
	 * @param dni long identificador único del cliente a eliminar dado por el path de la URL
	 * @param client entidad del tipo @see Client dada en el body de la petición
	 * @return HTTP response, incluyendo headers, body y status.
	 * @see ResposeEntity
	 */
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