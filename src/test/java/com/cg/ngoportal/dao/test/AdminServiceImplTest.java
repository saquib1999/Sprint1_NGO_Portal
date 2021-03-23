package com.cg.ngoportal.dao.test;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.cg.ngoportal.dao.AdminDao;
import com.cg.ngoportal.dao.DonationDistributionDao;
import com.cg.ngoportal.dao.EmployeeDao;
import com.cg.ngoportal.exception.DuplicateEmployeeException;
import com.cg.ngoportal.exception.IncorrectLoginDetailsException;
import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.Address;
import com.cg.ngoportal.model.Admin;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.DonationDistributionStatus;
import com.cg.ngoportal.model.DonationItem;
import com.cg.ngoportal.model.DonationType;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.User;
import com.cg.ngoportal.model.UserType;
import com.cg.ngoportal.service.AdminServiceImpl;

@SpringBootTest
public class AdminServiceImplTest {
	
	@Mock
	AdminDao adminDaoRepo;
	
	@Mock
	EmployeeDao employeeDaoRepo;
	
	@Mock
	DonationDistributionDao donationdistributionDaoRepo;
	
	@InjectMocks
	AdminServiceImpl adminService;
	
	Admin ad, ad1;
	
	List<Employee> elist, elistname;
	Employee emp1, emp2, emp3, emp4, emp5;
	
	List<DonationDistribution> dlist;
	DonationDistribution dd1, dd2, dd3, dd4;
	
	
	@BeforeTestClass
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@BeforeEach
	void setup() {
		elist = new ArrayList<>();
		elistname = new ArrayList<>();
		dlist = new ArrayList<>();
		ad = new Admin("Parth123", "parth123");
		ad1 = new Admin("Parth123", "parth");
		emp1 = new Employee("Parth", "parth@gmail.com", "9876543210", new Address("Pune", "Maharshtra", "411046", "Baner"), new User("parth123","parth", null), 1);
		emp2 = new Employee("Sagar", "sagar@gmail.com", "8765432109", new Address("Mumbai", "Maharshtra", "400020", "Bandra"), new User("sagar123","sagar", null), 1);
		emp3 = new Employee("Yash", "yash@gmail.com", "7654321098", new Address("Bangalore", "Karnataka", "456412", "SBI"), new User("yash123","yash", null), 1);
		emp4 = new Employee("Sagar", "sagar@gmail.com", "7654321098", new Address("Bangalore", "Karnataka", "456412", "SBI"), new User("sagar123","sagar", null), 1);
		emp5 = new Employee("Parth", "parth@gmail.com", "9876543210", new Address("Pune", "Maharshtra", "411046", "Baner"), new User("parth123","parth", null), 0);
		
		elist.add(emp1);
		elist.add(emp2);
		elist.add(emp3);
		
		elistname.add(emp1);
		
		dd1 = new DonationDistribution(new NeedyPeople("ABC", "12345", 20000, new User("ABC", "ABC123", UserType.NEEDYPERSON), new Address("Pune", "Maharshtra", "411046", "Baner")),new DonationItem(DonationType.MONEY, "Cheque"), new Employee("Yash", "yash@gmail.com", "7654321098", new Address("Bangalore", "Karnataka", "456412", "SBI"), new User("yash123","yash", UserType.EMPLOYEE), 1), 2000.00, null, null, DonationDistributionStatus.PENDING);
		dd3 = new DonationDistribution(new NeedyPeople("BCD", "123456", 200000, new User("BCD", "BCD123", UserType.NEEDYPERSON), new Address("Mumbai", "Maharshtra", "411002", "Bandra")),new DonationItem(DonationType.MONEY, "Cheque"), new Employee("Yash", "yash@gmail.com", "7654321098", new Address("Bangalore", "Karnataka", "456412", "SBI"), new User("yash123","yash", UserType.EMPLOYEE), 1), 24000.00, null, null, DonationDistributionStatus.PENDING);
		dd3 = new DonationDistribution(new NeedyPeople("DEF", "1234567", 30000, new User("DEF", "DEF123", UserType.NEEDYPERSON), new Address("Thane", "Maharshtra", "411001", "Thane")),new DonationItem(DonationType.MONEY, "Cheque"), new Employee("Yash", "yash@gmail.com", "7654321098", new Address("Bangalore", "Karnataka", "456412", "SBI"), new User("yash123","yash", UserType.EMPLOYEE), 1), 1000.00, null, null, DonationDistributionStatus.PENDING);
		dd4 = new DonationDistribution(new NeedyPeople("ABC", "12345", 20000, new User("ABC", "ABC123", UserType.NEEDYPERSON), new Address("Pune", "Maharshtra", "411046", "Baner")),new DonationItem(DonationType.MONEY, "Cheque"), new Employee("Yash", "yash@gmail.com", "7654321098", new Address("Bangalore", "Karnataka", "456412", "SBI"), new User("yash123","yash", UserType.EMPLOYEE), 1), 2000.00, null, LocalDate.now(), DonationDistributionStatus.APPROVED);
		
		dlist.add(dd1);
		dlist.add(dd2);
		dlist.add(dd3);
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}
	@Test
	public void Testlogin() throws IncorrectLoginDetailsException {
		when(adminDaoRepo.findByUsernameAndPassword(ad.getUsername(), ad.getPassword())).thenReturn(Optional.of(ad));
		Assertions.assertEquals("Logged in Successfully as " + ad.getUsername(), adminService.login(ad));
	}
	//working
	@Test
	public void TestaddEmployee() throws DuplicateEmployeeException, UserNotLoggedInException{
		when(employeeDaoRepo.save(emp1)).thenReturn(emp1);
		Assertions.assertEquals(emp1, adminService.addEmployee(emp1));
	}
	//working
	@Test
	public void TestmodifyEmployee() throws NoSuchEmployeeException, UserNotLoggedInException{
		when(employeeDaoRepo.findById(emp2.getId())).thenReturn(Optional.of(emp2));
		when(employeeDaoRepo.save(emp2)).thenReturn(emp4);
		Assertions.assertEquals(emp4, adminService.modifyEmployee(emp2.getId(), emp4));
	}
	//working
	@Test
	public void TestremoveEmployee() throws NoSuchEmployeeException, UserNotLoggedInException {
		when(employeeDaoRepo.findByUsername(emp1.getUserLoginDetails().getUsername())).thenReturn(Optional.of(emp1));
		Assertions.assertEquals(true, adminService.removeEmployee(emp1.getUserLoginDetails().getUsername()));
		
	}
	//working
	@Test
	public void TestfindEmployeeById() throws NoSuchEmployeeException, UserNotLoggedInException{
		when(employeeDaoRepo.findByEmployeeId(emp1.getId())).thenReturn(Optional.of(emp1));
		Assertions.assertEquals(emp1, adminService.findEmployeeById(emp1.getId()));
	}
	
