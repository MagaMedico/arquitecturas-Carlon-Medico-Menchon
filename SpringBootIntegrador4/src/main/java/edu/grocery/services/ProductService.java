package edu.grocery.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.grocery.irepositories.ProductRepository;
import edu.grocery.pojo.Product;

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
