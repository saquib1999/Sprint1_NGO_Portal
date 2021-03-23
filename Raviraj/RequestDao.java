package com.cg.ngoportal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.Request;

@Repository
public interface RequestDao extends JpaRepository<Request, Integer> {

	List<Request> findByNeedyPersonId(int needyPeopleId);

}
