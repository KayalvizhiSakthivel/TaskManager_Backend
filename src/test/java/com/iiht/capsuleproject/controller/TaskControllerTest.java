package com.iiht.capsuleproject.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.iiht.capsuleproject.model.ParentTask;
import com.iiht.capsuleproject.model.Project;
import com.iiht.capsuleproject.model.Task;
import com.iiht.capsuleproject.model.User;
import com.iiht.capsuleproject.service.ParentTaskService_impl;
import com.iiht.capsuleproject.service.TaskService_impl;

public class TaskControllerTest {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Mock
	private TaskService_impl taskservice;

	@Mock
	private ParentTaskService_impl parentTaskService;
	private MockMvc mockMvc;

	@InjectMocks
	private TaskController taskcontroller;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(taskcontroller).build();
	}

	public void AddParentTaskTest() throws IOException, ParseException, Exception {

		ParentTask pt1 = new ParentTask(0, "No parent task");
		ParentTask pt2 = new ParentTask(1, "develop");
		List<ParentTask> parenttask = new ArrayList<ParentTask>();
		parenttask.add(pt1);
		parenttask.add(pt2);

		when(parentTaskService.getAllParentTasks()).thenReturn(parenttask);
		mockMvc.perform(get("/task/parenttask")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$[0].parentTaskId", is(0)))
				.andExpect(jsonPath("$[0].parenttaskName", is("No parent task")))
				.andExpect(jsonPath("$[1].parentTaskId", is(1)))
				.andExpect(jsonPath("$[2].parenttaskName", is("develop"))).andDo(print());
		verify(parentTaskService, times(1)).getAllParentTasks();
		verifyNoMoreInteractions(taskservice);
	}

	public void addTaskTest() throws IOException, ParseException, Exception {

		LocalDate startdate1 = LocalDate.of(2018, 11, 11);
		LocalDate startdate2 = LocalDate.of(2019, 11, 11);
		LocalDate enddate1 = LocalDate.of(2019, 3, 3);
		LocalDate enddate2 = LocalDate.of(2020, 3, 3);
		Task t1 = new Task(
				new Project("project1", startdate1, enddate1, 5, new User("Kayal", "Sakthi", 578446), false, 2, 1),
				"Task1", 8, new ParentTask(1, "develop"), startdate1, enddate1,
				new User("Kayal", "Sakthi", 578446), 1, false);

		List<Task> task = new ArrayList<Task>();
		task.add(t1);

		when(taskservice.viewAllTask()).thenReturn(task);
		mockMvc.perform(get("/task/tasks")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$[0].taskId", is(1))).andExpect(jsonPath("$.project.projectId", is(101)))
				.andExpect(jsonPath("$.project.projectName", is("amex")))
				.andExpect(jsonPath("$.project.startDate", is(notNullValue())))
				.andExpect(jsonPath("$.project.endDate", is(notNullValue())))
				.andExpect(jsonPath("$.project.priority", is(5))).andExpect(jsonPath("$.project.user.userId", is(1)))
				.andExpect(jsonPath("$.project.user.firstName", is("firstuser1")))
				.andExpect(jsonPath("$.project.user.lastName", is("lastuser1")))
				.andExpect(jsonPath("$.project.user.empId", is(1234)))
				.andExpect(jsonPath("$.project.suspendProject", is(false))).andExpect(jsonPath("$[0].task", is("code")))
				.andExpect(jsonPath("$[0].priority", is(8)))
				.andExpect(jsonPath("$[1].parenttaskdata.parentTaskId", is(1)))
				.andExpect(jsonPath("$[2].parenttaskdata.parenttaskName", is("develop")))
				.andExpect(jsonPath("$.startDate", is(notNullValue())))
				.andExpect(jsonPath("$.endDate", is(notNullValue()))).andExpect(jsonPath("$.user.userId", is(1)))
				.andExpect(jsonPath("$.user.firstName", is("firstuser1")))
				.andExpect(jsonPath("$.user.lastName", is("lastuser1"))).andExpect(jsonPath("$.user.empId", is(1234)))
				.andDo(print());
	}
}
