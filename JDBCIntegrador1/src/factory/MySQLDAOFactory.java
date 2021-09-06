package factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.ClienteDAO;
import dao.FacturaDAO;
import dao.FacturaProductoDAO;
import dao.ProductoDAO;

public class MySQLDAOFactory extends DAOFactory {
	
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URI = "jdbc:mysql://localhost:3306/entregable1";
	private static Connection conn;
	
	public MySQLDAOFactory() {
		MySQLDAOFactory.registerDriver();
	}

	// method to create DB connection
	public static Connection createConnection() throws SQLException {
		conn = DriverManager.getConnection(URI, "root", "");
		conn.setAutoCommit(false);
		return conn;
	}

	private static void registerDriver() {

		try {
			Class.forName(DRIVER).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void closeConnection() throws SQLException {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}

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
