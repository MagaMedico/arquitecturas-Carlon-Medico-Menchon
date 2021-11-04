package edu.grocery.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "bill_product")
public class BillProduct {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
	@ManyToOne
    @MapsId("productId")
	@JoinColumn(name = "product_id")
	@JsonBackReference
    private Product product;
	
	
    @ManyToOne
    @MapsId("billId")
    @JoinColumn(name = "bill_id")
    @JsonBackReference
    private Bill bill;
    
    
    
    @Column
    private Date date;
    @Column 
    private int quantity;
    
    /*
    @ManyToOne(mappedBy = "product", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<BillProduct> bill;
	*/
 
    //@description Constructores

	public BillProduct(Product product, Bill bill, Date date, int quantity) {
		super();
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


	/*//@description se hace un override del equals para adaptarlo a esta clase.
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        CareerStudent that = (CareerStudent) o;
        return Objects.equals(student, that.student) && 
        	   Objects.equals(career, that.career);
    }
 
	//@description se hace un override del hashCode para adaptarlo a esta clase.
    @Override
    public int hashCode() {
        return Objects.hash(student, career);
    }*/
}
