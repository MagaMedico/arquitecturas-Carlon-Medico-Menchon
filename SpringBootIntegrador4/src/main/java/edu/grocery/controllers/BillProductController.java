package edu.grocery.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.grocery.pojo.BillProduct;
import edu.grocery.services.BillProductService;

@RestController
@RequestMapping("/bills")
public class BillProductController {
	public static Logger LOG = LoggerFactory.getLogger(BillProductController.class); 
	@Autowired
	private BillProductService serviceBill;
	
	@GetMapping("")
	public List<BillProduct> getAll() {
		return this.serviceBill.getBills();
	}
	
	@PostMapping("")
	public ResponseEntity<?> addBillProduct(@RequestBody BillProduct bp) {
		boolean ok = this.serviceBill.insert(bp);
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(bp, HttpStatus.OK);
	}
	
	@DeleteMapping("")
	public ResponseEntity<?> removeBillProduct(@RequestBody BillProduct bp){
		boolean ok = this.serviceBill.delete(bp);
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(bp, HttpStatus.OK);
	}
}