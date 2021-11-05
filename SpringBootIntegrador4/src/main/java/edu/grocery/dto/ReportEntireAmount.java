package edu.grocery.dto;

import java.util.List;

import edu.grocery.pojo.BillProduct;
import edu.grocery.pojo.Client;

public class ReportEntireAmount {
	
	//Atributos
	private Client client;
	private List<BillProduct> bills;
	private int amount;
	
	//Constructores
	public ReportEntireAmount() { }
	public ReportEntireAmount(Client c, List<BillProduct> b) {
		this.client = c;
		this.bills = b;
		this.amount = 0;
		this.calculateEntireAmount();
	}
	
	//Metodo para calcular total 
	public void calculateEntireAmount() {
		for(int i=0; i < this.bills.size(); i++) {
			amount += this.bills.get(i).getProduct().getUnitPrice() * this.bills.get(i).getQuantity();
		}
	}
	
	//Getters y Setters
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<BillProduct> getBills() {
		return bills;
	}
	public void addBills(List<BillProduct> b) {
		for(int i=0; i < b.size(); i++) {
			this.bills.add(b.get(i));
		}
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	//Metodo para visualizar los datos
	@Override
	public String toString() {
		return "Client= " + client.getName() + " " + client.getLastname() + " - Amount= " + amount;
	}
	
	
	
}
