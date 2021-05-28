package com.neosoft.microservices.temperatureexchange.service;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempratureExchangeController {
	@Autowired
	private TemperatureExchangeService service;
	@Autowired
	private Environment environment;
	@Autowired
	private TemperatureExchangeRepository temperatureExchangeRepository;
@GetMapping("/temperature-exchange/from/{tempratureFrom}/to/{tempratureTo}")
public TemperatureExchange retrieveValue(@PathVariable String tempratureFrom , @PathVariable String tempratureTo) {
	TemperatureExchange temperatureExchange = temperatureExchangeRepository.findByTempratureFromAndTempratureTo(tempratureFrom, tempratureTo);
	if(tempratureFrom.equalsIgnoreCase("F")) {
		temperatureExchange.setConversion(BigDecimal.valueOf((1-32)*0.5555));
	}
	else {
		temperatureExchange.setConversion(BigDecimal.valueOf((1*1.8)+32));
	}
	String port=environment.getProperty("local.server.port");
	temperatureExchange.setEnviorement(port);
	return temperatureExchange;
}
@GetMapping("/temperature-exchange-test/from/{tempratureFrom}/to/{tempratureTo}")
public ResponseModule retrieveValueTest(@PathVariable String tempratureFrom , @PathVariable String tempratureTo) {
 return service.exchangeTest(tempratureTo, tempratureFrom);
}
}
