package dao;

import java.util.List;

import javax.persistence.EntityManager;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import imodel.ICareer;
import model.Career;
/**
 * 
 * @author Cecilia Carl�n: ceciliacarlon2@gmail.com
		   Magal� M�dico: magamedico@gmail.com
		   Magal� Mench�n: magalimenchon@gmail.com	
	@version unica
	@description Implementa la l�gica de comportamiento de los m�todos especificados
	en la interfaz @see ICarrer. De esta forma define el los m�todos relacionados
	con la base de datos de la entidad @see Career
 *
 */
public class CareerDAO implements ICareer{
	/**
	 * Constantes de la clase
	 */
	final static String NAME = "name";
	final static String LENGTH = "length";
	
	/**
	 * Constructor vac�o necesario para JPA
	 */
	public CareerDAO() { }
	
	/**
	 * Dado un archivo CSV recorre todas sus filas y setea
	 * los datos recibidos de tipo String (de ser necesario parseados) a la
	 * tabla career de la base de datos, mediante la persistencia dada por el
	 * EntityManager y la entidad @see Career
	 */
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

	/**
	 * Retorna una lista de @see Career mediante una consulta de
	 * JPQL, seleccionando las distintas carreras por medio de la entidad
	 * @see Career obteniendo la cantidad de estudiantes de cada una
	 * y ordenandolas por esta especificaci�n.
	 * @see EntityManager
	 */
	@Override
	public List<Career> getCareersOrderByStudents(EntityManager em) {
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Career> careers = em.createQuery("SELECT DISTINCT c FROM Career c JOIN c.students s WHERE size(s) > 0 ORDER BY size(s)").getResultList();
		em.getTransaction().commit();
		return careers;
	}
	
}
