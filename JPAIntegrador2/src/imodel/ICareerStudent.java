package imodel;

import java.util.List;

import javax.persistence.EntityManager;
import org.apache.commons.csv.CSVParser;

import dto.ReportDTO;
import model.Career;
import model.CareerStudent;
import model.Student;

public interface ICareerStudent {
	/*
		@param1 del tipo EntityManager para poder establecer la conexión
		@param2 del tipo CSVParser para poder insertar los datos
		@return del tipo void
		@description recibe una lista de datos para poder insertar en la base de datos.
	*/
	public void career_studentPersistence(EntityManager em, CSVParser parserCareerStudent);
	/*
		@param1 del tipo EntityManager para poder establecer la conexión
		@param2 del tipo long ya que es el id del estudiante
		@param3 del tipo long ya que es el id de la carrera
		@return del tipo List<Student>
		@description agrega al estudiante pasado por parametro a la carrera pasada por parametro.
	*/
	public void addStudent(EntityManager em, long idStudent, long idCareer);
	/*
		@param1 del tipo EntityManager para poder establecer la conexión
		@param2 del tipo long ya que es el id de la carrera
		@param3 del tipo String ya que es la ciudad de un estudiante
		@return del tipo List<Student>
		@description obtiene todos los estudiantes que cumplen con el filtro por ciudad.
	*/
	public List<Student> getStudentsByCareerFilterCity(EntityManager em, Long career_id, String city);
	/*
		@param1 del tipo EntityManager para poder establecer la conexión
		@return del tipo List<ReportDTO>
		@description obtiene el reporte de la clase @see CareerStudent.
	*/
	public List<ReportDTO> getReport(EntityManager em);
}
