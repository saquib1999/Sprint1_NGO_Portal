package com.cg.ngoportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.ngoportal.model.Donation;
import com.cg.ngoportal.model.Donor;
import com.cg.ngoportal.service.DonorServiceImpl;

@RestController
@RequestMapping("/donor")
public class DonorController {
	@Autowired
	DonorServiceImpl donorService;
	@PostMapping("/register")
	public ResponseEntity<Donor> registerDonor(@RequestBody Donor donor) {		
		
		return new ResponseEntity<Donor>(donorService.registerDonor(donor), HttpStatus.CREATED);
	}
	
	@GetMapping("/login")
	public ResponseEntity<Integer> loginDonor(@RequestBody Login login)  {	
		
		return new ResponseEntity<Integer>(donorService.login(login.getUsername(), login.getPassword()), HttpStatus.ACCEPTED);
	}
	@PostMapping("/donate")
	public ResponseEntity<Donation> donateToNGO(@RequestBody Donation donation) {	
		
		return new ResponseEntity<Donation>(donorService.donateToNGO(donation,new SimpleMailMessage()), HttpStatus.ACCEPTED);
	}
	@GetMapping("/thankyou-mail")
	public ResponseEntity<String> sendThankyouMailToDonator() {	
		
		return new ResponseEntity<String>(donorService.sendThankyouMailToDonator(new SimpleMailMessage()), HttpStatus.OK);
	}
	@GetMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Login login) {	
		
		return new ResponseEntity<String>(donorService.forgotPassword(login.getUsername(),new SimpleMailMessage()), HttpStatus.OK);
	}
	@PatchMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPassword resetPassword) {	
		
		return new ResponseEntity<String>(donorService.resetPassword(resetPassword.getUsername(),resetPassword.getOldpassword(),resetPassword.getNewpassword()), HttpStatus.ACCEPTED);
	}
	@GetMapping("/email-passwordToDonor")
	public ResponseEntity<String> emailPasswordToDonor() {	
		
		return new ResponseEntity<String>(donorService.emailPasswordToDonor(new SimpleMailMessage()), HttpStatus.OK);
	}	
	@GetMapping("/logout")
	public ResponseEntity<String> logoutNeedyPerson() {
		return new ResponseEntity<String>(donorService.logOut(),HttpStatus.OK);

	}
	
	
}
