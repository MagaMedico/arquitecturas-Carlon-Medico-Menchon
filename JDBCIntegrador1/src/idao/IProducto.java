package idao;

import java.sql.SQLException;
import model.Producto;

public interface IProducto {
	public void insert(Producto producto) throws SQLException;
	//public void update(T generic) throws SQLException;
	//public void delete(T generic) throws SQLException;
	//public ArrayList<T> read() throws SQLException;
	public void createTable() throws SQLException;
}
