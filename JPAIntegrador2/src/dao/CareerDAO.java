package dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import imodel.ICareer;
import model.Career;
import model.Student;

public class CareerDAO implements ICareer{
	final static String NAME = "name";
	final static String LENGTH = "length";
	
	public CareerDAO() { }
	
	//Persistencia CSV de Career
	@Override
	public void careerPersistence(EntityManager em, CSVParser parserCareer) {
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
