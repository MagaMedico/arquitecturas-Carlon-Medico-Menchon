package dao;

import java.sql.Connection;
import java.sql.SQLException;

import factory.MySQLDAOFactory;
import idao.IFactura;
import model.Factura;

public class FacturaDAO implements IFactura{

	private Connection conn;

	@Override
	public void insert(Factura factura) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createTable() throws SQLException {
		this.conn = MySQLDAOFactory.createConnection();
		String tablaFactura = "CREATE TABLE Factura("
				+ "idFactura INT,"
				+ "idCliente_FK INT,"
				+ "PRIMARY KEY(idFactura),"
				+ "FOREIGN KEY(idCliente_FK) references Cliente(idCliente))";

		this.conn.prepareStatement(tablaFactura).execute();
		this.conn.commit();
		this.conn.close();
	}

}
