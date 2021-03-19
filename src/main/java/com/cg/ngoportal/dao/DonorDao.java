package com.cg.ngoportal.dao;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.Donor;


@Repository
public interface DonorDao extends CrudRepository<Donor, Integer> {
	
}
