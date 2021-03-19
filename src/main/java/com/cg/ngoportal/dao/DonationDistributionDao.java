package com.cg.ngoportal.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.DonationDistribution;
@Repository
public interface DonationDistributionDao extends CrudRepository<DonationDistribution, Integer> {

}
