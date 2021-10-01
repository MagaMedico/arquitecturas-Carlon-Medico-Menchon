package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import dto.ReportDTO;
import dto.ReportYearDTO;
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
	 * Constructor vacío necesario para JPA
	 */
	public CareerStudentDAO() { }
	
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
	public void career_studentPersistence(EntityManager em, CSVParser parserCareerStudent) {
		
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
	public void addStudent(EntityManager em, long idStudent, long idCareer) {
		Query query = em.createNativeQuery("INSERT INTO career_student (career_id, student_id, antiquity, graduation) "
				+ "VALUES (:career, :student, :antiquity, :graduation)");
	
		em.getTransaction().begin();
		
		query.setParameter(CAREER, idCareer);
		query.setParameter(STUDENT, idStudent);
		query.setParameter(ANTIQUITY, 0);
		query.setParameter(GRADUATION, null);
		
		Student student = em.find(Student.class, idStudent);
		Career career = em.find(Career.class, idCareer);
		
		career.addStudent(student);
		student.addCareer(career);
		
		query.executeUpdate();
		em.getTransaction().commit();
	}

	
	/**
	 *  Ejercicio 2) g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
	 * Retora una lista estudiantes de una determinada ciudad, obtenida mediante una consulta JPQL
	 * que filtra los estudiantes asociados a una determinada carrera de determinada ciudad.
	 * @see EntityManager
	 */
	@Override
	public List<Student> getStudentsByCareerFilterCity(EntityManager em, Long career_id, String city) {
		em.getTransaction().begin();
			
		@SuppressWarnings("unchecked")
		List<Student> students = em.createQuery("SELECT DISTINCT(s) FROM Student s, CareerStudent cs "
												+ "WHERE cs.career.id = :career "
												+ "AND s.city = :city")
									.setParameter(CAREER, career_id)
									.setParameter(CITY, city)
									.getResultList();
		
		em.getTransaction().commit();

		return students;
	}
	
	/**
	 * Ejercicio 3) Generar un reporte de las carreras, que para cada carrera
	 * incluya información de los inscriptos y egresados por año.
	 * Se deben ordenar las carreras alfabéticamente, y presentar los años
	 * de manera cronológica.
	 * Retorna una lista de reportes @see ReportDTO; cada uno de estos contiene a su
	 * vez una carrera y los reportes por año de la misma @see ReportDTOByYear,
	 * compuesta por el dato del año de ese reporte y dos listas de inscriptos y
	 * egresados de ese año.
	 * Estos datos se obtienen mediante una consulta JPQL para traer los nombres de las
	 * distintas carreras con al menos algún inscripto ordenadas alfabeticamente,
	 * luego por cada carrera se obtienen los años años posibles de graduación e inscripción,
	 * y mediante estos se van obteniendo las listas de estudiantes.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ReportDTO> getReport(EntityManager em) {
		em.getTransaction().begin();
		
		List<ReportDTO> reports = new ArrayList<ReportDTO>();
		List<Career> career = em.createQuery("SELECT DISTINCT c FROM Career c JOIN c.students s WHERE size(s) > 0 ORDER BY c.name").getResultList();
		List<Student> student_graduated;
		List<Student> student_registered;
		List<Integer> years;
		for(Career c: career) {
			
			//Se genera reporte de una carrera
			
			ReportDTO reportCareer = new ReportDTO(c);
			
		    years = em.createQuery("SELECT cs.graduation FROM CareerStudent cs WHERE cs.career = :career AND cs.graduation != NULL ORDER BY cs.graduation")
				  .setParameter(CAREER, c).getResultList();
			years.addAll(em.createQuery("SELECT DISTINCT(year(current_date) - cs.antiquity) AS year FROM CareerStudent cs WHERE cs.career = :career ORDER BY year ASC")
					  .setParameter(CAREER, c).getResultList());
			List<ReportYearDTO> reportsByYear = new ArrayList<ReportYearDTO>();
			for(Integer i: years) {
				
				ReportYearDTO reportByYear = new ReportYearDTO(i);
				
				//Se le setean los estudiantes graduados e inscriptos correspondientes
				student_graduated = em.createQuery("SELECT cs.student FROM CareerStudent cs WHERE cs.career = :career AND cs.graduation = :y ")
									.setParameter(CAREER, c).setParameter("y", i)
									.getResultList();
				reportByYear.setGraduated(student_graduated);
				
				student_registered = em.createQuery("SELECT cs.student FROM CareerStudent cs WHERE cs.career = :career AND cs.antiquity = :antiquity ORDER BY cs.antiquity")
									.setParameter(CAREER, c).setParameter("antiquity", 2021-i)
									.getResultList();
				reportByYear.setRegistered(student_registered);
				
				reportsByYear.add(reportByYear);
			}
			//Se agrega el reporte a la lista de reportes
			reportCareer.addReportsByYear(reportsByYear);
			reports.add(reportCareer);
		}	
	
		em.getTransaction().commit();
		return reports;
	}
}
