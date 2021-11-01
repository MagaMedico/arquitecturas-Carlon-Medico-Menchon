package edu.ipojo;

import java.util.List;

import edu.pojo.Client;
import edu.pojo.Grocery;
import edu.pojo.Product;

public interface IGrocery {
	
	public void insertGrocery(String name, List<Client> clients, List<Product> products);
	public void deleteGrocery(Grocery g);
	public void updateGrocery(Grocery g);

}
