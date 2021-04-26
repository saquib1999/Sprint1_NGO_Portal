
package com.cg.ngoportal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	private PasswordEncoder bcryptEncoder;

	
	@Autowired
	private UserDao userRepo;
	
	@Autowired
	private RequestDao requestRepo;
	
	@Override
	public String login(User user) throws NoSuchEmployeeException {

		Employee employee = employeeRepo.findByUserLoginDetails(user).get();
		
		if(employee.getActive() != 1)
			throw new NoSuchEmployeeException("Inactive Employee");
		return "Employee Logged In as "+ user.getUsername();
	}

	@Override
	public NeedyPeople addNeedyPerson(NeedyPeople person) throws  DuplicateNeedyPersonException,UserNotLoggedInException, InvalidNeedyPersonObjectException {
			if( userRepo.findByUsername(person.getUserLoginDetails().getUsername()).isEmpty())
			{	
				if(person.getUserLoginDetails().getUserType()!= UserType.NEEDYPERSON)
					throw new InvalidNeedyPersonObjectException("Check the User Type");
				person.setName(person.getName().toUpperCase());
				User user = person.getUserLoginDetails();
				user.setPassword(bcryptEncoder.encode(user.getPassword()));
				person.setUserLoginDetails(user);
				person.getUserLoginDetails().setUserType(UserType.NEEDYPERSON);

				return needyPeopleRepo.save(person);
			}
			else 
				throw new DuplicateNeedyPersonException("Needy person already exist");
		
	}

	@Override
	public NeedyPeople removeNeedyPerson(NeedyPeople person) throws UserNotLoggedInException, NoSuchNeedyPersonException {
			User user = userRepo.findByUsername(person.getUserLoginDetails().getUsername()).orElseThrow(()->new NoSuchNeedyPersonException("Please Check the Needy Person Details"));
			NeedyPeople deleteNeedyPerson = needyPeopleRepo.findByUserLoginDetails(user)
					.orElseThrow(()->new NoSuchNeedyPersonException("Please Check the Needy Person Details"));
			
			needyPeopleRepo.delete(deleteNeedyPerson);
			return deleteNeedyPerson;
		
	}

	@Override
	public NeedyPeople findNeedyPeopleById(int id) throws UserNotLoggedInException, NoSuchNeedyPersonException {
			return needyPeopleRepo.findById(id).orElseThrow(()->new NoSuchNeedyPersonException("Please Check the Needy Person Details"));
		
	}

	@Override
	public List<NeedyPeople> findNeedyPeopleByName(String name) throws UserNotLoggedInException {
			 return needyPeopleRepo.findByNameContainingIgnoreCaseOrderByNameAsc(name);
		
	}

	@Override
	public List<NeedyPeople> findAllNeedyPeople() throws UserNotLoggedInException {
		
			return (List<NeedyPeople>) needyPeopleRepo.findAll();
		}
		

	@Override
	public DonationDistribution helpNeedyPerson(DonationDistribution distribute) throws UserNotLoggedInException {
			
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

	@Override
	public boolean logOut() {
		
		return true;
	}

	
	@Override
	public DonationDistribution approveDonationDistributionEmployeeLevel(int employeeId, Request request, DonationDistribution distribution) throws UserNotLoggedInException, DataIntegrityViolationException {
					
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
		
	@Override
	public List<DonationDistribution> checkApprovedDistribution(int employeeId) throws UserNotLoggedInException {
		
					
			return donationDistributionRepo.findByStatusAndDistributedBy(DonationDistributionStatus.APPROVED, employeeRepo.findById(employeeId).get());
		
	}

	@Override
	public List<Request> checkPendingRequests() throws UserNotLoggedInException  {
			return requestRepo.findByStatusContaining(RequestStatus.PENDING);		
		
	}

	@Override
	public List<DonationDistribution> checkDistributedList(int employeeId) throws UserNotLoggedInException {
			
			return donationDistributionRepo.findByStatusAndDistributedBy(DonationDistributionStatus.FUND_DISBURSED, employeeRepo.findById(employeeId).get());
		
	}

	@Override
	public List<DonationDistribution> checkPendingList(int employeeId) throws UserNotLoggedInException {
		// TODO Auto-generated method stub
		return donationDistributionRepo.findByStatusAndDistributedBy(DonationDistributionStatus.PENDING, employeeRepo.findById(employeeId).get());
	}
	
	
	
	

}
