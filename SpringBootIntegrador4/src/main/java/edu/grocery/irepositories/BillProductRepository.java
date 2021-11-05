package edu.grocery.irepositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.grocery.pojo.Bill;
import edu.grocery.pojo.BillProduct;
import edu.grocery.pojo.Client;
import edu.grocery.pojo.Product;

public interface BillProductRepository extends JpaRepository<BillProduct, Object>  {

	//Metodo para actualizar los datos pasados por parametro en la tabla BillProduct
	@Modifying
	@Query("UPDATE BillProduct SET product = :product, bill = :bill, date = :date, quantity = :quantity WHERE id = :id")
	public void updateBillProduct(@Param("product") Product product, @Param("bill") Bill bill, 
								  @Param("date") LocalDate date, @Param("quantity") int quantity, @Param("id") long id);
	
	//Metodo para obtener todos los clientes que tengan facturas
	@Modifying
	@Query("SELECT b.client FROM BillProduct AS bp JOIN Bill AS b ON bp.bill = b")
	public List<Client> getAllClients();
	
	//Metodo para obtener las facturas de un cliente dado
	@Modifying
	@Query("SELECT bp FROM BillProduct AS bp JOIN Bill AS b ON bp.bill = b WHERE b.client = :client")
	public List<BillProduct> getBillProductOfClient(@Param("client") Client client);
}
