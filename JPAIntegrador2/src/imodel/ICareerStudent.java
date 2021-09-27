package imodel;

import javax.persistence.EntityManager;
import org.apache.commons.csv.CSVParser;

public interface ICareerStudent {
	public void career_studentPersistence(EntityManager em, CSVParser parserCareerStudent);
}
