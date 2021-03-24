package com.cg.ngoportal.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.DonationBox;
@Repository
public interface DonationBoxDao extends JpaRepository<DonationBox, Integer> {
	Optional<DonationBox> findByNgoName(String ngo);
}