	//working
	@Test
	public void TestfindEmployeeByName() throws NoSuchEmployeeException, UserNotLoggedInException{
		when(employeeDaoRepo.findByName(emp1.getName())).thenReturn(elistname);
		Assertions.assertEquals(elistname, adminService.findEmployeeByName(emp1.getName()));
	}
	//working
	@Test
	public void TestfindAllEmployees() throws NoSuchEmployeeException, UserNotLoggedInException{
		when(employeeDaoRepo.findAllActiveEmployee()).thenReturn(elist);
		Assertions.assertEquals(3,adminService.findAllEmployee().size());
	}
	
	@Test
	public void TestfindAllPendingDonations() throws UserNotLoggedInException {
		when(donationdistributionDaoRepo.findByStatus(DonationDistributionStatus.PENDING)).thenReturn(dlist);
		Assertions.assertEquals(3, adminService.findAllPendingDonations().size());
	}
	
	@Test
	public void TestapproveDonation() throws UserNotLoggedInException {
		when(donationdistributionDaoRepo.findById(dd1.getId())).thenReturn(Optional.of(dd1));
		when(donationdistributionDaoRepo.save(dd1)).thenReturn(dd4);
		Assertions.assertEquals(dd4, adminService.approveDonation(dd1));
	}
	
	@Test
	public void Testlogout() {
		Assertions.assertEquals("Logged Out Successfully", adminService.logout());
	}
	
	
}
