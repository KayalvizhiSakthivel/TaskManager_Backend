package com.iiht.capsuleproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.capsuleproject.dao.ParentTask_Repo;
import com.iiht.capsuleproject.dao.Project_Repo;
import com.iiht.capsuleproject.dao.Task_Repo;
import com.iiht.capsuleproject.model.ParentTask;
import com.iiht.capsuleproject.model.Project;
import com.iiht.capsuleproject.model.Task;

@Service
public class TaskService_impl {

	@Autowired
	Task_Repo trepo;
	@Autowired
	ParentTask_Repo ptRepo;

	@Autowired
	Project_Repo prepo;

	public List<Task> viewAllTask() {

		return (List<Task>) trepo.findAll();

	}

	public void addTask(Task task) {
		int count = 1;
		int kba;
		int completedCount;
		Project project = prepo.findByProjectId(task.getProject().getProjectId());

		if (task.getParentTask().getParentTaskName().equals("no value")) {

			task.setParentTask(ptRepo.getById(0));

			trepo.save(task);

			ParentTask ptask = new ParentTask();
			ptask.setId(task.getTaskId());
			ptask.setParentTaskName(task.getTask());
			ptRepo.save(ptask);

			kba = count + project.getNumberOfTasks();
			project.setNumberOfTasks(kba);

			prepo.save(project);
			if (task.getNumberOfTasks() != 0) {
				completedCount = project.getCompletedTasks() + task.getNumberOfTasks();
				project.setCompletedTasks(completedCount);

			}
		} else {

			kba = count + project.getNumberOfTasks();
			project.setNumberOfTasks(kba);
			if (task.getNumberOfTasks() != 0) {
				completedCount = project.getCompletedTasks() + task.getNumberOfTasks();
				project.setCompletedTasks(completedCount);

			}

			trepo.save(task);
		}

	}

}

/*
 * @Service public class TaskService_impl {
 * 
 * @Autowired private Task_Repo task_repo;
 * 
 * public Task addTask(Task task) { Task t = task_repo.save(task); return t; }
 * 
 * public Task updateTask(Task task) { Task t = task_repo.save(task); return t;
 * }
 * 
 * public Task findTaskById(long task_id) { // TODO Auto-generated method stub
 * Optional<Task> task = task_repo.findById(task_id); return task.isPresent() ?
 * task.get() : null; }
 * 
 * public List<Task> findAllTask() { // TODO Auto-generated method stub return
 * (List<Task>) task_repo.findAll();
 * 
 * }
 * 
 * public void delete(Task task) { // TODO Auto-generated method stub
 * task_repo.delete(task); }
 * 
 * }
 */