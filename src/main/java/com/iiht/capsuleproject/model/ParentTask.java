package com.iiht.capsuleproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ParentTask")
public class ParentTask {

	@Id
	private long id;

	private String parentTaskName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getParentTaskName() {
		return parentTaskName;
	}

	public void setParentTaskName(String parentTaskName) {
		this.parentTaskName = parentTaskName;
	}

	public ParentTask(long id, String parentTaskName) {
		super();
		this.id = id;
		this.parentTaskName = parentTaskName;
	}

	public ParentTask() {
		super();
	}

}
