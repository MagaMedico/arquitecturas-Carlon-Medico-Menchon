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

import edu.grocery.dto.BestProductDTO;
import edu.grocery.dto.ReportDailySalesDTO;
import edu.grocery.dto.ReportEntireAmount;
import edu.grocery.model.BillProduct;
import edu.grocery.services.BillProductService;
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
 * El RestController de @see BillProduct da acceso al manejo de operaciones
 * CRUD por medio de las URLs donde cada método retorna el tipo de respuesta
 * obtenida luego de cada transacción mediante el servicio @see BillProductService
 */
@RestController
@RequestMapping("/bills")
@Api(value= "BillProductController", description= "Api of the bill product controller")
public class BillProductController {
	/**Atributos
	 */
	public static Logger LOG = LoggerFactory.getLogger(BillProductController.class); 
	@Autowired
	private BillProductService serviceBill;
	
	/**Obtener todas las facturas
	 * @return una lista de @see BillProduct
	 */
	@ApiOperation(value="Get all bill products", response= List.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	@GetMapping("")
	public List<BillProduct> getAll() {
		return this.serviceBill.getBills();
	}
	
	/**
	 * Agrega la factura pasada mediante el requestBody
	 * @param bp entidad del tipo @see BillProduct
	 * @return HTTP response, incluyendo headers, body y status.
	 * @see ResposeEntity
	 */
	@ApiOperation(value="Add a bill product", response= ResponseEntity.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	@PostMapping("")
	public ResponseEntity<?> addBillProduct(@RequestBody BillProduct bp) {
		//Agrega la factura a la DB
		boolean ok = this.serviceBill.insert(bp);
		//Chequea el estado de la consulta y lo informa
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(bp, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina la factura que coincida con el id pasado por url
	 * @param id identificador único de la factura a borrar
	 * @return HTTP response, incluyendo headers, body y status.
	 * @see ResposeEntity
	 */
	@ApiOperation(value="Delete a bill product", response= ResponseEntity.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteBillProduct(@PathVariable("id") Long id) {
		//Elimina la factura de la DB
		boolean ok = this.serviceBill.delete(id);
		//Chequea el estado de la consulta y lo informa
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Reemplaza los datos de la factura que coincida con el id pasado por url
	 * por los nuevos datos pasados mediante el requestBody
	 * @param id long identificador único de la factura a modificar
	 * @param bp del tipo @see BillProduct con los datos de la factura
	 * @return HTTP response, incluyendo headers, body y status.
	 * @see ResposeEntity
	 */
	@ApiOperation(value="Update a bill product", response= ResponseEntity.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateBillProduct(@PathVariable( "id" ) Long id, @RequestBody BillProduct bp) {
		boolean ok = false;
		if(bp != null) {
			//Hace el update de la factura en la DB
			ok = this.serviceBill.update(bp.getProduct(), bp.getBill(), bp.getDate(), bp.getQuantity(), id);
		}
		//Chequea el estado de la consulta y lo informa
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	/**
	 * Obtiene los reportes con el total de las facturas de cada cliente
	 * @return lista de @see ReportEntireAmount
	 */
	@ApiOperation(value="Get reports of the entire amout of the clients", response= List.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	@GetMapping("/reportClientAmount")
	public List<ReportEntireAmount> getReportClientsEntireAmount() {
		return this.serviceBill.getBillProductOfClient();
	}
	
	/**
	 * Obtiene los reportes con el total de ventas por día
	 * @return lista de @see ReportEntireAmount
	 */
	@ApiOperation(value="Get reports of the daily sales", response= List.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	@GetMapping("/reportDailySales")
	public List<ReportDailySalesDTO> getReportDailySales() {
		return this.serviceBill.getDailySales();
	}
	
	/**
	 * Obtiene el reporte del producto más vendido
	 * @return entidad del tipo @see BestProductDTO 
	 */
	@ApiOperation(value="Get report of the best product", response= BestProductDTO.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	@GetMapping("/mostSoldProduct")
	public BestProductDTO getBestProduct(){
		return this.serviceBill.getBestProduct();
	}
}