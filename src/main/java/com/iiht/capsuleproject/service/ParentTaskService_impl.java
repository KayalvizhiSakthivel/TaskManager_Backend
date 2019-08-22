package com.iiht.capsuleproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.capsuleproject.dao.ParentTask_Repo;
import com.iiht.capsuleproject.model.ParentTask;

@Service
public class ParentTaskService_impl {
	@Autowired
	private ParentTask_Repo parentTaskRepository;

	public List<ParentTask> getAllParentTasks() {
		return (List<ParentTask>) parentTaskRepository.findAll();
	}

	public void addParentTask(ParentTask parentTask) {
		parentTaskRepository.save(parentTask);
	}

}
