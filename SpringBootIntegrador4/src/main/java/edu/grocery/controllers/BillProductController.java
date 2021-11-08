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

@RestController
@RequestMapping("/bills")
public class BillProductController {
	/**Atributos
	 */
	public static Logger LOG = LoggerFactory.getLogger(BillProductController.class); 
	@Autowired
	private BillProductService serviceBill;
	
	/**Metodo para obtener todas las facturas
	 * @return una lista de @see BillProduct
	 */
	@GetMapping("")
	public List<BillProduct> getAll() {
		return this.serviceBill.getBills();
	}
	
	//Metodo para agregar la factura pasada mediante el requestBody
	@PostMapping("")
	public ResponseEntity<?> addBillProduct(@RequestBody BillProduct bp) {
		//Agrega la factura a la DB
		boolean ok = this.serviceBill.insert(bp);
		//Chequea el estado de la consulta y lo informa
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(bp, HttpStatus.CREATED);
	}
	
	//Metodo para eliminar la factura que conicida con el id pasado por url
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteBillProduct(@PathVariable("id") Long id) {
		//Elimina la factura de la DB
		boolean ok = this.serviceBill.delete(id);
		//Chequea el estado de la consulta y lo informa
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
	}
	
	//Metodo para reemplazar los datos de la factura que coincida con el id pasado por url por los nuevos datos 
	//pasados mediante el requestBody
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
	
	//Metodo para obtener el reporte del total de las facturas de los clientes
	@GetMapping("/reportClientAmount")
	public List<ReportEntireAmount> getReportClientsEntireAmount() {
		return this.serviceBill.getBillProductOfClient();
	}
	
	/**
	 * 4) Genere un reporte con las ventas por día.
	 */
	@GetMapping("/reportDailySales")
	public List<ReportDailySalesDTO> getReportDailySales() {
		return this.serviceBill.getDailySales();
	}
	/**
	 * Metodo que hace el get a la base de datos para traerse
	 * la lista de productos
	 */
	@GetMapping("/mostSoldProduct")
	public BestProductDTO getBestProduct(){
		return this.serviceBill.getBestProduct();
	}
}