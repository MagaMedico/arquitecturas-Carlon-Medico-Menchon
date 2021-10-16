package model;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/*
	@author Cecilia Carlón: ceciliacarlon2@gmail.com
			Magalí Médico: magamedico@gmail.com
			Magalí Menchón: magalimenchon@gmail.com	
	@version unica
*/

@Entity
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Career {
	//@description Atributos
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	@NaturalId
	private String name;
	@Column
	private int length;
	@OneToMany(mappedBy = "career", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<CareerStudent> students;
	
	//@description Constructores
	public Career() {}
	
	public Career(String name, int length) {
		super();
		this.name = name;
		this.length = length;
		this.students = new ArrayList<CareerStudent>();
	}

	//@description Getters y Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public List<CareerStudent> getStudents() {
		return students;
	}
	
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param s del tipo @see Student
	 * @return del tipo void
	 * @description crea un @see CareerStudent, agrega la carrera actual en la lista del estudiante y 
	 * agrega a la lista de estudiantes el nuevo CareerStudent.
	 */
	public void addStudent(Student s) {
        CareerStudent cs = new CareerStudent(s, this);
        students.add(cs);
        s.getCareers().add(cs);
    }
	
	//@description se hace un override del equals para adaptarlo a esta clase.
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Career career = (Career) o;
        return Objects.equals(name, career.name);
    }
 
	//@description se hace un override del hashCode para adaptarlo a esta clase.
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    //@description se hace un override del toString para adaptarlo a esta clase.
	@Override
	public String toString() {
		return "Career [id=" + id + ", name=" + name + ", length=" + length + "]";
	}
}
