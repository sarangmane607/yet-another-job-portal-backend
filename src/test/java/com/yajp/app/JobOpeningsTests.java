package com.yajp.app;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yajp.app.controllers.payload.JobOpeningCollection;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class JobOpeningsTests {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JobOpeningsTests.class);
	
	@Autowired
    private WebApplicationContext context;
 
    private MockMvc mvc;
    private ObjectMapper objectMapper;
    
    @Before
    public void setup() {
        mvc = MockMvcBuilders
          .webAppContextSetup(context)
          .apply(springSecurity())
          .build();
        objectMapper = new ObjectMapper();
    }
	
	@Test
	@Order(1)
	public void contextLoads() {
	}
	
	@Test
	@Order(2)
	public void get_all_job_openings_with_out_login() throws Exception {
		LOGGER.debug(">>>Test get_all_job_openings_with_out_login");
		MvcResult mvcResult = mvc.perform(get("/jobopenings/getall")
				.contentType(MediaType.APPLICATION_JSON))
		.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		String content = mvcResult.getResponse().getContentAsString();
		JobOpeningCollection jobCollection = objectMapper.readValue(content, JobOpeningCollection.class);
		assertNotNull(jobCollection);
	}

	@Test
	@WithUserDetails("admin@gmail.com")
	@Order(3)
	public void get_all_job_openings_with_login() throws Exception {
		LOGGER.debug(">>>Test get_all_job_openings_with_login");
		MvcResult mvcResult = mvc.perform(get("/jobopenings/getall")
				.contentType(MediaType.APPLICATION_JSON))
		.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		String content = mvcResult.getResponse().getContentAsString();
		JobOpeningCollection jobCollection = objectMapper.readValue(content, JobOpeningCollection.class);
		assertNotNull(jobCollection);
		assertNotNull(jobCollection.getJobs());
		assertEquals(true, jobCollection.getJobs().size() > 0);
	}

	@Test
	@WithUserDetails("admin@gmail.com")
	@Order(4)
	public void get_active_job_openings_with_login() throws Exception {
		LOGGER.debug(">>>Test get_active_job_openings_with_login");
		MvcResult mvcResult = mvc.perform(get("/jobopenings/getallactive")
				.contentType(MediaType.APPLICATION_JSON))
		.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		String content = mvcResult.getResponse().getContentAsString();
		JobOpeningCollection jobCollection = objectMapper.readValue(content, JobOpeningCollection.class);
		assertNotNull(jobCollection);
		assertNotNull(jobCollection.getJobs());
		assertEquals(true, jobCollection.getJobs().size() > 0);
	}

	@Test
	@Order(5)
	public void get_one_job_openings() throws Exception {
		LOGGER.debug(">>>Test get_one_job_openings");
		MvcResult mvcResult = mvc.perform(get("/jobopenings/get/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		String content = mvcResult.getResponse().getContentAsString();
		JobOpeningCollection jobCollection = objectMapper.readValue(content, JobOpeningCollection.class);
		assertNotNull(jobCollection);
		assertNotNull(jobCollection.getJobs());
		assertEquals(true, jobCollection.getJobs().size() == 1);
	}

	@Test
	@Order(6)
	public void get_invalid_job_openings() throws Exception {
		LOGGER.debug(">>>Test get_invalid_job_openings");
		MvcResult mvcResult = mvc.perform(get("/jobopenings/get/11111111111111111")
				.contentType(MediaType.APPLICATION_JSON))
		.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		String content = mvcResult.getResponse().getContentAsString();
		JobOpeningCollection jobCollection = objectMapper.readValue(content, JobOpeningCollection.class);
		assertNotNull(jobCollection);
		assertNull(jobCollection.getJobs());
	}
}
