package com.iiht.capsuleproject.controller;

import org.springframework.http.MediaType;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.capsuleproject.exception.ImportException;
import com.iiht.capsuleproject.model.User;
import com.iiht.capsuleproject.service.UserService_impl;
import com.iiht.capsuleproject.test.TestUtil;

public class UserControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Mock
	private UserService_impl userservice;

	private MockMvc mockMvc;

	@InjectMocks
	private UserController usercontroller;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(usercontroller).build();
	}

	@Test
	public void getAllUsersTest() throws Exception, ParseException, IOException {
		User user1 = new User();
		User user2 = new User();
		user1.setUserId(1);
		user1.setFirstName("Kayal");
		user1.setLastName("Sakthi");
		user1.setEmployeeId(578446);
		user2.setUserId(2);
		user2.setFirstName("Kayalvizhi");
		user2.setLastName("Sakthivel");
		user2.setEmployeeId(327005872);

		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);

		when(userservice.findAllUser()).thenReturn(users);
		mockMvc.perform(get("/user/users")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$[0].userId", is(1))).andExpect(jsonPath("$[0].firstName", is("Kayal")))
				.andExpect(jsonPath("$[0].lastName", is("Sakthi"))).andExpect(jsonPath("$[0].empId", is(578446)))
				.andExpect(jsonPath("$[1].userId", is(2))).andExpect(jsonPath("$[1].firstName", is("Kayalvizhi")))
				.andExpect(jsonPath("$[1].lastName", is("Sakthivel"))).andExpect(jsonPath("$[1].empId", is(327005872)))
				.andDo(print());
		verify(userservice, times(1)).findAllUser();
		verifyNoMoreInteractions(userservice);

	}

	@Test
	public void CreateUserTest() throws Exception {
		User user = new User();
		user.setUserId(1);
		user.setFirstName("Kayal");
		user.setLastName("Sakthi");
		user.setEmployeeId(578446);
		when(userservice.addUser(user)).thenReturn(user);
		mockMvc.perform(
				post("/user/users/addUser").contentType(APPLICATION_JSON_UTF8).content(TestUtil.ObjecttoJSON(user)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.userId", is(1)))
				.andExpect(jsonPath("$.firstName", is("Kayal"))).andExpect(jsonPath("$.lastName", is("Sakthi")))
				.andExpect(jsonPath("$.empId", is(578446))).andDo(print());

	}

	@Test
	public void testPostUserExceptin() throws Exception {
		User user = new User(null, null, 123);
		when(userservice.addUser(user)).thenThrow(new ImportException());
		mockMvc.perform(post("/addUser").contentType(APPLICATION_JSON_UTF8).content(asJsonString(user)))
				.andExpect(status().isBadRequest()).andDo(print());

	}

	public static String asJsonString(final Object obj) {
		// TODO Auto-generated method stub
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
