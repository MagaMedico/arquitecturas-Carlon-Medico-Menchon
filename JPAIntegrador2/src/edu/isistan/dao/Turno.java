package edu.isistan.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Turno {

//FIELDS

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private Timestamp fecha;
	// Relación many to many con el conjunto de Personas denominada jugadores
	// El fetch en los casos a many es por defecto Lazy.
	//
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Persona> jugadores;

//CONSTRUCTORS

	public Turno() {
		super();
	}

	public Turno(Timestamp fecha) {
		super();
		this.fecha = fecha;
		this.jugadores = new ArrayList<Persona>();
	}

	
// METHODS

// GETTERS & SETTERS

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public List<Persona> getJugadores() {
		return jugadores;
	}


//OVERRIDES

	@Override
	public String toString() {
		return "Turno [id=" + id + ", fecha=" + fecha + ", jugadores=" + jugadores + ", getFecha()=" + getFecha()
				+ ", getId()=" + getId() + ", getJugadores()=" + getJugadores() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
