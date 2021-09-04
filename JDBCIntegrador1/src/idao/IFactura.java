package idao;

import java.sql.SQLException;
import model.Factura;

public interface IFactura {
	public void insert(Factura factura) throws SQLException;
	//public void update(T generic) throws SQLException;
	//public void delete(T generic) throws SQLException;
	//public ArrayList<T> read() throws SQLException;
	public void createTable() throws SQLException;
}
