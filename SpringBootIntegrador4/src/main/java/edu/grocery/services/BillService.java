package edu.grocery.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.grocery.model.Bill;
import edu.grocery.repositories.BillRepository;

/**
 * 
 * @author Cecilia Carlón: ceciliacarlon2@gmail.com
		   Magalí Médico: magamedico@gmail.com
		   Magalí Menchón: mamenchon@alumnos.exa.unicen.edu.ar	
 * @version 2.0
 * @since 22/11/2021
 * Permite acceder al repositorio @see BillRepository
 * a fin de desacoplar la implementación del mismo y posibilitar
 * el acceso a los servicios que brindaría la aplicación
 * relacionados a la entidad @see Bill
 */
@Service
public class BillService {
	/**
	 * Atributo instanciado por única vez y automáticamente
	 * @see Autowired
	 */
	@Autowired
	private BillRepository bills;
	
	/**Obtiene todos clientes
	 * @return una lista de @see Bill
	 * @see List
	 */
	public List<Bill> getBills() {
		return this.bills.findAll();
	}
	/**
	 * Agrega el cliente dado por parámetro a la base de datos
	 * @param b entidad de tipo @see Bill
	 * @return boolean del resultado de la transacción
	 */
	@Transactional
	public boolean insert(Bill b) {
		this.bills.save(b);
		return true;
	}
}
