package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import factory.MySQLDAOFactory;
import idao.ICliente;

public class ClienteDAO implements ICliente{
	
	private Connection conn;
	
	public ClienteDAO() throws SQLException {
		this.createTable();
	}

	@Override
	public void insertCSV(CSVParser parser) throws SQLException {
		this.conn = MySQLDAOFactory.createConnection();
		for(CSVRecord row: parser) { 
			int id_cliente = Integer.parseInt(row.get("idCliente"));
			String nombre = row.get("nombre");
			String email = row.get("email");
			
			String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
			PreparedStatement ps = this.conn.prepareStatement(insert);
			ps.setInt(1, id_cliente);
			ps.setString(2, nombre);
			ps.setString(3, email);
			ps.executeUpdate();
			this.conn.commit();
			ps.close();
		}
		this.conn.close();
	}

	private void createTable() throws SQLException {
		this.conn = MySQLDAOFactory.createConnection();
		String tablaCliente = "CREATE TABLE IF NOT EXISTS Cliente("
							+ "idCliente INT,"
							+ "nombre VARCHAR(500),"
							+ "email VARCHAR(150),"
							+ "PRIMARY KEY(idCliente))";
		
		this.conn.prepareStatement(tablaCliente).execute();
		this.conn.commit();
		this.conn.close();
		
	}

}
