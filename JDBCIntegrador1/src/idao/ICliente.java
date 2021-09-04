package idao;

import java.sql.SQLException;

import org.apache.commons.csv.CSVParser;

public interface ICliente {
	
	public void insertCSV(CSVParser parser) throws SQLException;
	//public void insert(Cliente cliente) throws SQLException;
	//public void update(T generic) throws SQLException;
	//public void delete(T generic) throws SQLException;
	//public ArrayList<T> read() throws SQLException;
	//private void createTable() throws SQLException;
}
