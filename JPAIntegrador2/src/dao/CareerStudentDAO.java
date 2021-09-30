package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import imodel.ICareerStudent;
import model.Career;
import model.CareerStudent;
import model.Student;

public class CareerStudentDAO implements ICareerStudent{
	final static String ANTIQUITY = "antiquity";
	final static String GRADUATION = "graduation";
	final static String CAREER = "career";
	final static String STUDENT = "student";
	
	public CareerStudentDAO() { }
	
	//Persistencia CSV de career_student
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
	//Inciso b
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
		//----------------------------------------------No se si va acá o iría en StudentDAO
	// Ejercicio 2) g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
	@Override
	public List<Student> getStudentsByCareerFilterCity(EntityManager em, Long career_id, String city) {
		em.getTransaction().begin();
			
		@SuppressWarnings("unchecked")
		List<Student> students = em.createQuery("SELECT DISTINCT(s) FROM Student s, CareerStudent cs "
												+ "WHERE cs.career.id = :career "
												+ "AND s.city = :city")
		/*List<Student> students = em.createQuery("SELECT s FROM CareerStudent s "
												+ "JOIN Student s "
												+ "WHERE cs.career_id = :career "
												+ "AND s.city = :city")*/
		/*List<Student> students = em.createQuery("SELECT DISTINCT cs.student FROM CareerStudent cs JOIN Student s WHERE cs.career_id = :career "
												+ "AND s.city = :city")*/
		/*List<Student> students = em.createQuery("SELECT DISTINCT s FROM Career c "
												+ "JOIN c.students s WHERE c.id = :career "
												+ "AND s.city = :city")*/
									.setParameter(CAREER, career_id)
									.setParameter("city", city)
									.getResultList();
		
		em.getTransaction().commit();

		return students;
	}
}
