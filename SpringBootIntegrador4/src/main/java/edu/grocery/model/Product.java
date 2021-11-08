package edu.grocery.model;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(nullable=false)
	private String name;
	@Column
	private int unitPrice;
	
	public Product() { }
	public Product(String name, int unitPrice) {
		super();
		this.name = name;
		this.unitPrice = unitPrice;
	}
	
	public String getName() {
		return name;
	}
	public Product(long id, String name, int unitPrice) {
		super();
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public long getId() {
		return id;
	}
	
	
}
