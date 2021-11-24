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
 * Data Transfer Object para mostrar cuá fue el producto más vendido
 */
@Entity
public class BestProductDTO {
	/**
	 * Atributos
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(notes= "Id of the best product", name="id", required=true, value="1")
	private long id;
	@Column
	@ApiModelProperty(notes= "Name of the best product", name="name", required=true, value="Doritos")
	private String name;
	@Column
	@ApiModelProperty(notes= "Product quantity", name="quantity", required=true, value="32")
	private long quantity;
	/**
	 * Constructores
	 */
	public BestProductDTO() { }
	
	public BestProductDTO(String name, long quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}
	/**
	 * Getters
	 */
	public String getName() {
		return name;
	}
	public long getQuantity() {
		return quantity;
	}
	/**
	 * ToString
	 */
	@Override
	public String toString() {
		return "[BestProduct=" + id + ", nombre=" + name + ", cantidad=" + quantity + "]";
	}
}
