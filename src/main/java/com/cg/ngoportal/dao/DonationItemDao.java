package com.cg.ngoportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.DonationItem;
@Repository
public interface DonationItemDao extends JpaRepository<DonationItem, Integer> {

}
