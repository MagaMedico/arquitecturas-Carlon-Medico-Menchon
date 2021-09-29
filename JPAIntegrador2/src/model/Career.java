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
	@OneToMany(mappedBy = "career", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<CareerStudent> students;
	
	public Career() {}
	
	public Career(String name, int length) {
		super();
		this.name = name;
		this.length = length;
		this.students = new ArrayList<CareerStudent>();
	}

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

	public void addStudent(Student s) {
        CareerStudent cs = new CareerStudent(s, this);
        students.add(cs);
        s.getCareers().add(cs);
    }
	
	public void addStudent(CareerStudent cs) {
		students.add(cs);
		cs.getStudent().getCareers().add(cs);
	}

	public Long getId() {
		return id;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Career career = (Career) o;
        return Objects.equals(name, career.name);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

	@Override
	public String toString() {
		return "hola";
		//return "Career [id=" + id + ", name=" + name + ", length=" + length + ", students=" + students.toString() + "]";
	}
}
