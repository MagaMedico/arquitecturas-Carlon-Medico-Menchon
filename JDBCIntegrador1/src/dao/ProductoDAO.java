package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import factory.*;
import idao.IProducto;
import model.Producto;

public class ProductoDAO implements IProducto{
	
	private Connection conn;
	
	public ProductoDAO() throws SQLException {
		this.createTable();
	}
	
	@Override
	public void insertCSV(CSVParser parser) throws SQLException {
		this.conn = MySQLDAOFactory.createConnection();
		for(CSVRecord row: parser) { 
			int id_producto = Integer.parseInt(row.get("idProducto"));
			String nombre = row.get("nombre");
			Float valor = Float.parseFloat(row.get("valor"));
			
			String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
			PreparedStatement ps = this.conn.prepareStatement(insert);
			ps.setInt(1, id_producto);
			ps.setString(2, nombre);
			ps.setFloat(3, valor);
			ps.executeUpdate();
			this.conn.commit();
			ps.close();
		}
		this.conn.close();
	}
	
	@Override
	public Producto productoMasRecaudado() throws SQLException {//devolverķa un producto
		Producto producto = null;
		this.conn = MySQLDAOFactory.createConnection();
		
		String select = "SELECT p.*, SUM(p.valor * fp.cantidad) as total "
				+ "FROM producto p JOIN factura_producto fp ON (p.idProducto = fp.idProducto) "
				+ "WHERE p.idProducto = fp.idProducto "
				+ "GROUP BY idProducto "
				+ "ORDER BY `total` DESC "
				+ "LIMIT 1";
		PreparedStatement ps = this.conn.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			producto = new Producto(rs.getInt(1), rs.getString(2), rs.getFloat(3));
		}
		this.conn.commit();
		ps.close();
		this.conn.close();
		
		return producto;
	}

	@Override
	public void createTable() throws SQLException {
		this.conn = MySQLDAOFactory.createConnection();
		String tablaProducto = "CREATE TABLE IF NOT EXISTS Producto("
				+ "idProducto INT,"
				+ "nombre VARCHAR(45),"
				+ "valor FLOAT,"
				+ "PRIMARY KEY(idProducto))";

		this.conn.prepareStatement(tablaProducto).execute();
		this.conn.commit();
		this.conn.close();

		
	}

}
