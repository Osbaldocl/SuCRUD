package com.apisucrud;

public class Distance {

	public int id;
	public String direccion;
	public float latitud;
	public float longitud;
	
	public float distanceBetweenCurrentLocation;
	
	public void setData(float latitud, float longitud)
	{
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	public void setData(int id, String direccion, float latitud, float longitud)
	{
		this.id = id;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public float getLatitud() {
		return latitud;
	}
	
	public float getLongitud() {
		return longitud;
	}
	
	public void setDistanceBetweenCurrentLocation(float distanceBetweenCurrentLocation)
	{
		this.distanceBetweenCurrentLocation = distanceBetweenCurrentLocation;
	}
	
	public float getDistanceBetweenCurrentLocation()
	{
		return distanceBetweenCurrentLocation;
	}
	
}
