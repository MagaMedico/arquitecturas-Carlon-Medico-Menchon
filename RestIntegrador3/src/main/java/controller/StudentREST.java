package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	
	/**
	 * Ejercicio 2) a) dar de alta un estudiante
	 * @param JSON con los datos necesario para la tabla Student
	 * @return Un mensaje informando si el estudiante se ingreso con exito o no
	 * @see Student
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addStudent(String studentJSON) throws FileNotFoundException, IOException, ParseException {
		// se parcea "studentJSON"
        Object obj = new JSONParser().parse(new FileReader(studentJSON));
          
        // se castea obj a JSONObject
        JSONObject jo = (JSONObject) obj;
          
        // se obtienen todos las columnas y se las pone en variables
        long dni = (long) jo.get("DNI");
        String name = (String) jo.get("name");
        String lastName = (String) jo.get("lastName");
        int age = (int) jo.get("age");
        String gender = (String) jo.get("gender");
        String city = (String) jo.get("city");
        long lu = (long) jo.get("LU");

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
	@Path("/{orderedBy}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentsOrderedBy(){
		return StudentDAO.getInstance().getStudentsWithOrderBy();
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
