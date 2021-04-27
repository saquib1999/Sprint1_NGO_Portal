package com.cg.ngoportal.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.EmailAndUsername;

@Repository
public interface EmailAndUsernamDao  extends JpaRepository<EmailAndUsername, Integer>{
	public Optional<EmailAndUsername> findByEmail(String email );
	public Optional<EmailAndUsername> findByUsername(String username );
	
}
