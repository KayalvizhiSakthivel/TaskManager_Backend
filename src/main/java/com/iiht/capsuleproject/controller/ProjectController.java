package com.iiht.capsuleproject.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.capsuleproject.model.Project;

import com.iiht.capsuleproject.service.ProjectService_impl;

@CrossOrigin(origins = "*")
@RestController
public class ProjectController {
	@Autowired
	ProjectService_impl projimpl;

	/* add new user */
	@PostMapping("/addProject")

	public Project addProject(@RequestBody Project project) {
		return projimpl.addProject(project);
	}

	/* View user */
	@GetMapping("/viewProject/{id}")
	public Project findProjById(@PathVariable long id) {
		return projimpl.findProjById(id);
	}

	/* View all users */
	@GetMapping("/viewProjects")
	public Iterable<Project> findAllProject() {
		return projimpl.findAllProject();
	}

	/* Update user */
	@PutMapping("/updateProject")
	public void updateProject(@RequestBody Project project) {
		projimpl.updateProject(project);
	}

	/* DELETE user */
	@PostMapping("/deleteProject")
	public void deleteProject(@RequestBody Project project) {
		projimpl.deleteProject(project);

	}

	/*
	 * @GetMapping("/viewProject") public ResponseEntity<List<Project>>
	 * findAllProject() { return new ResponseEntity<>(projimpl.findAllProject(),
	 * HttpStatus.OK); }
	 * 
	 * @PostMapping("/addProject") public ResponseEntity<String>
	 * addProject(@RequestBody Project project) { Project p =
	 * projimpl.addProject(project); ResponseEntity<String> r = null; if (p == null)
	 * { r = new ResponseEntity<>("Empty user found", HttpStatus.NO_CONTENT); } else
	 * { r = new ResponseEntity<>("User details added successfully" + p,
	 * HttpStatus.CREATED); } return r; }
	 * 
	 * @PostMapping("/deleteProject") public ResponseEntity<?> deleteProject(Long
	 * projectId, @RequestBody Project project) {
	 * 
	 * projectService.deleteProject(project); Project t =
	 * projimpl.findProjById(projectId); ResponseEntity<?> resp = null; if (t ==
	 * null) { resp = new ResponseEntity<>("User with id " + projectId +
	 * " not found", HttpStatus.NOT_FOUND); } else { projimpl.deleteProject(t); resp
	 * = new ResponseEntity<>("User deleted successfully!!!", HttpStatus.OK); }
	 * return resp; }
	 */
}
