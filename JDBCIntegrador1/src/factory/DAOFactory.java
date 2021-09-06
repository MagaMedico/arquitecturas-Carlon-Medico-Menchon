package factory;

import java.sql.SQLException;

import dao.*;

public abstract class DAOFactory {
	public static final int MYSQL_JDBC = 1;

	public abstract ClienteDAO getClienteDAO() throws SQLException;
	public abstract FacturaDAO getFacturaDAO() throws SQLException;
	public abstract FacturaProductoDAO getFacturaProductoDAO() throws SQLException;
	public abstract ProductoDAO getProductoDAO() throws SQLException;

	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
			case MYSQL_JDBC : return new MySQLDAOFactory();
			default: return null;
		}
	}
}
