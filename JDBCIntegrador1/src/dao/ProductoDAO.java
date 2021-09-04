package dao;

import java.sql.Connection;
import java.sql.SQLException;

import factory.MySQLDAOFactory;
import idao.IProducto;
import model.Producto;

public class ProductoDAO implements IProducto{
	
	private Connection conn;
	
	@Override
	public void insert(Producto producto) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createTable() throws SQLException {
		
		this.conn = MySQLDAOFactory.createConnection();
		String tablaProducto = "CREATE TABLE Producto("
				+ "idProducto INT,"
				+ "nombre VARCHAR(45),"
				+ "valor FLOAT,"
				+ "PRIMARY KEY(idProducto))";

		this.conn.prepareStatement(tablaProducto).execute();
		this.conn.commit();
		this.conn.close();

		
	}

}
