package edu.grocery.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.grocery.model.Bill;
import edu.grocery.repositories.BillRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/b")
@Api(value= "BillController", description= "Api of the bill controller")
public class BillController {

	@Autowired
	private BillRepository repositoryB;
	
	@ApiOperation(value="Get product by id", response= Bill.class)
	@ApiResponses(value= {
		@ApiResponse(code= 200, message= "Succesfuly, OK"),
		@ApiResponse(code= 401, message= "Check the autentication!, Unauthorized"),
		@ApiResponse(code= 403, message= "Denied access!, Forbidden"),
		@ApiResponse(code= 400, message= "Error!, Not Found")
	})
	@Transactional
	@GetMapping(value = "/{id}")
	public Bill getProductById(@PathVariable("id") Long id) {
		
		return this.repositoryB.findById(id).get();
	}
}
