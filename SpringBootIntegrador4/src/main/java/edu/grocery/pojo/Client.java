package edu.grocery.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Client {
	
	@Id
	private long DNI;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String lastname;
	
	public Client() { }
	public Client(long dNI, String name, String lastname) {
		super();
		DNI = dNI;
		this.name = name;
		this.lastname = lastname;
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

	public long getDNI() {
		return DNI;
	}	
	public void setDNI(long dni) {
		this.DNI = dni;
	}	
}
