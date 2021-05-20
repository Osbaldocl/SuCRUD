package com.apisucrud;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.apisucrud.DB.ConnectorDB;

public class Locations {
	
	Distance currentLocation = new Distance();
	
	/* Constructor
	*
	*
	*
	*/
	public Locations(double latitud, double longitud)
	{
		currentLocation.setData(latitud, longitud);
	}
	
	public String distance() throws SQLException {

		// Crea un Array de objetos tipo Distance donde se guardara el resultSet
		List<Distance> distancias = new ArrayList<Distance>();
		// obtiene el resultSet de todas las locaciones
		distancias = ConnectorDB.getLocations();

		for(int i = 0; i < distancias.size(); i++)
		{
			double calculated = calculateDistanceBetweenLocations(distancias.get(i), currentLocation);
			distancias.get(i).setDistanceBetweenCurrentLocation(calculated);
		}
		
		int minIndex = getLowestDistance(distancias);
		
		return "La sucursal mas cerncana se encuentra a " + distancias.get(minIndex).getDistanceBetweenCurrentLocation()
				+" KMs, en la Direccion: " + distancias.get(minIndex).getDireccion();
		
	}
	
	private int getLowestDistance(List<Distance> distancias) {

        //declare min value as the first element of the list
		double min = distancias.get(0).getDistanceBetweenCurrentLocation();
        //declare min elements index as 0 (i.e. first element)
		int minIndex = 0;
        //Iterate through ArrayList
		for(int i = 1; i < distancias.size(); i++)
		{
            /*
             * If current value is less than min value, it 
             * is new minimum value
             */
			if ( distancias.get(i).getDistanceBetweenCurrentLocation() < min ) 
			{
				min = distancias.get(i).getDistanceBetweenCurrentLocation();
				minIndex = i;
			}
		}
		return minIndex;
	}

	public static double calculateDistanceBetweenLocations(Distance distanceObj, Distance currentLocation)
	{
		double earthRadius = 3958.75; // in miles, change to 6371 for kilometer output
		
		double currentLat = currentLocation.getLatitud();
		double currentLon = currentLocation.getLongitud();
		double objectiveLat = distanceObj.getLatitud();
		double objectiveLon = distanceObj.getLongitud();
		
		double dLat = Math.toRadians(objectiveLat-currentLat);
	    double dLng = Math.toRadians(objectiveLon-currentLon);
	    
	    double sindLat = Math.sin(dLat / 2);
	    double sindLng = Math.sin(dLng / 2);
		
	    double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
        * Math.cos(Math.toRadians(currentLat)) * Math.cos(Math.toRadians(objectiveLat));

	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

	    double dist = earthRadius * c;
	    
	    return dist * 1.60934; // output distance, in MILES so we multiply by 1.60934 to make it KM
	}
	
}
