package edu.isistan;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.isistan.dao.Career;

public class Insert {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		//Insertar los datos
		CSVParser parserCareer = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/csv/career.csv"));
		CSVParser parserStudent = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/csv/student.csv"));
		CSVParser parserCareerStudent = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/csv/career_student.csv"));
		
		for(CSVRecord row: parserCareer) { 
			Long id = Long.parseLong(row.get("id"));
			String name = row.get("name");
			int length = Integer.parseInt(row.get("length"));
			
			Career insert = new Career(id, name, length);
			em.persist(insert);
			em.getTransaction().commit();
			em.close();	
		}

		
		emf.close();
		
	}
}
