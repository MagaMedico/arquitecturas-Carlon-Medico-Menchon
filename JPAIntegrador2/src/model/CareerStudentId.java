package model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@SuppressWarnings("serial")
@Embeddable
public class CareerStudentId implements Serializable {
 
	@Column(name = "student_id")
    private Long studentId;
    @Column(name = "career_id")
    private Long careerId;
 
    public CareerStudentId() {	}
    
    public CareerStudentId(Long studentId, Long careerId) {
    	this.studentId = studentId;
    	this.careerId = careerId;
    }
    
    public Long getStudentId() {
		return studentId;
	}

	public Long getCareerId() {
		return careerId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        CareerStudentId that = (CareerStudentId) o;
        return Objects.equals(careerId, that.careerId) &&
               Objects.equals(studentId, that.studentId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(careerId, studentId);
    }

	@Override
	public String toString() {
		return "CareerStudentId [studentId=" + studentId + ", careerId=" + careerId + "]";
	}
}
