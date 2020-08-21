package com.yajp.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
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
import com.yajp.security.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthenticationTests {
	
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
	public void contextLoads() {
	}

	@Test
	public void login() throws Exception {
		MvcResult mvcResult = mvc.perform(post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"admin@gmail.com\",\"password\":\"qwerty\"}"))
		.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		String content = mvcResult.getResponse().getContentAsString();
		assertNotNull(content);
	}

	@Test
	public void invaid_user_wrong_userid() throws Exception {
		mvc.perform(post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"usernotpresent@gmail.com\",\"password\":\"qwerty\"}"))
		.andExpect(status().isUnauthorized());
	}

	@Test
	public void invaid_user_wrong_password() throws Exception {
		mvc.perform(post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"admin@gmail.com\",\"password\":\"123\"}"))
		.andExpect(status().isUnauthorized());
	}

	@Test
	@WithUserDetails("admin@gmail.com")
	public void login_and_chk_profile() throws Exception {
		MvcResult mvcResult = mvc.perform(get("/user/me")
				.contentType(MediaType.APPLICATION_JSON))
		.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		String content = mvcResult.getResponse().getContentAsString();
		User userObj = objectMapper.readValue(content, User.class);
		assertNotNull(userObj);
		assertEquals(userObj.getEmail(), "admin@gmail.com");
	}
}
