package edu.grocery.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
/**
 * @author Cecilia Carlón: ceciliacarlon2@gmail.com
		   Magalí Médico: magamedico@gmail.com
		   Magalí Menchón: mamenchon@alumnos.exa.unicen.edu.ar	
 * @version 2.0
 * @since 22/11/2021
 * Data Transfer Object para generar un reporte con el
 * monto total de las compras de cada cliente
 */
@Entity
public class ReportEntireAmount {
	
	//Atributos
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(notes= "Id of the entire amount report", name="id", required=true,
	value="15")
	private long id;
	@Column
	@ApiModelProperty(notes= "Name of the client", name="name", required=true, value="Jose")
	private String name;
	@Column
	@ApiModelProperty(notes= "Total price of the entire amount report", name="amount",
	required=true, value="1235")
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
