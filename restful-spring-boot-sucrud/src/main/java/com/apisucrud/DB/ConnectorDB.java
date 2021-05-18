package com.apisucrud.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * ConnectorDB
 */

public class ConnectorDB {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mysql";
		String username = "root";
		String password = "sucrud2021";
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("connected to the database!: " + connection.toString());
		} catch(SQLException e) {
			System.out.println("Error en la conexion");
			e.printStackTrace();
		}
		
	}
}
