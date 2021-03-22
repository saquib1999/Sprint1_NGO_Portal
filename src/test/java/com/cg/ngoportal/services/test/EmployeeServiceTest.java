package com.cg.ngoportal.services.test;

import static org.junit.jupiter.api.Assertions.*;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.ngoportal.dao.DonationDistributionDao;
import com.cg.ngoportal.dao.EmployeeDao;
import com.cg.ngoportal.dao.NeedyPeopleDao;
import com.cg.ngoportal.dao.RequestDao;
import com.cg.ngoportal.dao.UserDao;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.User;
import com.cg.ngoportal.model.UserType;
import com.cg.ngoportal.service.EmployeeService;


class EmployeeServiceTest {
	
	@InjectMocks
	EmployeeService employeeService;
	
	@Mock
	NeedyPeopleDao needyPeopleRepo;
	
	@Mock
	EmployeeDao employeeRepo;
	
	@Mock
	DonationDistributionDao donationDistributionRepo;
	
	@Mock
	UserDao userRepo;
	
	@Mock
	RequestDao requestRepo;
	
	

	@Before(value = "")
    public void init() {
        MockitoAnnotations.initMocks(this);
        
    }

	@Test
	void testLogin() {
		User user2 = new User("saquib123", "saquib1234", UserType.EMPLOYEE);
		Employee emp = new Employee("SaquibEmp", "empemail", "95999", null, user2);
		employeeRepo.save(emp);
		
		
		
	}

	@Test
	void testAddNeedyPerson() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveNeedyPerson() {
		fail("Not yet implemented");
	}

	@Test
	void testFindNeedyPeopleById() {
		fail("Not yet implemented");
	}

	@Test
	void testFindNeedyPeopleByName() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAllNeedyPeople() {
		fail("Not yet implemented");
	}

	@Test
	void testHelpNeedyPerson() {
		fail("Not yet implemented");
	}

	@Test
	void testLogOut() {
		fail("Not yet implemented");
	}

	@Test
	void testApproveDonationDistribution() {
		fail("Not yet implemented");
	}

	@Test
	void testCheckApprovedDistribution() {
		fail("Not yet implemented");
	}

	@Test
	void testCheckPendingRequests() {
		fail("Not yet implemented");
	}

}
