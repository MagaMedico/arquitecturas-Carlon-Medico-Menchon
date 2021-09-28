package imodel;

import java.util.List;

import javax.persistence.EntityManager;
import org.apache.commons.csv.CSVParser;

import model.Student;

public interface IStudent {
	public void studentPersistence(EntityManager em, CSVParser parserStudent);
	public void insertStudent(EntityManager em, long DNI, String name, String lastname, int age, String gender, int LU, String city);
	public Student getStudentByLU(EntityManager em, Long LU);
	public List<Student> getStudentsByGender(EntityManager em, String gender);
}
