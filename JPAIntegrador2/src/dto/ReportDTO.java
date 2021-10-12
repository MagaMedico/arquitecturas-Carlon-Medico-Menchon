package dto;

import java.util.List;

import model.Career;
import model.Student;

public class ReportDTO {
	private Career career;
	private Integer year;
	private List<Student> graduated;
	private List<Student> registered;
	
	public ReportDTO(Career career, Integer year, List<Student> graduated, List<Student> registered) {
		this.career = career;
		this.year = year;
		this.graduated = graduated;
		this.registered = registered;
	}
	
	public Integer getYears() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<Student> getGraduates() {
		return graduated;
	}
	
	public List<Student> getRegistered() {
		return registered;
	}
	
	public void addGratuate(Student graduate) {
		this.graduated.add(graduate);
	}
	
	public void addRegistered(Student registered) {
		this.graduated.add(registered);
	}

	public void setGraduated(List<Student> graduated) {
		this.graduated = graduated;
	}

	public void setRegistered(List<Student> registered) {
		this.registered = registered;
	}

	@Override
	public String toString() {
		return "\n" + career + "\n" + year + "\n graduated=" + graduated + "\n registered=" + registered;
	}
}
