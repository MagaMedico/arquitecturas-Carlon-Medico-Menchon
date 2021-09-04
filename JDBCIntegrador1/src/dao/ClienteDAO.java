package dao;

import java.sql.Connection;
import java.sql.SQLException;

import factory.MySQLDAOFactory;
import idao.ICliente;
import model.Cliente;

public class ClienteDAO implements ICliente{
	
	private Connection conn;
	
	public ClienteDAO() throws SQLException {
		this.createTable();
	}

	@Override
	public void insert(Cliente cliente) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	private void createTable() throws SQLException {
		this.conn = MySQLDAOFactory.createConnection();
		String tablaCliente = "CREATE TABLE Cliente("
							+ "idCliente INT,"
							+ "nombre VARCHAR(500),"
							+ "email VARCHAR(150),"
							+ "PRIMARY KEY(idCliente))";
		
		this.conn.prepareStatement(tablaCliente).execute();
		this.conn.commit();
		this.conn.close();
		
	}

}
