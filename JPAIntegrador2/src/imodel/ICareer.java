package imodel;

import javax.persistence.EntityManager;
import org.apache.commons.csv.CSVParser;


public interface ICareer {
	public void careerPersistence(EntityManager em, CSVParser parserCareer);
}
