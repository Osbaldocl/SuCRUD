package com.apisucrud.restfulspringbootsucrud;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

import com.apisucrud.*;



class RestfulSpringBootSucrudApplicationTests {

	/*
	 * @Test indica que este es metodo de prueba
	 * pruebaDeCalculo() :Asegura que hace bien el calculo entre las distancias y que no es Null
	 */
	@Test
	void pruebaDeCalculo() {
		Distance distancia1 = new Distance();
		Distance distancia2 = new Distance();
		
		distancia1.setData(25.6647, -100.2915);
		distancia2.setData(25.6567, -100.3160);
		
		assertEquals(2.611741540378483,Locations.calculateDistanceBetweenLocations(distancia1, distancia2));
		assertNotNull(Locations.calculateDistanceBetweenLocations(distancia1, distancia2));
	}
	
	/*
	 * @Test indica que este es metodo de prueba
	 * pruebaDistanciaDeSucursalCercana() :Asegura que hace bien todo el proceso desde dar la latitud y longitud hasta recibir la sucursal mas cercana
	 */
	@Test
	 void pruebaDistanciaDeSucursalCercana() throws SQLException {
		
		String expected = "La sucursal mas cerncana se encuentra a 2.611741540378483 KMs, en la Direccion: Av. Revolución 3803, Villa del Río, 64850 Monterrey, N.L.";
		Locations sucursalCercana = new Locations(25.6647, -100.2915);
		assertEquals(expected,sucursalCercana.distance());
	}
	
	
}
