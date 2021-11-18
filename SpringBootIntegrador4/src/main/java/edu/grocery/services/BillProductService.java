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

@Service
public class BillProductService {
	/**Atributo
	 */
	@Autowired
	private BillProductRepository bills;
	
	/**Metodo para obtener todas las facturas
	 * @return una lista de @see BillProduct
	 */
	public List<BillProduct> getBills() {
		return this.bills.findAll();
	}	
	/**Metodo para agregar la factura pasada por parametro
	 * @param bp
	 * @return un booleano
	 */
	@Transactional
	public boolean insert(BillProduct bp) {
		/*long total = this.bills.cumpleCondicion(bp.getQuantity(), bp.getBill().getClient().getDNI(), bp.getDate(), bp.getProduct().getId(), bp.getBill().getBillId());
		if(total < 4) {*/
			this.bills.save(bp); 
			return true;
		//}else return false;
	}
	/**Metodo para reemplazar los datos de una factura dada
	 * @param product
	 * @param bill
	 * @param date
	 * @param quantity
	 * @param id
	 * @return un booleano
	 */
	@Transactional
	public boolean update(Product product, Bill bill, LocalDate date, int quantity, long id) {
		this.bills.updateBillProduct(product, bill, date, quantity, id);
		return true;
	}
	
	/**Metodo para eliminar la factura que conicida con el id pasado por parametro
	 * @param id
	 * @return un booleano
	 */
	@Transactional
	public boolean delete(long id) {
		this.bills.deleteById(id);
		return true;
	}
	
	/**Metodo para obtener las facturas de un cliente dado
	 * @param client
	 * @return una lista de @see BillProduct
	 */
	public List<ReportEntireAmount> getBillProductOfClient(){
		return this.bills.getBillProductOfClient();
	}
	/**
	 * Metodo para obtener las salidas diarias
	 * @return una lista de @see ReportDailySalesDTO
	 */
	public List<ReportDailySalesDTO> getDailySales() {
		return this.bills.getDailySales();
	}
	/**
	 * Metodo que retorna una lista de productos ordenada por cantidad
	 * de productos vendidos
	 * @return una lista de @see Product
	 */
	public BestProductDTO getBestProduct(){
		return this.bills.getBestProduct().get(0); 
	}
}
