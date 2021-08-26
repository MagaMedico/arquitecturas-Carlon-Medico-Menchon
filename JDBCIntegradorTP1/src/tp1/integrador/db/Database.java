package tp1.integrador.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

	public static void main(String[] args) {
		
		//--MySQL
		String driver = "com.mysql.cj.jdbc.Driver";
		//--Derby
		//String driver = "org.apache.derby.jdbc.EmbeddedDriver";

		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		//--Derby
		//String uri = "jdbc:derby:Integrador_1_db;create=true";
		//--MySQL
		String uri = "jdbc:mysql://localhost:3306/Integrador_1_db";
		
		try {
			//--Derby
			//Connection conn = DriverManager.getConnection(uri);
			//--MySQL
			Connection conn = DriverManager.getConnection(uri, "mag","123");
			
			//--MySQL
			conn.setAutoCommit(false);
			createTables(conn);
			//addPerson(conn, 1, "Juan", 20);
			//addPerson(conn, 2, "Paula", 30);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*private static void addPerson(Connection conn, int id, String name, int years) throws SQLException {
		String insert = "INSERT INTO persona (id, nombre, edad) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setInt(3, years);
		ps.executeUpdate();
		ps.close();
		conn.commit();
	}*/

	private static void createTables(Connection conn) throws SQLException {
		
		String tablaCliente = "CREATE TABLE Cliente("
												+ "idCliente INT,"
												+ "nombre VARCHAR(500),"
												+ "email VARCHAR(150),"
												+ "PRIMARY KEY(idCliente))";
		
		conn.prepareStatement(tablaCliente).execute();
		//conn.commit();
		

		String tablaFactura = "CREATE TABLE Factura("
				+ "idFactura INT,"
				+ "idCliente_FK INT,"
				+ "PRIMARY KEY(idFactura),"
				+ "FOREIGN KEY(idCliente_FK) references Cliente(idCliente))";

		conn.prepareStatement(tablaFactura).execute();
		//conn.commit();
		
		
		String tablaProducto = "CREATE TABLE Producto("
				+ "idProducto INT,"
				+ "nombre VARCHAR(45),"
				+ "valor FLOAT,"
				+ "PRIMARY KEY(idProducto))";

		conn.prepareStatement(tablaProducto).execute();
		//conn.commit();
		
		String tablaFactura_Producto = "CREATE TABLE Factura_Producto("
				+ "idFactura INT,"
				+ "idProducto INT,"
				+ "cantidad INT,"
				+ "PRIMARY KEY(idFactura, idProducto),"
				+ "FOREIGN KEY(idFactura) references Factura(idFactura),"
				+ "FOREIGN KEY(idProducto) references Producto(idProducto))";

		conn.prepareStatement(tablaFactura_Producto).execute();
		conn.commit();
		
		
	}
}
