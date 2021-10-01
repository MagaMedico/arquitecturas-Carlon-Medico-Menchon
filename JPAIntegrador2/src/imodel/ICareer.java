package imodel;

import java.util.List;

import javax.persistence.EntityManager;
import org.apache.commons.csv.CSVParser;

import model.Career;

/*
	@author Cecilia Carl�n: ceciliacarlon2@gmail.com
			Magal� M�dico: magamedico@gmail.com
			Magal� Mench�n: magalimenchon@gmail.com	
	@version unica
*/

public interface ICareer {
	/*
		@param1 del tipo EntityManager para poder establecer la conexi�n
		@param2 del tipo CSVParser para poder insertar los datos
		@return del tipo void
		@description recibe una lista de datos para poder insertar en la base de datos.
	*/
	public void careerPersistence(EntityManager em, CSVParser parserCareer);
	/*
		@param1 del tipo EntityManager para poder establecer la conexi�n
		@return del tipo List<Career>
		@description obtiene todas las carreras ordenadas por la cantidad de estudiantes que estas tengan.
	*/
	public List<Career> getCareersOrderByStudents(EntityManager em);
}
