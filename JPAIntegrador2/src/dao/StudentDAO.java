package dao;

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
	final static String LIBRETA = "LU";
	
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
			long LU = Long.parseLong(row.get(LIBRETA));
			
			Student insert = new Student(DNI, name, lastName, age, gender, city, LU);
			em.persist(insert);
			em.getTransaction().commit();
		}
	}

	@Override
	public Student getStudent(EntityManager em, int LU) {
		em.getTransaction().begin();
		Student s = (Student) em.createQuery("SELECT * FROM Studen s WHERE s.LU = ").getSingleResult();
		em.getTransaction().commit();
		return s;
	}
}
