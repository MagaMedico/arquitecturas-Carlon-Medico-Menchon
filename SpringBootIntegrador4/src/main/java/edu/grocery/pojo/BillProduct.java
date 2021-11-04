package edu.grocery.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "bill_product")
public class BillProduct {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
	@ManyToOne
    private Product product;
	
    @ManyToOne
    private Bill bill;
    
    @Column
    private Date date;
    @Column 
    private int quantity;
    
    //@description Constructores

	public BillProduct(Product product, Bill bill, Date date, int quantity) {
		super();
		this.product = product;
		this.bill = bill;
		this.date = date;
		this.quantity = quantity;
	}

	public BillProduct(long id, Product product, Bill bill, Date date, int quantity) {
		super();
		this.id = id;
		this.product = product;
		this.bill = bill;
		this.date = date;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "BillProduct [id=" + id + ", product=" + product + ", bill=" + bill + ", date=" + date + ", quantity="
				+ quantity + "]";
	}
}
