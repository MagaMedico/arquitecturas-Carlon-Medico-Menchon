package dao;

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
	
	@Override
	public void career_studentPersistence(EntityManager em, CSVParser parserCareerStudent) {
		//Persistencia CSV de career_student
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
}
