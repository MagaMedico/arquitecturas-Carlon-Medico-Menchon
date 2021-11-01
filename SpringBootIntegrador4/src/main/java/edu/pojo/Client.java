package edu.pojo;

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
	@OneToMany(mappedBy = "product", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<Product> products;
	
	public Client() { }
	public Client(long dNI, String name, String lastname, List<Product> products) {
		super();
		DNI = dNI;
		this.name = name;
		this.lastname = lastname;
		this.products = products;
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
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public long getDNI() {
		return DNI;
	}	

}
