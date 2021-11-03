package edu.grocery.irepositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.grocery.pojo.Bill;
import edu.grocery.pojo.BillProduct;
import edu.grocery.pojo.Product;

public interface BillProductRepository extends JpaRepository<BillProduct, Object>  {

	@Modifying
	@Query("UPDATE BillProduct SET product = :product, bill = :bill, date = :date, quantity = :quantity WHERE id = :id")
	public void updateBillProduct(@Param("product") Product product, @Param("bill") Bill bill, 
								  @Param("date") Date date, @Param("quantity") int quantity, @Param("id") long id);
}
