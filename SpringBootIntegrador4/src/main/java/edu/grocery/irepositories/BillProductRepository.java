package edu.grocery.irepositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import edu.grocery.pojo.Bill;
import edu.grocery.pojo.BillProduct;
import edu.grocery.pojo.Product;

public interface BillProductRepository extends JpaRepository<BillProduct, Object>  {

	/*@Modifying
	@Query("UPDATE BillProduct bp SET bp.product = ?1, bp.bill = ?2, bp.date = ?3"
			+ "bp.quentity = ?4 WHERE p.id = ?5")
	public void setUserInfoById(Product producto, Bill bill, Date date, int quantity, long id);*/
}
