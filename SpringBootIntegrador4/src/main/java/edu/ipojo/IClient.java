package edu.ipojo;

import java.util.List;

import edu.pojo.Client;
import edu.pojo.Product;

public interface IClient {

	public void insertClient(long dNI, String name, String lastname, List<Product> groceries);
	public void deleteClient(Client c);
	public void updateClient(Client c);
}
