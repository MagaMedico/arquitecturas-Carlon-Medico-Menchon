package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import dao.CareerDAO;
import dao.CareerStudentDAO;
import dao.StudentDAO;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final String TYPE = "JPA";
		final String CSV_CAREER = "./src/csv/career.csv";
		final String CSV_STUDENT = "./src/csv/student.csv";
		final String CSV_CAREER_STUDENT = "./src/csv/career_student.csv";
		
		//Inicialización de la persistencia
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(TYPE);
		EntityManager em = emf.createEntityManager();
		//em.getTransaction().begin();
		
		//Insertar los datos
		CSVParser parserCareer = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_CAREER));
		CSVParser parserStudent = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_STUDENT));
		CSVParser parserCareerStudent = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_CAREER_STUDENT));
		
		//Instancio los dao's
		CareerDAO career = new CareerDAO();
		StudentDAO student = new StudentDAO();
		CareerStudentDAO careerStudent = new CareerStudentDAO();

		student.studentPersistence(em, parserStudent);
		career.careerPersistence(em, parserCareer);
		careerStudent.career_studentPersistence(em, parserCareerStudent);
		
		//Cierre del manejador de entidades
		em.close();	
		emf.close();
		
		/*em.getTransaction().commit();
		em.close();
		emf.close();*/
		
	}

}
