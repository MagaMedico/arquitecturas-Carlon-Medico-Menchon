package edu.isistan.dao;
import java.util.*;

import javax.persistence.*;

@Entity
public class Student {
	
	@Id
	private int DNI;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String lastName;
	@Column(nullable=false)
	private Date age;
	@Column(nullable=false)
	private String gender;
	@Column(nullable=false)
	private String city;
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int LU;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CareerStudent> students;
	@ManyToMany (mappedBy = "listStudents")
	private List<Career> listCareers;
	
	public Student() {
		super();
	}
	public Student(int dNI, String name, String lastName, Date age, String gender, String city, int lU) {
		super();
		DNI = dNI;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.city = city;
		LU = lU;
		this.listCareers = new ArrayList<Career>();
		this.students = new ArrayList<CareerStudent>();
	}

	public long getDNI() {
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
	public Date getAge() {
		return age;
	}
	public void setAge(Date age) {
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
	public int getUniversityNumber() {
		return LU;
	}
	public void setUniversityNumber(int universityNumber) {
		this.LU = universityNumber;
	}
	public List<Career> getCareers() {
		return listCareers;
	}
	public void addCarrers(Career career) {
		listCareers.add(career);
	}	
	
}
