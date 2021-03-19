package com.cg.ngoportal.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.Request;
@Repository
public interface RequestDao extends CrudRepository<Request, Integer> {
	

}
