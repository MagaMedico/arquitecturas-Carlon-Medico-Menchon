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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private String name;
	@Column
	private int quantity;
	/**
	 * Constructores
	 */
	public BestProductDTO() { }
	
	public BestProductDTO(String name, int quantity) {
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
	public int getQuantity() {
		return quantity;
	}
	/**
	 * ToString
	 */
	@Override
	public String toString() {
		return "[BestProduct=" + name + ", quantity=" + quantity + "]";
	}
}
