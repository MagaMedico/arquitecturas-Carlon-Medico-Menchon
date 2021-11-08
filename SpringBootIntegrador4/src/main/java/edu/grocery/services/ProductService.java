package edu.grocery.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.grocery.model.Product;
import edu.grocery.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository products;
	
	public List<Product> getProducts() {
		return this.products.findAll();
	}
	
	@Transactional
	public boolean insert(Product p) {
		this.products.save(p);
		return true;
	}
	@Transactional
	public boolean update(String name, int unitPrice, long id) {
		this.products.updateProduct(name, unitPrice, id);
		return true;
	}
	@Transactional
	public boolean delete(Long id) {
		this.products.deleteById(id);
		return true;
	}

}
