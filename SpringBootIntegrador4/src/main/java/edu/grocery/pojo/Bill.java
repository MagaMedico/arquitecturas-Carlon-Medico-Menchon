package edu.grocery.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Bill implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@description Atributos
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long billId;
	
	@ManyToOne
    private Client client;
	
    //@description Constructores
    public Bill() {	}
    
	public Bill(Client client) {
		super();
		this.client = client;
	}

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


	public Bill(Long billId, Client client) {
		super();
		this.billId = billId;
		this.client = client;
	}

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", client=" + client + "]";
	}

    
}
