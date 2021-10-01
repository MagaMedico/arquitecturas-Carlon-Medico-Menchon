package imodel;

import java.util.List;

import javax.persistence.EntityManager;
import org.apache.commons.csv.CSVParser;

import model.Student;

/*
	@author Cecilia Carl�n: ceciliacarlon2@gmail.com
			Magal� M�dico: magamedico@gmail.com
			Magal� Mench�n: magalimenchon@gmail.com	
	@version unica
*/

public interface IStudent {
	/*
		@param1 del tipo EntityManager para poder establecer la conexi�n
		@param2 del tipo CSVParser para poder insertar los datos
		@return del tipo void
		@description recibe una lista de datos para poder insertar en la base de datos.
	*/
	public void studentPersistence(EntityManager em, CSVParser parserStudent);
	/*
		@param1 del tipo EntityManager para poder establecer la conexi�n
		@param2 al param8 son los datos que se deben insertar en la tabla @see Student
		@return del tipo void
		@description inserta un nuevo estudiante en la base de datos con los datos pasados por par�metro.
	*/
	public void insertStudent(EntityManager em, long DNI, String name, String lastname, int age, String gender, int LU, String city);
	/*
		@param1 del tipo EntityManager para poder establecer la conexi�n
		@return del tipo List<Student>
		@description obtiene todos los estudiantes en orden alfabetico por su apellido.
	*/
	public List<Student> getStudentsWithOrderBy(EntityManager em);
	/*
		@param1 del tipo EntityManager para poder establecer la conexi�n
		@param2 del tipo long ya que es la Libreta Universitaria
		@return del tipo Student
		@description obtiene el estudiante que tenga el mismo valor en la LU que el valor pasado por par�metro.
	*/
	public Student getStudentByLU(EntityManager em, Long LU);
	/*
		@param1 del tipo EntityManager para poder establecer la conexi�n
		@param2 del tipo String ya que es el genero de alg�n estudiante
		@return del tipo List<Student>
		@description obtiene todos los estudiantes que figuren con el mismo genero que el valor pasado por par�metro.
	*/
	public List<Student> getStudentsByGender(EntityManager em, String gender);
}
