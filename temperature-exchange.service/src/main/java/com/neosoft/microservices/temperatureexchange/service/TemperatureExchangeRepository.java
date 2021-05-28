package com.neosoft.microservices.temperatureexchange.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TemperatureExchangeRepository extends JpaRepository<TemperatureExchange , Long>{
	TemperatureExchange findByTempratureFromAndTempratureTo(String tempratureFrom , String tempratureTo);

}