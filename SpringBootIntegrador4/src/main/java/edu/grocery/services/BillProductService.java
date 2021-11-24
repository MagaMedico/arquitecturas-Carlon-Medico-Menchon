package edu.grocery.services;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.grocery.dto.BestProductDTO;
import edu.grocery.dto.ReportDailySalesDTO;
import edu.grocery.dto.ReportEntireAmount;
import edu.grocery.model.Bill;
import edu.grocery.model.BillProduct;
import edu.grocery.model.Product;
import edu.grocery.repositories.BillProductRepository;
/**
 * 
 * @author Cecilia Carlón: ceciliacarlon2@gmail.com
		   Magalí Médico: magamedico@gmail.com
		   Magalí Menchón: mamenchon@alumnos.exa.unicen.edu.ar	
 * @version 2.0
 * @since 22/11/2021
 * Permite acceder al repositorio @see BillProductRepository
 * a fin de desacoplar la implementación del mismo y posibilitar
 * el acceso a los servicios que brindaría la aplicación
 * relacionados a la entidad @see BillProduct
 */
@Service
public class BillProductService {
	/**
	 * Atributo instanciado por única vez y automáticamente
	 * @see Autowired
	 */
	@Autowired
	private BillProductRepository bills;
	
	/**Obtiene todas las facturas
	 * @return una lista de @see BillProduct
	 * @see List
	 */
	public List<BillProduct> getBills() {
		return this.bills.findAll();
	}
	
	/**Agrega la factura pasada por parametro
	 * @param bp entidad de tipo @see BillProduct
	 * @return boolean del resultado de la transacción
	 */
	@Transactional
	public boolean insert(BillProduct bp) {
			this.bills.save(bp); 
			return true;
	}
	
	/**Reemplaza los datos de una factura identificada
	 * por su id con los datos recibidos por parámetro
	 * @param product entidad del tipo @see Product
	 * @param bill entidad del tipo @see Bill
	 * @param date fecha de generación de factura @see Date
	 * @param quantity int con los datos de cantidad de producto en esa factura
	 * @param id identificador único de @see BillProduct a modificar
	 * @return boolean del resultado de la transacción
	 */
	@Transactional
	public boolean update(Product product, Bill bill, LocalDate date, int quantity, long id) {
		this.bills.updateBillProduct(product, bill, date, quantity, id);
		return true;
	}
	
	/**Elimina la factura que coincida con el id pasado por parametro
	 * @param id long del identificador único de la facutura a borrar
	 * @return boolean del resultado de la transacción
	 */
	@Transactional
	public boolean delete(long id) {
		this.bills.deleteById(id);
		return true;
	}
	
	/**Obtiene las facturas de un cliente dado
	 * @param client entidad de @see Client
	 * @return una lista con los reportes generados
	 * @see ReportEntireAmount
	 * @see List
	 */
	public List<ReportEntireAmount> getBillProductOfClient(){
		return this.bills.getBillProductOfClient();
	}
	
	/**
	 * Obtiene las ventas por día
	 * @return una lista con los reportes generados
	 * @see ReportDailySalesDTO
	 * @see List
	 */
	public List<ReportDailySalesDTO> getDailySales() {
		return this.bills.getDailySales();
	}
	
	/**
	 * Retorna una lista de productos ordenada por cantidad
	 * de productos vendidos
	 * @return una lista de @see Product
	 */
	public BestProductDTO getBestProduct(){
		return this.bills.getBestProduct().get(0); 
	}
}
