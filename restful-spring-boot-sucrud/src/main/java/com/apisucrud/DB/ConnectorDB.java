package com.apisucrud.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.apisucrud.Distance;

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
		
		
		public static String getSpecLocation(int id)
		{
			Distance sucursal = new Distance();
			ResultSet result = null;
			try {

				Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				
				String sql = "SELECT * FROM Sucursales WHERE Sucursalid = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, id);
				
				result = statement.executeQuery();
				

				while (result.next()) {
				System.out.println(result.toString()+" "+result.getInt("Sucursalid"));
				sucursal.setId(result.getInt("Sucursalid"));
				sucursal.setDireccion(result.getString("Direccion"));
				sucursal.setData(result.getFloat("Latitud"), result.getFloat("Longitud"));
				}
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("Error on ConnectorDB.getSpecLocation");
			}
			
			return "Sucursal seleccionada: " + sucursal.getDireccion() + "\nLatitud: " +
					sucursal.getLatitud() + "\nLongitud: " + sucursal.getLongitud();
		}
		
		public static List<Distance> getLocations()
		{
			
			ResultSet result = null;
			List<Distance> distancias = new ArrayList<Distance>();
			
			try {

				Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				
				String sql = "SELECT * FROM Sucursales";
				Statement statement = connection.createStatement();
				result = statement.executeQuery(sql);
				
				while (result.next()) {
					Distance dist = new Distance();
					dist.setData(result.getInt("Sucursalid"), result.getString("Direccion"), result.getDouble("Latitud"), result.getDouble("Longitud"));
					System.out.println(dist.toString());
					distancias.add(dist);
				}		
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("Error on ConnectorDB.getLocations");
			}
			return distancias;
		}
		
		
		
		public static String insertLocation(String direccion, float latitud, float longitud)
		{			
			String resultado = "No row added";
			
			try {

				Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				
				String sql = "INSERT INTO Sucursales (Direccion, Latitud, Longitud) VALUES (?, ?, ? )";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, direccion);
				statement.setFloat(2, latitud);
				statement.setFloat(3, longitud);
				
				int rows = statement.executeUpdate();
				
				if(rows > 0) {
					
					int id = 0;
					
					String sql2 = "SELECT Sucursalid FROM Sucursales WHERE Sucursalid=(SELECT MAX(Sucursalid) FROM Sucursales);";
					Statement statement2 = connection.createStatement();
					ResultSet result = statement2.executeQuery(sql2);
					
					while (result.next()) {
						id = result.getInt("Sucursalid");
					}
					resultado = "Se añadió la sucursal con exito, con el ID: "+id;
				}
			} catch(SQLException e) {
				System.out.println("Error on ConnectorDB.insertLocation: "+e);
			}
			
			return resultado;
		}
		
}