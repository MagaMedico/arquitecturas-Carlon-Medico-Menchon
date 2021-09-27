package dao;

import javax.persistence.EntityManager;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import imodel.ICareer;
import model.Career;

public class CareerDAO implements ICareer{
	final static String NAME = "name";
	final static String LENGTH = "length";
	
	public CareerDAO() { }
	
	@Override
	public void careerPersistence(EntityManager em, CSVParser parserCareer) {
		//Persistencia CSV de Career
		for(CSVRecord row: parserCareer) { 
			em.getTransaction().begin();
			String name = row.get(NAME);
			int length = Integer.parseInt(row.get(LENGTH));
					
			Career insert = new Career(name, length);
			em.persist(insert);
			em.getTransaction().commit();
		}
	}
}
