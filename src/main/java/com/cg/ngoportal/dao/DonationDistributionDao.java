package com.cg.ngoportal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.DonationDistributionStatus;
import com.cg.ngoportal.model.Employee;
@Repository
public interface DonationDistributionDao extends JpaRepository<DonationDistribution, Integer> {

//	@Query("Select d from DonationDistribution d where d.status= ?1")
	List<DonationDistribution> findByStatus(DonationDistributionStatus status);
	List<DonationDistribution> findByStatusAndDistributedBy(DonationDistributionStatus status, Employee emp);

}
