package edu.grocery.model;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @author Cecilia Carlón: ceciliacarlon2@gmail.com
		   Magalí Médico: magamedico@gmail.com
		   Magalí Menchón: mamenchon@alumnos.exa.unicen.edu.ar	
 * @version 2.0
 * @since 22/11/2021
 * Plain Old Java Object / Entidad que plasma los datos que contendrá
 * un producto dentro de la aplicación y utilizando el ORM de JPA:
 * identificador autogenerado, nombre del producto y precio unitario.
 */
@Entity
@Data
public class Product {

	/**
	 * Atributos
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@ApiModelProperty(notes= "Id of the Product", name="id", required=true
	,value="1", example="1")
	private long id;
	@Column(nullable=false)
	@ApiModelProperty(notes= "Name of the Product", name="name", required=true,
	value="Coca-Cola", example="Coca-Cola")
	private String name;
	@Column
	@ApiModelProperty(notes= "Unit price of the Product", name="unitPrice", required=true,
	value="150", example="150")
	private int unitPrice;
	
	/**
	 * Constructores
	 */
	public Product() { }
	
	public Product(String name, int unitPrice) {
		super();
		this.name = name;
		this.unitPrice = unitPrice;
	}
	
	public Product(long id, String name, int unitPrice) {
		super();
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
	}
	
	/**
	 * Getters y Setters
	 */
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public long getId() {
		return id;
	}

}
