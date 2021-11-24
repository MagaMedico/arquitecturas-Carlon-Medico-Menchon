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
 * un cliente dentro de la aplicación y utilizando el ORM de JPA:
 * identificador único coincidente con su DNI, nombre y apellido.
 */
@Entity
@Data
public class Client {
	/**
	 * Atributos
	 */
	@Id
	@ApiModelProperty(notes= "DNI of the Client", name="DNI", required=true, 
	value="21460139", example="21460139")
	private long DNI;
	@Column(nullable=false)
	@ApiModelProperty(notes= "Name of the Client", name="name", required=true,
	value="Silvana", example="Silvana")
	private String name;
	@Column(nullable=false)
	@ApiModelProperty(notes= "Lastname of the Client", name="lastname", required=true,
	value="Cejas", example="Cejas")
	private String lastname;
	
	/**
	 * Constructores
	 */
	public Client() { }
	public Client(long DNI, String name, String lastname) {
		super();
		this.DNI = DNI;
		this.name = name;
		this.lastname = lastname;
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
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public long getDNI() {
		return DNI;
	}	
	public void setDNI(long dni) {
		this.DNI = dni;
	}	
}
