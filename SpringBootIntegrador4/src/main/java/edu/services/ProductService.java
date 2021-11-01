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
	public boolean insert() {
		return true;
	}
	@Transactional
	public boolean update() {
		return true;
	}
	@Transactional
	public boolean delete() {
		return true;
	}
}
