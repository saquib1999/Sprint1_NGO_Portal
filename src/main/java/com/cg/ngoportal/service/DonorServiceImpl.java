package com.cg.ngoportal.service;



import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.cg.ngoportal.dao.DonationBoxDao;
import com.cg.ngoportal.dao.DonationDao;
import com.cg.ngoportal.dao.DonorDao;
import com.cg.ngoportal.dao.UserDao;
import com.cg.ngoportal.exception.DuplicateDonorException;
import com.cg.ngoportal.exception.NoDonationException;
import com.cg.ngoportal.exception.NoSuchDonorException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.Donation;
import com.cg.ngoportal.model.DonationBox;
import com.cg.ngoportal.model.DonationItem;
import com.cg.ngoportal.model.DonationType;
import com.cg.ngoportal.model.Donor;
import com.cg.ngoportal.model.User;
import com.cg.ngoportal.model.UserType;

@Service
public class DonorServiceImpl implements DonorService{
	@Autowired
	DonorDao donorRepo;
	@Autowired
	DonationDao donationRepo;
	@Autowired
	UserDao userRepo;
	@Autowired
	JavaMailSender mailSender;
	@Autowired
	DonationBoxDao donationBoxRepo;
	@Autowired
	private PasswordEncoder bcryptEncoder;



	private boolean loggedIn = true;
//	private int donorId=1000;
	private String adminEmail = "aladdin.aladdin0708@gmail.com"; 


	@Override
	public Donor registerDonor(Donor donor) throws DuplicateDonorException {

		Optional<User> user = userRepo.findByUsername(donor.getUserLoginDetails().getUsername());
		if(user.isEmpty()) 
		{
			User user2 = donor.getUserLoginDetails();
			user2.setPassword(bcryptEncoder.encode(user2.getPassword()));
			donor.setUserLoginDetails(user2);
			donor.getUserLoginDetails().setUserType(UserType.DONOR);
		donorRepo.save(donor);}
		else 
			throw new DuplicateDonorException("Username is already taken.");
		return donor;
	}

	@Override
	public String login(User user) throws NoSuchDonorException {

		loggedIn=true;
//		this.donorId=donorRepo.findByUserLoginDetails(user).get().getId();
		return "Donor Logged In  as " + user.getUsername();
	}

	@Override
	public Donation donateToNGO(Donation donation,int donorId,SimpleMailMessage message) throws UserNotLoggedInException {
			donation.setDonorId(donorId);
			donation.setDateOfDonation(new Date());
			donation.setItem(new DonationItem(DonationType.MONEY,"Donate"));
			DonationBox donationBox=donationBoxRepo.findByNgoName(donation.getNgo()).orElseThrow();
			System.out.println(donationBox.getTotalCollection());
//			System.out.println(donation.getItem().getItem().getVal());
			donationBox.setTotalCollection(donationBox.getTotalCollection()+(donation.getAmount()));
			System.out.println(donationBox.getTotalCollection());
			donationBoxRepo.save(donationBox);
			Donor donor=donorRepo.findById(donorId).orElseThrow();
			message.setFrom(adminEmail);
			message.setTo(donor.getEmail());
			message.setText("Thank You for Donation");
			message.setSubject("Thank You Mail");
			mailSender.send(message);
			return donationRepo.save(donation);
		
	}

	@Override
	public String sendCertificateToDonor(int donorId) throws MessagingException, UserNotLoggedInException{

			if (donationRepo.findByDonorIdOrderByIdDesc(donorId).size() == 0)
				throw new NoDonationException("Please do the Donation First");
			Donor donor=donorRepo.findById(donorId).orElseThrow();
	
			MimeMessage mimeMessage = mailSender.createMimeMessage();	
			MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage, true);	
			mimeMessageHelper.setFrom(adminEmail);
			mimeMessageHelper.setTo(donor.getEmail());
			mimeMessageHelper.setText("Thank You for Donation");
			mimeMessageHelper.setSubject("Certificate  for Donation");	
			FileSystemResource fileSystem= new FileSystemResource(new File("C:\\Users\\Saquib\\Desktop\\DonationCertificate\\Certificate-of-Donation.jpg"));	
			mimeMessageHelper.addAttachment(fileSystem.getFilename(),fileSystem);
			mailSender.send(mimeMessage);
			return "Certificate sent successfully to your Email";

	}

	@Override
	public String forgotPassword(String username,SimpleMailMessage message) throws NoSuchDonorException {
		User user = userRepo.findByUsername(username).orElseThrow(()->new NoSuchDonorException("Donor details not found"));
		Donor donor=donorRepo.findByUserLoginDetails(user).orElseThrow(()->new NoSuchDonorException("Donor details not found"));
		message.setFrom(adminEmail);
		message.setTo(donor.getEmail());
		message.setText(donor.getUserLoginDetails().getPassword());
		message.setSubject("Forgot Password ");
		mailSender.send(message);
		return "Password sent on email";

	}

	@Override
	public String resetPassword(String username,String oldPassword,String newPassword) throws NoSuchDonorException {
		User user = userRepo.findByUsernameAndPassword(username, oldPassword).orElseThrow(()->new NoSuchDonorException("Donor details not found"));
		user.setPassword(newPassword);
		userRepo.save(user);
		return "Password reset successfully";	
	}



	@Override
	public String logOut() {

		loggedIn = false;
//		donorId = -1;
		return "Logged Out!";

	}

	@Override
	public Donation lastDonationReceipt(SimpleMailMessage message,int donorId) throws UserNotLoggedInException {
			List<Donation> donationList = donationRepo.findByDonorIdOrderByIdDesc(donorId);
			Donor donor=donorRepo.findById(donorId).get();
			if(donationList.size() > 0){
				message.setFrom(adminEmail);
				message.setTo(donor.getEmail());
				message.setText(donationList.get(0).toString());
				message.setSubject("Donation Receipt ");
				mailSender.send(message);	

			}
			else 
				new NoDonationException("No Donation Found");
			
			return donationList.get(0);
		

	}

	@Override
	public List<Donation> allDonationReceipt(SimpleMailMessage message,int donorId) throws UserNotLoggedInException {
			List<Donation> donationList = donationRepo.findByDonorIdOrderByIdDesc(donorId);
			Donor donor=donorRepo.findById(donorId).get();
			if(donationList.size() > 0){
				message.setFrom(adminEmail);
				message.setTo(donor.getEmail());

				StringBuffer stringMessage = new StringBuffer();
				donationList.stream().forEach((x)->stringMessage.append(x.toString() + "\n"));

				message.setText(stringMessage.toString());
				message.setSubject("Donation Receipt ");
				mailSender.send(message);	


			}
			else 
				new NoDonationException("No Donation Found");


			return donationList;
		

	}

	public Donor getDonorById(int donorId) throws NoSuchDonorException, UserNotLoggedInException {
		return donorRepo.findById(donorId)
				.orElseThrow(()-> new NoSuchDonorException("Details not found"));
	}



}