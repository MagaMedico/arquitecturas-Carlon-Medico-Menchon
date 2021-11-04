package edu.grocery.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Client {
	
	@Id
	private long DNI;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String lastname;
	
	/*@OneToMany(mappedBy = "billP", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<Bill> bills;
	*/
	public Client() { }
	public Client(long dNI, String name, String lastname) {
		super();
		DNI = dNI;
		this.name = name;
		this.lastname = lastname;
		//this.bills = new ArrayList<Bill>();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/*
	public List<Bill> getbills() {
		return bills;
	}
	public void setbills(List<Bill> bills) {
		this.bills = bills;
	}*/
	public long getDNI() {
		return DNI;
	}	
	public void setDNI(long dni) {
		this.DNI = dni;
	}	
}
