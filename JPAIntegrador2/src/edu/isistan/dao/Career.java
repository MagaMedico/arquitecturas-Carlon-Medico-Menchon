package edu.isistan.dao;

import java.util.*;

import javax.persistence.*;

@Entity
public class Career {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(nullable=false)
	private String name;
	@Column
	private int length;
	@OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CareerStudent> careers;
	@ManyToMany
	private List<Student> listStudents;
	/*
	@Column
	private ArrayList<Student> graduates;*/
	public Career() {}
	
	public Career(int id, String name, int length) {
		super();
		this.id = id;
		this.name = name;
		this.length = length;
		this.listStudents = new ArrayList<Student>();
		this.careers = new ArrayList<CareerStudent>();
	}
	
	public List<Student> getStudents() {
		return listStudents;
	}
	public void setStudents(ArrayList<Student> students) {
		this.listStudents = students;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
