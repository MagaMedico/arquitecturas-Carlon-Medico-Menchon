package edu.grocery.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.grocery.model.Bill;
import edu.grocery.repositories.BillRepository;


@RestController
@RequestMapping("/b")
public class BillController {

	@Autowired
	private BillRepository repositoryB;
	
	@Transactional
	@GetMapping(value = "/{id}")
	public Bill getProductById(@PathVariable("id") Long id) {
		
		return this.repositoryB.findById(id).get();
	}
}
