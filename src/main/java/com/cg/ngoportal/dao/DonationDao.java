package com.cg.ngoportal.dao;

import org.springframework.data.repository.CrudRepository;

import com.cg.ngoportal.model.Donation;

public interface DonationDao extends CrudRepository<Donation, Integer> {

}
