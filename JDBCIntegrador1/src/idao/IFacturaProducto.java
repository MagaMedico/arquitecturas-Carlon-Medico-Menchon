package idao;

import java.sql.SQLException;

import org.apache.commons.csv.CSVParser;

public interface IFacturaProducto {
	
	public void insertCSV(CSVParser parser) throws SQLException;
	//public void insert(FacturaProducto facturaProducto) throws SQLException;
	//public void update(T generic) throws SQLException;
	//public void delete(T generic) throws SQLException;
	//public ArrayList<T> read() throws SQLException;
	//public void createTable() throws SQLException;
}
