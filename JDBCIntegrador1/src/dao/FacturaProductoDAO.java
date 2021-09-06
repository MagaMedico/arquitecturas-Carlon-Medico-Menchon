package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import factory.*;
import idao.IFacturaProducto;

public class FacturaProductoDAO implements IFacturaProducto{

	private Connection conn;

	public FacturaProductoDAO() throws SQLException {
		this.createTable();
	}
	
	@Override
	public void insertCSV(CSVParser parser) throws SQLException {
		this.conn = MySQLDAOFactory.createConnection();
		for(CSVRecord row: parser) { 
			int id_factura = Integer.parseInt(row.get("idFactura"));
			int id_producto = Integer.parseInt(row.get("idProducto"));
			int cantidad = Integer.parseInt(row.get("cantidad"));
			
			String insert = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
			PreparedStatement ps = this.conn.prepareStatement(insert);
			ps.setInt(1, id_factura);
			ps.setInt(2, id_producto);
			ps.setInt(3, cantidad);
			ps.executeUpdate();
			this.conn.commit();
			ps.close();
		}
		this.conn.close();
	}
	
	@Override
	public void createTable() throws SQLException {
		this.conn = MySQLDAOFactory.createConnection();
		
		String tablaFactura_Producto = "CREATE TABLE IF NOT EXISTS Factura_Producto("
				+ "idFactura INT,"
				+ "idProducto INT,"
				+ "cantidad INT,"
				+ "PRIMARY KEY(idFactura, idProducto),"
				+ "FOREIGN KEY(idFactura) references Factura(idFactura),"
				+ "FOREIGN KEY(idProducto) references Producto(idProducto))";

		this.conn.prepareStatement(tablaFactura_Producto).execute();
		this.conn.commit();
		this.conn.close();
	}

}
