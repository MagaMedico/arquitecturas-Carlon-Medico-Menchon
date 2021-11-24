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
import edu.grocery.model.Product;
/**
 * 
 * @author Cecilia Carlón: ceciliacarlon2@gmail.com
		   Magalí Médico: magamedico@gmail.com
		   Magalí Menchón: mamenchon@alumnos.exa.unicen.edu.ar	
 * @version 2.0
 * @since 22/11/2021
 * Repositorio de la entidad @see BillProduct que implementa la interfaz
 * propia de JPA @see JpaRepository que contiene las operaciones básicas
 * que se pueden realizar a la base de datos por medio del ORM, además
 * de consultas específicas que se definen en esta interfaz.
 */
public interface BillProductRepository extends JpaRepository<BillProduct, Object>  {
	/**
	 * Constantes utilizadas en los parámetros
	 */
	static final String PRODUCT = "product",
						BILL = "bill",
						DATE = "date",
						QUANTITY = "quantity",
						ID = "id",
						CLIENT = "client",
						BILL_PRODUCT = "billProduct";

	/**
	 * Transacción para insertar registro correspondiente a la tabla
	 * resultante del mapeo con la entidad @see BillProduct
	 * mediante una consulta SQL (nativeQuery)
	 * @param date fecha de la creación de la factura
	 * @param quantity cantidad del producto que compró a detallar en la factura
	 * @param bill long identificador de bill @see Bill
	 * @param product long identificador del producto @see Product
	 */
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO bill_product(DATE, QUANTITY, BILL_BILL_ID, PRODUCT_ID) VALUES (?1, ?2, ?3, ?4) " 
	+ "WHERE (SELECT dni FROM bill) = ?5 "
	, nativeQuery = true)
	public void insertBillProduct(LocalDate date, int quantity, long bill, long product, long client);

	/**Actualiza los datos pasados por parámetro en la tabla BillProduct
	 * mediante una consulta JPQL
	 * @param product entidad del tipo @see Product
	 * @param bill entidad del tipo @see Bill
	 * @param date fecha a reemplazar
	 * @param quantity cantidad a reemplazar
	 * @param id long identificador único para buscar de @see BillProduct
	 */
	@Modifying
	@Query("UPDATE BillProduct SET product = :product, bill = :bill, date = :date, quantity = :quantity WHERE id = :id")
	public void updateBillProduct(@Param(PRODUCT) Product product, @Param(BILL) Bill bill, 
								  @Param(DATE) LocalDate date, @Param(QUANTITY) int quantity, @Param(ID) long id);
	
	/**Obtiene las facturas de un cliente dado por medio de una consulta JPQL
	 * @param client entidad de @see Client
	 * @return una lista con cada reporte de tipo @see ReportEntireAmount
	 */
	@Modifying
	@Query("SELECT new ReportEntireAmount(bp.bill.client.name, SUM(bp.quantity*bp.product.unitPrice)) "
			+ "FROM BillProduct AS bp "
			+ "GROUP BY bp.bill.client.name ")
	public List<ReportEntireAmount> getBillProductOfClient();

	
	/**
	 * Obtiene las ventas agrupadas y ordenadas descendentemente por fecha,
	 * mostrando por cada una de ellas los productos con la cantidad y ganancia total en esa fecha
	 * @return Listado con cada reporte de tipo @see ReportDailySalesDTO
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
	 * Obtiene los productos ordenados por cantidad de venta
	 * @return una lista @see BestProductDTO
	 */
	@Modifying
	@Query("SELECT new BestProductDTO(bp.product.name, SUM(bp.quantity)) "
			+ "FROM BillProduct AS bp "
			+ "GROUP BY bp.product.name "
			+ "ORDER BY bp.quantity DESC ")
	public List<BestProductDTO> getBestProduct();
}
