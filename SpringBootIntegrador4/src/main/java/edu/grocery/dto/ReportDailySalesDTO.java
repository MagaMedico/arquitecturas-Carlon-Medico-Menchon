package edu.grocery.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReportDailySalesDTO {

	//ver porqué no genera la id (siempre es 0),
	//aunque no causa conflicto. Revisar si puede quitarse directamente
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id; 
	@Column
	private LocalDate date;
	@Column
	private String nameProduct;
	@Column
	private long totalPriceSale;
	@Column
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
