package com.iiht.capsuleproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.capsuleproject.dao.Project_Repo;
import com.iiht.capsuleproject.model.Project;

@Service
public class ProjectService_impl {

	@Autowired
	Project_Repo proj_repo;

	public Project addProject(Project project) {
		Project p = proj_repo.save(project);
		return p;
	}

	public void updateProject(Project project) {
		proj_repo.save(project);

	}

	public Project findProjById(long project_id) {
		// TODO Auto-generated method stub
		Optional<Project> project = proj_repo.findById(project_id);
		return project.isPresent() ? project.get() : null;
	}

	public List<Project> findAllProject() {
		// TODO Auto-generated method stub
		return (List<Project>) proj_repo.findAll();

	}

	public void deleteProject(Project project) {
		// TODO Auto-generated method stub
		proj_repo.delete(project);
	}

}
