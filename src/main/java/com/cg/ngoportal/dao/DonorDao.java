package com.cg.ngoportal.dao;

import java.sql.SQLException;

import org.springframework.data.repository.CrudRepository;

import com.cg.ngoportal.model.Donation;
import com.cg.ngoportal.model.Donor;
import com.cg.ngoportal.service.DonorService;

public interface DonorDao extends CrudRepository<Donor, Integer> {
	public int createDonor(Donor donor) throws SQLException;
	public int login(Donor donor) throws SQLException;
	public Donation donateToNGO(Donation donation);
	public String forgotPassword(String username);
	public String resetPassword(String username);
}
