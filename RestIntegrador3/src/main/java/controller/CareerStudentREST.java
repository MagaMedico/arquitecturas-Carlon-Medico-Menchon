package controller;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import dao.CareerStudentDAO;
import dto.ReportDTO;
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
	
	/**
	 * Ejercicio 2) b) matricular un estudiante en una carrera
	 * @param JSON con los datos necesario para la tabla CareerStudent: career_id y student_id
	 * @return Un mensaje informando si el estudiante se ingreso con exito o no
	 * @see Student
	 * @see Career
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addStudentIntoCareer(CareerStudentId cs) {
		long careerId = cs.getCareerId();
		long studentId = cs.getStudentId();
		
        CareerStudentDAO.getInstance().addStudent(studentId, careerId);
        //Se debe chequear si se inserto o no (transformando el return de addStudent a boolean
		return "El estudiante se a insertado con exito";
	}
	
	@GET
	@Path("/report")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ReportDTO> getReport(){
		return CareerStudentDAO.getInstance().getReport();
	}
}
