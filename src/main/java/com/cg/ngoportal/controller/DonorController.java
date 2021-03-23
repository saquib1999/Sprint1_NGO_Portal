package com.cg.ngoportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.ngoportal.model.Donation;
import com.cg.ngoportal.model.Donor;
import com.cg.ngoportal.service.DonorServiceImpl;

@RestController
public class DonorController {
	@Autowired
	DonorServiceImpl donorService;
	@PostMapping("/donor/register")
	public ResponseEntity<Donor> registerDonor(@RequestBody Donor donor) {		
		
		return new ResponseEntity<Donor>(donorService.registerDonor(donor), HttpStatus.CREATED);
	}
	
	@GetMapping("/donor/login")
	public ResponseEntity<Integer> loginDonor(@RequestBody Login login)  {	
		
		return new ResponseEntity<Integer>(donorService.login(login.getUsername(), login.getPassword()), HttpStatus.ACCEPTED);
	}
	@PostMapping("/donor/donate")
	public ResponseEntity<Donation> donateToNGO(@RequestBody Donation donation) {	
		
		return new ResponseEntity<Donation>(donorService.donateToNGO(donation), HttpStatus.ACCEPTED);
	}
	@GetMapping("donor/thankyou-mail")
	//@EventListener(ApplicationReadyEvent.class)
	public ResponseEntity<String> sendThankyouMailToDonator(@RequestBody Donor donor) {	
		
		return new ResponseEntity<String>(donorService.sendThankyouMailToDonator(donor), HttpStatus.OK);
	}
	@GetMapping("donor/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Login login) {	
		
		return new ResponseEntity<String>(donorService.forgotPassword(login.getUsername()), HttpStatus.OK);
	}
	@PostMapping("/donor/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPassword resetPassword) {	
		
		return new ResponseEntity<String>(donorService.resetPassword(resetPassword.getUsername(),resetPassword.getOldpassword(),resetPassword.getNewpassword()), HttpStatus.ACCEPTED);
	}
	@PostMapping("/donor/email-passwordToDonor")
	public ResponseEntity<String> emailPasswordToDonor(@RequestBody EmailToDonor email) {	
		
		return new ResponseEntity<String>(donorService.emailPasswordToDonor(email.getEmail()), HttpStatus.OK);
	}	
	@GetMapping("/donor/logout")
	public ResponseEntity<String> logoutNeedyPerson() {
		return new ResponseEntity<String>(donorService.logOut(),HttpStatus.OK);

	}
	
	
}
