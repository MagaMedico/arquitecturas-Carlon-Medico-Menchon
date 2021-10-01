package model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

/*
	@author Cecilia Carlón: ceciliacarlon2@gmail.com
			Magalí Médico: magamedico@gmail.com
			Magalí Menchón: magalimenchon@gmail.com	
	@version unica
	@see Embeddable
	@see Serializable
*/

@SuppressWarnings("serial")
@Embeddable
public class CareerStudentId implements Serializable {
 
	//@description Atributos
	@Column(name = "student_id")
    private Long studentId;
    @Column(name = "career_id")
    private Long careerId;
 
    //@description Constructores
    public CareerStudentId() {	}
    
    public CareerStudentId(Long studentId, Long careerId) {
    	this.studentId = studentId;
    	this.careerId = careerId;
    }
    
    //@description Getters
    public Long getStudentId() {
		return studentId;
	}

	public Long getCareerId() {
		return careerId;
	}

	//@description se hace un override del equals para adaptarlo a esta clase.
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        CareerStudentId that = (CareerStudentId) o;
        return Objects.equals(careerId, that.careerId) &&
               Objects.equals(studentId, that.studentId);
    }
 
	//@description se hace un override del hashCode para adaptarlo a esta clase.
    @Override
    public int hashCode() {
        return Objects.hash(careerId, studentId);
    }

    //@description se hace un override del toString para adaptarlo a esta clase.
	@Override
	public String toString() {
		return "CareerStudentId [studentId=" + studentId + ", careerId=" + careerId + "]";
	}
}
