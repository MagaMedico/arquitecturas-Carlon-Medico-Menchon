package edu.grocery.repositories;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.grocery.dto.BestProductDTO;
import edu.grocery.dto.ReportDailySalesDTO;
import edu.grocery.dto.ReportEntireAmount;
import edu.grocery.model.Bill;
import edu.grocery.model.BillProduct;
import edu.grocery.model.Client;
import edu.grocery.model.Product;

public interface BillProductRepository extends JpaRepository<BillProduct, Object>  {
	/**
	 * Parametros
	 */
	static final String PRODUCT = "product",
						BILL = "bill",
						DATE = "date",
						QUANTITY = "quantity",
						ID = "id",
						CLIENT = "client";
	static final int MAX = 3;
	/**
	 * 
	 * @param product
	 * @param bill
	 * @param date
	 * @param quantity
	 */
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO bill_product(DATE, QUANTITY, BILL_BILL_ID, PRODUCT_ID) VALUES (?1, ?2, ?3, ?4) " 
	+ "WHERE (SELECT dni FROM bill) = ?5 "
	//+ "AND date = ?1 AND SUM(quantity + ?2) < MAX"
	, nativeQuery = true)
	public void insertBillProduct(LocalDate date, int quantity,
			long bill, long product, long client);
	
	/**Metodo para actualizar los datos pasados por parametro en la tabla BillProduct
	 * @param product
	 * @param bill
	 * @param date
	 * @param quantity
	 * @param id
	 */
	@Modifying
	@Query("UPDATE BillProduct SET product = :product, bill = :bill, date = :date, quantity = :quantity WHERE id = :id")
	public void updateBillProduct(@Param(PRODUCT) Product product, @Param(BILL) Bill bill, 
								  @Param(DATE) LocalDate date, @Param(QUANTITY) int quantity, @Param(ID) long id);
	
	/**Metodo para obtener las facturas de un cliente dado
	 * @param client
	 * @return una lista de @see BillProduct
	 */
	@Modifying
	@Query("SELECT new ReportEntireAmount(bp.bill.client.name, SUM(bp.quantity*bp.product.unitPrice)) "
			+ "FROM BillProduct AS bp "
			+ "GROUP BY bp.bill.client.name ")
	public List<ReportEntireAmount> getBillProductOfClient();

	
	/**
	 * Obtiene las ventas agrupadas y ordenadas descentemente por fecha,
	 * mostrando por cada una de ellas los productos con la cantidad y ganancia total en esa fecha
	 * @return Listado con cada reporte de tipo DailySalesDTO
	 * @see ReportDailySalesDTO
	 */
	@Query("SELECT new ReportDailySalesDTO( "
			+ "bp.date, "
			+ "bp.product.name, "
			+ "SUM(bp.product.unitPrice * bp.quantity), "
			+ "SUM(bp.quantity) ) "
			+ "FROM BillProduct bp "
			+ "GROUP BY bp.date, bp.product.name "
			+ "ORDER BY bp.date DESC ")
	public List<ReportDailySalesDTO> getDailySales();
	/**
	 * Metodo que obtiene los productos ordenados por cantidad de venta
	 * @return una lista @see Product
	 */
	@Modifying
	@Query("SELECT new BestProductDTO(bp.product.name, SUM(bp.quantity)) "
			+ "FROM BillProduct AS bp "
			+ "GROUP BY bp.product.name "
			+ "ORDER BY bp.quantity DESC ")
	public List<BestProductDTO> getBestProduct();
}
