package model;
import java.util.*;

import javax.persistence.*;

/*
	@author Cecilia Carlón: ceciliacarlon2@gmail.com
			Magalí Médico: magamedico@gmail.com
			Magalí Menchón: magalimenchon@gmail.com	
	@version unica
*/

@Entity
public class Student{
	
	//@description Atributos
	@Id
	private Long DNI;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String lastName;
	@Column(nullable=false)
	private int age;
	@Column(nullable=false)
	private String gender;
	@Column(nullable=false)
	private String city;
	@Column(unique=true)
	private long LU;
    @OneToMany(mappedBy = "student", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<CareerStudent> careers;
	
    //@description Constructores
	public Student() {
		super();
	}
	public Student(Long dni, String name, String lastName, int age, String gender, String city, long LU) {
		super();
		this.DNI = dni;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.city = city;
		this.LU = LU;
		this.careers = new ArrayList<CareerStudent>();
	}

	//@description Getters y setters
	public Long getDNI() {
		return DNI;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getLU() {
		return LU;
	}
	public void setLU(Long LU) {
		this.LU = LU;
	}
	
	public List<CareerStudent> getCareers() {
		return careers;
	}
	
	/**
	 * 
	 * @param c del tipo @see Career
	 * @return del tipo void
	 * @description crea un @see CareerStudent, agrega la carrera actual en la lista del estudiante y 
	 * agrega a la lista de estudiantes el nuevo CareerStudent.
	 */
	public void addCareer(Career c) {
        CareerStudent cs = new CareerStudent(this, c);
        careers.add(cs);
        c.getStudents().add(cs);
    }
	
	//@description se hace un override del equals para adaptarlo a esta clase.
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }
 
	//@description se hace un override del hashCode para adaptarlo a esta clase.
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
	
    //@description se hace un override del toString para adaptarlo a esta clase.
	@Override
	public String toString() {
		return "Student [DNI=" + DNI + ", name=" + name + ", lastName=" + lastName + ", age=" + age + ", gender="
				+ gender + ", city=" + city + ", LU=" + LU + "]";
	}
}
