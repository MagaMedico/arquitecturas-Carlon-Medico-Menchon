package edu.grocery.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.grocery.irepositories.BillProductRepository;
import edu.grocery.pojo.Bill;
import edu.grocery.pojo.BillProduct;
import edu.grocery.pojo.Product;

@Service
public class BillProductService {
	@Autowired
	private BillProductRepository bills;
	
	public List<BillProduct> getBills() {
		return this.bills.findAll();
	}
	
	@Transactional
	public boolean insert(BillProduct bp) {
		this.bills.save(bp);
		return true;
	}
	@Transactional
	public boolean update(Product product, Bill bill, Date date, int quantity, long id) {
		this.bills.updateBillProduct(product, bill, date, quantity, id);
		return true;
	}
	@Transactional
	public boolean delete(Long id) {
		this.bills.deleteById(id);
		return true;
	}
}
