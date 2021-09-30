package dto;

import java.util.ArrayList;
import java.util.List;

import model.Career;
import model.Student;

public class ReportDTO {
	
	private Career career;
	private List<ReportYearDTO> reports;
	
	public ReportDTO(Career career) {
		this.career = career;
		this.reports = new ArrayList<ReportYearDTO>();
	}
	
	public ReportDTO() {
		super();
	}
	
	public Career getCareer() {
		return career;
	}
	
	public void addReportsByYear(List<ReportYearDTO> reports) {
		this.reports = reports; 
	}

	@Override
	public String toString() {
		return "ReportDTO [career=" + career.getName() + "\n reports=" + reports + "]";
	}
}
