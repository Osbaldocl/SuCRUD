package com.apisucrud.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/location")
public class LocationController {
	
	/*
	 * @GetMapping: This indicates the system to use this method when it gets a GET request
	 * getLocation(): with this method returns the closest location of the store
	 */
	@GetMapping
	public String getLocation() {
		return "Locación obtenida";
	}

	/*
	 * @GetMapping: This indicates the system to use this method when it gets a POST request
	 * createLocation(): This method creates a new instance of a store in the DB, returns successful
	 * 	creation or error message.
	 */
	@PostMapping
	public String createLocation() {
		return "Locación creada";
	}
}
