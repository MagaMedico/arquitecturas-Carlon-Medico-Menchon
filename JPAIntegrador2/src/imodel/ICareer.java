package imodel;

import java.util.List;

import javax.persistence.EntityManager;
import org.apache.commons.csv.CSVParser;

import model.Career;


public interface ICareer {
	public void careerPersistence(EntityManager em, CSVParser parserCareer);
	public List<Career> getCareersOrderByStudents(EntityManager em);
}
