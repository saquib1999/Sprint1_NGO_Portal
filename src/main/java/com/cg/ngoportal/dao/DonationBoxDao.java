package com.cg.ngoportal.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.DonationBox;
@Repository
public interface DonationBoxDao extends CrudRepository<DonationBox, Integer> {

}
