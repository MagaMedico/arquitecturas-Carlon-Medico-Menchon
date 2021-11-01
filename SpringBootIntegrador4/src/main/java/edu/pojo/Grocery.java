package edu.pojo;

import java.util.List;

import javax.persistence.*;

@Entity
public class Grocery {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(nullable=false)
	private String name;
	@OneToMany(mappedBy = "client", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<Client> clients;
	@OneToMany(mappedBy = "product", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<Product> products;
	
	public Grocery() { }
	public Grocery(String name, List<Client> clients, List<Product> products) {
		super();
		this.name = name;
		this.clients = clients;
		this.products = products;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public long getId() {
		return id;
	}
	

}
