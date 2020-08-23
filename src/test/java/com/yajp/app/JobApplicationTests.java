package com.yajp.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
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
import com.yajp.app.controllers.payload.ApplyJobResponse;
import com.yajp.app.controllers.payload.UserJobApplicationCollection;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class JobApplicationTests {
	
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
	@WithUserDetails("admin@gmail.com")
	public void apply_for_job_with_login() throws Exception {
		MvcResult mvcResult = applywithId(2);
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		String content = mvcResult.getResponse().getContentAsString();
		ApplyJobResponse response = objectMapper.readValue(content, ApplyJobResponse.class);
		assertNotNull(response);
		assertEquals("success", response.getRequestStatus());
	}
	
	private MvcResult applywithId(int id) throws Exception {
		return mvc.perform(post("/application/apply/" + id)
				.contentType(MediaType.APPLICATION_JSON))
		.andReturn();
	}

	@Test
	@Order(3)
	@WithUserDetails("admin@gmail.com")
	public void re_apply_for_job() throws Exception {
		MvcResult mvcResult = mvc.perform(post("/application/apply/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		String content = mvcResult.getResponse().getContentAsString();
		ApplyJobResponse response = objectMapper.readValue(content, ApplyJobResponse.class);
		assertNotNull(response);
		assertEquals("error", response.getRequestStatus());
	}

	@Test
	@Order(4)
	public void apply_for_job_with_out_login() throws Exception {
		mvc.perform(post("/application/apply/2")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized());
	}

	@Test
	@Order(5)
	@WithUserDetails("admin@gmail.com")
	public void apply_for_invalid_job() throws Exception {
		MvcResult mvcResult = mvc.perform(post("/application/apply/11111111111111")
				.contentType(MediaType.APPLICATION_JSON))
		.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		String content = mvcResult.getResponse().getContentAsString();
		ApplyJobResponse response = objectMapper.readValue(content, ApplyJobResponse.class);
		assertNotNull(response);
		assertEquals("error", response.getRequestStatus());
	}

	@Test
	@Order(6)
	@WithUserDetails("admin@gmail.com")
	public void get_applied_jobs() throws Exception {
		try {
			applywithId(1);
		}catch(Exception e) {
			
		}
		MvcResult mvcResult = mvc.perform(get("/application/getall")
				.contentType(MediaType.APPLICATION_JSON))
		.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		String content = mvcResult.getResponse().getContentAsString();
		UserJobApplicationCollection jobCollection = objectMapper.readValue(content, UserJobApplicationCollection.class);
		assertNotNull(jobCollection);
		assertNotNull(jobCollection.getApplications());
		assertEquals(true, jobCollection.getApplications().size() >= 1);
	}

	@Test
	@Order(7)
	public void get_applied_jobs_with_out_login() throws Exception {
		mvc.perform(post("/application/getall")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized());
	}
}
