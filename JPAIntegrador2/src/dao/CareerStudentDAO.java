package dao;

import java.util.List;

import javax.persistence.EntityManager;

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
			if(!row.get("graduation").equals("")){
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
	// Ejercicio 2) g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
	@Override
	public List<Student> getStudentsByCareerFilterCity(EntityManager em, Long career_id, String city) {
		em.getTransaction().begin();
			
		@SuppressWarnings("unchecked")
		List<Student> students = em.createQuery("SELECT cs.student FROM CareerStudent cs WHERE cs.career_id = :c "
												+ "AND cs.")
									.setParameter("c", career_id).getResultList();
		em.getTransaction().commit();

		return students;
	}
}
