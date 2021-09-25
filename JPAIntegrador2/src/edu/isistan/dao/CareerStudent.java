package edu.isistan.dao;

import java.util.*;
import javax.persistence.*;

@Entity(name = "CareerStudents")
@Table(name = "career_student")
public class CareerStudent {

	@EmbeddedId
    private CareerStudentId id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
    private Career career;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("DNI")
    private Student student;
    @Column
    private Integer graduation;
    @Column 
    private int antiquity;
 
    private CareerStudent() {}

	public CareerStudent(Career career, Student student) {
		super();
		this.career = career;
		this.student = student;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        CareerStudent that = (CareerStudent) o;
        return Objects.equals(career, that.career) &&
               Objects.equals(student, that.student);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(career, student);
    }
	
	public Career getCareer() {
		return career;
	}
	public void setCareer(Career career) {
		this.career = career;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Integer getGraduation() {
		return graduation;
	}
	public void setGraduation(Integer graduation) {
		this.graduation = graduation;
	}
	public int getAntiquity() {
		return antiquity;
	}
	public void setAntiquity(int antiquity) {
		this.antiquity = antiquity;
	}
	public CareerStudentId getId() {
		return id;
	}
	
}
