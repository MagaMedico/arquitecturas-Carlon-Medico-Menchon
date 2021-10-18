package controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.CareerDAO;
import model.Career;

@Path("/careers")
public class CareerREST {
	/**
	 * Constructor
	 */
	public CareerREST() { }
	
	/**
	 * 
	 */
	@GET
	@Path("/orderByStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Career> getCareersOrderByStudents() {
		return CareerDAO.getInstance().getCareersOrderByStudents();
	}
}
