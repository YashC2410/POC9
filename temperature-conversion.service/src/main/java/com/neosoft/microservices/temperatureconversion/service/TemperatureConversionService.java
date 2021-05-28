package com.neosoft.microservices.temperatureconversion.service;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class TemperatureConversionService {
	@Autowired
	private Environment environment;
	public ResponseModule conversionTest(String temperatureFrom, String temperatureTo,Double value) {
		HashMap<String , String> variables = new HashMap<>();
		variables.put("temperatureFrom", temperatureFrom);
		variables.put("temperatureTo", temperatureTo);
		//Using Response Entity Method for obtaining Conversion Object
		ResponseEntity<TemperatureConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000//temperature-exchange/from/{temperatureFrom}/to/{temperatureTo}",
				TemperatureConversion.class,variables);
		TemperatureConversion conversion=responseEntity.getBody();
		if(conversion!=null) {
			BigDecimal i=BigDecimal.ONE;
			if(temperatureFrom.equalsIgnoreCase("F")) {
				//Calculating Value based on F to C Conversion
				i=BigDecimal.valueOf((value-32)*0.5555);
			}
			else {
				//Calculating Value based on C to F Conversion
				i=BigDecimal.valueOf((value*9/5)+32);
			}
			//Assigning the Current Port upon which application is running
			String port=environment.getProperty("local.server.port");
			return new ResponseModule("From: "+temperatureFrom+" To: "+temperatureTo+" Value: "+value+" Conversion: "+conversion.getConversion(),Boolean.TRUE,"Success");
		}
		else {
			return new ResponseModule("No Such Conversion Method Found",Boolean.FALSE,"Failed");
		}
	}
}
