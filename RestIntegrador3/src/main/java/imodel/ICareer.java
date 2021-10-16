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
	@description Interfaz que especifica qué lógica de comportamiento deberá
	describir aquella clase que la implemente.
*/
public interface ICareer {
	
	/**
	 * 
	 * @param em del tipo EntityManager para poder establecer la conexión
	 * @param parserCareer del tipo CSVParser para poder insertar los datos
	 * @return del tipo void
	 * @description recibe una lista de datos para poder insertar en la base de datos.
	 */
	public void careerPersistence(CSVParser parserCareer);
	
	/**
	 * 
	 * @param em del tipo EntityManager para poder establecer la conexión
	 * @return del tipo List<Career>
	 * @description obtiene todas las carreras ordenadas por la cantidad de estudiantes que estas tengan.
	 */
	public List<Career> getCareersOrderByStudents();
}
