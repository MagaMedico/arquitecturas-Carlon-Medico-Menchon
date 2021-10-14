package dto;

import java.math.BigInteger;
import java.util.List;

import model.Career;
import model.Student;

public class ReportDTO {
	private String career;
	private BigInteger year;
	private BigInteger graduated;
	private BigInteger registered;
	
	public ReportDTO(String career, BigInteger year, BigInteger graduated, BigInteger registered) {
		this.career = career;
		this.year = year;
		this.graduated = graduated;
		this.registered = registered;
	}
	
	public BigInteger getYears() {
		return year;
	}

	public void setYear(BigInteger year) {
		this.year = year;
	}
	
	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public BigInteger getGraduated() {
		return graduated;
	}

	public void setGraduated(BigInteger graduated) {
		this.graduated = graduated;
	}

	public BigInteger getRegistered() {
		return registered;
	}

	public void setRegistered(BigInteger registered) {
		this.registered = registered;
	}

	@Override
	public String toString() {
		return "\n" + career + "\n" + year + "\n graduated=" + graduated + "\n registered=" + registered;
	}
}
