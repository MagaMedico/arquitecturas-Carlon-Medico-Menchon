package edu.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.irepositories.BillProductRepository;
import edu.pojo.BillProduct;

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
	public boolean update() {
		return true;
	}
	@Transactional
	public boolean delete(BillProduct bp) {
		this.bills.delete(bp);
		return true;
	}
}
