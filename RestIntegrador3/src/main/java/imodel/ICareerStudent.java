package imodel;

import java.util.List;

import javax.persistence.EntityManager;
import org.apache.commons.csv.CSVParser;

import dto.ReportDTO;
import model.Student;
/**
 * @author Cecilia Carlón: ceciliacarlon2@gmail.com
			Magalí Médico: magamedico@gmail.com
			Magalí Menchón: magalimenchon@gmail.com	
   @version unica
 * @description Interfaz que especifica qué lógica de comportamiento deberá
   describir aquella clase que la implemente.
 */
public interface ICareerStudent {

	/**
	 * 
	 * @param em del tipo EntityManager para poder establecer la conexión
	 * @param parserCareerStudent del tipo CSVParser para poder insertar los datos
	 * @return del tipo void
	 * @description recibe una lista de datos para poder insertar en la base de datos.
	 */
	public void career_studentPersistence(CSVParser parserCareerStudent);

	/**
	 * 
	 * @param em del tipo EntityManager para poder establecer la conexión
	 * @param idStudent del tipo long ya que es el id del estudiante
	 * @param idCareer del tipo long ya que es el id de la carrera
	 * @return del tipo List<Student>
	 * @description agrega al estudiante pasado por parametro a la carrera pasada por parametro.
	 */
	public void addStudent(long idStudent, long idCareer);

	/**
	 * 
	 * @param em del tipo EntityManager para poder establecer la conexión
	 * @param career_id  del tipo long ya que es el id de la carrera
	 * @param city del tipo String ya que es la ciudad de un estudiante
	 * @return del tipo List<Student>
	 * @description obtiene todos los estudiantes que cumplen con el filtro por ciudad.
	 */
	public List<Student> getStudentsByCareerFilterCity(Long career_id, String city);

	/**
	 * 
	 * @param em del tipo EntityManager para poder establecer la conexión
	 * @return del tipo List<ReportDTO>
	 * @description obtiene el reporte de la clase @see CareerStudent.
	 */
	public List<ReportDTO> getReport();
}
