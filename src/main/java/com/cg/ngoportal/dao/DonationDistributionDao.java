package com.cg.ngoportal.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.Admin;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.DonationDistributionStatus;
import com.cg.ngoportal.model.DonationType;
@Repository
public interface DonationDistributionDao extends JpaRepository<DonationDistribution, Integer> {
	
	//@Query("SELECT d FROM DonationDistribution d WHERE d.status = 'PENDING'")
	public List<DonationDistribution> findByStatus(DonationDistributionStatus status);
	
}
