package com.neosoft.microservices.temperatureconversion.service;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name="temperature-exchange")
public interface TemperatureExchangeProxy {
@GetMapping("/temperature-exchange/from/{temperatureFrom}/to/{temperatureTo}")
public TemperatureConversion retrieveValue(@PathVariable String temperatureFrom,@PathVariable String temperatureTo);
}
