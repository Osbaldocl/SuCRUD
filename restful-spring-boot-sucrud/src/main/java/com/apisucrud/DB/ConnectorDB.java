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
 * ConnectorDB - Clase que utiliza para las conexiones a la base de datos.
 */

public class ConnectorDB {
	
		/*
		 * Datos utilizados para conectarse a la BD
		 */
		final static String URL = "jdbc:mysql://localhost:3306/mysql";
		final static String USERNAME = "root";
		final static String PASSWORD = "sucrud2021";
		
		/*
		 * getSpecLocation - Se utiliza para obtener la Sucursal especifica por ID
		 * 
		 * @params
		 * int id - Con esto se busca la Sucursal por ID en la BD
		 * 
		 * @returns
		 * String - con los datos de la sucursal.
		 */
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
		
		/*
		 * getLocations - Se utiliza para obtener todas las Sucursales de la BD
		 * 
		 * @returns 
		 * List<Distance> - Una lista de objetos con todas las Sucursales en la BD 
		 */
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
		
		
		/*
		 * insertLocation - Se utiliza para insertar un nuevo registro de sucursal en la BD
		 * 
		 * @params String direccion, float latitud, float longitud - direccion de la sucursal, latitud de la sucursal, longitud de la sucursal.
		 * 
		 * @returns String - indicando si se añadió el registro con exito y el ID del registro, si no, un mensaje de error.
		 */
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