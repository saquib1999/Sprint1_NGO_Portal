package com.cg.ngoportal.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.Admin;
@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>{
	public Optional<Admin> findByUsernameAndPassword(String username, String password);
}
