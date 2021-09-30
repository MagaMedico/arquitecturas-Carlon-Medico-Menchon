package dto;

import java.util.ArrayList;
import java.util.List;

import model.Student;

public class ReportYearDTO {
	private Integer year;
	private List<Student> graduated;
	private List<Student> registered;
	
	public ReportYearDTO(Integer year) {
		super();
		this.year = year;
		this.graduated = new ArrayList<>();
		this.registered = new ArrayList<>();
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
		return "\n" + year + "\n graduated=" + graduated + "\n registered=" + registered;
	}
}
