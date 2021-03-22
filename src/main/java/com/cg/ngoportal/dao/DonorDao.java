package com.cg.ngoportal.dao;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.Donor;
import com.cg.ngoportal.model.User;


@Repository
public interface DonorDao extends JpaRepository<Donor, Integer> {
	Optional<Donor> findByEmail(String email);
	Optional<Donor> findByUserLoginDetails(User user);
	
}
