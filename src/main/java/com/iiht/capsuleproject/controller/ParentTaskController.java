package com.iiht.capsuleproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.capsuleproject.model.ParentTask;
import com.iiht.capsuleproject.service.ParentTaskService_impl;

@RestController
@CrossOrigin(origins = "*")
public class ParentTaskController {

	@Autowired
	private ParentTaskService_impl parentTaskService;

	@GetMapping("/viewParentTasks")
	public List<ParentTask> getParentTasks() {
		return parentTaskService.getAllParentTasks();

	}

	@PostMapping("/addParentTask")
	public void addParentTask(@RequestBody ParentTask parent) {
		parentTaskService.addParentTask(parent);
	}

}
