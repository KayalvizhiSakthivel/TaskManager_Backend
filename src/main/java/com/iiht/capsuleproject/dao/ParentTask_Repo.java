package com.iiht.capsuleproject.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iiht.capsuleproject.model.ParentTask;

@Repository
public interface ParentTask_Repo extends CrudRepository<ParentTask,Long>{



	ParentTask getById(long i);

	




		


}
