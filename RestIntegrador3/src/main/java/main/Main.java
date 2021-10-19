package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import model.Career;
import model.CareerStudent;
import model.Student;
/**
 * 
 * @author Cecilia Carlón: ceciliacarlon2@gmail.com
		   Magalí Médico: magamedico@gmail.com
		   Magalí Menchón: magalimenchon@gmail.com	
	@version unica
	Requisitos: Se debe ejecutar el main como una aplicación Java 
	para persistir los datos de los CSVs y para crear la Base de Datos entregable3.
 *
 */
public class Main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws FileNotFoundException, IOException {
		final String TYPE = "JPA";
		
		//Inicialización de la persistencia
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(TYPE);
		EntityManager em = emf.createEntityManager();
		
		//Constantes
		final String CSV_CAREER = "./src/main/java/csv/career.csv";
		final String CSV_STUDENT = "./src/main/java/csv/student.csv";
		final String CSV_CAREER_STUDENT = "./src/main/java/csv/career_student.csv";

		//Insertar los datos
		CSVParser parserCareer = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_CAREER));
		CSVParser parserStudent = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_STUDENT));
		CSVParser parserCareerStudent = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_CAREER_STUDENT));
		
		//Persistencia de CSVs
		studentPersistence(em, parserStudent);
		careerPersistence(em, parserCareer);
		career_studentPersistence(em, parserCareerStudent);
		
		em.close();	
		emf.close();
	}
	/**
	 * Dado un archivo CSV recorre todas sus filas y setea
	 * los datos recibidos de tipo String (de ser necesario parseados) a la
	 * tabla student de la base de datos, mediante la persistencia dada por el
	 * EntityManager y la entidad @see Student.
	 */
	public static void studentPersistence(EntityManager em, CSVParser parserStudent) {
		final String ID = "DNI";
		final String NAME = "name";
		final String LAST_NAME = "lastName";
		final String AGE = "age";
		final String GENDER = "gender";
		final String CITY = "city";
		final String ACADEMIC_TRANSCRIPT = "LU";
		
		//Persistencia CSV de Student
		for(CSVRecord row: parserStudent) { 
			em.getTransaction().begin();
			
			Long DNI = Long.parseLong(row.get(ID));
			String name = row.get(NAME);
			String lastName = row.get(LAST_NAME);
			int age = Integer.parseInt(row.get(AGE));
			String gender = row.get(GENDER);
			String city = row.get(CITY);
			long LU = Long.parseLong(row.get(ACADEMIC_TRANSCRIPT));
			
			Student insert = new Student(DNI, name, lastName, age, gender, city, LU);
			em.persist(insert);
			em.getTransaction().commit();
		}
	}
	
	/**
	 * Dado un archivo CSV recorre todas sus filas y setea
	 * los datos recibidos de tipo String (de ser necesario parseados) a la
	 * tabla career de la base de datos, mediante la persistencia dada por el
	 * EntityManager y la entidad @see Career
	 */
	public static void careerPersistence(EntityManager em, CSVParser parserCareer) {
		final String NAME = "name";
		final String LENGTH = "length";
		
		for(CSVRecord row: parserCareer) { 
			em.getTransaction().begin();
			
			String name = row.get(NAME);
			int length = Integer.parseInt(row.get(LENGTH));
					
			Career insert = new Career(name, length);
			em.persist(insert);
			em.getTransaction().commit();
		}
	}
	/**
	 * Dado un archivo CSV recorre todas sus filas y setea
	 * los datos recibidos de tipo String (de ser necesario parseados) a la
	 * tabla career_student de la base de datos, mediante la persistencia dada por el
	 * EntityManager y la entidad @see CareerStudent.
	 * Además, busca el estudiante y carrera correspondiente en @see Student y
	 * @see Career respectivamente, y agrega a cada una a la lista
	 * que define la relación que luego utilizará JPA para crear las tablas
	 * de cada entidad.
	 */
	public static void career_studentPersistence(EntityManager em, CSVParser parserCareerStudent) {
		final String ANTIQUITY = "antiquity";
		final String GRADUATION = "graduation";
		final String CAREER = "career";
		final String STUDENT = "student";
		
		for(CSVRecord row: parserCareerStudent) { 
			em.getTransaction().begin();

			int antiquity = Integer.parseInt(row.get(ANTIQUITY));
			Integer graduation = null;
			if(!row.get(GRADUATION).equals("")){
				graduation = Integer.parseInt(row.get(GRADUATION));
			}
			Long career_id = Long.parseLong(row.get(CAREER));
			Long student_DNI = Long.parseLong(row.get(STUDENT));
	
			//Busca al estudiante y a la carrera por sus PK y trae los datos
			Student student = em.find(Student.class, student_DNI);
			Career career = em.find(Career.class, career_id);
			student.addCareer(career);
			career.addStudent(student);
			
			CareerStudent insert = new CareerStudent(student, career, graduation, antiquity);
			em.persist(insert);
			em.getTransaction().commit();
		}
	}

}
