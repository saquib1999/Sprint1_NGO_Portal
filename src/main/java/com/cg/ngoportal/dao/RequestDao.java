package com.cg.ngoportal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.Request;
import com.cg.ngoportal.model.RequestStatus;
@Repository
public interface RequestDao extends JpaRepository<Request, Integer> {
	
//	List<Request> findByStatusContaining(RequestStatus status);
	@Query("Select r from Request r where r.status= ?1")
	List<Request> findByStatusContaining(RequestStatus status);

	

}
