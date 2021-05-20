package com.apisucrud.restapi.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apisucrud.Locations;
import com.apisucrud.DB.ConnectorDB;

/*
 * @RestController
 * Tells the system that this is a Spring Rest controller
 * 
 * @RequestMapping("/location")
 * Every time the mapping has this ending, it will call this class
 * 
 * LocationController
 * This class is where API is controlled
 */
@RestController
@RequestMapping("/Sucursal")
public class LocationController {
  
	/*
	 * @GetMapping :This indicates the system to use this method when it gets a GET request
	 * getLocation() :with this method returns the closest location of the store
	 */
	@GetMapping
	public String getClosestLocation(float latitud, float longitud) {
		Locations sucursalCercana = new Locations(latitud, longitud);
		try {
			return sucursalCercana.distance();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en LocationController.GetClosesLocation");
		}
		return "Error";
	}
  
	 /*
	  * @GetMapping :This indicates the system to use this method when it gets a GET request
	  * getLocation() :with this method returns the specified location by ID
	  */
	 @GetMapping("/id")
	 public String getSucursalID(int id) {
	   return ConnectorDB.getSpecLocation(id);
	 }
	
	 /*
	  * @PostMapping :This indicates the system to use this method when it gets a POST request
	  * createLocation() :This method creates a new instance of a store in the DB, returns successful
	  *   creation or error message.
	  */
	 @PostMapping
	 public String createLocation(String direccion, float latitud, float longitud) {
	   return ConnectorDB.insertLocation(direccion, latitud, longitud);
	 }
}