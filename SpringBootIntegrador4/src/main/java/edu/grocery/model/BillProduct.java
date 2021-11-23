package edu.grocery.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "bill_product")
public class BillProduct {

	//Atributos
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@ApiModelProperty(notes= "Id of the BillProduct", name="id", required=true,
	value="1", example="1")
	private long id;	
	@ManyToOne
	@ApiModelProperty(notes= "Product of the BillProduct", name="product", required=true)
    private Product product;	
    @ManyToOne
    @ApiModelProperty(notes= "Bill of the BillProduct", name="bill", required=true)
    private Bill bill;   
    @Column
    @ApiModelProperty(notes= "Product purchase day", name="date", required=true, 
    value="2021-11-23", example="2021-11-23")
    private LocalDate date;
    @Column 
    @ApiModelProperty(notes= "Product quantity", name="quantity", required=true, 
    value="18" ,example="18")
    private int quantity;
    
    //Constructores   
    public BillProduct() { }
    
	public BillProduct(Product product, Bill bill, LocalDate date, int quantity) {
		super();
		this.product = product;
		this.bill = bill;
		this.date = date;
		this.quantity = quantity;
	}

	public BillProduct(long id, Product product, Bill bill, LocalDate date, int quantity) {
		super();
		this.id = id;
		this.product = product;
		this.bill = bill;
		this.date = date;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	//Getters y Setters
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	//Metodo para visualizar los datos
	@Override
	public String toString() {
		return "BillProduct [id=" + id + ", product=" + product + ", bill=" + bill + ", date=" + date + ", quantity="
				+ quantity + "]";
	}
}
