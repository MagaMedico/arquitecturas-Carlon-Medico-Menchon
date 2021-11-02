package edu.grocery.pojo;

import java.util.List;

import javax.persistence.*;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(nullable=false)
	private String name;
	@Column
	private int unitPrice;
	@OneToMany(mappedBy = "product", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<BillProduct> bill;
	
	public Product() { }
	public Product(String name, int unitPrice) {
		super();
		this.name = name;
		this.unitPrice = unitPrice;
	}
	
	public String getName() {
		return name;
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
