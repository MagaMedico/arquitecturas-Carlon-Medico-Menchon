package edu.irepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import edu.pojo.Product;

public interface ProductRepository extends JpaRepository<Product, Object> {

	@Modifying
	@Query("UPDATE Product p SET p.name = ?1, p.unitPrice = ?2 WHERE p.id = ?3")
	public void setUserInfoById(String name, int unitPrice, long id);
}
