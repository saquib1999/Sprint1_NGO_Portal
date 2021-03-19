package com.cg.ngoportal.dao;

import org.springframework.data.repository.CrudRepository;

import com.cg.ngoportal.model.User;

public interface UserDao extends CrudRepository<User, Integer> {

}
