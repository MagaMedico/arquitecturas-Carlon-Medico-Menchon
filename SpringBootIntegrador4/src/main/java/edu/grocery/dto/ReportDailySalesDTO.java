package edu.grocery.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class ReportDailySalesDTO {

	//ver porqué no genera la id (siempre es 0),
	//aunque no causa conflicto. Revisar si puede quitarse directamente
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@ApiModelProperty(notes= "Id of the daily sales report", name="id", required=true, value="5")
	private long id; 
	@Column
	@ApiModelProperty(notes= "Date of the daily sales report", name="date", required=true,
	value="2021-07-14")
	private LocalDate date;
	@Column
	@ApiModelProperty(notes= "Name of the product", name="nameProduct", required=true,
	value="Oreos")
	private String nameProduct;
	@Column
	@ApiModelProperty(notes= "Total price of the daily sales report", name="totalPriceSale", 
	required=true, value="1000")
	private long totalPriceSale;
	@Column
	@ApiModelProperty(notes= "Total quantity of the daily sales report", name="totalQuantity", 
	required=true, value="10")
	private long totalQuantity;
	
	public ReportDailySalesDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReportDailySalesDTO(LocalDate date, String nameProduct, long totalPriceSale, long totalQuantity) {
		super();
		this.date = date;
		this.nameProduct = nameProduct;
		this.totalPriceSale = totalPriceSale;
		this.totalQuantity = totalQuantity;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public long getTotalPriceSale() {
		return totalPriceSale;
	}

	public void setTotalPriceSale(int totalPriceSale) {
		this.totalPriceSale = totalPriceSale;
	}

	public long getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	@Override
	public String toString() {
		return "ReportDailySalesDTO [date=" + date + ", nameProduct=" + nameProduct + ", totalPriceSale="
				+ totalPriceSale + ", totalQuantity=" + totalQuantity + "]";
	}
}
