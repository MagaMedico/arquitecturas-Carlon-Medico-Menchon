package imodel;

import java.util.List;

import javax.persistence.EntityManager;
import org.apache.commons.csv.CSVParser;

import model.Student;

public interface ICareerStudent {
	
	public void career_studentPersistence(EntityManager em, CSVParser parserCareerStudent);
	public List<Student> getStudentsByCareerFilterCity(EntityManager em, Long career_id, String city);
}
