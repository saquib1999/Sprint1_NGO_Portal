package com.cg.ngoportal.service;



import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cg.ngoportal.dao.DonationDao;
import com.cg.ngoportal.dao.DonorDao;
import com.cg.ngoportal.dao.UserDao;
import com.cg.ngoportal.exception.DuplicateDonorException;
import com.cg.ngoportal.exception.NoSuchDonorException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.Donation;
import com.cg.ngoportal.model.Donor;
import com.cg.ngoportal.model.User;

@Service
public class DonorServiceImpl implements DonorService{
	@Autowired
	DonorDao donorRepo;
	@Autowired
	DonationDao donationRepo;
	@Autowired
	UserDao userRepo;
	//@Autowired
	//JavaMailSender mailSender;
	private boolean loggedIn = true;
	private int donorId=1000;
	
	@Override
	public Donor registerDonor(Donor donor) throws DuplicateDonorException {
		// TODO Auto-generated method stub
		Optional<User> user = userRepo.findByUsername(donor.getUserLoginDetails().getUsername());
		if(user.isEmpty()) {
			donorRepo.save(donor);
		}
		else {
			throw new DuplicateDonorException("Username is already taken.");
		}
		return donor;
	}

	@Override
	public int login(String username,String password) throws NoSuchDonorException {
		// TODO Auto-generated method stub
		User user = userRepo.findByUsernameAndPassword(username, password).orElseThrow(()->new NoSuchDonorException("Donor details not found"));
		loggedIn=true;
		this.donorId=donorRepo.findByUserLoginDetails(user).get().getId();
		return donorId;
	}

	@Override
	public Donation donateToNGO(Donation donation) {
		// TODO Auto-generated method stub	
		if(loggedIn==true) {
			donation.setDonorId(donorId);
			donation.setDateOfDonation(new Date());
			
			return donationRepo.save(donation);
		}
		else {
			throw new UserNotLoggedInException("Please Log In");
		}
	}

	@Override
	public String sendThankyouMailToDonator(Donor donor) {
		// TODO Auto-generated method stub
		/*SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("umang@gmail.com");
		message.setTo(donor.getEmail());
		message.setText("Thank You for Donation");
		message.setSubject("Thank You Mail");
		mailSender.send(message);*/
		return "Thank You "+ donor.getName();
	}

	@Override
	public String forgotPassword(String username) throws NoSuchDonorException {
		// TODO Auto-generated method stub
		User user = userRepo.findByUsername(username).orElseThrow(()->new NoSuchDonorException("Donor details not found"));
		return user.getPassword();
	}

	@Override
	public String resetPassword(String username,String oldPassword,String newPassword) throws NoSuchDonorException {
		// TODO Auto-generated method stub
		User user = userRepo.findByUsernameAndPassword(username, oldPassword).orElseThrow(()->new NoSuchDonorException("Donor details not found"));
		//String newpassword=user.getUsername();
		//newpassword+="1234";
		user.setPassword(newPassword);
		userRepo.save(user);
		return newPassword;	
	}

	@Override
	public String emailPasswordToDonor(String email) throws NoSuchDonorException {
		/*SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("umang@gmail.com");
		message.setTo(donor.getEmail());
		message.setText(donor.getUserLoginDetails().getPassword());
		message.setSubject("Forgot Password ");
		mailSender.send(message);*/
		Donor donor=donorRepo.findByEmail(email).orElseThrow(()->new NoSuchDonorException("Donor details not found"));
		return donor.getUserLoginDetails().getPassword();
	}

	@Override
	public String logOut() {
		// TODO Auto-generated method stub
		loggedIn = false;
		donorId = -1;
		return "Logged Out!";
		
	}
	

}
