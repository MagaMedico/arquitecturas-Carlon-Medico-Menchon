package dao;

import java.sql.Connection;
import java.sql.SQLException;

import factory.MySQLDAOFactory;
import idao.IFacturaProducto;
import model.FacturaProducto;

public class FacturaProductoDAO implements IFacturaProducto{

	private Connection conn;

	@Override
	public void insert(FacturaProducto facturaProducto) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createTable() throws SQLException {
		this.conn = MySQLDAOFactory.createConnection();
		
		String tablaFactura_Producto = "CREATE TABLE Factura_Producto("
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
