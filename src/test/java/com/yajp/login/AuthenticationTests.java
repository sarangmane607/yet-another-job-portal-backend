package com.yajp.login;

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
import com.yajp.security.model.User;
import com.yajp.security.payload.ApiResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class AuthenticationTests {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationTests.class);
	
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
	public void signup() throws Exception {
		LOGGER.debug(">>>Test signup");
		MvcResult mvcResult = mvc.perform(post("/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"admin\", \"email\": \"test_admin@gmail.com\", \"password\": \"qwerty\"}"))
		.andReturn();
		assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
		String content = mvcResult.getResponse().getContentAsString();
		ApiResponse signupResponse = objectMapper.readValue(content, ApiResponse.class);
		assertNotNull(signupResponse);
		assertEquals(signupResponse.isSuccess(), true);
	}
	
	@Test
	@Order(3)
	public void signup_already_in_use() throws Exception {
		System.err.println(">>>Test signup_already_in_use");
		MvcResult mvcResult = mvc.perform(post("/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"admin\", \"email\": \"test_admin@gmail.com\", \"password\": \"qwerty\"}"))
		.andReturn();
		assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
	}

	@Test
	@Order(4)
	public void login() throws Exception {
		System.err.println(">>>Test login");
		MvcResult mvcResult = mvc.perform(post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"test_admin@gmail.com\",\"password\":\"qwerty\"}"))
		.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		String content = mvcResult.getResponse().getContentAsString();
		assertNotNull(content);
	}

	@Test
	@WithUserDetails("test_admin@gmail.com")
	@Order(5)
	public void login_and_chk_profile() throws Exception {
		System.err.println(">>>Test login_and_chk_profile");
		MvcResult mvcResult = mvc.perform(get("/user/me")
				.contentType(MediaType.APPLICATION_JSON))
		.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		String content = mvcResult.getResponse().getContentAsString();
		assertNotNull(content);
		User user = objectMapper.readValue(content, User.class);
		assertNotNull(user);
		assertEquals("test_admin@gmail.com", user.getEmail());
	}

	@Test
	@Order(6)
	public void invaid_user_wrong_userid() throws Exception {
		System.err.println(">>>Test invaid_user_wrong_userid");
		mvc.perform(post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"usernotpresent@gmail.com\",\"password\":\"qwerty\"}"))
		.andExpect(status().isUnauthorized());
	}

	@Test
	@Order(7)
	public void invaid_user_wrong_password() throws Exception {
		System.err.println(">>>Test invaid_user_wrong_password");
		mvc.perform(post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"test_admin@gmail.com\",\"password\":\"123\"}"))
		.andExpect(status().isUnauthorized());
	}
}
