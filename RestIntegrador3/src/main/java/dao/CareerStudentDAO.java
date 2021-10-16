package dao;

import java.util.stream.Collectors;
import java.util.List;
import java.math.BigInteger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import dto.ReportDTO;
import imodel.ICareerStudent;
import model.Career;
import model.CareerStudent;
import model.Student;
/**
 * 
 * @author Cecilia Carlón: ceciliacarlon2@gmail.com
		   Magalí Médico: magamedico@gmail.com
		   Magalí Menchón: magalimenchon@gmail.com	
	@version unica
	@description Implementa la lógica de comportamiento de los métodos especificados
	en la interfaz @see ICarrerStudent. De esta forma define el los métodos relacionados
	con la base de datos de la entidad @see CareerStudent @see CareerStudentId
 *
 */
public class CareerStudentDAO implements ICareerStudent{
	
	/**
	 * Constantes de la clase
	 */
	final static String ANTIQUITY = "antiquity";
	final static String GRADUATION = "graduation";
	final static String CAREER = "career";
	final static String STUDENT = "student";
	final static String CITY = "city";
	
	/** 
	 * Atributos de la clase
	 */
	private EntityManager em;
	private static CareerStudentDAO cs;
	
	/**
	 * Constructor vacío necesario para JPA
	 */
	public CareerStudentDAO() { }
	
	/**
	 * Constructor
	 */
	public CareerStudentDAO(EntityManager em) { 
		this.em = em;
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
	@Override
	public void career_studentPersistence(CSVParser parserCareerStudent) {
		
		for(CSVRecord row: parserCareerStudent) { 
			this.em.getTransaction().begin();

			int antiquity = Integer.parseInt(row.get(ANTIQUITY));
			Integer graduation = null;
			if(!row.get(GRADUATION).equals("")){
				graduation = Integer.parseInt(row.get(GRADUATION));
			}
			Long career_id = Long.parseLong(row.get(CAREER));
			Long student_DNI = Long.parseLong(row.get(STUDENT));
	
			//Busca al estudiante y a la carrera por sus PK y trae los datos
			Student student = this.em.find(Student.class, student_DNI);
			Career career = this.em.find(Career.class, career_id);
			student.addCareer(career);
			career.addStudent(student);
			
			CareerStudent insert = new CareerStudent(student, career, graduation, antiquity);
			this.em.persist(insert);
			this.em.getTransaction().commit();
		}
	}
	/**
	 * Ejercicio 2) b) matricular un estudiante en una carrera
	 * Inserta un registro que relaciona un estudiante a una carrera
	 * mediante una consulta de SQL en la tabla career_student seteando los datos
	 * recibidos por parámetro.
	 * Además, busca el estudiante y carrera correspondiente en @see Student y
	 * @see Career respectivamente, y agrega a cada una a la lista
	 * que define la relación que luego utilizará JPA para crear las tablas
	 * de cada entidad.
	 * @see EntityManager
	 */
	public void addStudent(long idStudent, long idCareer) {
		Query query = this.em.createNativeQuery("INSERT INTO career_student (career_id, student_id, antiquity, graduation) "
				+ "VALUES (:career, :student, :antiquity, :graduation)");
	
		this.em.getTransaction().begin();
		
		query.setParameter(CAREER, idCareer);
		query.setParameter(STUDENT, idStudent);
		query.setParameter(ANTIQUITY, 0);
		query.setParameter(GRADUATION, null);
		
		Student student = this.em.find(Student.class, idStudent);
		Career career = this.em.find(Career.class, idCareer);
		
		career.addStudent(student);
		student.addCareer(career);
		
		query.executeUpdate();
		this.em.getTransaction().commit();
	}

	
	/**
	 *  Ejercicio 2) g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
	 * Retora una lista estudiantes de una determinada ciudad, obtenida mediante una consulta JPQL
	 * que filtra los estudiantes asociados a una determinada carrera de determinada ciudad.
	 * @see EntityManager
	 */
	@Override
	public List<Student> getStudentsByCareerFilterCity(Long career_id, String city) {
		this.em.getTransaction().begin();
			
		@SuppressWarnings("unchecked")
		List<Student> students = this.em.createQuery("SELECT DISTINCT(s) FROM Student s, CareerStudent cs "
												+ "WHERE cs.career.id = :career "
												+ "AND s.city = :city")
									.setParameter(CAREER, career_id)
									.setParameter(CITY, city)
									.getResultList();
		
		this.em.getTransaction().commit();

		return students;
	}
	
	/**
	 * Ejercicio 3) Generar un reporte de las carreras, que para cada carrera
	 * incluya información de los inscriptos y egresados por año.
	 * Se deben ordenar las carreras alfabéticamente, y presentar los años
	 * de manera cronológica.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ReportDTO> getReport() {
		this.em.getTransaction().begin();
		
		List<Object[]> query = this.em.createNativeQuery("SELECT name, graduation years, NULL enrolled, cs.student_id graduate "
				+ "FROM career c "
				+ "INNER JOIN  career_student cs "
				+ "ON c.id= cs.career_id "
				+ "WHERE cs.graduation IS NOT NULL "
				+ "UNION ALL "
				+ "SELECT name, YEAR(CURDATE()) - cs.antiquity years, cs.student_id enrolled, NULL graduate "
				+ "FROM career c INNER JOIN  career_student cs "
				+ "ON c.id= cs.career_id "
				+ "WHERE cs.graduation IS NULL "
				+ "UNION ALL "
				+ "SELECT name, (graduation - cs.antiquity) years, cs.student_id enrolled, NULL graduate "
				+ "FROM career c "
				+ "INNER JOIN  career_student cs "
				+ "ON c.id= cs.career_id "
				+ "WHERE cs.graduation IS NOT NULL "
				+ "ORDER BY name, years, enrolled DESC")
				.getResultList();
		
		List<ReportDTO> reports = query.stream().map(o -> new ReportDTO((String)o[0], (BigInteger)o[1], (BigInteger)o[2], (BigInteger)o[3])).collect(Collectors.toList());
		
		this.em.getTransaction().commit();
		return reports;
	}
	
	public static CareerStudentDAO getInstance() {
		if(cs == null)
		cs = new CareerStudentDAO();
		return cs;
	}
}
