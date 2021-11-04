package edu.grocery.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@Entity
@JsonIgnoreType
public class Bill{
 
	//@description Atributos
	@Id
    @Column(name = "bill_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long billId;
	
	
	/*@Column(name = "client_id")
    private Long clientId;*/
	
	
	@ManyToOne
	@MapsId("DNI")
	@JoinColumn(name = "client_DNI")
	@JsonBackReference
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
	}/*
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}*/
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}


	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", client=" + client + "]";
	}


	/*//@description se hace un override del equals para adaptarlo a esta clase.
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        CareerStudentId that = (CareerStudentId) o;
        return Objects.equals(careerId, that.careerId) &&
               Objects.equals(studentId, that.studentId);
    }
 
	//@description se hace un override del hashCode para adaptarlo a esta clase.
    @Override
    public int hashCode() {
        return Objects.hash(careerId, studentId);
    }*/

    
}
