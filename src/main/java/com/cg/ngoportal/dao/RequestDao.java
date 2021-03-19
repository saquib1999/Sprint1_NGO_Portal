package com.cg.ngoportal.dao;

import org.springframework.data.repository.CrudRepository;

import com.cg.ngoportal.model.Request;

public interface RequestDao extends CrudRepository<Request, Integer> {
	

}
