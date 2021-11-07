package edu.grocery.irepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.grocery.pojo.Product;

public interface ProductRepository extends JpaRepository<Product, Object> {
	
	static final String NAME = "name",
						UNITPRICE = "unitPrice",
						ID = "id";
	
	@Modifying
	@Query("UPDATE Product SET name = :name, unitPrice = :unitPrice WHERE id = :id")
	public void updateProduct(@Param("name") String name, @Param("unitPrice") int unitPrice, @Param("id") long id);
	
}
