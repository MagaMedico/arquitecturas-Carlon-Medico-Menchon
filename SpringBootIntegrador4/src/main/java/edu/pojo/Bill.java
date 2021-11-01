package edu.pojo;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
public class Bill{
 
	//@description Atributos
	@Column(name = "client_id")
    private Long clientId;
    @Column(name = "bill_id")
    private Long billId;
 
    //@description Constructores
    public Bill() {	}
    
    public Bill(Long clientId, Long billId) {
		super();
		this.clientId = clientId;
		this.billId = billId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	@Override
	public String toString() {
		return "Bill [clientId=" + clientId + ", billId=" + billId + "]";
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
