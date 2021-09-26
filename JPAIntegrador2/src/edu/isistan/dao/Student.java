package edu.isistan.dao;
import java.util.*;

import javax.persistence.*;

@Entity
public class Student {
	
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
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long LU;
    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CareerStudent> careers;
	/*@ManyToMany (mappedBy = "students")
	private List<Career> careers;*/
	
	public Student() {
		super();
	}
	public Student(Long dni, String name, String lastName, int age, String gender, String city, Long lU) {
		super();
		this.DNI = dni;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.city = city;
		LU = lU;
		//this.careers = new ArrayList<Career>();
		this.careers = new ArrayList<CareerStudent>();
	}

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
	public Long getUniversityNumber() {
		return LU;
	}
	public void setUniversityNumber(Long universityNumber) {
		this.LU = universityNumber;
	}
	/*public List<Career> getCareers() {
		return careers;
	}
	public void addCarrers(Career career) {
		careers.add(career);
	}*/
	
}
