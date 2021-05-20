package com.apisucrud.restfulspringbootsucrud;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.apisucrud.*;

@SpringBootTest
class RestfulSpringBootSucrudApplicationTests {

	@Test
	void contextLoads() {

		Locations calculo = new Locations(0, 0);
		
		calculo.calculateDistanceBetweenLocations(null, null);
	}
}
