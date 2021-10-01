package imodel;

import java.util.List;

import javax.persistence.EntityManager;
import org.apache.commons.csv.CSVParser;

import model.Career;

/*
	@author Cecilia Carlón: ceciliacarlon2@gmail.com
			Magalí Médico: magamedico@gmail.com
			Magalí Menchón: magalimenchon@gmail.com	
	@version unica
*/

public interface ICareer {
	/*
		@param1 del tipo EntityManager para poder establecer la conexión
		@param2 del tipo CSVParser para poder insertar los datos
		@return del tipo void
		@description recibe una lista de datos para poder insertar en la base de datos.
	*/
	public void careerPersistence(EntityManager em, CSVParser parserCareer);
	/*
		@param1 del tipo EntityManager para poder establecer la conexión
		@return del tipo List<Career>
		@description obtiene todas las carreras ordenadas por la cantidad de estudiantes que estas tengan.
	*/
	public List<Career> getCareersOrderByStudents(EntityManager em);
}
