package idao;

import java.sql.SQLException;

import org.apache.commons.csv.CSVParser;

//interface de Factura que contiene los métodos minimos que debe tener
public interface IFactura {
	public void insertCSV(CSVParser parser) throws SQLException;
	public void createTable() throws SQLException;
}
