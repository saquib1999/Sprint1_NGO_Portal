package com.cg.ngoportal.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.User;
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

}
