package edu.grocery.irepositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.grocery.dto.BestProductDTO;
import edu.grocery.dto.ReportDailySalesDTO;
import edu.grocery.pojo.Bill;
import edu.grocery.pojo.BillProduct;
import edu.grocery.pojo.Client;
import edu.grocery.pojo.Product;

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
	
	/**Metodo para obtener todos los clientes que tengan facturas
	 * @return una lista de @see Client
	 */
	@Modifying
	@Query("SELECT b.client FROM BillProduct AS bp JOIN Bill AS b ON bp.bill = b")
	public List<Client> getAllClients();
	
	/**Metodo para obtener las facturas de un cliente dado
	 * @param client
	 * @return una lista de @see BillProduct
	 */
	@Modifying
	@Query("SELECT bp FROM BillProduct AS bp JOIN Bill AS b ON bp.bill = b WHERE b.client = :client")
	public List<BillProduct> getBillProductOfClient(@Param(CLIENT) Client client);

	
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
	@Query("SELECT new BestProductDTO(bp.product.name, bp.quantity) "
			+ "FROM BillProduct AS bp "
			+ "ORDER BY bp.quantity DESC ")
	public List<BestProductDTO> getBestProduct();
}
