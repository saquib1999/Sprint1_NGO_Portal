package com.cg.ngoportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.Donation;
@Repository
public interface DonationDao extends JpaRepository<Donation, Integer> {

}