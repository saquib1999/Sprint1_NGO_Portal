package com.cg.ngoportal.dao;

import org.springframework.data.repository.CrudRepository;

import com.cg.ngoportal.model.DonationItem;

public interface DonationItemDao extends CrudRepository<DonationItem, Integer> {

}
