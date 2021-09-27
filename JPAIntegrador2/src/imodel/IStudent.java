package imodel;

import javax.persistence.EntityManager;
import org.apache.commons.csv.CSVParser;

import model.Student;

public interface IStudent {
	public void studentPersistence(EntityManager em, CSVParser parserStudent);
	public Student getStudent(EntityManager em, int LU);
}
