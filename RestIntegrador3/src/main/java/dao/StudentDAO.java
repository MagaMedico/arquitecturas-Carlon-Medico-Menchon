package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import emf.EMF;
import imodel.IStudent;
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
	 * Atributos de la clase
	 */
	private EntityManager em = EMF.createEntityManager();
	private static StudentDAO student;
	
	/**
	 * Constructor vacío necesario para JPA
	 */
	public StudentDAO() { }
	
	/**
	 * Constructor
	 */
	public StudentDAO(EntityManager em) { 
		this.em = em;
	}
	
	/**
	 * Ejercicio 2) a) dar de alta un estudiante.
	 * Inserta un registro con los datos recibidos por parámetro en la tabla
	 * student mediante una consulta SQL mediante una transacción.
	 * @see EntityManager
	 */
	@SuppressWarnings("null")
	@Override
	public boolean insertStudent(long DNI, String name, String lastname, int age, String gender, long LU, String city) {
			
		
		Student s = this.em.find(Student.class, DNI);
		Query query = null;
		if(s != null) {
			query = this.em.createNativeQuery("UPDATE Student "
					+ "SET name = :name, lastname = :lastName, "
					+ "age = :age, gender = :gender, LU = :LU, city = :city "
					+ "WHERE DNI = :DNI");
		}
		else {
			query = this.em.createNativeQuery("INSERT INTO Student (DNI, name, lastname, age, gender, city, LU) "
					+ "VALUES (:DNI, :name, :lastName, :age, :gender, :city, :LU)");
		}
		
		this.em.getTransaction().begin();
		
		if(query != null) {
			query.setParameter(ID, DNI);
			query.setParameter(NAME, name);
			query.setParameter(LAST_NAME, lastname);
			query.setParameter(AGE, age);
			query.setParameter(GENDER, gender);
			query.setParameter(CITY, city);
			query.setParameter(ACADEMIC_TRANSCRIPT, LU);
			
			query.executeUpdate();
			this.em.getTransaction().commit();
			
			return true;
		}else {
			query.executeUpdate();
			this.em.getTransaction().commit();
			return false;
		}
		
	}
	

	/**
	 * Ejercicio 2) c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
	 * Retorna una lista de estudiantes, obtenida mediante una consulta JPQL mediante
	 * la entidad @see Student ordenada por apellido.
	 */
	@Override
	public List<Student> getStudentsWithOrderBy(){
		this.em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Student> students = this.em.createQuery("SELECT s FROM Student s ORDER BY s.lastName").getResultList();
		this.em.getTransaction().commit();
		return students;
	}
	
	/**
	 * Retorna una lista de estudiantes, obtenida mediante una consulta JPQL mediante
	 * la entidad @see Student.
	 */
	@Override
	public List<Student> getStudents(){
		this.em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Student> students = this.em.createQuery("SELECT s FROM Student s").getResultList();
		this.em.getTransaction().commit();
		return students;
	}


	/**
	 * Ejercicio 2) d) recuperar un estudiante, en base a su número de libreta universitaria.
	 * Retorna un estudiante obtenido de la base de datos mediante su LU único.
	 * @see Student
	 */
	@Override
	public List<Student> getStudentByLU(Long LU) {
		this.em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Student> s = this.em.createQuery("SELECT s FROM Student s WHERE s.LU = :LU").
				    setParameter(ACADEMIC_TRANSCRIPT, LU).getResultList();
		this.em.getTransaction().commit();
		return s;
	}
	
	/**
	 * Ejercicio 2) e) recuperar todos los estudiantes, en base a su género.
	 * Retorna una lista de estudiantes obtenida de la base de datos con una consulta
	 * JPQL filtrando por un género recibido por parámetro.
	 * @see Student
	 */
	@Override
	public List<Student> getStudentsByGender(String gender) {
		this.em.getTransaction().begin();

		@SuppressWarnings("unchecked")
		List<Student> students = this.em.createQuery("SELECT s FROM Student s WHERE s.gender = :gender")
								.setParameter(GENDER, gender).getResultList();
		this.em.getTransaction().commit();

		return students;
	}
	

	public static StudentDAO getInstance() {
		if(student == null)
			student = new StudentDAO();
		return student;
	}
	
}
