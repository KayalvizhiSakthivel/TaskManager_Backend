package com.iiht.capsuleproject.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.capsuleproject.controller.ProjectController;
import com.iiht.capsuleproject.exception.ImportException;
import com.iiht.capsuleproject.model.Project;
import com.iiht.capsuleproject.model.User;
import com.iiht.capsuleproject.service.ProjectService_impl;
import com.iiht.capsuleproject.test.TestUtil;

public class ProjectControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Mock
	private ProjectService_impl projectservice;

	private MockMvc mockMvc;

	@InjectMocks
	private ProjectController projectcontroller;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(projectcontroller).build();
	}

	@Test
	public void getAllProjectsTest() throws IOException, ParseException, Exception {

		LocalDate startdate1 = LocalDate.of(2018, 11, 11);
		LocalDate startdate2 = LocalDate.of(2019, 11, 11);
		LocalDate enddate1 = LocalDate.of(2019, 3, 3);
		LocalDate enddate2 = LocalDate.of(2020, 3, 3);
		Project project1 = new Project("project1", startdate1, enddate1, 22, new User("Kayalvizhi", "Sakthivel", 327005872),
				true, 3, 1);
		Project project2 = new Project("project2", startdate2, enddate2, 23, new User("Kayal", "Sakthi", 578446),
				true, 3, 2);
		List<Project> projects = new ArrayList<Project>();
		projects.add(project1);
		projects.add(project2);

		when(projectservice.findAllProject()).thenReturn(projects);
		mockMvc.perform(get("/project/projects")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$[0].projectId", is(101))).andExpect(jsonPath("$[0].projectName", is("project1")))
				.andExpect(jsonPath("$[0].startDate", is(notNullValue())))
				.andExpect(jsonPath("$[0].endDate", is(notNullValue()))).andExpect(jsonPath("$[0].priority", is(5)))
				.andExpect(jsonPath("$[0].user.userId", is(1)))
				.andExpect(jsonPath("$[0].user.firstName", is("Kayal")))
				.andExpect(jsonPath("$[0].user.lastName", is("Sakthi")))
				.andExpect(jsonPath("$[0].user.empId", is(578446))).andExpect(jsonPath("$[0].noOfTasks", is(2)))
				.andExpect(jsonPath("$[0].completedTask", is(1))).andExpect(jsonPath("$[0].suspendProject", is(false)))
				.andExpect(jsonPath("$[1].projectId", is(102))).andExpect(jsonPath("$[1].projectName", is("mit")))
				.andExpect(jsonPath("$[1].startDate", is(notNullValue())))
				.andExpect(jsonPath("$[1].endDate", is(notNullValue()))).andExpect(jsonPath("$[1].priority", is(7)))
				.andExpect(jsonPath("$[1].user.userId", is(2)))
				.andExpect(jsonPath("$[1].user.firstName", is("Kayalvizhi")))
				.andExpect(jsonPath("$[1].user.lastName", is("Sakthivel")))
				.andExpect(jsonPath("$[1].user.empId", is(327005872))).andExpect(jsonPath("$[1].noOfTasks", is(4)))
				.andExpect(jsonPath("$[1].completedTask", is(2))).andExpect(jsonPath("$[1].suspendProject", is(true)))
				.andDo(print());
		verify(projectservice, times(1)).findAllProject();
		verifyNoMoreInteractions(projectservice);
	}

	public void AddProjectTest() throws IOException, Exception {
		LocalDate startdate = LocalDate.of(2018, 11, 11);
		LocalDate enddate = LocalDate.of(2019, 11, 11);
		Project project1 = new Project("project1", startdate, enddate, 5, new User("Kayal", "Sakthi", 578446), false,
				2, 1);
		when(projectservice.addProject(project1)).thenReturn(project1);
		mockMvc.perform(post("/addTask").contentType(APPLICATION_JSON_UTF8).content(TestUtil.ObjecttoJSON(project1)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.projectId", is(101)))
				.andExpect(jsonPath("$.projectName", is("project1"))).andExpect(jsonPath("$.startDate", is(notNullValue())))
				.andExpect(jsonPath("$.endDate", is(notNullValue()))).andExpect(jsonPath("$.priorityValue", is(5)))
				.andExpect(jsonPath("$.user.userId", is(1))).andExpect(jsonPath("$.user.firstName", is("Kayal")))
				.andExpect(jsonPath("$.user.lastName", is("Sakthi"))).andExpect(jsonPath("$.user.empId", is(578446)))
				.andExpect(jsonPath("$.noOfTasks", is(2))).andExpect(jsonPath("$.completedTask", is(1)))
				.andExpect(jsonPath("$.activeProject", is(false))).andDo(print());

	}

	@Test
	public void testPostProjectExceptin() throws Exception {

		LocalDate startdate = LocalDate.of(2018, 11, 11);
		LocalDate enddate = LocalDate.of(2019, 11, 11);
		Project project = new Project(null, startdate, enddate, 10, null, false, 2, 1);
		when(projectservice.addProject(project)).thenThrow(new ImportException());
		mockMvc.perform(post("/addProject").contentType(APPLICATION_JSON_UTF8).content(asJsonString(project)))
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
