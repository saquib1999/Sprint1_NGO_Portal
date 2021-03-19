package com.cg.ngoportal.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.NeedyPeople;
@Repository
public interface NeedyPeopleDao extends CrudRepository<NeedyPeople, Integer> {
	
	
}
