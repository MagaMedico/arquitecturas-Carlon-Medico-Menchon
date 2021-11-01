package edu.ipojo;

import edu.pojo.Product;

public interface IProduct {

	public void insertProduct(String name, int unitPrice);
	public void deleteProduct(Product p);
	public void updateProduct(Product p);
}
