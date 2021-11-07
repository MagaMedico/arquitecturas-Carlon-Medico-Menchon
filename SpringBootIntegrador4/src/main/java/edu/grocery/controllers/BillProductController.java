package edu.grocery.controllers;

import java.util.ArrayList;
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
import edu.grocery.pojo.BillProduct;
import edu.grocery.pojo.Client;
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
	@GetMapping("report/clients/entireAmount")
	public String getReportClientsEntireAmount() {
		List<ReportEntireAmount> reportes = new ArrayList<ReportEntireAmount>();
		List<Client> clients = new ArrayList<Client>();
		//Obtiene todos los clientes que tengan facturas
		List<Client> allClients = this.serviceBill.getClients();
		//Recorre todos los clientes con facturas
		for(int i=0; i < allClients.size(); i++) {
			//Si el cliente ya tiene un reporte, es decir, mas de una factura
			if(clients.contains(allClients.get(i))){
				//Recorre los reportes
				for(int j=0; j < reportes.size(); j++) {
					//Si el cliente del reporte coincide con el cliente actual
					if(reportes.get(j).getClient().equals(allClients.get(i))) {
						//Obtiene las facturas del cliente
						List<BillProduct> bills = this.serviceBill.getBillProductOfClient(allClients.get(i));
						//Las agrega al reporte ya existente
						reportes.get(j).addBills(bills);
						//Vuelve a calcular el total
						reportes.get(j).calculateEntireAmount();
					}
				}
			}//Si el cliente todavia no tiene un reporte
			else {
				//Agrega al cliente a la lista de clientes (no repetidos)
				clients.add(allClients.get(i));
				//Obtiene las facturas del cliente
				List<BillProduct> bills = this.serviceBill.getBillProductOfClient(allClients.get(i));
				//Crea el reporte del cliente
				reportes.add(new ReportEntireAmount(allClients.get(i), bills));
			}
		}//Retorna todos los reportes existentes.
		return reportes.toString();
	}
	
	/**
	 * 4) Genere un reporte con las ventas por día.
	 */
	@GetMapping("/report/dailySales")
	public List<ReportDailySalesDTO> getReportDailySales() {
		return this.serviceBill.getDailySales();
	}
	/**
	 * Metodo que hace el get a la base de datos para traerse
	 * la lista de productos
	 */
	@GetMapping("/best")
	public List<BestProductDTO> getBestProduct(){
		return this.serviceBill.getBestProduct();
	}
}