package edu.isistan.dao;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Embeddable
public class CareerStudentId implements Serializable {
 
    @Column(name = "student_DNI")
    private int studentId;
 
    @Column(name = "career_id")
    private int careerId;
 
    private CareerStudentId() {}
    
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
}
