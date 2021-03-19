package com.cg.ngoportal.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.Donation;
@Repository
public interface DonationDao extends CrudRepository<Donation, Integer> {

}
