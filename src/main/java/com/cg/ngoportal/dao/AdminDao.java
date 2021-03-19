package com.cg.ngoportal.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.Admin;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.Employee;
@Repository
public interface AdminDao extends CrudRepository<Admin, Integer>{

}
