package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import dao.*;
import factory.*;
import model.Cliente;
/*
 * Este proyecto JAVA está configurado en un entorno de ejecución JavaSE-11.
 * El tipo de tecnología de Base de Datos es MySQL.
 * El manejador de dependencias es Maven: contiene dependencia de mysql-connector-java.
 * 
 * Requisitos:
 * - Instalación de XAMPP.
 * - Disponer del usuario root sin contraseña.
 * 
 * Acceso y utilización:
 * - Habilitar los módulos de Apache y MySQL de XAMPP.
 * - Con los modulos activos, se debe crear una Base de Datos vacía
 * 	 dentro de http://localhost/phpmyadmin/ con el nombre de "entregable1".
 */
public class Main {

	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
		DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);
		
		//Crear las tablas
		ClienteDAO clienteDAO = mysqlFactory.getClienteDAO();
		FacturaDAO facturaDAO = mysqlFactory.getFacturaDAO();
		ProductoDAO productoDAO = mysqlFactory.getProductoDAO();
		FacturaProductoDAO facturaProductoDAO = mysqlFactory.getFacturaProductoDAO();
		
		//Insertar los datos
		CSVParser parserProducto = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/csv/productos.csv"));
		CSVParser parserCliente = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/csv/clientes.csv"));
		CSVParser parserFactura = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/csv/facturas.csv"));
		CSVParser parserFacturaProducto = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/csv/facturas-productos.csv"));
		
		clienteDAO.insertCSV(parserCliente);
		facturaDAO.insertCSV(parserFactura);
		productoDAO.insertCSV(parserProducto);
		facturaProductoDAO.insertCSV(parserFacturaProducto);
		
		//Imprimir el producto que más recaudo
		System.out.println("Producto más recaudado: ");
		System.out.println(productoDAO.productoMasRecaudado() + System.lineSeparator());
		
		//Imprimir el listado de clientes ordenado por facturación
		System.out.println("Listado de clientes ordenado por facturación: ");
		ArrayList<Cliente> clientes = clienteDAO.masFacturados();
		for(Cliente c: clientes) {
			System.out.println(c);
		}
	}

}
