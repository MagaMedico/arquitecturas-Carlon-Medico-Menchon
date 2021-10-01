package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import dao.CareerDAO;
import dao.CareerStudentDAO;
import dao.StudentDAO;
import dto.ReportDTO;
import model.Career;
import model.Student;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final String TYPE = "JPA";
		final String CSV_CAREER = "./src/csv/career.csv";
		final String CSV_STUDENT = "./src/csv/student.csv";
		final String CSV_CAREER_STUDENT = "./src/csv/career_student.csv";
		
		//Inicialización de la persistencia
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(TYPE);
		EntityManager em = emf.createEntityManager();
		/*
		//Insertar los datos
		CSVParser parserCareer = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_CAREER));
		CSVParser parserStudent = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_STUDENT));
		CSVParser parserCareerStudent = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_CAREER_STUDENT));
		*/
		//Instanciación de los dao's
		CareerDAO career = new CareerDAO();
		StudentDAO student = new StudentDAO();
		CareerStudentDAO careerStudent = new CareerStudentDAO();
		/*
		//Persistencia de CSVs
		student.studentPersistence(em, parserStudent);
		career.careerPersistence(em, parserCareer);
		careerStudent.career_studentPersistence(em, parserCareerStudent);
		*/
		
		//Pruebas de consultas ejercicio 2
		//Inciso a
		//student.insertStudent(em, 44113202, "Mariela", "Dominguez", 50, "Female", 500124, "Balcarce");
		//Inciso b
		//careerStudent.addStudent(em, 44113202, 10);
		/*Inciso c
		List<Student> studentsOrderBy = student.getStudentsWithOrderBy(em);
		studentsOrderBy.forEach(s -> System.out.println(s));
		//Inciso d
		System.out.println("Inciso d");
		Student studentByLU = student.getStudentByLU(em, (long) 208077);
		System.out.println(studentByLU.toString());
		//Inciso e
		System.out.println("Inciso e");
		List<Student> studentsByGender = student.getStudentsByGender(em, "Female");
		studentsByGender.forEach(s -> System.out.println(s));
		//Inciso f
		System.out.println("Inciso f");
		List<Career> careers = career.getCareersOrderByStudents(em);
		careers.forEach(c -> System.out.println(c));
		//Inciso g
		System.out.println("Inciso g");
		List<Student> studentsByCareerFilterCity = careerStudent.getStudentsByCareerFilterCity(em, (long) 10, "QÅ­shkÅ­pir");
		//Deberia retornar: 20381505
		studentsByCareerFilterCity.forEach(s -> System.out.println(s));*/
		
		//-----------ASDASDAS
		List<ReportDTO> reports = careerStudent.getReport(em);
		int cont = 0;
		for(ReportDTO r: reports) {
			if(cont == 0) {
				System.out.println(r);
				cont++;
			}
		}
		
		//												------ MENÚ ---------
				Scanner sn = new Scanner(System.in);
		        boolean out = false;
		        int option; 
		 
		        while (!out) {
		        	
		        	System.out.println("1. Inciso a");
		            System.out.println("2. Inciso b");
		            System.out.println("3. Inciso c");
		            System.out.println("4. Inciso d");
		            System.out.println("5. Inciso e");
		            System.out.println("6. Inciso f");
		            System.out.println("7. Inciso g");
		            System.out.println("8. Ejercicio 3: Reporte");
		            System.out.println("9. Salir");
		 
		            try {
		 
		                System.out.println("Escriba una de las opciones");
		                option = sn.nextInt();
		 
		                switch (option) {
		                    case 1:
		                    	System.out.println("a) Dar de alta un estudiante \n" + "Ingrese: ");
		                    	Scanner entry = new Scanner (System.in);
		                    	
		                    	System.out.println("\n Nombre");
		                        String name = entry.nextLine();
		                        
		                        System.out.println("\n DNI");
		                        String DNIEntry = entry.nextLine();
		                        long DNI = Long.parseLong(DNIEntry);
		                        
		                        System.out.println("\n Apellido");
		                        String lastName = entry.nextLine();
		                        
		                        System.out.println("\n Edad");
		                        String ageEntry = entry.nextLine();
		                        int age = Integer.parseInt(ageEntry);
		                        
		                        System.out.println("\n Género");
		                        String gender = entry.nextLine();
		                        
		                        System.out.println("\n Número de libreta universitaria:");
		                        String LUEntry = entry.nextLine();
		                        int LU = Integer.parseInt(LUEntry);
		                        
		                        System.out.println("\n Ciudad");
		                        String city = entry.nextLine();
		                        
		                        student.insertStudent(em, DNI, name, lastName, age, gender, LU, city);
		                        break;
		                    case 2:
		                    	System.out.println("b) Matricular un estudiante en una carrera \n" + "Ingrese: ");
		                    	Scanner entry2 = new Scanner (System.in);
		                    	
		                    	System.out.println("\n DNI del estudiante: ");
		                    	DNIEntry = entry2.nextLine();
		                        DNI = Long.parseLong(DNIEntry);
		                        
		                    	System.out.println("\n Identificador de la carrera: ");
		                    	String idCareerEntry = entry2.nextLine();
		                        long idCareer = Long.parseLong(idCareerEntry);
		                        
		                        careerStudent.addStudent(em, DNI, idCareer);
		                        break;
		                    case 3:
		                    	System.out.println("\n c) Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple. \n");
		                    	
		                    	List<Student> studentsOrderBy = student.getStudentsWithOrderBy(em);
		                		
		                    	studentsOrderBy.forEach(s -> System.out.println(s));
		                        break;
		                    case 4:
		                    	System.out.println("\n d) Recuperar un estudiante, en base a su número de libreta universitaria. \n" + "Ingrese: ");
		                    	Scanner entry3 = new Scanner (System.in);
		                    	
		                    	System.out.println("\n Número de libreta universitaria:");
		                        LUEntry = entry3.nextLine();
		                        long LU3 = Long.parseLong(LUEntry);
		                    	
		                    	Student studentByLU = student.getStudentByLU(em, LU3);
		                		
		                    	System.out.println(studentByLU.toString());
		                        break; 
		                    case 5:
		                    	System.out.println("\n e) Recuperar todos los estudiantes, en base a su género. \n" + "Ingrese: ");
		                    	Scanner entry4 = new Scanner (System.in);
		                    	
		                    	System.out.println("\n Género:");
		                        gender = entry4.nextLine();
		                    	
		                        List<Student> studentsByGender = student.getStudentsByGender(em, gender);
		                		studentsByGender.forEach(s -> System.out.println(s));
		                        break; 
		                    case 6:
		                    	System.out.println("\n f) Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos. \n");
		                    	
		                    	List<Career> careers = career.getCareersOrderByStudents(em);
		                		
		                    	careers.forEach(c -> System.out.println(c));
		                        break;
		                    case 7:
		                    	System.out.println("\n g) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia. \n" + "Ingrese: ");
		                    	Scanner entry5 = new Scanner (System.in);
		                    	
		                    	System.out.println("\n Identificador de la carrera: ");
		                    	idCareerEntry = entry5.nextLine();
		                        idCareer = Long.parseLong(idCareerEntry);
		                    	
		                        System.out.println("\n Ciudad");
		                        city = entry5.nextLine();
		                        
		                        List<Student> studentsByCareerFilterCity = careerStudent.getStudentsByCareerFilterCity(em, idCareer, city);
		                		studentsByCareerFilterCity.forEach(s -> System.out.println(s));
		                        break; 
		                    case 8:
		                    	System.out.println("\n 3- Generar un reporte de las carreras, que para cada carrera incluya información de los\r\n"
		                    			+ "inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar\r\n"
		                    			+ "los años de manera cronológica.\n");
		                    	
		                    	List<ReportDTO> reports = careerStudent.getReport(em);
		                		
		                    	int cont = 0;
		                		for(ReportDTO r: reports) {
		                			if(cont == 0) {
		                				System.out.println(r);
		                				cont++;
		                			}
		                		}
		                        break;
		                    case 9:
		                    	out = true;
		                        break;
		                    default:
		                        System.out.println("Solo números entre 1 y 9");
		                }
		            } catch (InputMismatchException e) {
		                System.out.println("Debe insertar un número");
		                sn.next();
		            }
		        }
		
		//Cierre del manejador de entidades
		em.close();	
		emf.close();
		
	}

}
