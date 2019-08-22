package com.iiht.capsuleproject.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private long taskId;

	@OneToOne
	private Project project;

	private String task;

	private int priority;

	@OneToOne
	private ParentTask parentTask;

	private LocalDate startDate;

	private LocalDate endDate;

	@OneToOne(cascade = CascadeType.MERGE)
	private User user;

	int numberOfTasks;
	private boolean isSuspended;

	public boolean isSuspended() {
		return isSuspended;
	}

	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getTask() {
		return task;
	}

	// public Task(Project project, String task) {
	// super();
	// this.project = project;
	// this.task = task;
	// }

	public void setTask(String task) {
		this.task = task;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public ParentTask getParentTask() {
		return parentTask;
	}

	public int getNumberOfTasks() {
		return numberOfTasks;
	}

	public void setNumberOfTasks(int numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
	}

	public void setParentTask(ParentTask parentTask) {
		this.parentTask = parentTask;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Task(Project project, String task, int priority, ParentTask parentTask, LocalDate startDate,
			LocalDate endDate, User user, int numberOfTasks, boolean isSuspended) {
		super();
		this.project = project;
		this.task = task;
		this.priority = priority;
		this.parentTask = parentTask;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
		this.numberOfTasks = numberOfTasks;
		this.isSuspended = isSuspended;
	}

	public Task() {
		super();
	}

}

/*
 * @Entity
 * 
 * @Table(name = "Task") public class Task {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.AUTO)
 * 
 * @Column(name = "taskId") private long taskId;
 * 
 * @Column(name = "task") private String task;
 * 
 * @Column(name = "priority") private int priority;
 * 
 * @Column(name = "parentTask") private String parentTask;
 * 
 * @DateTimeFormat(pattern = "yyyy-MM-dd")
 * 
 * @Column(name = "startDate") private LocalDate startDate;
 * 
 * @DateTimeFormat(pattern = "yyyy-MM-dd")
 * 
 * @Column(name = "endDate") private LocalDate endDate; public long getTaskId()
 * { return taskId; } public void setTaskId(long taskId) { this.taskId = taskId;
 * } public String getTask() { return task; } public void setTask(String task) {
 * this.task = task; } public int getPriority() { return priority; } public void
 * setPriority(int priority) { this.priority = priority; } public String
 * getParentTask() { return parentTask; } public void setParentTask(String
 * parentTask) { this.parentTask = parentTask; } public LocalDate getStartDate()
 * { return startDate; } public void setStartDate(LocalDate startDate) {
 * this.startDate = startDate; } public LocalDate getEndDate() { return endDate;
 * } public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
 * public Task(long taskId, String task, int priority, String parentTask,
 * LocalDate startDate, LocalDate endDate) { super(); this.taskId = taskId;
 * this.task = task; this.priority = priority; this.parentTask = parentTask;
 * this.startDate = startDate; this.endDate = endDate; }
 * 
 * @Override public String toString() { return "Task [taskId=" + taskId +
 * ", task=" + task + ", priority=" + priority + ", parentTask=" + parentTask +
 * ", startDate=" + startDate + ", endDate=" + endDate + "]"; } public Task() {
 * super(); // TODO Auto-generated constructor stub }
 * 
 * 
 * }
 */