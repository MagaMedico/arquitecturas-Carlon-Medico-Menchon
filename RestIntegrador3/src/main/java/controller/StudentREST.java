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
	
	/**
	 * Ejercicio 2) a) dar de alta un estudiante
	 * @param JSON con los datos necesario para la tabla Student
	 * @return Un mensaje informando si el estudiante se ingreso con exito o no
	 * @see Student
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addStudent(Student student) {

	    long dni = student.getDNI();
	    String name = student.getName();
	    String lastName = student.getLastName();
	    int age = student.getAge();
	    String gender = student.getGender();
	    String city = student.getCity();
	    long lu = student.getLU();
	
	    boolean insertado = StudentDAO.getInstance().insertStudent(dni, name, lastName, age, gender, lu, city);
			if (insertado) return "El estudiante se a insertado con exito";
			else return "El estudiante no se ha podido insertar";

	}
	
	/**
	 * Ejercicio 2) c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
	 * @return Lista de instancias de clase Student por medio de la consulta a la base de datos.
	 * @see Student
	 */
	@GET
	@Path("/orderedBy")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentsOrderedBy(){
		return StudentDAO.getInstance().getStudentsWithOrderBy();
	}
	
	/**
	 * Ejercicio 2) d) recuperar un estudiante, en base a su número de libreta universitaria.
	 * Retorna un estudiante obtenido de la base de datos mediante su LU único.
	 * @see Student
	 */
	@GET
	@Path("/LU-{LU}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentByLU(@PathParam("LU") Long LU) {
		return StudentDAO.getInstance().getStudentByLU(LU);
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
