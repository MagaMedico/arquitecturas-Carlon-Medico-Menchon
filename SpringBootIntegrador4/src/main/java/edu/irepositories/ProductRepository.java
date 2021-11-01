package edu.irepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pojo.Product;

public interface ProductRepository extends JpaRepository<Product, Object> {

}
