package model;

import java.util.*;
import javax.persistence.*;

/*
	@author Cecilia Carlón: ceciliacarlon2@gmail.com
			Magalí Médico: magamedico@gmail.com
			Magalí Menchón: magalimenchon@gmail.com	
	@version unica
*/

@Entity/*(name = "CareerStudents")*/
@Table(name = "career_student")
public class CareerStudent {

	//@description Atributos
	@EmbeddedId
	private CareerStudentId id;
	@ManyToOne/*(fetch = FetchType.LAZY)*/
    @MapsId("studentId")
	@JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne/*(fetch = FetchType.LAZY)*/
    @MapsId("careerId")
    @JoinColumn(name = "career_id")
    private Career career;
    @Column
    private Integer graduation;
    @Column 
    private int antiquity;
 
    //@description Constructores
    public CareerStudent() {	}
    
    public CareerStudent(Student student, Career career) {
    	super();
		this.career = career;
		this.student = student;
    }

	public CareerStudent(Student student, Career career, Integer graduation, int antiquity) {
		super();
		this.career = career;
		this.student = student;
		this.graduation = graduation;
		this.antiquity = antiquity;
		this.id = new CareerStudentId(student.getDNI(), career.getId());
	}
	
	//@description Getters y setters
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
	
	//@description se hace un override del equals para adaptarlo a esta clase.
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        CareerStudent that = (CareerStudent) o;
        return Objects.equals(student, that.student) && 
        	   Objects.equals(career, that.career);
    }
 
	//@description se hace un override del hashCode para adaptarlo a esta clase.
    @Override
    public int hashCode() {
        return Objects.hash(student, career);
    }

    //@description se hace un override del toString para adaptarlo a esta clase.
	@Override
	public String toString() {
		return "CareerStudent [id=" + this.getId() + ", student=" + student.getDNI() + ", career=" + career.getId() + ", graduation=" + graduation
				+ ", antiquity=" + antiquity + "]";
	}
}
