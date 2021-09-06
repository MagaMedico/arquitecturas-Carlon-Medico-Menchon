package imodel;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVParser;

import model.Cliente;

//interface de Cliente que contiene los métodos minimos que debe tener
public interface ICliente {
	public void insertCSV(CSVParser parser) throws SQLException;
	public void createTable() throws SQLException;
	public ArrayList<Cliente> masFacturados() throws SQLException;
}
