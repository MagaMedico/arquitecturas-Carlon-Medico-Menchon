package controller;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import dao.CareerDAO;
import dao.CareerStudentDAO;
import dao.StudentDAO;
import model.*;

@Path("/students")
public class StudentREST {
	
	//Instanciación de los dao's
	CareerDAO career;
	StudentDAO student;
	CareerStudentDAO careerStudent;
	
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
}
