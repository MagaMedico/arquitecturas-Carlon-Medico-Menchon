package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import model.Career;
import model.CareerStudent;
import model.Student;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");
		EntityManager em = emf.createEntityManager();
		//em.getTransaction().begin();
		
		//Insertar los datos
		CSVParser parserCareer = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/csv/career.csv"));
		CSVParser parserStudent = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/csv/student.csv"));
		CSVParser parserCareerStudent = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/csv/career_student.csv"));
		
		
		//Esto iría en cada DAO:
		studentPersistence(em, parserStudent);
		careerPersistence(em, parserCareer);
		// career_studentPersistence(em, parserCareerStudent);

		
		//Cierre del manejador de entidades
		em.close();	
		emf.close();
		
		/*em.getTransaction().commit();
		em.close();
		emf.close();*/
		
	}
	public static void career_studentPersistence(EntityManager em, CSVParser parserCareerStudent) {
		//Persistencia CSV de career_student
		for(CSVRecord row: parserCareerStudent) { 
			em.getTransaction().begin();

			int antiquity = Integer.parseInt(row.get("antiquity"));
			int graduation = Integer.parseInt(row.get("antiquity"));
			Long career_id = Long.parseLong(row.get("career"));
			Long student_DNI = Long.parseLong(row.get("student"));
			//------Debería buscar al estudiante y a la carrera y traer los datos?
			/*CareerStudent insert = new CareerStudent(student_DNI, career_id, graduation, antiquity);
			em.persist(insert);*/
			em.getTransaction().commit();
		}
	}
	
	public static void studentPersistence(EntityManager em, CSVParser parserStudent) {
		//Persistencia CSV de Student
		for(CSVRecord row: parserStudent) { 
			em.getTransaction().begin();
			Long DNI = Long.parseLong(row.get("DNI"));
			String name = row.get("name");
			String lastName = row.get("lastName");
			int age = Integer.parseInt(row.get("age"));
			String gender = row.get("gender");
			String city = row.get("city");
			long LU = Long.parseLong(row.get("LU"));
			Student insert = new Student(DNI, name, lastName, age, gender, city, LU);
			em.persist(insert);
			em.getTransaction().commit();
		}
	}
	
	public static void careerPersistence(EntityManager em, CSVParser parserCareer) {
		//Persistencia CSV de Career
		for(CSVRecord row: parserCareer) { 
			em.getTransaction().begin();
			//Long id = Long.parseLong(row.get("id"));	//Id autogenerada
			String name = row.get("name");
			int length = Integer.parseInt(row.get("length"));
					
			Career insert = new Career(/*id, */name, length);
			em.persist(insert);
			em.getTransaction().commit();
		}
	}
}
