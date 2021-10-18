package controller;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import dao.CareerStudentDAO;
import model.*;

@Path("/careerStudent")
public class CareerStudentREST {
	
	public CareerStudentREST() {}
	
	/**
	 * Ejercicio 2) g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
	 * @param career_id Long de PK de identificador único de carrera
	 * @param city String de nombre de ciudad
	 * @return Lista de instancias de clase Student por medio de la consulta a la base de datos.
	 * @see Student
	 */
	@GET
	@Path("/{career}/{city}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentsByCareerFilterCity(@PathParam("career") Long career_id, @PathParam("city") String city){
		return CareerStudentDAO.getInstance().getStudentsByCareerFilterCity(career_id, city);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addStudent(Student student) {
		System.out.println(student.toString());
		return "El estudiante se a insertado con exito";
	}
}
