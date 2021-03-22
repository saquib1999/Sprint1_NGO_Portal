package com.cg.ngoportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.Employee;

@Repository
public interface IAdminRepository extends JpaRepository<Employee, Integer> {

}
