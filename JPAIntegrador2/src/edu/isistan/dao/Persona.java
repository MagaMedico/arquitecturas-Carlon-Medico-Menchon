package edu.isistan.dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.engine.profile.Fetch;

@Entity
public class Persona {
	
//FIELDS
	
	@Id
	private int id;
	@Column(nullable=false)
	private String nombre;
	@Column(name="anios")
	private int edad;
	//Multiples personas viven en 1 domicilio.
	@ManyToOne
	private Direccion domicilio;
	
	//Para que la Persona tenga el mapeo de la relación ManyToMany
	//El fetch es Lazy por defecto.
	/*Indica que la relación ya está mapeada por el atributo jugadores
	 * de la clase Turno (se da cuenta que es esta entidad, tabla o clase
	 * porque es la colección de este atributo es de ese tipo).*/
	//Esta relación es la que crea la tabla interna.
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "jugadores")
	private List<Turno> turnos;
	
//CONSTRUCTORS

	public Persona() {
		super();
	}

	public Persona(int id, String nombre, int edad, Direccion domicilio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.domicilio = domicilio;
	}


//METHODS

//GETTERS & SETTERS
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Direccion getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Direccion domicilio) {
		this.domicilio = domicilio;
	}

	public int getId() {
		return id;
	}

//OVERRIDES

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", domicilio=" + domicilio + "]";
	}
}
