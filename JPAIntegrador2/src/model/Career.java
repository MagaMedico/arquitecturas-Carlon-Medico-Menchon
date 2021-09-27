package model;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Career {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	@NaturalId
	private String name;
	@Column
	private int length;
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CareerStudent> students;
	/*@ManyToMany
	private List<Student> students;*/
	/*
	@Column
	private ArrayList<Student> graduates;*/
	public Career() {}
	
	public Career(/*Long id, */String name, int length) {
		super();
		//this.id = id;
		this.name = name;
		this.length = length;
		//this.students = new ArrayList<Student>();
		this.students = new ArrayList<CareerStudent>();
	}
	
	/*public List<Student> getStudents() {
		return students;
	}
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}*/
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
