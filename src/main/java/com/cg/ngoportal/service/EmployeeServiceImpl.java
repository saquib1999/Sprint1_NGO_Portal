
package com.cg.ngoportal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cg.ngoportal.dao.DonationBoxDao;
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
import com.cg.ngoportal.model.DonationBox;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.DonationDistributionStatus;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.Request;
import com.cg.ngoportal.model.RequestStatus;
import com.cg.ngoportal.model.User;
import com.cg.ngoportal.model.UserType;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private NeedyPeopleDao needyPeopleRepo ;
	@Autowired
	private EmployeeDao employeeRepo; 
	@Autowired
	private DonationDistributionDao donationDistributionRepo;
	
	@Autowired
	DonationBoxDao donationBoxRepo;
	
	@Autowired
	private UserDao userRepo;
	
	@Autowired
	private RequestDao requestRepo;
	
	private boolean loggedIn = true;
	private int employeeId = -1;
	@Override
	public String login(User user) throws NoSuchEmployeeException {
		
		Employee employee = employeeRepo.findByUserLoginDetails(user).get();
		
		if(employee.getActive() != 1)
			throw new NoSuchEmployeeException("Inactive Employee");
		loggedIn = true;
		employeeId = employee.getId();
		return "Employee Logged In as "+ user.getUsername();
	}

	@Override
	public NeedyPeople addNeedyPerson(NeedyPeople person) throws  DuplicateNeedyPersonException,UserNotLoggedInException, InvalidNeedyPersonObjectException {
		if(loggedIn) {
			if( userRepo.findByUsername(person.getUserLoginDetails().getUsername()).isEmpty())
			{	
				if(person.getUserLoginDetails().getUserType()!= UserType.NEEDYPERSON)
					throw new InvalidNeedyPersonObjectException("Check the User Type");
				person.setName(person.getName().toUpperCase());
				person.getUserLoginDetails().setUserType(UserType.NEEDYPERSON);

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
			User user = userRepo.findByUsername(person.getUserLoginDetails().getUsername()).orElseThrow(()->new NoSuchNeedyPersonException("Please Check the Needy Person Details"));
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
		if (loggedIn) {
			 return needyPeopleRepo.findByNameContainingIgnoreCaseOrderByNameAsc(name);
		}
		else
			throw new UserNotLoggedInException("Employee Not Logged In");
	}

	@Override
	public List<NeedyPeople> findAllNeedyPeople() throws UserNotLoggedInException {
		
		if (loggedIn) {
			return (List<NeedyPeople>) needyPeopleRepo.findAll();
		}
		else
			throw new UserNotLoggedInException("Employee Not Logged In");
	}

	@Override
	public DonationDistribution helpNeedyPerson(DonationDistribution distribute) throws UserNotLoggedInException {
		if (loggedIn) {
			
			DonationBox donationBox = donationBoxRepo.findByNgoName(distribute.getNgo()).get();
			donationBox.setTotalCollection(donationBox.getTotalCollection() -
					(distribute.getAmountDistributed() * distribute.getItem().getItem().getVal()));
			
			
			Request request= requestRepo.findById(distribute.getRequestId()).get();
			request.setStatus(RequestStatus.FUND_DISBURSED);
			
			requestRepo.save(request);
			
			distribute.setDateOfDistribution(new Date());
			distribute.setStatus(DonationDistributionStatus.FUND_DISBURSED);
			distribute.setAmountDistributed(request.getAmountOrQuantity());
			
		return 	donationDistributionRepo.save(distribute);
		}
		else
			throw new UserNotLoggedInException("Employee Not Logged In");
	}

	@Override
	public boolean logOut() {
		loggedIn = false;
		employeeId = -1;
		return true;
	}

	@Override
	public DonationDistribution approveDonationDistributionEmployeeLevel(Request request, DonationDistribution distribution) throws UserNotLoggedInException, DataIntegrityViolationException {
		
		if (loggedIn) {
			
			if (request.getStatus() == RequestStatus.REJECTED_BY_EMPLOYEE) {
				requestRepo.save(request);
				return null;
			}
			NeedyPeople needyPerson = needyPeopleRepo.findById(request.getNeedyPersonId()).get();
			distribution.setDistributedBy(employeeRepo.findById(employeeId).orElseThrow(()->new UserNotLoggedInException("Employee Not Logged In")));
			distribution.setStatus(DonationDistributionStatus.PENDING);
			distribution.setRequestId(request.getId());
			distribution.setPerson(needyPerson);
			distribution.getItem().setItem(request.getDonationType());
			distribution.getItem().setDescription(request.getReason());
			distribution.setAmountDistributed(request.getAmountOrQuantity());
			request.setStatus(RequestStatus.APPROVED_BY_EMPLOYEE);
			requestRepo.save(request);
			return donationDistributionRepo.save(distribution);
		}
		else
			throw new UserNotLoggedInException("Employee Not Logged In");
	}
		
	@Override
	public List<DonationDistribution> checkApprovedDistribution() throws UserNotLoggedInException {
		
		
		if (loggedIn) {
			
			return donationDistributionRepo.findByStatusAndDistributedBy(DonationDistributionStatus.APPROVED, employeeRepo.findById(employeeId).get());
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

	@Override
	public List<DonationDistribution> checkDistributedList() throws UserNotLoggedInException {
			
		if (loggedIn) {

			return donationDistributionRepo.findByStatusAndDistributedBy(DonationDistributionStatus.FUND_DISBURSED, employeeRepo.findById(employeeId).get());
		}
		else
			throw new UserNotLoggedInException("Employee Not Logged In");
	}
	
	
	
	

}
