package com.neosoft.microservices.temperatureconversion.service;

import java.math.BigDecimal;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
//Temperature Conversion Controller
public class TemperatureConversionController {
	//Defining Logger object
	private Logger logger = LoggerFactory.getLogger(TemperatureConversionController.class);
	//Autowiring Environment Variable to get the Port Number
	@Autowired
	private Environment environment;
	@Autowired
	private TemperatureExchangeProxy proxy;
	@Autowired 
	private TemperatureConversionService service;
//Temperature Conversion Controller Method without Feign Client
@GetMapping("/temperature-conversion/from/{temperatureFrom}/to/{temperatureTo}/value/{value}")
public TemperatureConversion calculateTemperatureConversion(@PathVariable String temperatureFrom,@PathVariable String temperatureTo, @PathVariable Double value) {
	HashMap<String , String> variables = new HashMap<>();
	variables.put("temperatureFrom", temperatureFrom);
	variables.put("temperatureTo", temperatureTo);
	//Using Response Entity Method for obtaining Conversion Object
	ResponseEntity<TemperatureConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000//temperature-exchange/from/{temperatureFrom}/to/{temperatureTo}",
			TemperatureConversion.class,variables);
	TemperatureConversion conversion=responseEntity.getBody();
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
	//Providing some information on Console as Well
	logger.info("Temperature Conversion From: "+temperatureFrom);
	logger.info("Temperature Conversion To: "+temperatureTo);
	logger.info("Conversio  Tyep:" +conversion.getConversion());
	logger.info("Value obtained: "+i);
	return new TemperatureConversion(conversion.getId(),temperatureFrom,temperatureTo,value,conversion.getConversion(),i,port);
}
//Temperature Conversion Controller Method with Feign Client
@GetMapping("/temperature-conversion-feign/from/{temperatureFrom}/to/{temperatureTo}/value/{value}")
public TemperatureConversion calculateTemperatureConversionFeign(@PathVariable String temperatureFrom,@PathVariable String temperatureTo, @PathVariable Double value) {
	//Using Feign Method Defined in Proxy Class
	TemperatureConversion conversion= proxy.retrieveValue(temperatureFrom, temperatureTo);
	BigDecimal i=BigDecimal.ONE;
	if(temperatureFrom.equalsIgnoreCase("F")) {
		i=BigDecimal.valueOf((value-32)*0.5555);
	}
	else {
		i=BigDecimal.valueOf((value*9/5)+32);
	}
	//Providing some information on Console as Well
	logger.info("Temperature Conversion From: "+temperatureFrom);
	logger.info("Temperature Conversion To: "+temperatureTo);
	logger.info("Conversio  Tyep:" +conversion.getConversion());
	logger.info("Value obtained: "+i);
	return new TemperatureConversion(conversion.getId(),temperatureFrom,temperatureTo,value,conversion.getConversion(),i,conversion.getEnviorement()+" feign client");
}
@GetMapping("/temperature-conversion-test/from/{temperatureFrom}/to/{temperatureTo}/value/{value}")
public ResponseModule calculateTemperatureConversionTest(@PathVariable String temperatureFrom,@PathVariable String temperatureTo, @PathVariable Double value) {
  return service.conversionTest(temperatureFrom, temperatureTo, value);
}
}
