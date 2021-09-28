package dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import imodel.IStudent;
import model.Student;

public class StudentDAO implements IStudent{
	final static String ID = "DNI";
	final static String NAME = "name";
	final static String LAST_NAME = "lastName";
	final static String AGE = "age";
	final static String GENDER = "gender";
	final static String CITY = "city";
	final static String ACADEMIC_TRANSCRIPT = "LU";
	
	public StudentDAO() { }
	
	@Override
	public void studentPersistence(EntityManager em, CSVParser parserStudent) {
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

	// Ejercicio 2) d) recuperar un estudiante, en base a su número de libreta universitaria.
	@Override
	public Student getStudentByLU(EntityManager em, int LU) {
		em.getTransaction().begin();
		Student s = (Student) em.createQuery("SELECT * FROM Student s WHERE s.LU = ").getSingleResult();
		em.getTransaction().commit();
		return s;
	}
	
	// Ejercicio 2) e) recuperar todos los estudiantes, en base a su género.
	@Override
	public List<Student> getStudentsByGender(EntityManager em, String gender) {
		em.getTransaction().begin();
		
		@SuppressWarnings("unchecked")
		List<Student> students = em.createQuery("SELECT s FROM Student s WHERE s.gender = :g")
								.setParameter("g", gender).getResultList();
		em.getTransaction().commit();

		return students;
	}
	
}
