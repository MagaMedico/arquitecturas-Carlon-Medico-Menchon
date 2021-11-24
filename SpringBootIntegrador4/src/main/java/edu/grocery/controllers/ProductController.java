package edu.grocery.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.grocery.model.Product;
import edu.grocery.services.ProductService;
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
 * El RestController de @see Product da acceso al manejo de operaciones
 * CRUD por medio de las URLs donde cada método retorna el tipo de respuesta
 * obtenida luego de cada transacción mediante el servicio @see ProductService
 */
@RestController
@RequestMapping("/products")
@Api(value= "ProductController", description= "Api of the product controller")
public class ProductController {
	/**
	 * Atributos
	 */
	/**
	 * Logs interno de la aplicación reflejados en consola
	 * @see Logger
	 */
	public static Logger LOG = LoggerFactory.getLogger(ProductController.class); 
	/**
	 * Atributo instanciado por única vez y automáticamente
	 */
	@Autowired
	private ProductService serviceProduct;
	
	@ApiOperation(value="Get all products", response= List.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	
	/**
	 * Obtiene todos los productos de la base de datos mediante el servicio
	 * @return Lista de @see Product
	 */
	@GetMapping("")
	public List<Product> getAll() {
		LOG.info("Ejecutando");
		return this.serviceProduct.getProducts();
	}

	/**
	 * Retorna la respuesta HTTP al pedido de inserción de un cliente
	 * @param product entidad del tipo @see Product dada en el body de la petición
	 * @return HTTP response, incluyendo headers, body y status.
	 * @see ResposeEntity
	 */
	@ApiOperation(value="Add a product", response= ResponseEntity.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	@PostMapping("")
	public ResponseEntity<?> addProduct(@RequestBody Product product) {
		boolean ok = this.serviceProduct.insert(product);
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(product, HttpStatus.CREATED);
	}

	/**
	 * Retorna la respuesta HTTP al pedido de eliminación de un cliente
	 * @param id identificador único del producto a eliminar dado por el path de la URL
	 * @return HTTP response, incluyendo headers, body y status.
	 * @see ResposeEntity
	 */
	@ApiOperation(value="Delete a product", response= ResponseEntity.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
		boolean ok = this.serviceProduct.delete(id);
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Retorna la respuesta HTTP al pedido de edición de un cliente
	 * @param id identificador único del producto a eliminar dado por el path de la URL
	 * @param product entidad del tipo @see Product dada en el body de la petición a reemplazar
	 * @return HTTP response, incluyendo headers, body y status.
	 * @see ResposeEntity
	 */
	@ApiOperation(value="Update a product", response= ResponseEntity.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable( "id" ) Long id, @RequestBody Product product) {
		boolean ok = false;
		if(product != null) {
			ok = this.serviceProduct.update(product.getName(), product.getUnitPrice(), id);
		}
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	/**
	 * Retorna la respuesta HTTP al pedido de búsqueda de un determinado producto
	 * @param id long del identificador único del producto a buscar dado por el path de la URL
	 * @return entidad del tipo @see Product
	 */
	@GetMapping(value = "/{id}")
	public Product getProductById(@PathVariable("id") Long id) {
		
		return this.serviceProduct.findById(id);
	}
}