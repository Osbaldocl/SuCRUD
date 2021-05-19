package com.apisucrud;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.apisucrud.DB.ConnectorDB;

public class Locations {
	
	Distance currentLocation = new Distance();
	
	/* Constructor
	*
	*
	*
	*/
	public Locations(float latitud, float longitud)
	{
		currentLocation.setData(latitud, longitud);
	}
	
	public String distance() throws SQLException {
		
		// obtiene el resultSet de todas las locaciones
		ResultSet locations = ConnectorDB.getLocations();
		// Crea un Array de objetos tipo Distance donde se guardara el resultSet
		Distance distanceObj[] = new Distance[locations.getMetaData().getColumnCount()];
		
		int count = 0;
		while (locations.next())
		{
			distanceObj[count].setData(locations.getInt("Sucursalid"), locations.getString("Direccion"), 
					locations.getFloat("Latitud"), locations.getFloat("Longitud"));
			
			distanceObj[count].setDistanceBetweenCurrentLocation(calculateDistanceBetweenLocations(distanceObj[count], currentLocation));
			
			
			count++;
		}

		return "";
	}
	
	public float calculateDistanceBetweenLocations(Distance distanceObj, Distance currentLocation)
	{

		float currentLat = currentLocation.getLatitud();
		float currentLon = currentLocation.getLongitud();
		float objectiveLat = distanceObj.getLatitud();
		float objectiveLon = distanceObj.getLongitud();
		
		return 0;
	}
}
