package model;
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
	//@Column(nullable=false)
	//@GeneratedValue(strategy=GenerationType.AUTO)	//solo posible su uso para IDs
	@Column(unique=true)
	private long LU;
    @OneToMany(mappedBy = "student", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<CareerStudent> careers;
	/*@ManyToMany (mappedBy = "students")
	private List<Career> careers;*/
	
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
	public long getLU() {
		return LU;
	}
	public void setLU(Long LU) {
		this.LU = LU;
	}
	/*public List<Career> getCareers() {
		return careers;
	}
	public void addCarrers(Career career) {
		careers.add(career);
	}*/
	
}
