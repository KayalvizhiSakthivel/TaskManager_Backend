package com.iiht.capsuleproject.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iiht.capsuleproject.dao.ParentTask_Repo;
import com.iiht.capsuleproject.dao.Task_Repo;
import com.iiht.capsuleproject.model.ParentTask;
import com.iiht.capsuleproject.model.Project;
import com.iiht.capsuleproject.model.Task;
import com.iiht.capsuleproject.model.User;

public class TestTaskService {
	@InjectMocks
	TaskService_impl taskservice;

	@Mock
	Task_Repo taskrepo;

	@Mock
	ParentTask_Repo Ptrepo;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getTaskTest() throws ParseException {

		LocalDate startdate1 = LocalDate.of(2018, 11, 11);
		LocalDate startdate2 = LocalDate.of(2019, 11, 11);
		LocalDate enddate1 = LocalDate.of(2019, 3, 3);
		LocalDate enddate2 = LocalDate.of(2020, 3, 3);
		Task t1 = new Task(
				new Project("project1", startdate1, enddate1, 5, new User("Kayal", "sakthi", 111), false, 2, 1),
				"Task1", 8, new ParentTask(1, "pt1"), startdate1, enddate1, new User("Kayal", "sakthi", 111), 1, false);

		List<Task> task = new ArrayList<Task>();
		task.add(t1);

		when(taskrepo.findAll()).thenReturn(task);
		// test
		List<Task> taskList = taskservice.viewAllTask();
		assertEquals(1, taskList.size());
		verify(taskrepo, times(1)).findAll();
	}

}
