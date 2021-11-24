package edu.grocery.controllers;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.grocery.model.Bill;
import edu.grocery.repositories.BillRepository;
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
 * El RestController de @see Bill da acceso al manejo de operaciones
 * CRUD por medio de las URLs donde cada método retorna el tipo de respuesta
 * obtenida luego de cada transacción mediante el repositorio @see BillRepository
 */
@RestController
@RequestMapping("/b")
@Api(value= "BillController", description= "Api of the bill controller")
public class BillController {
	/**
	 * Atributo instanciado por única vez y automáticamente
	 */
	@Autowired
	private BillRepository repositoryB;
	/**
	 * Retorna el producto dado por el id deentro del path de la URL
	 * @param id long identificador único del producto a retornar
	 * @return entidad del tipo @see Bill
	 */
	@ApiOperation(value="Get product by id", response= Bill.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	
	@Transactional
	@GetMapping(value = "/{id}")
	public Bill getProductById(@PathVariable("id") Long id) {
		
		return this.repositoryB.findById(id).get();
	}
}
