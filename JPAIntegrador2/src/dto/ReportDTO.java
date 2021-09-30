package dto;

import java.util.ArrayList;
import java.util.List;

import model.Career;
import model.Student;

public class ReportDTO {
	
	private Career career;
	private List<Student> graduates;
	private List <Student> registered;
	
	
	public ReportDTO(Career career) {
		this.career = career;
		this.graduates = new ArrayList<>();
		this.registered = new ArrayList<>();	
	}
	
	public ReportDTO() {
		super();
	}
	
	public Career getCareer() {
		return career;
	}
	
	public List<Student> getGraduates() {
		return graduates;
	}
	
	public List<Student> getRegistered() {
		return registered;
	}
	
	public void addGratuate(Student graduate) {
		this.graduates.add(graduate);
	}
	
	public void addRegistered(Student registered) {
		this.graduates.add(registered);
	}

	public void setGraduates(List<Student> graduates) {
		this.graduates = graduates;
	}

	public void setRegistered(List<Student> registered) {
		this.registered = registered;
	}
	
	
}
