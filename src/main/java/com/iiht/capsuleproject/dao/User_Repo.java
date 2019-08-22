package com.iiht.capsuleproject.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iiht.capsuleproject.model.User;

@Repository
public interface User_Repo extends CrudRepository<User, Long> {

}
