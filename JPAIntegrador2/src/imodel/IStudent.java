package imodel;

import java.util.List;

import javax.persistence.EntityManager;
import org.apache.commons.csv.CSVParser;

import model.Student;

public interface IStudent {
	public void studentPersistence(EntityManager em, CSVParser parserStudent);
	public Student getStudentByLU(EntityManager em, int LU);
	public List<Student> getStudentsByGender(EntityManager em, String gender);
}
