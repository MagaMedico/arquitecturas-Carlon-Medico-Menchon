package tp1.integrador.db;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Database {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
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
		String uri = "jdbc:mysql://localhost:3306/entregable1";
		
		try {
			//--Derby
			//Connection conn = DriverManager.getConnection(uri);
			//--MySQL
			//Connection conn = DriverManager.getConnection(uri, "root","");
			//--MySQL
			//conn.setAutoCommit(false);
			//createTables(conn);
			//addPerson(conn, 1, "Juan", 20);
			//addPerson(conn, 2, "Paula", 30);
			
			//Busca el archivo
			CSVParser parserProducto = CSVFormat.DEFAULT.withHeader().parse(new FileReader("productos.csv"));
			CSVParser parserCliente = CSVFormat.DEFAULT.withHeader().parse(new FileReader("clientes.csv"));
			CSVParser parserFactura = CSVFormat.DEFAULT.withHeader().parse(new FileReader("facturas.csv"));
			CSVParser parserFacturaProducto = CSVFormat.DEFAULT.withHeader().parse(new FileReader("facturas-productos.csv"));
			
			insertCliente(uri, parserCliente);
			insertProducto(uri, parserProducto);
			insertFactura(uri, parserFactura);
			insertFacturaProducto(uri, parserFacturaProducto);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void insertCliente(String uri, CSVParser parser) throws SQLException {
		Connection conn = DriverManager.getConnection(uri, "root","");
		conn.setAutoCommit(false);
		for(CSVRecord row: parser) { 
			int id_cliente = Integer.parseInt(row.get("idCliente"));
			String nombre = row.get("nombre");
			String email = row.get("email");
			
			String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, id_cliente);
			ps.setString(2, nombre);
			ps.setString(3, email);
			ps.executeUpdate();
			conn.commit();
			ps.close();
		}
		conn.close();
	}

	private static void insertProducto(String uri, CSVParser parser) throws SQLException {
		Connection conn = DriverManager.getConnection(uri, "root","");
		conn.setAutoCommit(false);
		for(CSVRecord row: parser) { 
			int id_producto = Integer.parseInt(row.get("idProducto"));
			String nombre = row.get("nombre");
			Float valor = Float.parseFloat(row.get("valor"));
			
			String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, id_producto);
			ps.setString(2, nombre);
			ps.setFloat(3, valor);
			ps.executeUpdate();
			conn.commit();
			ps.close();
		}
		conn.close();
	}
	
	private static void insertFactura(String uri, CSVParser parser) throws SQLException {
		Connection conn = DriverManager.getConnection(uri, "root","");
		conn.setAutoCommit(false);
		for(CSVRecord row: parser) { 
			int id_factura = Integer.parseInt(row.get("idFactura"));
			int id_cliente = Integer.parseInt(row.get("idCliente"));
			
			String insert = "INSERT INTO Factura (idFactura, idCliente_FK) VALUES (?, ?)";
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, id_factura);
			ps.setInt(2, id_cliente);
			ps.executeUpdate();
			conn.commit();
			ps.close();
		}
		conn.close();
	}
	
	private static void insertFacturaProducto(String uri, CSVParser parser) throws SQLException {
		Connection conn = DriverManager.getConnection(uri, "root","");
		conn.setAutoCommit(false);
		for(CSVRecord row: parser) { 
			int id_factura = Integer.parseInt(row.get("idFactura"));
			int id_producto = Integer.parseInt(row.get("idProducto"));
			int cantidad = Integer.parseInt(row.get("cantidad"));
			
			String insert = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, id_factura);
			ps.setInt(2, id_producto);
			ps.setInt(3, cantidad);
			ps.executeUpdate();
			conn.commit();
			ps.close();
		}
		conn.close();
	}

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
