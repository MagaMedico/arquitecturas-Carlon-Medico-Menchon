package edu.isistan.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Socio {
	
//FIELDS
	
	@Id
	private int id;
	
	//Es una relación 1 a 1 con Persona.
	//Se indica que utilice el mapeo sobre la id.
	//Termina siendo PK y FK a la vez.
	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	private Persona persona;
	@Column
	private String tipo;
	
	
//CONSTRUCTORS

	public Socio() {
		super();
	}

	
	public Socio(Persona persona, String tipo) {
		super();
		this.persona = persona;
		this.tipo = tipo;
	}



//METHODS

//GETTERS & SETTERS

	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public int getId() {
		return id;
	}
	

//OVERRIDES
	
	@Override
	public String toString() {
		return "Socio [persona=" + persona + ", tipo=" + tipo + "]";
	}

}
