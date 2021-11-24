package edu.grocery.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
 * una factura dentro de la aplicación y utilizando el ORM de JPA:
 * identificador único autogenerado y cliente asociado a esa factura.
 * @see Serializable
 */
@Entity
@Data
public class Bill implements Serializable{
 
	/**
	 * Atributos
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(notes= "Id of the Bill", name="billId", required=true, value="22", example="22")
    private Long billId;	
	@ManyToOne
	@ApiModelProperty(notes= "Client of the Bill", name="client", required=true)
    private Client client;
	
    /**
     * Constructores
     */
    public Bill() {	}
    
	public Bill(Client client) {
		super();
		this.client = client;
	}
	
	public Bill(Long billId, Client client) {
		super();
		this.billId = billId;
		this.client = client;
	}

	/**
	 * Getters y Setters
	 */
	public Long getBillId() {
		return billId;
	}
	public void setBillId(Long billId) {
		this.billId = billId;
	}	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	/**
	 * Visualizar los datos
	 */
	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", client=" + client + "]";
	}
}
