package main;

import java.sql.SQLException;

import dao.*;
import factory.*;

public class main {

	public static void main(String[] args) throws SQLException {
		DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);
		ClienteDAO clienteDAO = mysqlFactory.getClienteDAO();
	

	}

}
