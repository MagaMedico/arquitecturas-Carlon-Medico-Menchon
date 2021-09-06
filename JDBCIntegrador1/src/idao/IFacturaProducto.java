package idao;

import java.sql.SQLException;


import org.apache.commons.csv.CSVParser;
//interface de FacturaProducto que contiene los métodos minimos que debe tener
public interface IFacturaProducto {
	public void insertCSV(CSVParser parser) throws SQLException;
	public void createTable() throws SQLException;
}
