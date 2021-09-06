package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import factory.*;
import idao.ICliente;
import model.Cliente;

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
	
	@Override
	public void createTable() throws SQLException {
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

	@Override
	public ArrayList<Cliente> masFacturados() throws SQLException {
		ArrayList<Cliente> clientes = new ArrayList<>();
		this.conn = MySQLDAOFactory.createConnection();
		
		String select = "SELECT c.*, SUM(p.valor * fp.cantidad) as total "
				+ "FROM cliente c JOIN factura f ON (c.idCliente = f.idCliente_FK) JOIN factura_producto fp ON(f.idFactura = fp.idFactura) JOIN producto p ON (p.idProducto = fp.idProducto) "
				+ "WHERE c.idCliente = f.idCliente_FK "
				+ "GROUP BY c.idCliente "
				+ "ORDER BY `total` DESC";
		PreparedStatement ps = this.conn.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Cliente c = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
			clientes.add(c);
		}
		this.conn.commit();
		ps.close();
		this.conn.close();
		
		return clientes;
	}	

}
