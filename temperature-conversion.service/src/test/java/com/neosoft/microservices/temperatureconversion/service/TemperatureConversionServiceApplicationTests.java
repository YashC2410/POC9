package com.neosoft.microservices.temperatureconversion.service;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class TemperatureConversionServiceApplicationTests {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;
	ObjectMapper objectMapper=new ObjectMapper();
	@BeforeAll
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	void getExchangeTest() throws Exception{
		MvcResult result = mockMvc.perform(get("/temperature-conversion-test/from/F/to/C/value/90.4").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
		.andExpect(status().isOk()).andReturn();
		String resultContext = result.getResponse().getContentAsString();
		ResponseModule response = objectMapper.readValue(resultContext, ResponseModule.class);
		Assertions.assertTrue(response.isStatus());
		Assertions.assertEquals("Success",response.getProgressMessage());
	}
}
