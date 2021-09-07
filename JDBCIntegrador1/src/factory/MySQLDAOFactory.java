package factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.ClienteDAO;
import dao.FacturaDAO;
import dao.FacturaProductoDAO;
import dao.ProductoDAO;

/* Desaclopa el dise�o: se tiene una f�brica de DAOs por cada tipo de tecnolog�a (Base de Datos).
 * Contiene lo necesario para establecer la conexi�n a la Base de Datos espec�fica y
 * para dar y crear los DAOs concretos.
 * Permite de esta forma manipular los DAOs acorde a la tecnolog�a que se quiere manejar.*/

public class MySQLDAOFactory extends DAOFactory {
	
	//Atributos de clase
	//Datos para la conexi�n:
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	//Direcci�n donde se encuentra la Base de Datos.
	//Se compone como: "protocolo:tipo de conexi�n de BD://path:puerto/Nombre de la BD"*/
	public static final String URI = "jdbc:mysql://localhost:3306/entregable1";
	private static Connection conn;
	
	//Constructor
	//Registra el Driver a la hora de instanciarse.
	public MySQLDAOFactory() {
		MySQLDAOFactory.registerDriver();
	}

	//Crea la conexi�n a la Base de Datos MySQL.
	public static Connection createConnection() throws SQLException {
		conn = DriverManager.getConnection(URI, "root", "");
		//Se desactivan los autocomits.
		conn.setAutoCommit(false);
		return conn;
	}
	
	//Genera el Driver
	private static void registerDriver() {

		try {
			//Se crea una instancia del Driver utilizando mecanismo de reflexi�n
			Class.forName(DRIVER).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			//Captura fallos al agregar el Driver y sale del programa informando.
			e.printStackTrace();
			System.exit(1);
		}
	}

	//Cierra la conexi�n a la Base de Datos, previendo que exista y est� activa.
	public static void closeConnection() throws SQLException {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}

	//Factory Methods: Instancia aquellos productos que se generan por medio de la f�brica.
	//Crear� los DAOs correspondientes.
	@Override
	public ClienteDAO getClienteDAO() throws SQLException {
		return new ClienteDAO();
	}

	@Override
	public FacturaDAO getFacturaDAO() throws SQLException {
		return new FacturaDAO();
	}

	@Override
	public FacturaProductoDAO getFacturaProductoDAO() throws SQLException {
		return new FacturaProductoDAO();
	}

	@Override
	public ProductoDAO getProductoDAO() throws SQLException {
		return new ProductoDAO();
	}
}
