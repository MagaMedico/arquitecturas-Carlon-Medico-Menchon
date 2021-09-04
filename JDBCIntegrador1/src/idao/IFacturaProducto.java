package idao;

import java.sql.SQLException;

import model.FacturaProducto;

public interface IFacturaProducto {
	public void insert(FacturaProducto facturaProducto) throws SQLException;
	//public void update(T generic) throws SQLException;
	//public void delete(T generic) throws SQLException;
	//public ArrayList<T> read() throws SQLException;
	public void createTable() throws SQLException;
}
