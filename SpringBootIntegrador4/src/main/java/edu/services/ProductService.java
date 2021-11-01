package edu.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.irepositories.ProductRepository;
import edu.pojo.Product;

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
		this.products.setUserInfoById(name, unitPrice, id);
		return true;
	}
	@Transactional
	public boolean delete(Long id) {
		this.products.deleteById(id);
		return true;
	}

	public Product findProduct(long id) {
		return this.products.getById(id);
	}
}
