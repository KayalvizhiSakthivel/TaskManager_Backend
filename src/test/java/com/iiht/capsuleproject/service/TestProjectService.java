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

import com.iiht.capsuleproject.dao.Project_Repo;
import com.iiht.capsuleproject.model.Project;
import com.iiht.capsuleproject.model.User;

public class TestProjectService {

	@InjectMocks
	ProjectService_impl projecttestservice;

	@Mock
	Project_Repo projecttestrepo;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllProjectTest() throws ParseException {
		LocalDate startdate1 = LocalDate.of(2018, 11, 11);
		LocalDate startdate2 = LocalDate.of(2019, 11, 11);
		LocalDate enddate1 = LocalDate.of(2019, 3, 3);
		LocalDate enddate2 = LocalDate.of(2020, 3, 3);
		Project project1 = new Project("project1", startdate1, enddate1, 22, new User("Kayal", "sakthi", 578446),
				true, 3, 1);
		Project project2 = new Project("project1", startdate2, enddate2, 23, new User("Kayalvizhi", "Sakthivel", 327005872),
				true, 3, 2);
		List<Project> project = new ArrayList<Project>();
		project.add(project1);
		project.add(project2);
		when(projecttestrepo.findAll()).thenReturn(project);

		List<Project> projectList = (List<Project>) projecttestservice.findAllProject();
		assertEquals(2, projectList.size());
		verify(projecttestrepo, times(1)).findAll();

	}

	public void createProjectTest() throws ParseException {
		LocalDate startdate1 = LocalDate.of(2017, 1, 13);
		LocalDate enddate1 = LocalDate.of(2017, 2, 13);
		Project project1 = new Project("project1", startdate1, enddate1, 22, new User("Kayalvizhi", "Sakthivel", 578446),
				true, 3, 1);
		projecttestservice.addProject(project1);
		verify(projecttestrepo, times(1)).save(project1);
	}

}
