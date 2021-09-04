package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import dao.*;
import factory.*;

public class main {

	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
		DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);
		
		//Crear las tablas
		/*
		ClienteDAO clienteDAO = mysqlFactory.getClienteDAO();
		FacturaDAO facturaDAO = mysqlFactory.getFacturaDAO();
		ProductoDAO productoDAO = mysqlFactory.getProductoDAO();
		FacturaProductoDAO facturaProductoDAO = mysqlFactory.getFacturaProductoDAO();
		*/
		//Insertar los datos
		CSVParser parserProducto = CSVFormat.DEFAULT.withHeader().parse(new FileReader("productos.csv"));
		CSVParser parserCliente = CSVFormat.DEFAULT.withHeader().parse(new FileReader("clientes.csv"));
		CSVParser parserFactura = CSVFormat.DEFAULT.withHeader().parse(new FileReader("facturas.csv"));
		CSVParser parserFacturaProducto = CSVFormat.DEFAULT.withHeader().parse(new FileReader("facturas-productos.csv"));
		
		//mysqlFactory.getClienteDAO().insertCSV(parserCliente);
		mysqlFactory.getFacturaDAO().insertCSV(parserFactura);
		mysqlFactory.getProductoDAO().insertCSV(parserProducto);
		mysqlFactory.getFacturaProductoDAO().insertCSV(parserFacturaProducto);
	}

}
