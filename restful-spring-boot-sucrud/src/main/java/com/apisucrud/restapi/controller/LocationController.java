package com.apisucrud.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController {
	
	@GetMapping
	public String getLocation() {
		return "Locación obtenida";
	}
	
	@PostMapping
	public String createLocation() {
		
		return "Locación creada";
	}
}
