package com.cg.ngoportal.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.User;
@Repository
public interface NeedyPeopleDao extends JpaRepository<NeedyPeople, Integer> {

	Optional<NeedyPeople> findByUserLoginDetails(User user);

}
