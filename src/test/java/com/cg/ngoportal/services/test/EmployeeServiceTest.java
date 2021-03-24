package com.cg.ngoportal.services.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import com.cg.ngoportal.dao.DonationDistributionDao;
import com.cg.ngoportal.dao.EmployeeDao;
import com.cg.ngoportal.dao.NeedyPeopleDao;
import com.cg.ngoportal.dao.RequestDao;
import com.cg.ngoportal.dao.UserDao;
import com.cg.ngoportal.exception.DuplicateNeedyPersonException;
import com.cg.ngoportal.exception.InvalidNeedyPersonObjectException;
import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.exception.NoSuchNeedyPersonException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.Address;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.DonationDistributionStatus;
import com.cg.ngoportal.model.DonationItem;
import com.cg.ngoportal.model.DonationType;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.Request;
import com.cg.ngoportal.model.RequestStatus;
import com.cg.ngoportal.model.User;
import com.cg.ngoportal.model.UserType;
import com.cg.ngoportal.service.EmployeeServiceImpl;



@SpringBootTest
class EmployeeServiceTest {
	
	@InjectMocks
	EmployeeServiceImpl employeeService;
	
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
	
	List<NeedyPeople> needyPeopleList;

	List<NeedyPeople> needyPeopleListByName;
	NeedyPeople needyPerson1, needyPerson2, needyPerson3;
	
	List<Request> requestList;
	Request request1, request2;
	
	List<DonationDistribution> distributionsList;
	List<DonationDistribution> distributionsApprovedList;
	List<DonationDistribution> distributionsPendingList;
	List<DonationDistribution> distributedList;
	
	DonationDistribution distribution1, distribution2, distribution3;
	
	Employee emp1;
	
	
	

	
	@BeforeTestExecution
    public void init() {
        MockitoAnnotations.openMocks(this);
   
    }
	
	@BeforeEach
	void setUp() throws Exception {
		User userEmp = new User("saquibemp", "saquibpwd", UserType.EMPLOYEE);
		emp1 = new Employee("saquin", "saquib@gmail.com", "9999988888", null, userEmp);
		emp1.setActive(1);
		employeeRepo.save(emp1);
		
		needyPeopleList = new ArrayList<NeedyPeople>();
		
		SimpleDateFormat dateParse = new SimpleDateFormat ("dd-MM-yyyy");
		Date date;
		
		User user1 =new User("rajpal123", "rajpalpwd", UserType.NEEDYPERSON);
		User user2 =new User("amir123", "amirpwd", UserType.NEEDYPERSON);
		User user3 =new User("j123", "josephpwd", UserType.NEEDYPERSON);
		
		Address address1 = new Address("Pune", "Maharashtra", "411044", "Near Railway Staion");
		Address address2 = new Address("New Delhi", "Delhi", "100006", "Near Bus Staion");
		Address address3 = new Address("Mumbai", "Maharashtra", "440044", "Near Hanuman Sweets");
		
		needyPerson1 = new NeedyPeople("Rajpal Raut", "9999888811", 8000, user1, address1);
		needyPerson2 = new NeedyPeople("Amir Pathan", "8888999911", 8520, user2, address2);
		needyPerson3 = new NeedyPeople("Joseph Jose", "9898989811", 9000, user3, address3);
		
		needyPeopleList.add(needyPerson1);
		needyPeopleList.add(needyPerson2);
		needyPeopleListByName = new ArrayList<NeedyPeople>();
		needyPeopleListByName.add(needyPerson1);
		
		request1 = new Request(needyPerson1.getId(), DonationType.BOOKS, 2, "Books for Studies");
		request2 = new Request(needyPerson2.getId(), DonationType.CLOTHS, 2, "Winter");
		requestList = new ArrayList<Request>();
		requestList.add(request2);
		
		
		DonationItem donationItem1 = new DonationItem(DonationType.BOOKS, "Books for Studies"); 
		DonationItem donationItem2 = new DonationItem(DonationType.CLOTHS, "Winter"); 
		
		date = dateParse.parse("30-03-2021");
		distribution1 = new DonationDistribution(needyPerson1, donationItem1, emp1, 4, date, new Date(), DonationDistributionStatus.APPROVED);
		distributionsApprovedList = new ArrayList<DonationDistribution>();
		distributionsApprovedList.add(distribution1);
		
		distribution2 = new DonationDistribution(needyPerson2, donationItem2, emp1, 0, null, null, DonationDistributionStatus.PENDING);
		distributionsPendingList = new ArrayList<DonationDistribution>();
		distributionsPendingList.add(distribution2);
		
		distribution3 = new DonationDistribution(needyPerson1, donationItem1, emp1, 4, date, new Date(), DonationDistributionStatus.FUND_DISBURSED);
		distributedList = new ArrayList<DonationDistribution>();
		distributedList.add(distribution3);
		
	}
	
	

	
	@Test
	void testAddNeedyPerson() throws DuplicateNeedyPersonException, UserNotLoggedInException, InvalidNeedyPersonObjectException {
		when(needyPeopleRepo.save(needyPerson3)).thenReturn(needyPerson3);
		Assertions.assertEquals(needyPerson3, employeeService.addNeedyPerson(needyPerson3));
	}

