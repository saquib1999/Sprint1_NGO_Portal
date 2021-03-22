package com.cg.ngoportal.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.User;
@Repository
public interface NeedyPeopleDao extends JpaRepository<NeedyPeople, Integer> {
	
	
	List<NeedyPeople> findByNameContainingIgnoreCaseOrderByNameAsc(String name);
	List<NeedyPeople> findByNameStartingWith(String name);

	Optional<NeedyPeople> findByUserLoginDetails(User userLoginDetails);
	
	
}
