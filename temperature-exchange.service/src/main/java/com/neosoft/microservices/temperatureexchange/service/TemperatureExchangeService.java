package com.neosoft.microservices.temperatureexchange.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class TemperatureExchangeService {
@Autowired
private TemperatureExchangeRepository temperatureExchangeRepository;
@Autowired
private Environment environment;
public ResponseModule exchangeTest(String temperatureTo, String temperatureFrom) {
	TemperatureExchange temperatureExchange = temperatureExchangeRepository.findByTempratureFromAndTempratureTo(temperatureFrom, temperatureTo);
	if(temperatureExchange!=null) {
		if(temperatureFrom.equalsIgnoreCase("F")) {
			temperatureExchange.setConversion(BigDecimal.valueOf((1-32)*0.5555));
		}
		else {
			temperatureExchange.setConversion(BigDecimal.valueOf((1*1.8)+32));
		}
		String port=environment.getProperty("local.server.port");
		temperatureExchange.setEnviorement(port);
		return new ResponseModule("From: "+temperatureFrom+" To: "+temperatureTo+" Value: "+temperatureExchange.getConversion(),Boolean.TRUE,"Success");
	}
	else {
		return new ResponseModule("No Such Conversion Method Found",Boolean.FALSE,"Failed");
	}
}
}
