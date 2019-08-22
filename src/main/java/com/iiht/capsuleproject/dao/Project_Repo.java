package com.iiht.capsuleproject.dao;

import org.springframework.data.repository.CrudRepository;

import com.iiht.capsuleproject.model.Project;

public interface Project_Repo extends CrudRepository<Project, Long> {

	Project findByProjectId(long projectId);

}
