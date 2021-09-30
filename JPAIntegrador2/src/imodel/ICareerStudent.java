package imodel;

import java.util.List;

import javax.persistence.EntityManager;
import org.apache.commons.csv.CSVParser;

import dto.ReportDTO;
import model.Career;
import model.CareerStudent;
import model.Student;

public interface ICareerStudent {
	
	public void career_studentPersistence(EntityManager em, CSVParser parserCareerStudent);
	public void addStudent(EntityManager em, long idStudent, long idCareer);
	public List<Student> getStudentsByCareerFilterCity(EntityManager em, Long career_id, String city);
	public List<ReportDTO> getReport(EntityManager em);
}