	@Test
	void testRemoveNeedyPerson() throws UserNotLoggedInException, NoSuchNeedyPersonException {
		when(userRepo.findByUsername(needyPerson2.getUserLoginDetails().getUsername()))
		.thenReturn(Optional.of(needyPerson2.getUserLoginDetails()));
		when(needyPeopleRepo.findByUserLoginDetails(needyPerson2.getUserLoginDetails()))
		.thenReturn(Optional.of(needyPerson2));
		employeeService.removeNeedyPerson(needyPerson2);
		verify(needyPeopleRepo,times(1)).delete(needyPerson2);
	}

	@Test
	void testFindNeedyPeopleById() throws UserNotLoggedInException, NoSuchNeedyPersonException {
		when(needyPeopleRepo.findById(needyPerson1.getId())).thenReturn(Optional.of(needyPerson1));
		Assertions.assertEquals(needyPerson1, employeeService.findNeedyPeopleById(needyPerson1.getId()));
	}

	@Test
	void testFindNeedyPeopleByName() throws UserNotLoggedInException {
		when(needyPeopleRepo.findByNameContainingIgnoreCaseOrderByNameAsc("rajpal")).thenReturn(needyPeopleListByName);
		Assertions.assertEquals(1, employeeService.findNeedyPeopleByName("rajpal").size());
	}

	@Test
	void testFindAllNeedyPeople() throws UserNotLoggedInException {
		when(needyPeopleRepo.findAll()).thenReturn(needyPeopleList);
		Assertions.assertEquals(2, employeeService.findAllNeedyPeople().size());
		
		
	}

	@Test
	void testHelpNeedyPerson() throws UserNotLoggedInException {
		
		when(donationDistributionRepo.save(distribution1)).thenReturn(distribution1);
		when(requestRepo.findById(distribution1.getRequestId())).thenReturn(Optional.of(request1));
		Assertions.assertEquals(DonationDistributionStatus.FUND_DISBURSED, employeeService.helpNeedyPerson(distribution1).getStatus());
	}

	@Test
	void testLogOut() {
		Assertions.assertEquals(true, employeeService.logOut());

	}

	@Test
	void testApproveDonationDistribution() throws DataIntegrityViolationException, UserNotLoggedInException {
		when(needyPeopleRepo.findById(request2.getNeedyPersonId())).thenReturn(Optional.of(needyPerson2));
		when(employeeRepo.findById(-1)).thenReturn(Optional.of(emp1));

		when(donationDistributionRepo.save(distribution2)).thenReturn(distribution2);
		System.out.println(distribution2);
		System.out.println(distribution2);
		Assertions.assertEquals(distribution2, employeeService.approveDonationDistributionEmployeeLevel(request2, distribution2));
	}

	@Test
	void testCheckApprovedDistribution() throws UserNotLoggedInException {
		when(donationDistributionRepo.findByStatusAndDistributedBy(DonationDistributionStatus.APPROVED, emp1)).thenReturn(distributionsApprovedList);
		when(employeeRepo.findById(-1)).thenReturn(Optional.of(emp1));
		Assertions.assertEquals(1, employeeService.checkApprovedDistribution().size());
		
		
		
	}
	
	
	@Test
	void testCheckDistributedList() throws UserNotLoggedInException {
		when(donationDistributionRepo.findByStatusAndDistributedBy(DonationDistributionStatus.FUND_DISBURSED, emp1)).thenReturn(distributedList);
		when(employeeRepo.findById(-1)).thenReturn(Optional.of(emp1));
		Assertions.assertEquals(1, employeeService.checkDistributedList().size());
		
		
		
	}

	@Test
	void testCheckPendingRequests() throws UserNotLoggedInException {
		when(requestRepo.findByStatusContaining(RequestStatus.PENDING)).thenReturn(requestList);
		Assertions.assertEquals(1, employeeService.checkPendingRequests().size());
	}

}
	