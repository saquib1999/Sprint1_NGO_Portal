package com.cg.ngoportal.service.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.cg.ngoportal.dao.DonationDao;
import com.cg.ngoportal.dao.DonationItemDao;
import com.cg.ngoportal.dao.DonorDao;
import com.cg.ngoportal.dao.UserDao;
import com.cg.ngoportal.exception.DuplicateDonorException;
import com.cg.ngoportal.exception.NoSuchDonorException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.Address;
import com.cg.ngoportal.model.Donation;
import com.cg.ngoportal.model.DonationItem;
import com.cg.ngoportal.model.DonationType;
import com.cg.ngoportal.model.Donor;
import com.cg.ngoportal.model.User;
import com.cg.ngoportal.model.UserType;
import com.cg.ngoportal.service.DonorServiceImpl;

@SpringBootTest
class DonorServiceImplTest {
	@Mock
	DonorDao donorRepo;
	@Mock
	DonationDao donationRepo;
	@Mock
	UserDao userRepo;
	@Mock
	DonationItemDao donationItemRepo;
	@Mock
	JavaMailSender mailSender;
	@InjectMocks
	DonorServiceImpl donorService;
	
	@SuppressWarnings("deprecation")
	   @Before(value = "")
	   public void init() {
		   MockitoAnnotations.initMocks(this);
	   }
	Optional<User> user;
	Donor donor;
	List<Donor> donorList;
	Donor donor2;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		donorList = new ArrayList<>();
		donor =new Donor("shiv","shivsrivastava35@gmail.com","783911484",new User("shiv","shiv",UserType.DONOR),new Address("lakhimpur","UttarPradesh","262701","Dr.Dua Clinic"));
		donor2 =new Donor("umang","umangsrivastava35@gmail.com","783911483",new User("umang","divinity",UserType.DONOR),new Address("lakhimpur","UttarPradesh","262701","Dr.Dua Clinic"));
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	@Test
	void testRegisterDonor() throws DuplicateDonorException {
		when(donorRepo.save(donor)).thenReturn(donor);
		Assertions.assertEquals(donor, donorService.registerDonor(donor));
		
	}
	
	@Test
	void testLogin() throws NoSuchDonorException{
		when(userRepo.findByUsernameAndPassword("shiv","shiv")).thenReturn(Optional.of(donor.getUserLoginDetails()));
		when(donorRepo.findByUserLoginDetails(donor.getUserLoginDetails())).thenReturn(Optional.of(donor));
		Assertions.assertEquals(donor.getId(), donorService.login("shiv","shiv"));
	}
	@Test
	void testDonateToNGO()throws UserNotLoggedInException {
		DonationItem i=new DonationItem(DonationType.MONEY,"10000$");
		Donation don=new Donation(1000,i,10000,null);
		when(donorRepo.findById(1000)).thenReturn(Optional.of(donor));
		when(donationRepo.save(don)).thenReturn(don);
		SimpleMailMessage message=new SimpleMailMessage();
		donorService.donateToNGO(don,message);
		verify(mailSender,times(1)).send(message);
		Assertions.assertEquals(don, donorService.donateToNGO(don,message));
		
	}
	@Test
	void testForgotPassword()throws NoSuchDonorException {
		when(userRepo.findByUsername("shiv")).thenReturn(Optional.of(donor.getUserLoginDetails()));
		when(donorRepo.findByUserLoginDetails(donor.getUserLoginDetails())).thenReturn(Optional.of(donor));
		SimpleMailMessage message=new SimpleMailMessage();
		donorService.forgotPassword("shiv", message);
		verify(mailSender,times(1)).send(message);
	}
	@Test
	void testResetPassword()throws NoSuchDonorException {
		when(userRepo.findByUsernameAndPassword("shiv","shiv")).thenReturn(Optional.of(donor.getUserLoginDetails()));
		Assertions.assertEquals("Password reset successfully", donorService.resetPassword("shiv","shiv","SHIV"));
	}
	@Test
	void testLogOut() {
		Assertions.assertEquals("Logged Out!", donorService.logOut());
	}
	@Test
	void testSendThankyouMailToDonator()throws UserNotLoggedInException{
		when(donorRepo.findById(1000)).thenReturn(Optional.of(donor));
		SimpleMailMessage message=new SimpleMailMessage();
		donorService.sendThankyouMailToDonator(message);
		verify(mailSender,times(1)).send(message);
	}
	@Test
	void testEmailPasswordToDonor()throws UserNotLoggedInException{
		when(donorRepo.findById(1000)).thenReturn(Optional.of(donor));
		SimpleMailMessage message=new SimpleMailMessage();
		donorService.emailPasswordToDonor(message);
		verify(mailSender,times(1)).send(message);
	}

}
