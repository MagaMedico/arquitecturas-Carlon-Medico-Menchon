package imodel;

import java.sql.SQLException;

import org.apache.commons.csv.CSVParser;

import model.Producto;

//interface de Producto que contiene los métodos minimos que debe tener
public interface IProducto {
	public void insertCSV(CSVParser parser) throws SQLException;
	public Producto productoMasRecaudado() throws SQLException;
	public void createTable() throws SQLException;
}
