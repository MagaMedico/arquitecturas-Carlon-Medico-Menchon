package edu.isistan.dao;

import java.util.*;
import javax.persistence.*;

@Entity(name = "CareerStudents")
@Table(name = "career_student")
public class CareerStudent {

	@EmbeddedId
    private CareerStudentId id;
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("careerId")
    private Career career;
    @Column
    private Integer graduation;
    @Column 
    private int antiquity;
 
    private CareerStudent() {}

	public CareerStudent(Student student, Career career, Integer graduation, int antiquity) {
		super();
		this.career = career;
		this.student = student;
		this.graduation = graduation;
		this.antiquity = antiquity;
		this.id = new CareerStudentId(student.getDNI(), career.getId());
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        CareerStudent that = (CareerStudent) o;
        return Objects.equals(student, that.student) && 
        	   Objects.equals(career, that.career);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(student, career);
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
