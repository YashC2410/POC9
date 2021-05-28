package com.neosoft.microservices.temperatureexchange.service;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class TemperatureExchange {
@Id
private Long id;
@Column(name="temperature_from")
private String tempratureFrom;
@Column(name="temperature_to")
private String tempratureTo;
private BigDecimal conversion;
private String enviorement;
public TemperatureExchange() {
	
}
public TemperatureExchange(Long id,String tempratureFrom,String tempratureTo,Double value,BigDecimal conversion) {
	super();
	this.id=id;
	this.tempratureFrom=tempratureFrom;
	this.tempratureTo=tempratureTo;
	this.conversion=conversion;
	
}
public String getEnviorement() {
	return enviorement;
}

public void setEnviorement(String enviorement) {
	this.enviorement = enviorement;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getTempratureFrom() {
	return tempratureFrom;
}

public void setTempratureFrom(String tempratureFrom) {
	this.tempratureFrom = tempratureFrom;
}

public String getTempratureTo() {
	return tempratureTo;
}

public void setTempratureTo(String tempratureTo) {
	this.tempratureTo = tempratureTo;
}
public BigDecimal getConversion() {
	return conversion;
}
public void setConversion(BigDecimal conversion) {
	this.conversion = conversion;
}

}
