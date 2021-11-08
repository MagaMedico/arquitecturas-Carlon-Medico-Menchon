package edu.grocery.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReportEntireAmount {
	
	//Atributos
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@Column
	private long amount;
	
	//Constructores
	public ReportEntireAmount() { }
	public ReportEntireAmount(String name, long amount) {
		this.name = name;
		this.amount = amount;
	}
	
	//Getters y Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	//Metodo para visualizar los datos
	@Override
	public String toString() {
		return "Client= " + name + " - Amount= " + amount;
	}
}
