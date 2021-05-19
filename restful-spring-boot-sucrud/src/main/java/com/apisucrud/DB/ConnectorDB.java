package com.apisucrud.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
			String direccion = "Blvd Gustavo Díaz Ordaz 333, Sin Nombre de Col 4, La Fama, 66210 San Pedro Garza García, N.L.";
			float latitud = (float) 25.6704;
			float longitud = (float) -100.4436;
			getLocations();
			//System.out.println(insertLocation(direccion, latitud, longitud));
		}
		
		
		public static ResultSet getLocations()
		{
			
			ResultSet result = null;
			
			try {

				Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				
				String sql = "SELECT * FROM Sucursales";
				Statement statement = connection.createStatement();
				result = statement.executeQuery(sql);
				
				while (result.next()) {
					String Sucursalid = result.getString("Sucursalid");
					String Direccion = result.getString("Direccion");
					String Longitud = result.getString("Latitud");
					String Latitud = result.getString("Longitud");
					
					System.out.println("Sucursal: "+Sucursalid+", Direccion: "+Direccion+", Longitud: "+Longitud+", Latitud: "+Latitud);
				}
				
				connection.close();
				return result;
										
			} catch(SQLException e) {
				System.out.println("Error on ConnectorDB.getLocations: "+e.getSQLState());
			}
			return result;
		}
		
		
		
		public static String insertLocation(String direccion, float latitud, float longitud)
		{			
			try {

				Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				
				String sql = "INSERT INTO Sucursales (Direccion, Latitud, Longitud) VALUES (?, ?, ? )";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, direccion);
				statement.setFloat(2, latitud);
				statement.setFloat(3, longitud);
				
				int rows = statement.executeUpdate();
				
				if(rows > 0) {
					return "The record has been successfully added";
				}
				
				
						
			} catch(SQLException e) {
				System.out.println("Error on ConnectorDB.insertLocation: "+e);
			}
			
			return "No row added";
		}
		
}