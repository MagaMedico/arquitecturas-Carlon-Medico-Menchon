package controller;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import dao.StudentDAO;
import model.*;

@Path("/students")
public class StudentREST {
	
	public StudentREST() {}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudents(){
		return StudentDAO.getInstance().getStudents();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("id") int i){
		/*return new Student(i, "Name_"+i, "Apellido_"+i);*/
		return null;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addStudent(Student student) {
		System.out.println(student.toString());
		return "El estudiante se a insertado con exito";
	}
	
	/**
	 * Ejercicio 2) e) recuperar todos los estudiantes, en base a su género.
	 * @param gender String de género a filtrar
	 * @return Lista de instancias de clase Student por medio de la consulta a la base de datos.
	 * @see Student
	 */
	@GET
	@Path("/{gender}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentsByGender(@PathParam("gender") String gender){
		return StudentDAO.getInstance().getStudentsByGender(gender);
	}
}
