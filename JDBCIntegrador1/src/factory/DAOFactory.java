package factory;

import java.sql.SQLException;

import dao.*;

/*Abstract Factory: Manejador de las fábricas de DAOs.
 * Crea una fábrica de DAOs por cada tipo de tecnología*/
public abstract class DAOFactory {
	
	//Atributo de la clase
	public static final int MYSQL_JDBC = 1;

	//Métodos a implementar en cada clase para la generación de productos según la Base de Datos.
	public abstract ClienteDAO getClienteDAO() throws SQLException;
	public abstract FacturaDAO getFacturaDAO() throws SQLException;
	public abstract FacturaProductoDAO getFacturaProductoDAO() throws SQLException;
	public abstract ProductoDAO getProductoDAO() throws SQLException;

	//Crea un factory específico según la tecnología que se elija.
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
			case MYSQL_JDBC : return new MySQLDAOFactory();
			default: return null;
		}
	}
}
