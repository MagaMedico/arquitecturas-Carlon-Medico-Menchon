package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import imodel.IStudent;
import model.Career;
import model.Student;
/**
 * 
 * @author Cecilia Carlón: ceciliacarlon2@gmail.com
		   Magalí Médico: magamedico@gmail.com
		   Magalí Menchón: magalimenchon@gmail.com	
	@version unica
	Implementa la lógica de comportamiento de los métodos especificados
	en la interfaz @see IStudent. De esta forma define el los métodos relacionados
	con la base de datos de la entidad @see Student
 *
 */
public class StudentDAO implements IStudent{
	
	/**
	 * Constantes de la clase
	 */
	final static String ID = "DNI";
	final static String NAME = "name";
	final static String LAST_NAME = "lastName";
	final static String AGE = "age";
	final static String GENDER = "gender";
	final static String CITY = "city";
	final static String ACADEMIC_TRANSCRIPT = "LU";
	
	/**
	 * Constructor vacío necesario para JPA
	 */
	public StudentDAO() { }
	
	/**
	 * Dado un archivo CSV recorre todas sus filas y setea
	 * los datos recibidos de tipo String (de ser necesario parseados) a la
	 * tabla student de la base de datos, mediante la persistencia dada por el
	 * EntityManager y la entidad @see Student.
	 */
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
	
	/**
	 * Ejercicio 2) a) dar de alta un estudiante.
	 * Inserta un registro con los datos recibidos por parámetro en la tabla
	 * student mediante una consulta SQL mediante una transacción.
	 * @see EntityManager
	 */
	@Override
	public void insertStudent(EntityManager em, long DNI, String name, String lastname, int age, String gender, int LU, String city) {
			
		
		Student s = em.find(Student.class, DNI);
		Query query = null;
		if(s != null) {
			query = em.createNativeQuery("UPDATE Student "
					+ "SET name = :name, lastname = :lastName, "
					+ "age = :age, gender = :gender, LU = :LU, city = :city "
					+ "WHERE DNI = :DNI");
		}
		else {
			query = em.createNativeQuery("INSERT INTO Student (DNI, name, lastname, age, gender, city, LU) "
					+ "VALUES (:DNI, :name, :lastName, :age, :gender, :city, :LU)");
		}
		
		em.getTransaction().begin();
		
		query.setParameter(ID, DNI);
		query.setParameter(NAME, name);
		query.setParameter(LAST_NAME, lastname);
		query.setParameter(AGE, age);
		query.setParameter(GENDER, gender);
		query.setParameter(CITY, city);
		query.setParameter(ACADEMIC_TRANSCRIPT, LU);
		
		query.executeUpdate();
		em.getTransaction().commit();
	}
	

	/**
	 * Ejercicio 2) c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
	 * Retorna una lista de estudiantes, obtenida mediante una consulta JPQL mediante
	 * la entidad @see Student ordenada por apellido.
	 */
	@Override
	public List<Student> getStudentsWithOrderBy(EntityManager em){
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Student> students = em.createQuery("SELECT s FROM Student s ORDER BY lastname").getResultList();
		em.getTransaction().commit();
		return students;
	}


	/**
	 * Ejercicio 2) d) recuperar un estudiante, en base a su número de libreta universitaria.
	 * Retorna un estudiante obtenido de la base de datos mediante su LU único.
	 * @see Student
	 */
	@Override
	public Student getStudentByLU(EntityManager em, Long LU) {
		em.getTransaction().begin();
		Student s = (Student) em.createQuery("SELECT s FROM Student s WHERE s.LU = :LU").
				    setParameter(ACADEMIC_TRANSCRIPT, LU).getSingleResult();
		em.getTransaction().commit();
		return s;
	}
	
	/**
	 * Ejercicio 2) e) recuperar todos los estudiantes, en base a su género.
	 * Retorna una lista de estudiantes obtenida de la base de datos con una consulta
	 * JPQL filtrando por un género recibido por parámetro.
	 * @see Student
	 */
	@Override
	public List<Student> getStudentsByGender(EntityManager em, String gender) {
		em.getTransaction().begin();
		
		@SuppressWarnings("unchecked")
		List<Student> students = em.createQuery("SELECT s FROM Student s WHERE s.gender = :gender")
								.setParameter(GENDER, gender).getResultList();
		em.getTransaction().commit();

		return students;
	}
	
}
