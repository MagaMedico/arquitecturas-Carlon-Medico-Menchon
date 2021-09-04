package idao;

import java.sql.SQLException;
import model.Cliente;

public interface ICliente {
	public void insert(Cliente cliente) throws SQLException;
	//public void update(T generic) throws SQLException;
	//public void delete(T generic) throws SQLException;
	//public ArrayList<T> read() throws SQLException;
	//private void createTable() throws SQLException;
}
