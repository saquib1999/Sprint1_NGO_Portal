
package com.cg.ngoportal.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cg.ngoportal.dao.DonationDistributionDao;
import com.cg.ngoportal.dao.EmployeeDao;
import com.cg.ngoportal.dao.NeedyPeopleDao;
import com.cg.ngoportal.dao.RequestDao;
import com.cg.ngoportal.dao.UserDao;
import com.cg.ngoportal.exception.DuplicateEmployeeException;
import com.cg.ngoportal.exception.DuplicateNeedyPersonException;
import com.cg.ngoportal.exception.InvalidNeedyPersonObjectException;
import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.exception.NoSuchNeedyPersonException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.Address;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.DonationDistributionStatus;
import com.cg.ngoportal.model.DonationItem;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.Request;
import com.cg.ngoportal.model.RequestStatus;
import com.cg.ngoportal.model.User;
import com.cg.ngoportal.model.UserType;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private NeedyPeopleDao needyPeopleRepo ;
	@Autowired
	private EmployeeDao employeeRepo; 
	@Autowired
	private DonationDistributionDao donationDistributionRepo;
	
	@Autowired
	private UserDao userRepo;
	
	@Autowired
	private RequestDao requestRepo;
	
	private boolean loggedIn = true;
	private int employeeId = -1;
	@Override
	public boolean logIn(String username,String password) throws NoSuchEmployeeException {
		User user = userRepo.findByUsernameAndPassword(username, password).orElseThrow(()-> new NoSuchEmployeeException("Either Username or password Incorrect"));
		loggedIn = true;
//		User user2 = new User("saquib123", "saquib1234", UserType.EMPLOYEE);
//		Employee emp = new Employee("SaquibEmp", "empemail", "95999", null, user2);
//		employeeRepo.save(emp);
		employeeId = employeeRepo.findByUserLoginDetails(user).get().getId();
		

		return true;
	}

	@Override
	public NeedyPeople addNeedyPerson(NeedyPeople person) throws  DuplicateNeedyPersonException,UserNotLoggedInException, InvalidNeedyPersonObjectException {
		// TODO Auto-generated method stub
		if(loggedIn) {
			if( userRepo.findByUsername(person.getUserLoginDetails().getUsername()).isEmpty())
			{	
				if(person.getUserLoginDetails().getUserType()!= UserType.NEEDYPERSON)
					throw new InvalidNeedyPersonObjectException("Check the User Type");
				person.setName(person.getName().toUpperCase());
				return needyPeopleRepo.save(person);
			}
			else 
				throw new DuplicateNeedyPersonException("Needy person already exist");
		}
		else
			throw new UserNotLoggedInException("Employee Not Logged In");
			

	}

	@Override
	public NeedyPeople removeNeedyPerson(NeedyPeople person) throws UserNotLoggedInException, NoSuchNeedyPersonException {
		if (loggedIn) {
			User user = userRepo.findByUsername(person.getUserLoginDetails().getUsername())
					.orElseThrow(()->new NoSuchNeedyPersonException("Please Check the Needy Person Details"));
			NeedyPeople deleteNeedyPerson = needyPeopleRepo.findByUserLoginDetails(user)
					.orElseThrow(()->new NoSuchNeedyPersonException("Please Check the Needy Person Details"));
			needyPeopleRepo.delete(deleteNeedyPerson);
			return deleteNeedyPerson;
		}
		else
			throw new UserNotLoggedInException("Employee Not Logged In");
		
	}

	@Override
	public NeedyPeople findNeedyPeopleById(int id) throws UserNotLoggedInException, NoSuchNeedyPersonException {
		if (loggedIn)
			return needyPeopleRepo.findById(id).orElseThrow(()->new NoSuchNeedyPersonException("Please Check the Needy Person Details"));
		else
			throw new UserNotLoggedInException("Employee Not Logged In");
	}

	@Override
	public List<NeedyPeople> findNeedyPeopleByName(String name) throws UserNotLoggedInException {
		// TODO Auto-generated method stub
		if (loggedIn) {
			 return needyPeopleRepo.findByNameContainingIgnoreCaseOrderByNameAsc(name);
		}
		else
			throw new UserNotLoggedInException("Employee Not Logged In");
	}

	@Override
	public List<NeedyPeople> findAllNeedyPeople() throws UserNotLoggedInException {
		// TODO Auto-generated method stub
		
		if (loggedIn) {
			return (List<NeedyPeople>) needyPeopleRepo.findAll();
		}
		else
			throw new UserNotLoggedInException("Employee Not Logged In");
	}

	@Override
	public DonationDistribution helpNeedyPerson(DonationDistribution distribute) throws UserNotLoggedInException {
		if (loggedIn) {
			distribute.setStatus(DonationDistributionStatus.FUND_DISBURSED);
			
		return 	donationDistributionRepo.save(distribute);
		}
		else
			throw new UserNotLoggedInException("Employee Not Logged In");
	}

	@Override
	public boolean logOut() {
		// TODO Auto-generated method stub
		loggedIn = false;
		employeeId = -1;
		return true;
	}

	@Override
	public DonationDistribution approveDonationDistributionEmployeeLevel(Request request, DonationDistribution distribution) throws UserNotLoggedInException, DataIntegrityViolationException {
		// TODO Auto-generated method stub
		
		if (loggedIn) {
			NeedyPeople needyPerson = needyPeopleRepo.findById(request.getNeedyPersonId()).get();
			DonationItem donationItem = new DonationItem(request.getDonationType(), request.getReason());
			distribution.setDistributedBy(employeeRepo.findById(employeeId).orElseThrow(()->new UserNotLoggedInException("Employee Not Logged In")));
			distribution.setStatus(DonationDistributionStatus.PENDING);
			distribution.setPerson(needyPerson);
			distribution.getItem().setItem(request.getDonationType());
			distribution.getItem().setDescription(request.getReason());
			return donationDistributionRepo.save(distribution);
		}
		else
			throw new UserNotLoggedInException("Employee Not Logged In");
	}

	@Override
	public List<DonationDistribution> checkApprovedDistribution() throws UserNotLoggedInException {
		
		
		if (loggedIn) {
			return donationDistributionRepo.findByStatus(DonationDistributionStatus.APPROVED);
		}
		else
			throw new UserNotLoggedInException("Employee Not Logged In");
	}

	@Override
	public List<Request> checkPendingRequests() throws UserNotLoggedInException  {
		if (loggedIn) {
			return requestRepo.findByStatusContaining(RequestStatus.PENDING);		}
		else
			throw new UserNotLoggedInException("Employee Not Logged In");
	}
	
	

}
