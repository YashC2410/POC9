package com.neosoft.micorservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//Defining an configuration Class to Map Requests
@Configuration
public class ApiGatewayConfiguration {
    //Initializing Router Gateway to Navigate
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) { 
		return builder.routes()
				.route(p -> p
					.path("/get")
					.filters(f -> f
							.addRequestHeader("MyHeader", "MyURI")
							.addRequestParameter("Param","MyValue"))
					.uri("http://httpbin.org:80"))
				.route(p -> p.path("/temperature-exchange/**")
						.uri("lb://temperature-exchange"))
				.route(p -> p.path("/temperature-conversion/**")
						.uri("lb://temperature-conversion"))
				.route(p -> p.path("/temperature-conversion-feign/**")
						.uri("lb://temperature-conversion"))
				.build();
		
	}
}

