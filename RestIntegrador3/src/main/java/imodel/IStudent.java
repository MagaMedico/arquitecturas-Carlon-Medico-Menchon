package imodel;

import java.util.List;

import model.Student;

/*
	@author Cecilia Carlón: ceciliacarlon2@gmail.com
			Magalí Médico: magamedico@gmail.com
			Magalí Menchón: magalimenchon@gmail.com	
	@version unica
	@description Interfaz que especifica qué lógica de comportamiento deberá
	describir aquella clase que la implemente.
*/
public interface IStudent {
	
	/**
	 * 
	 * @param em del tipo EntityManager para poder establecer la conexión
	 * @param DNI del tipo long e ID de la entidad Student
	 * @param name del tipo String y nombre del estudiante
	 * @param lastname del tipo String y apellido del estudiante
	 * @param age del tipo int y edad del estudiante
	 * @param gender del tipo String y edad género del estudiante
	 * @param LU del tipo int y libreta universitaria única del estudiante
	 * @param city del tipo String y ciudad del estudiante
	 * @see Student
	 * @return del tipo void
	 * @description inserta un nuevo estudiante en la base de datos con los datos pasados por parámetro.
	 */
	public boolean insertStudent(long DNI, String name, String lastname, int age, String gender, long LU, String city);

	/**
	 * 
	 * @param em del tipo EntityManager para poder establecer la conexión
	 * @return del tipo List<Student>
	 * @see Student
	 * @description obtiene todos los estudiantes en orden alfabetico por su apellido.
	 */
	public List<Student> getStudentsWithOrderBy();

	/**
	 * 
	 * @param em del tipo EntityManager para poder establecer la conexión
	 * @param LU  del tipo long ya que es la Libreta Universitaria
	 * @return del tipo Student
	 * @see Student
	 * @description obtiene todos los estudiantes que figuren con el mismo genero que el valor pasado por parámetro.
	 */
	public List<Student> getStudentByLU(Long LU);

	/**
	 * 
	 * @param em del tipo EntityManager para poder establecer la conexión
	 * @param gender del tipo String ya que es el genero de algún estudiante
	 * @return del tipo List<Student>
	 * @see Student
	 * @description obtiene todos los estudiantes que figuren con el mismo genero que el valor pasado por parámetro.
	 */
	public List<Student> getStudentsByGender(String gender);

	/**
	 * Retorna una lista de estudiantes, obtenida mediante una consulta JPQL mediante
	 * la entidad @see Student.
	 */
	List<Student> getStudents();
}
