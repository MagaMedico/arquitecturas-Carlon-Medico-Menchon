package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import dao.CareerDAO;
import dao.CareerStudentDAO;
import dao.StudentDAO;
import model.Student;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final String TYPE = "JPA";
		final String CSV_CAREER = "./src/csv/career.csv";
		final String CSV_STUDENT = "./src/csv/student.csv";
		final String CSV_CAREER_STUDENT = "./src/csv/career_student.csv";
		
		//Inicialización de la persistencia
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(TYPE);
		EntityManager em = emf.createEntityManager();
		
		//Insertar los datos
		CSVParser parserCareer = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_CAREER));
		CSVParser parserStudent = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_STUDENT));
		CSVParser parserCareerStudent = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_CAREER_STUDENT));
		
		//Instanciación de los dao's
		CareerDAO career = new CareerDAO();
		StudentDAO student = new StudentDAO();
		CareerStudentDAO careerStudent = new CareerStudentDAO();

		//Persistencia de CSVs
		student.studentPersistence(em, parserStudent);
		career.careerPersistence(em, parserCareer);
		careerStudent.career_studentPersistence(em, parserCareerStudent);
		
		//Pruebas de consultas ejercicio 2
		//Inciso e
		System.out.println("Inciso e");
		List<Student> studentsByGender = student.getStudentsByGender(em, "Female");
		studentsByGender.forEach(s -> System.out.println(s));
		//Inciso g
		
		//Cierre del manejador de entidades
		em.close();	
		emf.close();
		
	}

}
