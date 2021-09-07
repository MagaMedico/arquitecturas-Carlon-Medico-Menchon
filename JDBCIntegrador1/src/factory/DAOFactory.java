package factory;

import java.sql.SQLException;

import dao.*;

/*Abstract Factory: Manejador de las f�bricas de DAOs.
 * Crea una f�brica de DAOs por cada tipo de tecnolog�a*/
public abstract class DAOFactory {
	
	//Atributo de la clase
	public static final int MYSQL_JDBC = 1;

	//M�todos a implementar en cada clase para la generaci�n de productos seg�n la Base de Datos.
	public abstract ClienteDAO getClienteDAO() throws SQLException;
	public abstract FacturaDAO getFacturaDAO() throws SQLException;
	public abstract FacturaProductoDAO getFacturaProductoDAO() throws SQLException;
	public abstract ProductoDAO getProductoDAO() throws SQLException;

	//Crea un factory espec�fico seg�n la tecnolog�a que se elija.
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
			case MYSQL_JDBC : return new MySQLDAOFactory();
			default: return null;
		}
	}
}
