package com.neosoft.microservices.temperatureconversion.service;

import java.math.BigDecimal;

public class TemperatureConversion {
private Long id;
private String tempratureFrom;
private String tempratureTo;
private Double value;
private BigDecimal conversion;
private BigDecimal calculatedValue;
private String enviorement;
public TemperatureConversion() {
	
}
public TemperatureConversion(Long id,String tempratureFrom, String tempratureTo ,Double value, BigDecimal conversion, BigDecimal calculatedValue,String enviorement) {
	this.id=id;
	this.tempratureFrom=tempratureFrom;
	this.tempratureTo=tempratureTo;
	this.value=value;
	this.conversion=conversion;
	this.calculatedValue=calculatedValue;
	this.enviorement=enviorement;
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
public Double getValue() {
	return value;
}
public void setValue(Double value) {
	this.value = value;
}
public BigDecimal getConversion() {
	return conversion;
}
public void setConversion(BigDecimal conversion) {
	this.conversion = conversion;
}
public BigDecimal getCalculatedValue() {
	return calculatedValue;
}
public void setCalculatedValue(BigDecimal calculatedValue) {
	this.calculatedValue = calculatedValue;
}
public String getEnviorement() {
	return enviorement;
}
public void setEnviorement(String enviorement) {
	this.enviorement = enviorement;
}

}
