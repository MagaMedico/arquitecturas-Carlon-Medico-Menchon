package edu.grocery.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BestProductDTO {
	/**
	 * Atributos
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@Column
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
