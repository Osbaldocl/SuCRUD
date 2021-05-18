package com.apisucrud.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * ConnectorDB
 */

public class ConnectorDB {
	
		final static String URL = "jdbc:mysql://localhost:3306/mysql";
		final static String USERNAME = "root";
		final static String PASSWORD = "sucrud2021";
		
		public void testConnDB() {
			try {
				Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				System.out.println("connected to the database!: " + connection.toString());
				connection.close();
			}
			catch(SQLException e) {
				System.out.println("Error en la conexion");
				e.printStackTrace();
			}
		}
		
		//TODO Remove main method
		public static void main(String[] args) {
			try {

				Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				
				String sql = ("SELECT * FROM Sucursales");
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				
				while (result.next()) {
					String Sucursalid = result.getString("Sucursalid");
					String Direccion = result.getString("Direccion");
					String Longitud = result.getString("Longitud");
					String Latitud = result.getString("Latitud");
					
					System.out.println("Sucursal: "+Sucursalid+", Direccion: "+Direccion+", Longitud: "+Longitud+", Latitud: "+Latitud);
				}
				
				
				connection.close();
			} catch(SQLException e) {
				System.out.println("Error on ConnectorDB.getLocations: "+e.getSQLState());
			}
		}
		
		
		public void getLocations()
		{
			try {

				Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				
				String sql = "SELECT * FROM Sucursales";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				
				while (result.next()) {
					String Sucursalid = result.getString("Sucursalid");
					String Direccion = result.getString("Direccion");
					String Longitud = result.getString("Longitud");
					String Latitud = result.getString("Latitud");
					
					System.out.println("Sucursal: "+Sucursalid+", Direccion: "+Direccion+", Longitud: "+Longitud+", Latitud: "+Latitud);
				}
				
				
				connection.close();
			} catch(SQLException e) {
				System.out.println("Error on ConnectorDB.getLocations: "+e.getSQLState());
			}
		}
		
}