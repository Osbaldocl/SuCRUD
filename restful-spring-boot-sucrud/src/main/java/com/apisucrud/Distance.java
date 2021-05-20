package com.apisucrud;

public class Distance {

	public int id;
	public String direccion;
	public double latitud;
	public double longitud;
	
	public double distanceBetweenCurrentLocation;
	
	public void setData(double latitud, double longitud)
	{
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	public void setData(int id, String direccion, double latitud, double longitud)
	{
		this.id = id;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public double getLatitud() {
		return latitud;
	}
	
	public double getLongitud() {
		return longitud;
	}
	
	public void setDistanceBetweenCurrentLocation(double distanceBetweenCurrentLocation)
	{
		this.distanceBetweenCurrentLocation = distanceBetweenCurrentLocation;
	}
	
	public double getDistanceBetweenCurrentLocation()
	{
		return distanceBetweenCurrentLocation;
	}
	
}
